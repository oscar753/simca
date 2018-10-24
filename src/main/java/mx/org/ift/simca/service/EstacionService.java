package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;

import mx.org.ift.simca.model.Estacion;

public interface EstacionService extends Serializable {
	/**
	 * 
	 * @param distintivo
	 * @param concesionario
	 * @param canalProg
	 * @return
	 */
	List<Estacion> buscarEstacionProgramacion(String distintivo, String concesionario, String canalProg);

}
