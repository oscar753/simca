package mx.org.ift.simca.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.model.EstacionFormulario;
import mx.org.ift.simca.model.Opcion;
import mx.org.ift.simca.model.TipoPregunta;
import mx.org.ift.simca.persistence.EstacionFormularioMapper;
import mx.org.ift.simca.persistence.OpcionMapper;
import mx.org.ift.simca.persistence.TipoPreguntaMapper;

@Service
public class EstacionFormularioServiceImpl implements EstacionFormularioService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2531877043790540776L;

	@Autowired
	OpcionMapper opcionMapper;
	
	@Autowired
	TipoPreguntaMapper tipoPreguntaMapper;
	
	@Autowired
	EstacionFormularioMapper estacionFormularioMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(EstacionServiceImpl.class);

	public List<Opcion> buscarOpciones(String pregunta, Integer idTipoFormulario) {
		LOGGER.info("Metodo para buscar las opciones del formulario dado el tipo de pregunta");

		List<Opcion> opcionesResult = new ArrayList<Opcion>();
		try {
			opcionesResult = opcionMapper.getOpciones(StringUtils.isBlank(pregunta) ? null : pregunta, idTipoFormulario);
			return opcionesResult;
			
		} catch (Exception e) {
			System.out.println("Error en el método buscarOpciones: " + e.getMessage());
			return opcionesResult;
		}
	}
	
	public List<TipoPregunta> buscarTipoPreguntasPorFormulario(Integer idTipoFormulario){
		LOGGER.info("Metodo para buscar los diferentes tipos de pregunta del formulario con idTipoFormulario " + idTipoFormulario);
		
		List<TipoPregunta> tipoPreguntas = new ArrayList<TipoPregunta>();
		try {
			tipoPreguntas = tipoPreguntaMapper.obtenPreguntasFormulario(idTipoFormulario);
			return tipoPreguntas;
		}
		catch (Exception e) {
			System.out.println("Error en el método buscarTipoPreguntasPorFormulario: " + e.getMessage());
			return tipoPreguntas;
		}
	}
	
	public List<EstacionFormulario> buscarRespuestasFormulario(String folioElectronico, Integer idTipoFormulario){
		LOGGER.info("Metodo para buscar las respuestas del formulario de un folio electrónico " + folioElectronico + " con idTipoFormulario " + idTipoFormulario);
		
		List<EstacionFormulario> respuestasFormulario = new ArrayList<EstacionFormulario>();
		try {
			respuestasFormulario = estacionFormularioMapper.obtenRespuestasFormulario(folioElectronico, idTipoFormulario);
			return respuestasFormulario;
		}
		catch (Exception e) {
			System.out.println("Error en el método buscarRespuestasFormulario: " + e.getMessage());
			return respuestasFormulario;
		}
		
	}

}
