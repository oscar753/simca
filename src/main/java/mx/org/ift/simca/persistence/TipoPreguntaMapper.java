package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.TipoPregunta;

public interface TipoPreguntaMapper extends IMapper<TipoPregunta>{
	
	/**
	 * Obtien las tipo preguntas con base al tipo de formulario dado
	 * @param idTipoFormulario
	 * @return
	 */
	List<TipoPregunta> obtenPreguntasFormulario(
			@Param("idTipoFormulario") Integer idTipoFormulario
			);
}
