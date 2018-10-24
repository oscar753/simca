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

import mx.org.ift.simca.exposition.dto.CanalVO;
import mx.org.ift.simca.model.Canal;
import mx.org.ift.simca.service.CanalService;

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
	
	private String distintivo;
	private String concesionario;
	private String canalProg;
	private List<CanalVO> canalVOList = new ArrayList<CanalVO>();
	
	@PostConstruct
	public void init() {
		canalVOList.clear();
		canalProg = "Prueba";
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
				CanalVO itemCanal = new CanalVO();
				
				itemCanal.setDistintivo(StringUtils.isNotBlank(canal.getDistintivo())?canal.getDistintivo():"");
				
				canalVOList.add(itemCanal);				
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
	 * @return the canalVOList
	 */
	public List<CanalVO> getCanalVOList() {
		return canalVOList;
	}

	/**
	 * @param canalVOList the canalVOList to set
	 */
	public void setCanalVOList(List<CanalVO> canalVOList) {
		this.canalVOList = canalVOList;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}