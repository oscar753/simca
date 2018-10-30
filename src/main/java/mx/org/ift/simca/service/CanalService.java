/**
 * 
 */
package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;

import mx.org.ift.simca.exposition.dto.CanalDTO;
import mx.org.ift.simca.model.CanalVirtual;

/**
 * @author KODE-LAP0077
 *
 */
public interface CanalService extends Serializable {
	
	/**
	 * 
	 * @param distintivo
	 * @param concesionario
	 * @param canalProg
	 * @return
	 */
	List<CanalVirtual> buscarCanalProgramacion(String distintivo, String concesionario, String canalProg);
	
	/**
	 * 
	 * @return
	 */
	List<CanalDTO> buscarDistintivo();

	/**
	 * 
	 * @return
	 */
	List<CanalVirtual> buscarNomPrograma();
}
