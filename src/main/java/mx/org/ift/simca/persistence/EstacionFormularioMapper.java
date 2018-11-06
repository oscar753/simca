package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.EstacionFormulario;

public interface EstacionFormularioMapper extends IMapper<EstacionFormulario>{
	
	/**
	 * 
	 * @param pregunta
	 * @return
	 */
	List<EstacionFormulario> getOpciones(@Param("pregunta") String pregunta);
}
