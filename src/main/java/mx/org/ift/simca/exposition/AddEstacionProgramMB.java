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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.exposition.dto.CoberturaRadioDTO;
import mx.org.ift.simca.exposition.dto.EstacionDTO;
import mx.org.ift.simca.service.CatalogoService;

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

	@PostConstruct
	public void init() {
		LOG.info("/**** Se inicializa MB para agregar ****/");
		estadosCoberDTO = estadosDTO = catalogoService.consultaEstado();
		clasesDTO = catalogoService.consultaClase();
		tiposUsoEstacionDTO = catalogoService.consultaTipoUsoEstacion();
		concesionariosDTO = catalogoService.consultaConcesionario();
		bandasDTO = catalogoService.consultaBanda();
		tiposFrecuenciaDTO = catalogoService.consultaTipoFrecuencia();
		coberturasRadioDTO= null;
		distintivo = "";
		estacionDTO.setFrecuenciaFM("");
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
		CatalogoDTO catalogoDTO = new CatalogoDTO();
		coberturaRadioDTO = new CoberturaRadioDTO();
		
		catalogoDTO.setIdentificador(idEstadoCober.toString());
		catalogoDTO.setDescripcion(StringUtils.isNotBlank(estadosCoberDTO.get(idEstadoCober-1).getDescripcion())?estadosCoberDTO.get(idEstadoCober-1).getDescripcion():"");
		coberturaRadioDTO.setEstado(catalogoDTO);
		catalogoDTO = new CatalogoDTO();
		catalogoDTO.setIdentificador(idMunicipioCober.toString());
		catalogoDTO.setDescripcion(StringUtils.isNotBlank(municipiosCoberDTO.get(idMunicipioCober-1).getDescripcion())?municipiosCoberDTO.get(idMunicipioCober-1).getDescripcion():"");
		coberturaRadioDTO.setMunicipio(catalogoDTO);
		
		if(!itemRepetido(coberturasRadioDTO, idEstadoCober, idMunicipioCober)) {
			System.out.println("agregando cober");
			coberturasRadioDTO.add(coberturaRadioDTO);
		}
	}
	
	public boolean itemRepetido(List<CoberturaRadioDTO> coberturasRadioDTO, Integer idEstadoCober, Integer idMunicipioCober) {
		System.out.println("Tama�o de coberturasRadioDTO:" + coberturasRadioDTO.size());
		
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
	
	public void agregarEstacion() {
		System.out.println("Se agregar� estaci�n con los siguientes datos:\nN�mero: " + estacionDTO.getNumero());
		System.out.println("VigenciaIni: " + estacionDTO.getVigenciaIni());
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
}
