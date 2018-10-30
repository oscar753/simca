/**
 * 
 */
package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.Canal;

/**
 * @author cesar.agustin
 *
 */
public interface CanalMapper extends IMapper<Canal>{
		
	/**
	 * 
	 * @return
	 */
	List<Canal> getDistintivo();

	/**
	 * 
	 * @param distintivo
	 * @param idConcesionario
	 * @param canalProg
	 * @return
	 */
	List<Canal> getDinamico(@Param("distintivo") String distintivo, @Param("idConcesionario") Integer idConcesionario, @Param("canalProg") Integer canalProg);
	
}
