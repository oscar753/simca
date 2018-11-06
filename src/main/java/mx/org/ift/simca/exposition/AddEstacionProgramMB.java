/**
 * 
 */
package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.exposition.dto.CoberturaRadioDTO;
import mx.org.ift.simca.exposition.dto.EstacionDTO;
import mx.org.ift.simca.model.Opcion;
import mx.org.ift.simca.model.TipoPregunta;
import mx.org.ift.simca.service.CatalogoService;
import mx.org.ift.simca.service.EstacionFormularioService;

/**
 * @author KODE-LAP0077
 *
 */
@Controller
@Scope("session")
public class AddEstacionProgramMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1423852143850426510L;

	private static final Logger LOG = LoggerFactory.getLogger(AddEstacionProgramMB.class);

	private int idEstado;
	private int idPoblacion;
	private int idClase;
	private int idTipoUsoEstacion;
	private int idConcesionario;
	private int idBanda;
	private int idTipoFrecuencia;
	private Integer idEstadoCober;
	private Integer idMunicipioCober;
	private String distintivo;
	private int idMultiprograma;

	private List<CatalogoDTO> poblacionesDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> estadosDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> municipiosCoberDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> estadosCoberDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> tiposUsoEstacionDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> clasesDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> concesionariosDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> bandasDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> tiposFrecuenciaDTO = new ArrayList<CatalogoDTO>();
	
	private List<CoberturaRadioDTO> coberturasRadioDTO = new ArrayList<CoberturaRadioDTO>();
	
	private EstacionDTO estacionDTO = new EstacionDTO();
	private CoberturaRadioDTO coberturaRadioDTO = new CoberturaRadioDTO();

	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private EstacionFormularioService estacionFormularioService;

	@PostConstruct
	public void init() {
		LOG.info("/**** Se inicializa MB para agregar ****/");
		estadosCoberDTO = estadosDTO = catalogoService.consultaEstado();
		clasesDTO = catalogoService.consultaClase();
		tiposUsoEstacionDTO = catalogoService.consultaTipoUsoEstacion();
		concesionariosDTO = catalogoService.consultaConcesionario();
		bandasDTO = catalogoService.consultaBanda();
		tiposFrecuenciaDTO = catalogoService.consultaTipoFrecuencia();
		coberturasRadioDTO.clear();
		distintivo = "";
		estacionDTO.setFrecuenciaFM("");
		generarOpcionesFormulario();
	}

	public void onEstadoChange() {
		if (idEstado > 0) {
			poblacionesDTO = catalogoService.consultaPoblacionEstado(idEstado);
		}
	}
	
	public void onEstadoCoberChange() {
		try {
			if (idEstadoCober > 0) {
				idMunicipioCober = 0;
				municipiosCoberDTO = catalogoService.consultaPoblacionEstado(idEstadoCober);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void agregarCobertura() {
		try {
			System.out.println("entrando a agregarCober. idEstadoCober=" + idEstadoCober + "\nidMunicipioCober:" + idMunicipioCober);
			CatalogoDTO catalogoDTO = new CatalogoDTO();
			coberturaRadioDTO = new CoberturaRadioDTO();
			
			catalogoDTO.setIdentificador(idEstadoCober.toString());
			catalogoDTO.setDescripcion(estadosCoberDTO.get(obtenIdLista(estadosCoberDTO, idEstadoCober)).getDescripcion());
			coberturaRadioDTO.setEstado(catalogoDTO);
			catalogoDTO = new CatalogoDTO();
			catalogoDTO.setIdentificador(idMunicipioCober.toString());
			catalogoDTO.setDescripcion(municipiosCoberDTO.get(obtenIdLista(municipiosCoberDTO, idMunicipioCober)).getDescripcion());
			coberturaRadioDTO.setMunicipio(catalogoDTO);
			
			if(!itemRepetido(coberturasRadioDTO, idEstadoCober, idMunicipioCober)) {
				System.out.println("Agregando cobertura");
				coberturasRadioDTO.add(coberturaRadioDTO);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean itemRepetido(List<CoberturaRadioDTO> coberturasRadioDTO, Integer idEstadoCober, Integer idMunicipioCober) {
		for (int i = 0; i < coberturasRadioDTO.size(); i++) {
			if(coberturasRadioDTO.get(i).getEstado().getIdentificador().equals(idEstadoCober.toString()) &&
					coberturasRadioDTO.get(i).getMunicipio().getIdentificador().equals(idMunicipioCober.toString())) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Mensaje", "Cobertura repetida"));
				return true;
			}
		}
		return false;
	}
	
	public int obtenIdLista(List<CatalogoDTO> list, Integer id) {
		Integer i = 0;
		for(CatalogoDTO catalogoDTO: list) { 
		   if(catalogoDTO.getIdentificador().equals(id.toString())) return i;
		   else i++;
		}
		return 0;
	}
	
	public void generarOpcionesFormulario() {
		List<TipoPregunta> tipoPreguntas = new ArrayList<TipoPregunta>();
		tipoPreguntas = estacionFormularioService.buscarTipoPreguntas();
		System.out.println("pregunta 2: " + tipoPreguntas.get(2).getPregunta());

		//for (int i = 0; i < tipoPreguntas.size(); i++) {
			estacionDTO.setOpMultiprograma(estacionFormularioService.buscarOpciones(tipoPreguntas.get(2).getPregunta()));
			
		//}
	}
	
	public void agregarEstacion() {
		System.out.println("Se agregará estación con los siguientes datos:\nNúmero: " + estacionDTO.getNumero());
		System.out.println("VigenciaIni: " + estacionDTO.getVigenciaIni());
		System.out.println("Multiprograma: "+ idMultiprograma);
	}
	
	public String deleteAction(CoberturaRadioDTO coberturaRadioDTO) {
	    System.out.println("Eliminando fila:" + coberturaRadioDTO.getEstado().getDescripcion() + " " + coberturaRadioDTO.getMunicipio().getDescripcion());
	    coberturasRadioDTO.remove(coberturaRadioDTO);
		return null;
	}

	/**
	 * @return the estacionDTO
	 */
	public EstacionDTO getEstacionDTO() {
		return estacionDTO;
	}

	/**
	 * @param estacionDTO
	 *            the estacionDTO to set
	 */
	public void setEstacionDTO(EstacionDTO estacionDTO) {
		this.estacionDTO = estacionDTO;
	}

	/**
	 * @return the poblacionesDTO
	 */
	public List<CatalogoDTO> getPoblacionesDTO() {
		return poblacionesDTO;
	}

	/**
	 * @param poblacionesDTO
	 *            the poblacionesDTO to set
	 */
	public void setPoblacionesDTO(List<CatalogoDTO> poblacionesDTO) {
		this.poblacionesDTO = poblacionesDTO;
	}

	/**
	 * @return the estadosDTO
	 */
	public List<CatalogoDTO> getEstadosDTO() {
		return estadosDTO;
	}

	/**
	 * @param estadosDTO
	 *            the estadosDTO to set
	 */
	public void setEstadosDTO(List<CatalogoDTO> estadosDTO) {
		this.estadosDTO = estadosDTO;
	}

	/**
	 * @return the tiposUsoEstacionDTO
	 */
	public List<CatalogoDTO> getTiposUsoEstacionDTO() {
		return tiposUsoEstacionDTO;
	}

	/**
	 * @param tiposUsoEstacionDTO
	 *            the tiposUsoEstacionDTO to set
	 */
	public void setTiposUsoEstacionDTO(List<CatalogoDTO> tiposUsoEstacionDTO) {
		this.tiposUsoEstacionDTO = tiposUsoEstacionDTO;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdPoblacion() {
		return idPoblacion;
	}

	public void setIdPoblacion(int idPoblacion) {
		this.idPoblacion = idPoblacion;
	}

	public List<CatalogoDTO> getClasesDTO() {
		return clasesDTO;
	}

	public void setClasesDTO(List<CatalogoDTO> clasesDTO) {
		this.clasesDTO = clasesDTO;
	}

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public int getIdTipoUsoEstacion() {
		return idTipoUsoEstacion;
	}

	public void setIdTipoUsoEstacion(int idTipoUsoEstacion) {
		this.idTipoUsoEstacion = idTipoUsoEstacion;
	}

	public int getIdConcesionario() {
		return idConcesionario;
	}

	public void setIdConcesionario(int idConcesionario) {
		this.idConcesionario = idConcesionario;
	}

	public String getDistintivo() {
		return distintivo;
	}

	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}

	public List<CatalogoDTO> getConcesionariosDTO() {
		return concesionariosDTO;
	}

	public void setConcesionariosDTO(List<CatalogoDTO> concesionariosDTO) {
		this.concesionariosDTO = concesionariosDTO;
	}

	public int getIdBanda() {
		return idBanda;
	}

	public void setIdBanda(int idBanda) {
		this.idBanda = idBanda;
	}

	public List<CatalogoDTO> getBandasDTO() {
		return bandasDTO;
	}

	public void setBandasDTO(List<CatalogoDTO> bandasDTO) {
		this.bandasDTO = bandasDTO;
	}

	public int getIdTipoFrecuencia() {
		return idTipoFrecuencia;
	}

	public void setIdTipoFrecuencia(int idTipoFrecuencia) {
		this.idTipoFrecuencia = idTipoFrecuencia;
	}

	public List<CatalogoDTO> getTiposFrecuenciaDTO() {
		return tiposFrecuenciaDTO;
	}

	public void setTiposFrecuenciaDTO(List<CatalogoDTO> tiposFrecuenciaDTO) {
		this.tiposFrecuenciaDTO = tiposFrecuenciaDTO;
	}

	public List<CoberturaRadioDTO> getCoberturasRadioDTO() {
		return coberturasRadioDTO;
	}

	public void setCoberturasRadioDTO(List<CoberturaRadioDTO> coberturasRadioDTO) {
		this.coberturasRadioDTO = coberturasRadioDTO;
	}

	public List<CatalogoDTO> getMunicipiosCoberDTO() {
		return municipiosCoberDTO;
	}

	public void setMunicipiosCoberDTO(List<CatalogoDTO> municipiosCoberDTO) {
		this.municipiosCoberDTO = municipiosCoberDTO;
	}

	public List<CatalogoDTO> getEstadosCoberDTO() {
		return estadosCoberDTO;
	}

	public void setEstadosCoberDTO(List<CatalogoDTO> estadosCoberDTO) {
		this.estadosCoberDTO = estadosCoberDTO;
	}

	public Integer getIdEstadoCober() {
		return idEstadoCober;
	}

	public void setIdEstadoCober(Integer idEstadoCober) {
		this.idEstadoCober = idEstadoCober;
	}

	public Integer getIdMunicipioCober() {
		return idMunicipioCober;
	}

	public void setIdMunicipioCober(Integer idMunicipioCober) {
		this.idMunicipioCober = idMunicipioCober;
	}

	public CoberturaRadioDTO getCoberturaRadioDTO() {
		return coberturaRadioDTO;
	}

	public void setCoberturaRadioDTO(CoberturaRadioDTO coberturaRadioDTO) {
		this.coberturaRadioDTO = coberturaRadioDTO;
	}

	public int getIdMultiprograma() {
		return idMultiprograma;
	}

	public void setIdMultiprograma(int idMultiprograma) {
		this.idMultiprograma = idMultiprograma;
	}
	
}
