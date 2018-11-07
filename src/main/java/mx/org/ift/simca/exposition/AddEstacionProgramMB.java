/**
 * 
 */
package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import mx.org.ift.simca.model.TipoPregunta;
import mx.org.ift.simca.service.CatalogoService;
import mx.org.ift.simca.service.EstacionFormularioService;
import mx.org.ift.simca.utils.GeneraRadioXML;

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

	private Integer idEstadoCober;
	private Integer idMunicipioCober;

	private List<CatalogoDTO> poblacionesDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> estadosDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> municipiosCoberDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> estadosCoberDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> tiposUsoEstacionDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> clasesDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> concesionariosDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> bandasDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> tiposFrecuenciaDTO = new ArrayList<CatalogoDTO>();
	private List<TipoPregunta> tipoPreguntas = new ArrayList<TipoPregunta>();

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
		estacionDTO.getCoberturasRadioDTO().clear();
		generarOpcionesFormulario();
	}

	public void onEstadoChange() {
		if (estacionDTO.getIdEstado() > 0) {
			poblacionesDTO = catalogoService.consultaPoblacionEstado(estacionDTO.getIdEstado());
		}
	}

	public void onEstadoCoberChange() {
		try {
			if (idEstadoCober > 0) {
				idMunicipioCober = 0;
				municipiosCoberDTO = catalogoService.consultaPoblacionEstado(idEstadoCober);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void agregarCobertura() {
		try {
			System.out.println("entrando a agregarCober. idEstadoCober=" + idEstadoCober + ". idMunicipioCober:"
					+ idMunicipioCober);
			if (idEstadoCober != null && idMunicipioCober != null) {
				CatalogoDTO catalogoDTO = new CatalogoDTO();
				coberturaRadioDTO = new CoberturaRadioDTO();

				catalogoDTO.setIdentificador(idEstadoCober.toString());
				catalogoDTO.setDescripcion(
						estadosCoberDTO.get(obtenIdLista(estadosCoberDTO, idEstadoCober)).getDescripcion());
				coberturaRadioDTO.setEstado(catalogoDTO);
				catalogoDTO = new CatalogoDTO();
				catalogoDTO.setIdentificador(idMunicipioCober.toString());
				catalogoDTO.setDescripcion(
						municipiosCoberDTO.get(obtenIdLista(municipiosCoberDTO, idMunicipioCober)).getDescripcion());
				coberturaRadioDTO.setMunicipio(catalogoDTO);

				if (!itemRepetido(estacionDTO.getCoberturasRadioDTO(), idEstadoCober, idMunicipioCober)) {
					System.out.println("Agregando cobertura");
					estacionDTO.getCoberturasRadioDTO().add(coberturaRadioDTO);
				}
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Mensaje", "Favor de seleccionar Estado y Municipio"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean itemRepetido(List<CoberturaRadioDTO> coberturasRadioDTO, Integer idEstadoCober,
			Integer idMunicipioCober) {
		for (int i = 0; i < coberturasRadioDTO.size(); i++) {
			if (coberturasRadioDTO.get(i).getEstado().getIdentificador().equals(idEstadoCober.toString())
					&& coberturasRadioDTO.get(i).getMunicipio().getIdentificador()
							.equals(idMunicipioCober.toString())) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Mensaje", "Cobertura repetida"));
				return true;
			}
		}
		return false;
	}

	public int obtenIdLista(List<CatalogoDTO> list, Integer id) {
		Integer i = 0;
		for (CatalogoDTO catalogoDTO : list) {
			if (catalogoDTO.getIdentificador().equals(id.toString()))
				return i;
			else
				i++;
		}
		return 0;
	}

	public void generarOpcionesFormulario() {
		int idTipoFormulario = 3;
		tipoPreguntas = estacionFormularioService.buscarTipoPreguntasPorFormulario(idTipoFormulario);
		
		estacionDTO.setOpTipoEstacion(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(0).getPregunta(), idTipoFormulario));
		estacionDTO.setOpMultiprograma(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(2).getPregunta(), idTipoFormulario));
		estacionDTO.setOpBDINE(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(3).getPregunta(), idTipoFormulario));
		estacionDTO.setOpBdAuditsa(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(8).getPregunta(), idTipoFormulario));
		estacionDTO.setOpMonitoreoServExt(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(12).getPregunta(), idTipoFormulario));
		estacionDTO.setOpProgramInfantil(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(15).getPregunta(), idTipoFormulario));
		estacionDTO.setOpObligAcces(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(16).getPregunta(), idTipoFormulario));
		estacionDTO.setOpMecanAcces(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(17).getPregunta(), idTipoFormulario));
		estacionDTO.setOpMedioPublico(
				estacionFormularioService.buscarOpciones(tipoPreguntas.get(18).getPregunta(), idTipoFormulario));
	}

	public void agregarEstacion() {
//		System.out.println("Se agregará estación con los siguientes datos:\nNúmero: " + estacionDTO.getNumero());
//		System.out.println("banda: " + estacionDTO.getIdBanda());
//		System.out.println("VigenciaIni: " + (estacionDTO.getVigenciaIni() != null
//				? new SimpleDateFormat("dd/MM/yyyy").format(estacionDTO.getVigenciaIni())
//				: null));
//		System.out.println("VigenciaFin: " + (estacionDTO.getVigenciaFin() != null
//				? new SimpleDateFormat("dd/MM/yyyy").format(estacionDTO.getVigenciaFin())
//				: null));
//		System.out.println("Multiprograma: " + estacionDTO.getIdMultiprograma());
//		System.out.println("idBDINE: " + estacionDTO.getIdBDINE());
//		System.out.println("idBdAuditsa: " + estacionDTO.getIdBdAuditsa());
//		System.out.println("idMonitoreoServExt: " + estacionDTO.getIdMonitoreoServExt());
//		System.out.println("idProgramInfantil: " + estacionDTO.getIdProgramInfantil());
//		System.out.println("idObligAcces: " + estacionDTO.getIdObligAcces());
//		System.out.println("idMecanAcces: " + estacionDTO.getIdMecanAcces());
//		System.out.println("idMedioPublico: " + estacionDTO.getIdMedioPublico());

		GeneraRadioXML.generaEstacionXML(estacionDTO, tipoPreguntas);
	}

	public String deleteAction(CoberturaRadioDTO coberturaRadioDTO) {
		System.out.println("Eliminando fila:" + coberturaRadioDTO.getEstado().getDescripcion() + " "
				+ coberturaRadioDTO.getMunicipio().getDescripcion());
		estacionDTO.getCoberturasRadioDTO().remove(coberturaRadioDTO);
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

	public List<CatalogoDTO> getClasesDTO() {
		return clasesDTO;
	}

	public void setClasesDTO(List<CatalogoDTO> clasesDTO) {
		this.clasesDTO = clasesDTO;
	}

	public List<CatalogoDTO> getConcesionariosDTO() {
		return concesionariosDTO;
	}

	public void setConcesionariosDTO(List<CatalogoDTO> concesionariosDTO) {
		this.concesionariosDTO = concesionariosDTO;
	}

	public List<CatalogoDTO> getBandasDTO() {
		return bandasDTO;
	}

	public void setBandasDTO(List<CatalogoDTO> bandasDTO) {
		this.bandasDTO = bandasDTO;
	}

	public List<CatalogoDTO> getTiposFrecuenciaDTO() {
		return tiposFrecuenciaDTO;
	}

	public void setTiposFrecuenciaDTO(List<CatalogoDTO> tiposFrecuenciaDTO) {
		this.tiposFrecuenciaDTO = tiposFrecuenciaDTO;
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
