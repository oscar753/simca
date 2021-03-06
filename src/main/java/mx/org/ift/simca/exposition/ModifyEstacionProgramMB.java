/**
 * 
 */
package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.exposition.dto.CoberturaRadioDTO;
import mx.org.ift.simca.exposition.dto.EstacionDTO;
import mx.org.ift.simca.model.Estacion;
import mx.org.ift.simca.model.EstacionFormulario;
import mx.org.ift.simca.model.TipoPregunta;
import mx.org.ift.simca.service.CatalogoService;
import mx.org.ift.simca.service.CoberturaRadioService;
import mx.org.ift.simca.service.EstacionFormularioService;
import mx.org.ift.simca.service.EstacionService;
import mx.org.ift.simca.utils.GeneraRadioXML;

/**
 * @author KODE-LAP0077
 *
 */
@Controller
@Scope("session")
public class ModifyEstacionProgramMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3661174108639906905L;

	private static final Logger LOG = LoggerFactory.getLogger(AddEstacionProgramMB.class);

	private Integer idEstadoCober;
	private Integer idMunicipioCober;
	
	private boolean disable = false;

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
	
	private List<EstacionFormulario> respuestasFormulario = new ArrayList<EstacionFormulario>();
	
	@Autowired
	private EstacionProgramacionMB estacionProgramacionMB;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private CoberturaRadioService coberturaRadioService;

	@Autowired
	private EstacionFormularioService estacionFormularioService;

	@Autowired
	private EstacionService estacionService;
	
	@PostConstruct
	public void init() {
		LOG.info("/**** Se inicializa MB para modificar ****/");
		estadosCoberDTO = estadosDTO = catalogoService.consultaEstado();
		clasesDTO = catalogoService.consultaClase();
		tiposUsoEstacionDTO = catalogoService.consultaTipoUsoEstacion();
		concesionariosDTO = catalogoService.consultaConcesionario();
		bandasDTO = catalogoService.consultaBanda();
		tiposFrecuenciaDTO = catalogoService.consultaTipoFrecuencia();
	}
	
	public String poblarDatosRegistro(Estacion estacion) {
		try {
			estacionDTO.setNumero(estacion.getIdSenial());
			estacionDTO.setFolioRPCUMCA(estacion.getGrupoRadio().getFolioElectronico());
			estacionDTO.setIdEstado(estacion.getEstado().getIdEstado());
			if (estacionDTO.getIdEstado() > 0) {
				poblacionesDTO = catalogoService.consultaPoblacionEstado(estacionDTO.getIdEstado());
				estacionDTO.setIdPoblacion(estacion.getPoblacion().getIdPoblacion());
			}
			estacionDTO.setIdClase(estacion.getClase() != null ? estacion.getClase().getIdClase() : null);
			estacionDTO.setIdTipoUsoEstacion(estacion.getTipoUsoEstacion() != null ? estacion.getTipoUsoEstacion().getIdTipoUsoEstacion() : null);
			estacionDTO.setIdConcesionario(estacion.getGrupoRadio().getConcesionario().getIdConcesionario());
			estacionDTO.setDistintivo(estacion.getDistintivo());
			estacionDTO.setIdBanda(estacion.getBanda().getIdBanda());
			if(estacionDTO.getIdBanda() == 1) {
				estacionDTO.setFrecuenciaAM(estacion.getFrecuencia());
				estacionDTO.setFrecuenciaFM("");
			}
			else {
				estacionDTO.setFrecuenciaAM("");
				estacionDTO.setFrecuenciaFM(estacion.getFrecuencia());
			}
			estacionDTO.setVigenciaIni(estacion.getVigenciaIni());
			estacionDTO.setVigenciaFin(estacion.getVigenciaFin());
			estacionDTO.setIdTipoFrecuencia(estacion.getTipoFrecuencia().getIdTipoFrecuencia());
	
			generarOpcionesFormulario();
			setearRespuestasFormulario();
			obtenerCoberturas(estacionDTO.getFolioRPCUMCA(), estacion.getIdSenial());
			
			return "modificaEstacionProgramacion.xhtml";
		}
		catch (Exception e) {
			System.out.println("Error en m�todo poblarDatosRegistro" + e.getMessage());
			return null;
		}
	}
	
	public String eliminarDatosRegistro(Estacion estacion) {
		return null;
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
			if (idEstadoCober != null && idEstadoCober != 0 && idMunicipioCober != null && idMunicipioCober != 0) {
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
		return -1;
	}
	
	public void updateFrecComponents() {
		RequestContext.getCurrentInstance().update("formModEstacionProg");
	}
	
	public String cancelar() {
		return "estacionProgramacion.xhtml";
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

	public void setearRespuestasFormulario() {
		System.out.println("Seteando respuestas del formulario");
		try {
			respuestasFormulario = estacionFormularioService.buscarRespuestasFormulario(estacionDTO.getNumero(), estacionDTO.getFolioRPCUMCA(), 3);
			
			estacionDTO.setIdTipoEstacion(Integer.parseInt(respuestasFormulario.get(0).getValor() != null ? respuestasFormulario.get(0).getValor() : "0"));
			estacionDTO.setNombreProgramacion(respuestasFormulario.get(1).getValor());
			estacionDTO.setIdMultiprograma(Integer.parseInt(respuestasFormulario.get(2).getValor() != null ? respuestasFormulario.get(2).getValor() : "0"));
			estacionDTO.setIdBDINE(Integer.parseInt(respuestasFormulario.get(3).getValor() != null ? respuestasFormulario.get(3).getValor() : "0"));
			estacionDTO.setCentroVMINE(respuestasFormulario.get(4).getValor() != null ? new SimpleDateFormat("dd/MM/yyyy").parse(respuestasFormulario.get(4).getValor()) : null);
			estacionDTO.setNomCanalINE(respuestasFormulario.get(5).getValor());
			estacionDTO.setNomComINE(respuestasFormulario.get(6).getValor());
			estacionDTO.setTipoMonitoreoINE(respuestasFormulario.get(7).getValor());
			estacionDTO.setIdBdAuditsa(Integer.parseInt(respuestasFormulario.get(8).getValor() != null ? respuestasFormulario.get(8).getValor() : "0"));
			estacionDTO.setNombreCanalAuditsa(respuestasFormulario.get(9).getValor());
			estacionDTO.setProgramAuditsa(respuestasFormulario.get(10).getValor());
			estacionDTO.setLocalidadAuditsa(respuestasFormulario.get(11).getValor());
			estacionDTO.setIdMonitoreoServExt(Integer.parseInt(respuestasFormulario.get(12).getValor() != null ? respuestasFormulario.get(12).getValor() : "0"));
			estacionDTO.setLocServExt(respuestasFormulario.get(13).getValor());
			estacionDTO.setNomComServExt(respuestasFormulario.get(14).getValor());
			estacionDTO.setIdProgramInfantil(Integer.parseInt(respuestasFormulario.get(15).getValor() != null ? respuestasFormulario.get(15).getValor() : "0"));
			estacionDTO.setIdObligAcces(Integer.parseInt(respuestasFormulario.get(16).getValor() != null ? respuestasFormulario.get(16).getValor() : "0"));
			estacionDTO.setIdMecanAcces(Integer.parseInt(respuestasFormulario.get(17).getValor() != null ? respuestasFormulario.get(17).getValor() : "0"));
			estacionDTO.setIdMedioPublico(Integer.parseInt(respuestasFormulario.get(18).getValor() != null ? respuestasFormulario.get(18).getValor() : "0"));
			estacionDTO.setInstPubFed(respuestasFormulario.get(19).getValor());
		} catch(ParseException e) {
			System.out.println("Error en parseo en m�todo setearRespuestas: " + e.getMessage());
		} catch(Exception e) {
			System.out.println("Error en m�todo setearRespuestas: " + e.getMessage());
		}
    }

	public void deleteAction(CoberturaRadioDTO coberturaRadioDTO) {
		System.out.println("Eliminando fila:" + coberturaRadioDTO.getEstado().getDescripcion() + " "
				+ coberturaRadioDTO.getMunicipio().getDescripcion());
		estacionDTO.getCoberturasRadioDTO().remove(coberturaRadioDTO);
		this.updateFrecComponents();
	}
	
	public void obtenerCoberturas(String folioElectronico, Integer idSenial) {
		estacionDTO.setCoberturasRadioDTO(coberturaRadioService.buscarCoberturas(folioElectronico, idSenial));
	}
	
	public void modificarEstacion() {
		String camposFaltantes="";
		String respuesta = "";
		if(estacionDTO.getFolioRPCUMCA().equals("")) camposFaltantes+="Folio RPC/UMCA, \n";
		if(estacionDTO.getIdEstado() == null) camposFaltantes+="Estado, \n";
		if(estacionDTO.getIdPoblacion() == null) camposFaltantes+="Poblaci�n, \n";
		if(estacionDTO.getIdTipoUsoEstacion() == null || estacionDTO.getIdTipoUsoEstacion() == 0) camposFaltantes+="Tipo de uso, \n";
		if(estacionDTO.getIdConcesionario() == null) camposFaltantes+="Concesionario, \n";
		if(estacionDTO.getDistintivo().equals("")) camposFaltantes+="Distintivo, \n";
		if(estacionDTO.getIdBanda() == null || estacionDTO.getIdBanda() == 0) camposFaltantes+="Banda, \n";
		if(((estacionDTO.getIdBanda() != null ? estacionDTO.getIdBanda() : 0) == 1 && estacionDTO.getFrecuenciaAM().equals("")) || 
				((estacionDTO.getIdBanda() != null ? estacionDTO.getIdBanda() : 0) == 2 && estacionDTO.getFrecuenciaFM().equals(""))) camposFaltantes+="Frecuencia, \n";
		if(estacionDTO.getVigenciaIni() == null) camposFaltantes+="Vigencia Inicio\n";
		if(estacionDTO.getIdTipoFrecuencia() == null) camposFaltantes+="Tipo frecuencia\n";
		if(estacionDTO.getIdTipoEstacion() == null) camposFaltantes+="Tipo de estaci�n\n";
		
		if(!camposFaltantes.equals(""))
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensaje", "Ingrese los campos obligatorios: " + camposFaltantes));
		else {
			respuesta = GeneraRadioXML.generaEstacionXML(estacionService, estacionDTO, tipoPreguntas);			
			if(respuesta == "success") {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensaje", "Registro modificado"));				
			}
			else
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Mensaje", respuesta));
		}

	}

	public EstacionProgramacionMB getEstacionProgramacionMB() {
		return estacionProgramacionMB;
	}

	public void setEstacionProgramacionMB(EstacionProgramacionMB estacionProgramacionMB) {
		this.estacionProgramacionMB = estacionProgramacionMB;
	}

	public EstacionDTO getEstacionDTO() {
		return estacionDTO;
	}

	public void setEstacionDTO(EstacionDTO estacionDTO) {
		this.estacionDTO = estacionDTO;
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

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public List<CatalogoDTO> getPoblacionesDTO() {
		return poblacionesDTO;
	}

	public void setPoblacionesDTO(List<CatalogoDTO> poblacionesDTO) {
		this.poblacionesDTO = poblacionesDTO;
	}

	public List<CatalogoDTO> getEstadosDTO() {
		return estadosDTO;
	}

	public void setEstadosDTO(List<CatalogoDTO> estadosDTO) {
		this.estadosDTO = estadosDTO;
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

	public List<CatalogoDTO> getTiposUsoEstacionDTO() {
		return tiposUsoEstacionDTO;
	}

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

	public CoberturaRadioDTO getCoberturaRadioDTO() {
		return coberturaRadioDTO;
	}

	public void setCoberturaRadioDTO(CoberturaRadioDTO coberturaRadioDTO) {
		this.coberturaRadioDTO = coberturaRadioDTO;
	}
}
