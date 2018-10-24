/**
 * 
 */
package mx.org.ift.simca.persistence;

import java.util.List;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.Canal;

/**
 * @author cesar.agustin
 *
 */
public interface CanalMapper extends IMapper<Canal>{
	
	/**
	 * Obtien los canales de acuerdo a los parametros enviados.
	 * @param distintivo
	 * @param concesionario
	 * @param canalProg
	 * @return
	 */
	List<Canal> buscarCanalProgramacion(String distintivo, String concesionario, String canalProg);
	
}
