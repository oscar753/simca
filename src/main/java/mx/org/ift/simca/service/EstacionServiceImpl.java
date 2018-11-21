/**
 * 
 */
package mx.org.ift.simca.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.model.Estacion;
import mx.org.ift.simca.persistence.EstacionMapper;

/**
 * @author cesar.agustin
 *
 */
@Service
public class EstacionServiceImpl implements EstacionService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3987842573859089067L;

	@Autowired
	EstacionMapper estacionMapper;



	private static final Logger LOGGER = LoggerFactory.getLogger(EstacionServiceImpl.class);
	
	public List<Estacion> buscarEstacionProgramacion(String distintivo, String idConcesionario, String canalProg) {
		LOGGER.info("Metodo para buscar los canales de programacion de radio");
		
		List<Estacion> estacionResult = new ArrayList<Estacion>();
		System.out.println("Distintivo: " + distintivo + " idConcesionario: "+ idConcesionario + " canalProg: " + canalProg);
		try {
			estacionResult = estacionMapper.buscarEstacionProgramacion(
					StringUtils.isBlank(distintivo)?null:distintivo,
					StringUtils.isBlank(idConcesionario)?null:idConcesionario,
					StringUtils.isBlank(canalProg)?null:canalProg);
			System.out.println(estacionResult.size());
			return estacionResult;
		}
		catch(Exception e) {
			System.out.println("Error en la clase buscarEstacionProgramacion: " + e.getMessage());
			return estacionResult;
		}
	}
	
	public List<Estacion> buscarDistintivosRadio(){
		LOGGER.info("Metodo para buscar los distintivos de estaciones de radio");
		
		List<Estacion> distintivosResult = estacionMapper.getDistintivosRadio();
		try {
			
		}
		catch(Exception e) {
			
		}
		return distintivosResult;
	}
	
	public void generaRegistroEstacion(String model, String user, String longXML) {
		try {
			estacionMapper.generaRegEstacion(model, user, longXML);
			
		}
		catch(Exception e) {
			System.out.println("Error en generaRegistroEstacion: " + e.getMessage());
		}
	}
}
