package mx.org.ift.simca.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.model.Opcion;
import mx.org.ift.simca.model.TipoPregunta;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(EstacionServiceImpl.class);

	public List<Opcion> buscarOpciones(String pregunta) {
		LOGGER.info("Metodo para buscar las opciones del formulario dado el tipo de pregunta");

		List<Opcion> opcionesResult = new ArrayList<Opcion>();
		System.out.println("Pregunta " + pregunta);
		try {
			opcionesResult = opcionMapper.getOpciones(StringUtils.isBlank(pregunta) ? null : pregunta);
			System.out.println(opcionesResult.size());
			return opcionesResult;
			
		} catch (Exception e) {
			System.out.println("Error en el método buscarOpciones: " + e.getMessage());
			return opcionesResult;
		}
	}
	
	public List<TipoPregunta> buscarTipoPreguntas(){
		LOGGER.info("Metodo para buscar los diferentes tipos de pregunta del formulario");
		
		List<TipoPregunta> tipoPreguntas = new ArrayList<TipoPregunta>();
		
		try {
			tipoPreguntas = tipoPreguntaMapper.getAll();
			return tipoPreguntas;
		}
		catch (Exception e) {
			System.out.println("Error en el método buscarTipoPreguntas: " + e.getMessage());
			return tipoPreguntas;
		}
		
	}

}
