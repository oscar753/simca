/**
 * 
 */
package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.exposition.dto.CanalVirtualDTO;
import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.exposition.dto.CoberturaDTO;
import mx.org.ift.simca.exposition.dto.MultiprogramacionXML;
import mx.org.ift.simca.exposition.dto.PoblacionDTO;
import mx.org.ift.simca.model.Canal;
import mx.org.ift.simca.model.CanalVirtual;
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
		
	private List<CoberturaDTO> coberturasDTO = new ArrayList<CoberturaDTO>();
	
	private MultiprogramacionXML multiprog;
	
	private String estadoCobertura;
	private String municipioCobertura;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@PostConstruct
	public void init() {
		LOG.info("/**** Se inicializa MB para agregar ****/");
//		try {
//			Canal canal = new Canal();
//			canal.setIdCanal(1L);
//			canal.setIdConcesionario(2L);
//			canal.setDistintivo("Prueba");
//			
//			JAXBContext jaxbContext = JAXBContext.newInstance(Canal.class);
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			StringWriter sw = new StringWriter();
//			jaxbMarshaller.marshal(canal, sw);
//			String xmlString = sw.toString();
//			LOG.info(xmlString);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		multiprog = new MultiprogramacionXML();
		
		coberturasDTO = new ArrayList<CoberturaDTO>();
		estadosDTO = catalogoService.consultaEstado();
		tiposContenidoDTO = catalogoService.consultaTipoContenido();
		concesionariosDTO = catalogoService.consultaConcesionario();
		tiposUsoDTO = catalogoService.consultaTipoUso();
		gruposDTO = catalogoService.consultaGrupo();
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
	
	public void agregarCobertura() {
		LOG.info("/**** Se agrega cobertura");
		PoblacionDTO poblacionDTO = new PoblacionDTO();
		CoberturaDTO coberturaDTO = new CoberturaDTO();
		
		poblacionDTO.setEstado(estadoCobertura);
		poblacionDTO.setMunicipio(municipioCobertura);
		coberturaDTO.setPoblacion(poblacionDTO);
		coberturasDTO.add(coberturaDTO);
	}
	
	public void guardarMultiprog() {
		LOG.info("/**** ID senial :: " +multiprog.getCanal_virtual().getId_senial());
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
			
}
