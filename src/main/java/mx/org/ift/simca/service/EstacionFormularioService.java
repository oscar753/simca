package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;

import mx.org.ift.simca.model.EstacionFormulario;
import mx.org.ift.simca.model.Opcion;
import mx.org.ift.simca.model.TipoPregunta;

public interface EstacionFormularioService extends Serializable {
	
	/**
	 * 
	 * @param pregunta
	 * @param idTipoFormulario
	 * @return
	 */
	List<Opcion> buscarOpciones(String pregunta, Integer idTipoFormulario);
	
	/**
	 * 
	 * @param idTipoFormulario
	 * @return
	 */
	List<TipoPregunta> buscarTipoPreguntasPorFormulario(Integer idTipoFormulario);
	
	/**
	 * 
	 * @param idSenial
	 * @param folioElectronico
	 * @param idTipoFormulario
	 * @return
	 */
	List<EstacionFormulario> buscarRespuestasFormulario(Integer idSenial, String folioElectronico, Integer idTipoFormulario);

}
