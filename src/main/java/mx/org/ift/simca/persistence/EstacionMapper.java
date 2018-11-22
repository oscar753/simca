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
	 * 
	 * @return
	 */
	List<Estacion> getDistintivosRadio();
	
	/**
	 * Obtien los canales de acuerdo a los parametros enviados.
	 * @param distintivo
	 * @param idConcesionario
	 * @param folioElectronico
	 * @return
	 */
	List<Estacion> buscarEstacionProgramacion(
			@Param("distintivo") String distintivo,
			@Param("idConcesionario") String idConcesionario,
			@Param("folioElectronico") String folioElectronico
			);
	
	/**
	 * Llama al SP para insertar el registro
	 * @param model
	 * @param user
	 * @param longXML
	 * @return
	 */
	String generaRegEstacion(
			@Param("model") String model,
			@Param("user") String user,
			@Param("longXML") String longXML
			);
	
	/**
	 * Llama al SP para insertar el registro
	 * @param model
	 * @param user
	 * @param longXML
	 * @return
	 */
	String modificaRegEstacion(
			@Param("model") String model,
			@Param("user") String user,
			@Param("longXML") String longXML
			);
}
