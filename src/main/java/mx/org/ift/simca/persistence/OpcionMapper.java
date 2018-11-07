package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.Opcion;

public interface OpcionMapper extends IMapper<Opcion>{
	
	/**
	 * 
	 * @param pregunta
	 * @param idTipoFormulario
	 * @return
	 */
	List<Opcion> getOpciones(@Param("pregunta") String pregunta, @Param("idTipoFormulario") Integer idTipoFormulario);
	
}
