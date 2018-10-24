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

import mx.org.ift.simca.exposition.dto.EstacionVO;
import mx.org.ift.simca.model.Estacion;
import mx.org.ift.simca.service.EstacionService;

@Controller
@Scope("session")
public class EstacionProgramacionMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4567893375079648053L;
	
	private static Logger LOG = LoggerFactory.getLogger(EstacionProgramacionMB.class);

	@Autowired	
	private EstacionService estacionService;
	
	private String distintivo;
	private String concesionario;
	private String canalProg;
	private List<EstacionVO> estacionVOList = new ArrayList<EstacionVO>();
	
	@PostConstruct
	public void init() {
		estacionVOList.clear();
		canalProg = "Prueba";
	}
	
	public void limpiar() {
		canalProg = "";
		concesionario = "";
		distintivo = "";
	}
	
	public void buscarEstacion() {				
		List<Estacion> canalesBD = estacionService.buscarEstacionProgramacion(distintivo, concesionario, canalProg);
		
		if  (canalesBD.isEmpty()) {
			LOG.info("No se encontraron canales");
		}
		else {
			for (Estacion canal : canalesBD) {
				EstacionVO itemCanal = new EstacionVO();
				
				itemCanal.setDistintivo(StringUtils.isNotBlank(canal.getDistintivo())?canal.getDistintivo():"");
				
				estacionVOList.add(itemCanal);				
			}
		}
		
		
		System.out.println("FINALIZO");
		
		
	}

	public String getDistintivo() {
		return distintivo;
	}

	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}

	public String getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}

	public String getCanalProg() {
		return canalProg;
	}

	public void setCanalProg(String canalProg) {
		this.canalProg = canalProg;
	}

	public List<EstacionVO> getEstacionVOList() {
		return estacionVOList;
	}

	public void setEstacionVOList(List<EstacionVO> estacionVOList) {
		this.estacionVOList = estacionVOList;
	}
	
}
