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

import mx.org.ift.simca.exposition.dto.CanalDTO;
import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.model.CanalVirtual;
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
	
	private final String sinSeleccion = "0";
	
	private String claveProg;
	private String claveConcesionario;
	private String claveDistintivo;
	
	private List<CanalVirtual> canalesBD = new ArrayList<CanalVirtual>();
	private List<CanalVirtual> programasBD = new ArrayList<CanalVirtual>();
	private List<CatalogoDTO> concesionariosDTO = new ArrayList<CatalogoDTO>(); 
	private List<CanalDTO> distintivosDTO = new ArrayList<CanalDTO>();
	
	@PostConstruct
	public void init() {
		canalesBD.clear();	
		programasBD.clear();
		concesionariosDTO.clear();
		distintivosDTO.clear();
		
		concesionariosDTO=catalogoService.consultaConcesionario();
		distintivosDTO = canalService.buscarDistintivo();
		programasBD = canalService.buscarNomPrograma();
	}

	public void limpiar() {
		claveProg = "0";
		claveConcesionario = "0";
		claveDistintivo = "0";
		canalesBD.clear();	
	}
	
	public void buscarCanal() {	
		canalesBD.clear();
		
		if (claveConcesionario.equals(sinSeleccion)) {
			claveConcesionario = null;
		}
		if (claveDistintivo.equals(sinSeleccion)) {
			claveDistintivo = null;
		}
		if (claveProg.equals(sinSeleccion)) {
			claveProg = null;
		}
		
		canalesBD = canalService.buscarCanalProgramacion(claveDistintivo, claveConcesionario, claveProg);
		
		if  (canalesBD.isEmpty()) {
			LOG.info("No se encontraron canales");
		}
	}
			

	/**
	 * @return the claveProg
	 */
	public String getClaveProg() {
		return claveProg;
	}
	/**
	 * @param claveProg the claveProg to set
	 */
	public void setClaveProg(String claveProg) {
		this.claveProg = claveProg;
	}

	/**
	 * @return the programasBD
	 */
	public List<CanalVirtual> getProgramasBD() {
		return programasBD;
	}
	/**
	 * @param programasBD the programasBD to set
	 */
	public void setProgramasBD(List<CanalVirtual> programasBD) {
		this.programasBD = programasBD;
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

	/**
	 * @return the distintivosDTO
	 */
	public List<CanalDTO> getDistintivosDTO() {
		return distintivosDTO;
	}

	/**
	 * @param distintivosDTO the distintivosDTO to set
	 */
	public void setDistintivosDTO(List<CanalDTO> distintivosDTO) {
		this.distintivosDTO = distintivosDTO;
	}

	/**
	 * @return the claveDistintivo
	 */
	public String getClaveDistintivo() {
		return claveDistintivo;
	}

	/**
	 * @param claveDistintivo the claveDistintivo to set
	 */
	public void setClaveDistintivo(String claveDistintivo) {
		this.claveDistintivo = claveDistintivo;
	}

	/**
	 * @return the canalesBD
	 */
	public List<CanalVirtual> getCanalesBD() {
		return canalesBD;
	}

	/**
	 * @param canalesBD the canalesBD to set
	 */
	public void setCanalesBD(List<CanalVirtual> canalesBD) {
		this.canalesBD = canalesBD;
	}
	
	
}
