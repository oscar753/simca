/**
 * 
 */
package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.arq.core.exposition.CommonDataMB;
import mx.org.ift.simca.enums.PreguntaCanalVirtual;
import mx.org.ift.simca.exposition.dto.CanalDTO;
import mx.org.ift.simca.exposition.dto.CanalFormularioDTO;
import mx.org.ift.simca.exposition.dto.CanalVirtualDTO;
import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.exposition.dto.CoberturaDTO;
import mx.org.ift.simca.exposition.dto.CoberturasXMLDTO;
import mx.org.ift.simca.exposition.dto.FormularioXMLDTO;
import mx.org.ift.simca.exposition.dto.MultiprogramacionDTO;
import mx.org.ift.simca.exposition.dto.MultiprogramacionXML;
import mx.org.ift.simca.exposition.dto.PoblacionDTO;
import mx.org.ift.simca.exposition.dto.PoblacionXMLDTO;
import mx.org.ift.simca.exposition.dto.PreguntaDTO;
import mx.org.ift.simca.exposition.dto.PreguntaXMLDTO;
import mx.org.ift.simca.exposition.dto.PreguntasXMLDTO;
import mx.org.ift.simca.model.Canal;
import mx.org.ift.simca.model.CanalVirtual;
import mx.org.ift.simca.model.CanalVirtualFormulario;
import mx.org.ift.simca.model.Cobertura;
import mx.org.ift.simca.service.CanalService;
import mx.org.ift.simca.service.CatalogoService;

/**
 * @author KODE-LAP0077
 *
 */
