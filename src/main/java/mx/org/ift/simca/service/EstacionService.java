package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;

import mx.org.ift.simca.exposition.dto.CanalDTO;
import mx.org.ift.simca.model.Estacion;

public interface EstacionService extends Serializable {
	
	/**
	 * 
	 * @return
	 */
	List<Estacion> buscarDistintivosRadio();
	/**
	 * 
	 * @param distintivo
	 * @param idConcesionario
	 * @param folioElectronico
	 * @return
	 */
	List<Estacion> buscarEstacionProgramacion(String distintivo, String idConcesionario, String folioElectronico);
	
	String generaRegistroEstacion(String model, String user, String longXML);
	
	String modificaRegistroEstacion(String model, String user, String longXML);
}
