/**
 * 
 */
package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.Estacion;

/**
 * @author cesar.agustin
 *
 */
public interface EstacionMapper extends IMapper<Estacion>{
	
	/**
	 * Obtien los canales de acuerdo a los parametros enviados.
	 * @param distintivo
	 * @param idConcesionario
	 * @param canalProg
	 * @return
	 */
	List<Estacion> buscarEstacionProgramacion(
			@Param("distintivo") String distintivo,
			@Param("idConcesionario") String idConcesionario,
			@Param("canalProg") String canalProg
			);
	
}