@Controller
@Scope("session")
public class AddCanalProgramMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1423852143850426510L;
	
	private static final Logger LOG = LoggerFactory.getLogger(AddCanalProgramMB.class);
		
	private List<CatalogoDTO> poblacionesDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> poblacionesCoberDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> estadosDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> tiposUsoDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> tiposContenidoDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> concesionariosDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> gruposDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> tercerosDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> estatusDTO = new ArrayList<CatalogoDTO>();
		
	private List<CoberturaDTO> coberturasDTO = new ArrayList<CoberturaDTO>();
	
	private MultiprogramacionXML multiprog;
	
	private String estadoCobertura;
	private String municipioCobertura;
	private String logoB64;
	
	private Date fechaOficioNotAut;
	private Date fechaOficioNot;
	
	private CanalFormularioDTO formularioDTO;
	
	private UploadedFile fileLogo;
	private byte[] fileContenido;

	private Boolean esEditar;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private CanalService canalService;
	
	@Autowired
	private CommonDataMB commonDataMB;
	
	@PostConstruct
	public void init() {
		LOG.info("/**** Se inicializa MB para agregar ****/");		
		
		multiprog = new MultiprogramacionXML();
		formularioDTO = new CanalFormularioDTO();
		esEditar = Boolean.FALSE;
		
		coberturasDTO = new ArrayList<CoberturaDTO>();
		estadosDTO = catalogoService.consultaEstado();
		tiposContenidoDTO = catalogoService.consultaTipoContenido();
		concesionariosDTO = catalogoService.consultaConcesionario();
		tiposUsoDTO = catalogoService.consultaTipoUso();
		gruposDTO = catalogoService.consultaGrupo();
		estatusDTO = catalogoService.consultaEstatus();
		tercerosDTO = catalogoService.consultaTercero();
	}
	
	public void poblacionEst() {
		poblacionesDTO = consultaPoblacion(multiprog.getCanal().getEstado());		
	}
	
	public void poblacionEstCober() {
		poblacionesCoberDTO = consultaPoblacion(estadoCobertura);		
	}
	
	private List<CatalogoDTO> consultaPoblacion(String idEstado) {
		return catalogoService.consultaPoblacionEstado(new Integer(idEstado));
	}
	
	public void eliminarCobertura(CoberturaDTO varCoberturaDTO) {
		LOG.info("/**** Cobertura :: "+varCoberturaDTO.getPoblacion().getEstado());
		coberturasDTO.remove(varCoberturaDTO);
		
		for (PoblacionXMLDTO itemCoberXML: multiprog.getCoberturas().getPoblaciones()) {
			if (itemCoberXML.getEstado().equals(varCoberturaDTO.getPoblacion().getEstado()) &&
					itemCoberXML.getMunicipio().equals(varCoberturaDTO.getPoblacion().getMunicipio())) {
				multiprog.getCoberturas().getPoblaciones().remove(itemCoberXML);
				break;
			}
		}
	}
	
	public void agregarCobertura() {
		LOG.info("/**** Se agrega cobertura");		
		
		if (addCoberturaCanal()) {
			PoblacionDTO poblacionDTO = new PoblacionDTO();
			CoberturaDTO coberturaDTO = new CoberturaDTO();
			
			poblacionDTO.setEstado(obtenerDescrCatalogo(estadosDTO, estadoCobertura));
			poblacionDTO.setMunicipio(obtenerDescrCatalogo(poblacionesCoberDTO, municipioCobertura));
			
			coberturaDTO.setPoblacion(poblacionDTO);
			
			coberturasDTO.add(coberturaDTO);
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Mensaje", "Cobertura repetida"));
		}
	}
	
	private Boolean addCoberturaCanal() {
	
		List<PoblacionXMLDTO> coberturasCanal = multiprog.getCoberturas().getPoblaciones();
		Boolean noExisteCober = Boolean.TRUE;
		
		for (PoblacionXMLDTO coberturaDTO : coberturasCanal) {
			if (coberturaDTO.getIdEstado().equals(estadoCobertura) && 
					coberturaDTO.getIdPoblacion().equals(municipioCobertura)) {
				noExisteCober = Boolean.FALSE;
				LOG.info("/**** La cobertura ya existe ****/");
				break;
			}
		}
		
		if (noExisteCober) {
			PoblacionXMLDTO poblacionDTO = new PoblacionXMLDTO();
			
			poblacionDTO.setIdEstado(estadoCobertura);
			poblacionDTO.setEstado(obtenerDescrCatalogo(estadosDTO, estadoCobertura));
			poblacionDTO.setIdPoblacion(municipioCobertura);
			poblacionDTO.setMunicipio(obtenerDescrCatalogo(poblacionesDTO, municipioCobertura));
			
			multiprog.getCoberturas().getPoblaciones().add(poblacionDTO); 
		}
		return noExisteCober;
	}
	
	private String obtenerDescrCatalogo(List<CatalogoDTO> listaCat, String idItem) {
		String descripcionCat = "";
		
		for (CatalogoDTO catalogoDTO : listaCat) {
			if (catalogoDTO.getIdentificador().equals(idItem)) {
				return catalogoDTO.getDescripcion();
			}
		}
		
		return descripcionCat;
	}
	
	public void guardarMultiprog() {		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");			
		if (fechaOficioNotAut!=null) {
			formularioDTO.setFechaOficioNotAut(dateFormat.format(fechaOficioNotAut));
		}
		if (fechaOficioNot!=null) {
			formularioDTO.setFechaOficioNot(dateFormat.format(fechaOficioNot));
		}
		
		cargarFormulario();
		if (StringUtils.isNotBlank(logoB64)) {
			multiprog.getCanal_virtual().setLogo_b64(logoB64);
		}
		
		if (esEditar) {
			canalService.actualizarCanalVirtual(generarXmlStr(), commonDataMB.getUsuarioNombre());
		} else {
			canalService.agregarCanalVirtual(generarXmlStr(), commonDataMB.getUsuarioNombre());
		}
		
	}

	private String generarXmlStr() {
		String xmlString = null;
		try {		
			JAXBContext jaxbContext = JAXBContext.newInstance(MultiprogramacionXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(multiprog, sw);
			xmlString = sw.toString();
			LOG.info("/**** XML :: "+xmlString);
		} catch (JAXBException e) {
			LOG.error("No se logro generar el XML :: ");
			e.printStackTrace();
		}
		return xmlString;
	}

	private void cargarFormulario() {		
		
		PreguntasXMLDTO preguntasForm = new PreguntasXMLDTO();
		
		if (StringUtils.isNotBlank(formularioDTO.getLineaFrontera())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA1.getIdentificador(), PreguntaCanalVirtual.PREGUNTA1.getPregunta(), formularioDTO.getLineaFrontera()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getOpinionFcc())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA2.getIdentificador(), PreguntaCanalVirtual.PREGUNTA2.getPregunta(), formularioDTO.getOpinionFcc()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getNotificaCv())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA3.getIdentificador(), PreguntaCanalVirtual.PREGUNTA3.getPregunta(), formularioDTO.getNotificaCv()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getFechaOficioNot())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA4.getIdentificador(), PreguntaCanalVirtual.PREGUNTA4.getPregunta(), formularioDTO.getFechaOficioNot()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getNivelTransmicion())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA5.getIdentificador(), PreguntaCanalVirtual.PREGUNTA5.getPregunta(), formularioDTO.getNivelTransmicion()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getPublicaListado())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA6.getIdentificador(), PreguntaCanalVirtual.PREGUNTA6.getPregunta(), formularioDTO.getPublicaListado()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getPublicacion())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA7.getIdentificador(), PreguntaCanalVirtual.PREGUNTA7.getPregunta(), formularioDTO.getPublicacion()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getObservacionCV())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA8.getIdentificador(), PreguntaCanalVirtual.PREGUNTA8.getPregunta(), formularioDTO.getObservacionCV()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getPrimerNota())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA9.getIdentificador(), PreguntaCanalVirtual.PREGUNTA9.getPregunta(), formularioDTO.getPrimerNota()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getTipoResolMulti())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA10.getIdentificador(), PreguntaCanalVirtual.PREGUNTA10.getPregunta(), formularioDTO.getTipoResolMulti()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getResolMulti())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA11.getIdentificador(), PreguntaCanalVirtual.PREGUNTA11.getPregunta(), formularioDTO.getResolMulti()));
		}
		//Falta una pregunta que si esta en BD
		if (StringUtils.isNotBlank(formularioDTO.getFechaOficioNotAut())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA13.getIdentificador(), PreguntaCanalVirtual.PREGUNTA13.getPregunta(), formularioDTO.getFechaOficioNotAut()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getModificacion())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA14.getIdentificador(), PreguntaCanalVirtual.PREGUNTA14.getPregunta(), formularioDTO.getModificacion()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getResConcesionario())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA15.getIdentificador(), PreguntaCanalVirtual.PREGUNTA15.getPregunta(), formularioDTO.getResConcesionario()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getEstatusModificacion())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA16.getIdentificador(), PreguntaCanalVirtual.PREGUNTA16.getPregunta(), formularioDTO.getEstatusModificacion()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getPrimeraPublicacion())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA17.getIdentificador(), PreguntaCanalVirtual.PREGUNTA17.getPregunta(), formularioDTO.getPrimeraPublicacion()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getPublicaListadoMulti())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA18.getIdentificador(), PreguntaCanalVirtual.PREGUNTA18.getPregunta(), formularioDTO.getPublicaListadoMulti()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getObservacionesMulti())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA19.getIdentificador(), PreguntaCanalVirtual.PREGUNTA19.getPregunta(), formularioDTO.getObservacionesMulti()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getBdIne())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA20.getIdentificador(), PreguntaCanalVirtual.PREGUNTA20.getPregunta(), formularioDTO.getBdIne()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getCentroVm())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA21.getIdentificador(), PreguntaCanalVirtual.PREGUNTA21.getPregunta(), formularioDTO.getCentroVm()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getNombreCanalIne())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA22.getIdentificador(), PreguntaCanalVirtual.PREGUNTA22.getPregunta(), formularioDTO.getNombreCanalIne()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getNombreComercialIne())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA23.getIdentificador(), PreguntaCanalVirtual.PREGUNTA23.getPregunta(), formularioDTO.getNombreComercialIne()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getTipoMonitoreoIne())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA24.getIdentificador(), PreguntaCanalVirtual.PREGUNTA24.getPregunta(), formularioDTO.getTipoMonitoreoIne()));			
		}
		if (StringUtils.isNotBlank(formularioDTO.getBdAudista())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA25.getIdentificador(), PreguntaCanalVirtual.PREGUNTA25.getPregunta(), formularioDTO.getBdAudista()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getNomCanalAudista())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA26.getIdentificador(), PreguntaCanalVirtual.PREGUNTA26.getPregunta(), formularioDTO.getNomCanalAudista()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getPrograAudista())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA27.getIdentificador(), PreguntaCanalVirtual.PREGUNTA27.getPregunta(), formularioDTO.getPrograAudista()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getLocalidadAudista())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA28.getIdentificador(), PreguntaCanalVirtual.PREGUNTA28.getPregunta(), formularioDTO.getLocalidadAudista()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getMonitorServExt())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA29.getIdentificador(), PreguntaCanalVirtual.PREGUNTA29.getPregunta(), formularioDTO.getMonitorServExt()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getLocalidadServExt())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA30.getIdentificador(), PreguntaCanalVirtual.PREGUNTA30.getPregunta(), formularioDTO.getLocalidadServExt()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getNomComServExt())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA31.getIdentificador(), PreguntaCanalVirtual.PREGUNTA31.getPregunta(), formularioDTO.getNomComServExt()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getTipoPrograInfant())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA32.getIdentificador(), PreguntaCanalVirtual.PREGUNTA32.getPregunta(), formularioDTO.getTipoPrograInfant()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getObligaAccesibilidad())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA33.getIdentificador(), PreguntaCanalVirtual.PREGUNTA33.getPregunta(), formularioDTO.getObligaAccesibilidad()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getMecaAccesibilidad())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA34.getIdentificador(), PreguntaCanalVirtual.PREGUNTA34.getPregunta(), formularioDTO.getMecaAccesibilidad()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getMedioPublico())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA35.getIdentificador(), PreguntaCanalVirtual.PREGUNTA35.getPregunta(), formularioDTO.getMedioPublico()));
		}
		if (StringUtils.isNotBlank(formularioDTO.getInstPublicaFed())) {
			preguntasForm.getPreguntaXMLDTO().add(obtenerPregunta(PreguntaCanalVirtual.PREGUNTA36.getIdentificador(), PreguntaCanalVirtual.PREGUNTA36.getPregunta(), formularioDTO.getInstPublicaFed()));
		}
		
		FormularioXMLDTO formularioXML = new FormularioXMLDTO();
		formularioXML.setPreguntasXMLDTO(preguntasForm);
		
		multiprog.setFormulario(formularioXML);
	}

	private PreguntaXMLDTO obtenerPregunta(int identificador, String pregunta, String respuesta) {
		PreguntaXMLDTO preguntaItem = new PreguntaXMLDTO();
		
		preguntaItem.setDescPregunta(pregunta);
		preguntaItem.setIdPregunta(new Integer(identificador).toString());
		preguntaItem.setRespuesta(respuesta);
		
		return preguntaItem;
	}

	public void visualizarLogo() {
		LOG.info("/**** Imagen a B64 ****/");
		fileContenido = fileLogo.getContents();
		logoB64 = Base64.getEncoder().encodeToString(fileContenido);
	}
	
	public String editarCanal(CanalVirtual canalVirtualEdit) {
		LOG.info("ID canal virtual a editar :: "+canalVirtualEdit.getNumCanalVirtual());
		
		CanalVirtual canalVirtualBD = canalService.buscarCanalVirtualPorId(new Integer(canalVirtualEdit.getNumCanalVirtual()));
		
		LOG.info(" Regreso del servicio :: "+canalVirtualBD.getCanal().getDistintivo());
				
		esEditar = Boolean.TRUE;
		poblarMultiprogXML(canalVirtualBD);
		
		return "agregaCanalProgramacion.jsf";
	}
	
	private void poblarMultiprogXML(CanalVirtual canalVirtualBD) {
		CanalVirtualDTO canVirDTOEdit = new CanalVirtualDTO();
		
		canVirDTOEdit.setNo_canal_virtual(canalVirtualBD.getNumCanalVirtual().toString());
		canVirDTOEdit.setId_senial(canalVirtualBD.getIdCanalVirtual().toString());
		canVirDTOEdit.setFolio_rpc_umca(canalVirtualBD.getFolioRpcUmca());
		canVirDTOEdit.setTipo_uso(canalVirtualBD.getIdTipoUso().toString());
		canVirDTOEdit.setContenido(canalVirtualBD.getIdTipoContenido().toString());
		canVirDTOEdit.setCanal_digital(canalVirtualBD.getCanalDigital().toString());
		canVirDTOEdit.setCanal_virtual(canalVirtualBD.getCanalAsignado().toString());
		canVirDTOEdit.setProgramacion(canalVirtualBD.getProgramacion());
		canVirDTOEdit.setMc_mo(canalVirtualBD.getMcMo());
		canVirDTOEdit.setPrimer_asignacion(canalVirtualBD.getPrimerAsignacion());
		logoB64 = canalVirtualBD.getLogob64();
				
		poblarCanalDTO(canalVirtualBD.getCanal());
		poblarMultiprogramacionDTO(canalVirtualBD);
		poblarCoberturasDTO(canalVirtualBD.getCoberturas());
		poblarFormularioDTO(canalVirtualBD.getPreguntasFormulario());
		
		multiprog.setCanal_virtual(canVirDTOEdit);		
	}
	
	private void poblarFormularioDTO(List<CanalVirtualFormulario> preguntasFormulario) {
		formularioDTO = new CanalFormularioDTO();
		for (CanalVirtualFormulario itemPregunta : preguntasFormulario) {									
			if (PreguntaCanalVirtual.PREGUNTA1.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setLineaFrontera(itemPregunta.getValor());				
			} else if (PreguntaCanalVirtual.PREGUNTA2.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setOpinionFcc(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA3.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setNotificaCv(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA4.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setFechaOficioNot(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA5.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setNivelTransmicion(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA6.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setPublicaListado(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA7.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setPublicacion(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA8.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setObservacionCV(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA9.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setPrimerNota(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA10.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setTipoResolMulti(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA11.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setResolMulti(itemPregunta.getValor());
			}
			//Falta una pregunta que si esta en BD
			else if (PreguntaCanalVirtual.PREGUNTA13.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setFechaOficioNotAut(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA14.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setModificacion(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA15.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setResConcesionario(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA16.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setEstatusModificacion(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA17.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setPrimeraPublicacion(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA18.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setPublicaListadoMulti(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA19.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setObservacionesMulti(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA20.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setBdIne(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA21.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setCentroVm(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA22.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setNombreCanalIne(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA23.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setNombreComercialIne(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA24.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setTipoMonitoreoIne(itemPregunta.getValor());			
			} else if (PreguntaCanalVirtual.PREGUNTA25.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setBdAudista(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA26.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setNomCanalAudista(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA27.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setPrograAudista(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA28.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setLocalidadAudista(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA29.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setMonitorServExt(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA30.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setLocalidadServExt(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA31.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setNomComServExt(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA32.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setTipoPrograInfant(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA33.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setObligaAccesibilidad(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA34.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setMecaAccesibilidad(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA35.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setMedioPublico(itemPregunta.getValor());
			} else if (PreguntaCanalVirtual.PREGUNTA36.getIdentificador()==itemPregunta.getIdPregunta().intValue()) {
				formularioDTO.setInstPublicaFed(itemPregunta.getValor());
			}						
		}
	}

	private void poblarCoberturasDTO(List<Cobertura> coberturas) {
		coberturasDTO.clear();
		
		for (Cobertura cobertura : coberturas) {
			CoberturaDTO itemCoberDTO = new CoberturaDTO();
			PoblacionDTO itemPoblacionDTO = new PoblacionDTO();
			
			PoblacionXMLDTO poblacionXML = new PoblacionXMLDTO();
			
			itemPoblacionDTO.setEstado(cobertura.getEstado().getEstado());
			itemPoblacionDTO.setMunicipio(cobertura.getPoblacion().getPoblacion());
			
			poblacionXML.setIdEstado(cobertura.getEstado().getIdEstado().toString());
			poblacionXML.setIdPoblacion(cobertura.getPoblacion().getIdPoblacion().toString());
			
			itemCoberDTO.setPoblacion(itemPoblacionDTO);
			coberturasDTO.add(itemCoberDTO);
			
			multiprog.getCoberturas().getPoblaciones().add(poblacionXML);
		}
//		multiprog.setCoberturas(coberturas);
	}

	private void poblarCanalDTO(Canal canalBD) {
		CanalDTO canalDTOEdit = new CanalDTO();
		
		canalDTOEdit.setEstado(canalBD.getIdEstado().toString());
		
		poblacionesDTO = consultaPoblacion(canalBD.getIdEstado().toString());
		
		canalDTOEdit.setId_canal(canalBD.getIdCanal().toString());
		canalDTOEdit.setPoblacion(canalBD.getIdPoblacion().toString());
		canalDTOEdit.setConcesionario(canalBD.getIdConcesionario().toString());
		canalDTOEdit.setDistintivo(canalBD.getDistintivo());
		canalDTOEdit.setGrupo(canalBD.getGrupo());
		
		multiprog.setCanal(canalDTOEdit);
	}
	
	private void poblarMultiprogramacionDTO(CanalVirtual canalVirtualBD) {
		MultiprogramacionDTO multiDTOEdit = new MultiprogramacionDTO();
		
		multiDTOEdit.setMultiprograma(canalVirtualBD.getMultiprogramacion());
		multiDTOEdit.setCalidad(canalVirtualBD.getIdCalidad().toString());
		multiDTOEdit.setTasa_transf(canalVirtualBD.getTasaTrans().toString());
		multiDTOEdit.setMetodoCompr(canalVirtualBD.getIdMetodoCompr().toString());
		multiDTOEdit.setTerceroBenf(canalVirtualBD.getIdTerceroBenef().toString());
		multiDTOEdit.setEstatus(canalVirtualBD.getIdEstatus().toString());
		
		multiprog.setMultiprograma(multiDTOEdit);
	}
	
	/**
	 * @return the poblacionesDTO
	 */
	public List<CatalogoDTO> getPoblacionesDTO() {
		return poblacionesDTO;
	}

	/**
	 * @param poblacionesDTO the poblacionesDTO to set
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
	 * @param estadosDTO the estadosDTO to set
	 */
	public void setEstadosDTO(List<CatalogoDTO> estadosDTO) {
		this.estadosDTO = estadosDTO;
	}

	/**
	 * @return the tiposUsoDTO
	 */
	public List<CatalogoDTO> getTiposUsoDTO() {
		return tiposUsoDTO;
	}

	/**
	 * @param tiposUsoDTO the tiposUsoDTO to set
	 */
	public void setTiposUsoDTO(List<CatalogoDTO> tiposUsoDTO) {
		this.tiposUsoDTO = tiposUsoDTO;
	}

	/**
	 * @return the coberturasDTO
	 */
	public List<CoberturaDTO> getCoberturasDTO() {
		return coberturasDTO;
	}

	/**
	 * @param coberturasDTO the coberturasDTO to set
	 */
	public void setCoberturasDTO(List<CoberturaDTO> coberturasDTO) {
		this.coberturasDTO = coberturasDTO;
	}	

	/**
	 * @return the tiposContenidoDTO
	 */
	public List<CatalogoDTO> getTiposContenidoDTO() {
		return tiposContenidoDTO;
	}

	/**
	 * @param tiposContenidoDTO the tiposContenidoDTO to set
	 */
	public void setTiposContenidoDTO(List<CatalogoDTO> tiposContenidoDTO) {
		this.tiposContenidoDTO = tiposContenidoDTO;
	}

	/**
	 * @return the concesionariosDTO
	 */
	public List<CatalogoDTO> getConcesionariosDTO() {
		return concesionariosDTO;
	}

	/**
	 * @param concesionariosDTO the concesionariosDTO to set
	 */
	public void setConcesionariosDTO(List<CatalogoDTO> concesionariosDTO) {
		this.concesionariosDTO = concesionariosDTO;
	}

	/**
	 * @return the gruposDTO
	 */
	public List<CatalogoDTO> getGruposDTO() {
		return gruposDTO;
	}

	/**
	 * @param gruposDTO the gruposDTO to set
	 */
	public void setGruposDTO(List<CatalogoDTO> gruposDTO) {
		this.gruposDTO = gruposDTO;
	}

	/**
	 * @return the multiprog
	 */
	public MultiprogramacionXML getMultiprog() {
		return multiprog;
	}

	/**
	 * @param multiprog the multiprog to set
	 */
	public void setMultiprog(MultiprogramacionXML multiprog) {
		this.multiprog = multiprog;
	}

	/**
	 * @return the estadoCobertura
	 */
	public String getEstadoCobertura() {
		return estadoCobertura;
	}

	/**
	 * @param estadoCobertura the estadoCobertura to set
	 */
	public void setEstadoCobertura(String estadoCobertura) {
		this.estadoCobertura = estadoCobertura;
	}

	/**
	 * @return the municipioCobertura
	 */
	public String getMunicipioCobertura() {
		return municipioCobertura;
	}

	/**
	 * @param municipioCobertura the municipioCobertura to set
	 */
	public void setMunicipioCobertura(String municipioCobertura) {
		this.municipioCobertura = municipioCobertura;
	}

	/**
	 * @return the poblacionesCoberDTO
	 */
	public List<CatalogoDTO> getPoblacionesCoberDTO() {
		return poblacionesCoberDTO;
	}

	/**
	 * @param poblacionesCoberDTO the poblacionesCoberDTO to set
	 */
	public void setPoblacionesCoberDTO(List<CatalogoDTO> poblacionesCoberDTO) {
		this.poblacionesCoberDTO = poblacionesCoberDTO;
	}

	/**
	 * @return the formularioDTO
	 */
	public CanalFormularioDTO getFormularioDTO() {
		return formularioDTO;
	}

	/**
	 * @param formularioDTO the formularioDTO to set
	 */
	public void setFormularioDTO(CanalFormularioDTO formularioDTO) {
		this.formularioDTO = formularioDTO;
	}

	/**
	 * @return the fileLogo
	 */
	public UploadedFile getFileLogo() {
		return fileLogo;
	}

	/**
	 * @param fileLogo the fileLogo to set
	 */
	public void setFileLogo(UploadedFile fileLogo) {
		this.fileLogo = fileLogo;
	}

	/**
	 * @return the logoB64
	 */
	public String getLogoB64() {
		return logoB64;
	}

	/**
	 * @param logoB64 the logoB64 to set
	 */
	public void setLogoB64(String logoB64) {
		this.logoB64 = logoB64;
	}

	/**
	 * @return the fileContenido
	 */
	public byte[] getFileContenido() {
		return fileContenido;
	}

	/**
	 * @param fileContenido the fileContenido to set
	 */
	public void setFileContenido(byte[] fileContenido) {
		this.fileContenido = fileContenido;
	}

	/**
	 * @return the fechaOficioNotAut
	 */
	public Date getFechaOficioNotAut() {
		return fechaOficioNotAut;
	}

	/**
	 * @param fechaOficioNotAut the fechaOficioNotAut to set
	 */
	public void setFechaOficioNotAut(Date fechaOficioNotAut) {
		this.fechaOficioNotAut = fechaOficioNotAut;
	}

	/**
	 * @return the fechaOficioNot
	 */
	public Date getFechaOficioNot() {
		return fechaOficioNot;
	}

	/**
	 * @param fechaOficioNot the fechaOficioNot to set
	 */
	public void setFechaOficioNot(Date fechaOficioNot) {
		this.fechaOficioNot = fechaOficioNot;
	}

	/**
	 * @return the tercerosDTO
	 */
	public List<CatalogoDTO> getTercerosDTO() {
		return tercerosDTO;
	}

	/**
	 * @param tercerosDTO the tercerosDTO to set
	 */
	public void setTercerosDTO(List<CatalogoDTO> tercerosDTO) {
		this.tercerosDTO = tercerosDTO;
	}

	/**
	 * @return the estatusDTO
	 */
	public List<CatalogoDTO> getEstatusDTO() {
		return estatusDTO;
	}

	/**
	 * @param estatusDTO the estatusDTO to set
	 */
	public void setEstatusDTO(List<CatalogoDTO> estatusDTO) {
		this.estatusDTO = estatusDTO;
	}
	
	
		
}
