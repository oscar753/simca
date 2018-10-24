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

import mx.org.ift.simca.model.Canal;
import mx.org.ift.simca.persistence.CanalMapper;

/**
 * @author cesar.agustin
 *
 */
@Service
public class CanalServiceImpl implements CanalService {
	
	@Autowired
	CanalMapper canalMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5682996010696164790L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CanalServiceImpl.class);
	
	public List<Canal> buscarCanalProgramacion(String distintivo, String concesionario, String canalProg) {		
		LOGGER.info("Metodo para buscar los canales de programacion");
		
		List<Canal> canalResult = new ArrayList<Canal>();
		
		if (StringUtils.isBlank(distintivo) && StringUtils.isBlank(concesionario) && StringUtils.isBlank(canalProg)) {
			canalResult = canalMapper.getAll();
		}
		
		return canalResult;
	}
}
