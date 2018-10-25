/**
 * 
 */
package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.exposition.dto.CanalDTO;
import mx.org.ift.simca.exposition.dto.CanalVirtualDTO;
import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.model.Canal;
import mx.org.ift.simca.service.CanalService;
import mx.org.ift.simca.service.CatalogoService;

/**
 * @author KODE-LAP0077
 *
 */
@Controller
@Scope("session")
public class CanalProgramacionMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5964160101902309565L;

	private static final Logger LOG = LoggerFactory.getLogger(CanalProgramacionMB.class);
	
	@Autowired
	private CanalService canalService;
	
	@Autowired
	private CatalogoService catalogoService;
	
	private String distintivo;
	private String concesionario;
	private String canalProg;
	private String claveConcesionario;
	private List<CanalVirtualDTO> canalesVirtDTO = new ArrayList<CanalVirtualDTO>();
	private List<CatalogoDTO> concesionariosDTO = new ArrayList<CatalogoDTO>(); 
	
	
	@PostConstruct
	public void init() {
		canalesVirtDTO.clear();	
		concesionariosDTO.clear();
		
		concesionariosDTO=catalogoService.consultaConcesionario();
	}

	public void limpiar() {
		canalProg = "";
		concesionario = "";
		distintivo = "";
	}
	
	public void buscarCanal() {				
		List<Canal> canalesBD = canalService.buscarCanalProgramacion(distintivo, concesionario, canalProg);
		
		if  (canalesBD.isEmpty()) {
			LOG.info("No se encontraron canales");
		}
		else {
			for (Canal canal : canalesBD) {
				CanalVirtualDTO itemCanal = new CanalVirtualDTO();
				CanalDTO canalDTO = new CanalDTO();
				
				canalDTO.setDistintivo(StringUtils.isNotBlank(canal.getDistintivo())?canal.getDistintivo():"");
				itemCanal.setCanal(canalDTO);
				
				canalesVirtDTO.add(itemCanal);				
			}
		}		
		
		System.out.println("FINALIZO");
	}
	
	/**
	 * @return the distintivo
	 */
	public String getDistintivo() {
		return distintivo;
	}
	/**
	 * @param distintivo the distintivo to set
	 */
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}
	/**
	 * @return the concesionario
	 */
	public String getConcesionario() {
		return concesionario;
	}
	/**
	 * @param concesionario the concesionario to set
	 */
	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}
	/**
	 * @return the canalProg
	 */
	public String getCanalProg() {
		return canalProg;
	}
	/**
	 * @param canalProg the canalProg to set
	 */
	public void setCanalProg(String canalProg) {
		this.canalProg = canalProg;
	}	

	/**
	 * @return the canalesVirtDTO
	 */
	public List<CanalVirtualDTO> getCanalesVirtDTO() {
		return canalesVirtDTO;
	}

	/**
	 * @param canalesVirtDTO the canalesVirtDTO to set
	 */
	public void setCanalesVirtDTO(List<CanalVirtualDTO> canalesVirtDTO) {
		this.canalesVirtDTO = canalesVirtDTO;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the claveConcesionario
	 */
	public String getClaveConcesionario() {
		return claveConcesionario;
	}

	/**
	 * @param claveConcesionario the claveConcesionario to set
	 */
	public void setClaveConcesionario(String claveConcesionario) {
		this.claveConcesionario = claveConcesionario;
	}
	
}
