package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;

import mx.org.ift.simca.model.Opcion;
import mx.org.ift.simca.model.TipoPregunta;

public interface EstacionFormularioService extends Serializable {
	
	/**
	 * 
	 * @param pregunta
	 * @return
	 */
	List<Opcion> buscarOpciones(String pregunta);
	
	/**
	 * 
	 * @param idTipoFormulario
	 * @return
	 */
	List<TipoPregunta> buscarTipoPreguntasPorFormulario(Integer idTipoFormulario);

}
