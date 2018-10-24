/**
 * 
 */
package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;

import mx.org.ift.simca.model.Canal;

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
	List<Canal> buscarCanalProgramacion(String distintivo, String concesionario, String canalProg);

}
