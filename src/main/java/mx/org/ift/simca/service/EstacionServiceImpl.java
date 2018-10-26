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
	
	public List<Estacion> buscarEstacionProgramacion(String distintivo, String concesionario, String canalProg) {		
		LOGGER.info("Metodo para buscar los canales de programacion de radio");
		
		List<Estacion> estacionResult = new ArrayList<Estacion>();

		try {
			estacionResult = estacionMapper.buscarEstacionProgramacion(
					StringUtils.isBlank(distintivo)?null:distintivo,
					StringUtils.isBlank(concesionario)?null:concesionario,
					StringUtils.isBlank(canalProg)?null:canalProg);
			
			return estacionResult;
		}
		catch(Exception e) {
			System.out.println("Error en la clase buscarEstacionProgramacion: " + e.getMessage());
			return estacionResult;
		}
	}
}
