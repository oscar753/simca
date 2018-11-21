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
import mx.org.ift.simca.model.CanalVirtualFormulario;
import mx.org.ift.simca.model.Cobertura;
import mx.org.ift.simca.model.GrupoCanal;
import mx.org.ift.simca.persistence.CanalMapper;
import mx.org.ift.simca.persistence.CanalVirtualFormularioMapper;
import mx.org.ift.simca.persistence.CanalVirtualMapper;
import mx.org.ift.simca.persistence.CoberturaMapper;
import mx.org.ift.simca.persistence.GrupoCanalMapper;

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
	
	@Autowired
	CanalVirtualFormularioMapper canVirFormMapper;
	
	@Autowired
	CoberturaMapper coberturaMapper;
	
	@Autowired
	GrupoCanalMapper grupoCanalMapper;

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
			LOGGER.error("No se logro obtener la información de los canales");
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
			LOGGER.error("No se logro obtener la información de los canales");
		}
		
		return programas;
	}

	@Override
	public CanalVirtual buscarCanalVirtualPorId(Integer idCanalVirtual) {
		LOGGER.info("Metodo para buscar canal virtual por su ID");
		
		CanalVirtual canVirtualResult = null;
		List<CanalVirtual> canalesVirtuales = new ArrayList<CanalVirtual>();
		
		try {						
			canalesVirtuales = canVirtualMapper.getEditar(idCanalVirtual);
			
			canVirtualResult = canalesVirtuales.get(0);
			LOGGER.info("/****Id Canal virtual :: "+canVirtualResult.getNumCanalVirtual());
			LOGGER.info("/****Id Canal :: "+canVirtualResult.getCanal().getIdCanal());
			
			
			List<CanalVirtualFormulario> canVirFormulario = canVirFormMapper.getByCanalVirtual(canVirtualResult.getCanal().getIdCanal().intValue(), canVirtualResult.getNumCanalVirtual().intValue());							
			if (!canVirFormulario.isEmpty()) {
//				LOGGER.info("/****Preguntas :: "+canVirFormulario.size());
				canVirtualResult.setPreguntasFormulario(canVirFormulario);
			}
			
			List<Cobertura> coberturas = coberturaMapper.getByCanalVirtual(canVirtualResult.getNumCanalVirtual().intValue());
			if (!coberturas.isEmpty()) {
//				LOGGER.info("/****Coberturas :: "+coberturas.size());				
				canVirtualResult.setCoberturas(coberturas);
			}
			
			List<GrupoCanal> gruposCanal = grupoCanalMapper.getByCanal(canVirtualResult.getCanal().getIdCanal().intValue());
			for (GrupoCanal grupoCanal : gruposCanal) {
				LOGGER.info("/****Grupos :: "+gruposCanal.size());
				canVirtualResult.getCanal().setGrupo(grupoCanal.getIdGrupo().toString());
			}
		} catch (Exception e) {
			LOGGER.error("No se logro obtener la información de los canales");
			e.printStackTrace();
		}
		
		return canVirtualResult;
	}

	public Boolean agregarCanalVirtual(String canalVirtualXML, String usuarioNombre) {
		LOGGER.info("Servicio para INSERTAR canal virtual");
		
		try {
			canVirtualMapper.insertCanalVirtual("canal", canalVirtualXML, usuarioNombre);
		} catch (Exception e) {			
			LOGGER.error("No se logro insertar el canal virtual"+e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	
	public Boolean actualizarCanalVirtual(String canalVirtualXML, String usuarioNombre) {
		LOGGER.info("Servicio para ACTUALIZAR canal virtual");
		
		try {
			canVirtualMapper.updateCanalVirtual("canal", canalVirtualXML, usuarioNombre);
		} catch (Exception e) {			
			LOGGER.error("No se logro actualizar el canal virtual"+e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
