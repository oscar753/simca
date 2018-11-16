/**
 * 
 */
package mx.org.ift.simca.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.exposition.dto.CanalDTO;
import mx.org.ift.simca.model.Canal;
import mx.org.ift.simca.model.CanalVirtual;
import mx.org.ift.simca.persistence.CanalMapper;
import mx.org.ift.simca.persistence.CanalVirtualMapper;

/**
 * @author cesar.agustin
 *
 */
@Service
public class CanalServiceImpl implements CanalService {
	
	@Autowired
	CanalMapper canalMapper;
	
	@Autowired
	CanalVirtualMapper canVirtualMapper;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5682996010696164790L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CanalServiceImpl.class);
	
	public List<CanalVirtual> buscarCanalProgramacion(String distintivo, String concesionario, String canalProg) {		
		LOGGER.info("/**** Metodo para buscar los canales de programacion ****/");
		LOGGER.info("---- Distintivo ----"+distintivo+"----");
		LOGGER.info("---- Concesionario ----"+concesionario+"----");
		LOGGER.info("---- Canal Prog ----"+canalProg+"----");
		LOGGER.info("/***********************************************/");
		List<CanalVirtual> canalResult = new ArrayList<CanalVirtual>();
		try {
			
				canalResult = canVirtualMapper.getDinamico(distintivo, 
										StringUtils.isNotBlank(concesionario)?new Integer(concesionario):null, 
										canalProg);
			
			LOGGER.info("/**** Canales :: "+canalResult.size());
		} catch (Exception e) {
			LOGGER.error("No se logro obtener la información de los canales"+e);
		}
		
		return canalResult;
	}

	public List<CanalDTO> buscarDistintivo() {
		LOGGER.info("Metodo para buscar los distintivos de los canales de programacion");
		
		List<CanalDTO> canalResult = new ArrayList<CanalDTO>();
		try {			
			List<Canal> distintivos = canalMapper.getDistintivo();
			LOGGER.info("/**** List distintivos :: "+distintivos.size());
			
			for (Canal canal : distintivos) {
				CanalDTO itemDTO = new CanalDTO();
				itemDTO.setIdentificador(canal.getIdCanal().toString());
				itemDTO.setDistintivo(canal.getDistintivo());
				
				canalResult.add(itemDTO);
			}
		} catch (Exception e) {
			Log.error("No se logro obtener la información de los canales");
		}
		
		return canalResult;
	}

	@Override
	public List<CanalVirtual> buscarNomPrograma() {
		LOGGER.info("Metodo para buscar los nombres programas de canales");
		
		List<CanalVirtual> programas = new ArrayList<CanalVirtual>();
		try {						
			programas = canVirtualMapper.getAll();
			LOGGER.info("/**** Programas :: "+programas.size());
			
		} catch (Exception e) {
			Log.error("No se logro obtener la información de los canales");
		}
		
		return programas;
	}

	@Override
	public CanalVirtual buscarCanalVirtualPorId(Integer idCanalVirtual) {
		LOGGER.info("Metodo para buscar canal virtual por su ID");
		
		List<CanalVirtual> canalesVirtuales = new ArrayList<CanalVirtual>();
		try {						
			canalesVirtuales = canVirtualMapper.getEditar(idCanalVirtual);
			LOGGER.info("/**** Canal virtual :: "+canalesVirtuales.size());
			
		} catch (Exception e) {
			Log.error("No se logro obtener la información de los canales");
		}
		
		return canalesVirtuales.get(0);
	}
}
