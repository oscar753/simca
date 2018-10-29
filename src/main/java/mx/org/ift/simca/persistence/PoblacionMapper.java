package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.Poblacion;

public interface PoblacionMapper extends IMapper<Poblacion>{
	
	/**
	 * Obtien las poblaciones con base al estado seleccionado.
	 * @param idEstado
	 * @return
	 */
	List<Poblacion> obtenPoblacionEstado(
			@Param("idEstado") int idEstado
			);
}
