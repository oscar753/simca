/**
 * 
 */
package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.exposition.dto.CanalVirtualDTO;
import mx.org.ift.simca.exposition.dto.CatalogoDTO;
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
	
	private String claveEstado;
	
	private List<CatalogoDTO> poblacionesDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> estadosDTO = new ArrayList<CatalogoDTO>();
	private List<CatalogoDTO> tiposUsoDTO = new ArrayList<CatalogoDTO>();	
	private CanalVirtualDTO canalVirtualDTO = new CanalVirtualDTO();
	
	@Autowired
	private CatalogoService catalogoService;
	
	@PostConstruct
	public void init() {
		LOG.info("/**** Se inicializa MB para agregar ****/");
		estadosDTO = catalogoService.consultaEstado();
	}
	
	/**
	 * @return the canalVirtualDTO
	 */
	public CanalVirtualDTO getCanalVirtualDTO() {
		return canalVirtualDTO;
	}

	/**
	 * @param canalVirtualDTO the canalVirtualDTO to set
	 */
	public void setCanalVirtualDTO(CanalVirtualDTO canalVirtualDTO) {
		this.canalVirtualDTO = canalVirtualDTO;
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
	 * @return the claveEstado
	 */
	public String getClaveEstado() {
		return claveEstado;
	}

	/**
	 * @param claveEstado the claveEstado to set
	 */
	public void setClaveEstado(String claveEstado) {
		this.claveEstado = claveEstado;
	}
	
}
