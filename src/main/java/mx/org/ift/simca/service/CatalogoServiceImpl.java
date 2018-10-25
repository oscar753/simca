/**
 * 
 */
package mx.org.ift.simca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.model.Estado;
import mx.org.ift.simca.model.Poblacion;
import mx.org.ift.simca.model.Banda;
import mx.org.ift.simca.model.Clase;
import mx.org.ift.simca.model.TipoUso;
import mx.org.ift.simca.model.TipoUsoEstacion;
import mx.org.ift.simca.persistence.EstadoMapper;
import mx.org.ift.simca.persistence.PoblacionMapper;
import mx.org.ift.simca.persistence.BandaMapper;
import mx.org.ift.simca.persistence.ClaseMapper;
import mx.org.ift.simca.persistence.TipoUsoMapper;
import mx.org.ift.simca.persistence.TipoUsoEstacionMapper;

/**
 * @author KODE-LAP0077
 *
 */
@Service
public class CatalogoServiceImpl implements CatalogoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7589438207707462142L;
	
	@Autowired
	private PoblacionMapper poblacionMap;
	
	@Autowired
	private EstadoMapper estadoMap;
	
	@Autowired
	private BandaMapper bandaMap;
	
	@Autowired
	private ClaseMapper claseMap;
	
	@Autowired
	private TipoUsoMapper tipoUsoMap;
	
	@Autowired
	private TipoUsoEstacionMapper tipoUsoEstacionMap;

	public List<CatalogoDTO> consultaPoblacion() {
		List<CatalogoDTO> poblacionesResult = new ArrayList<CatalogoDTO>();
		
		List<Poblacion> poblaciones =  poblacionMap.getAll();
		for (Poblacion poblacion : poblaciones) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(poblacion.getPoblacion());
			itemCat.setIdentificador(poblacion.getIdPoblacion().toString());
			
			poblacionesResult.add(itemCat);
		}
		
		return poblacionesResult;
	}

	public List<CatalogoDTO> consultaEstado() {
		List<CatalogoDTO> estadosResult = new ArrayList<CatalogoDTO>();
		
		List<Estado> estados = estadoMap.getAll();
		for (Estado estado : estados) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(estado.getEstado());
			itemCat.setIdentificador(estado.getIdEstado().toString());
			
			estadosResult.add(itemCat);
		}
		return estadosResult;
	}

	public List<CatalogoDTO> consultaBanda() {
		List<CatalogoDTO> bandasResult = new ArrayList<CatalogoDTO>();
		
		List<Banda> bandas = bandaMap.getAll();
		for (Banda banda : bandas) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(banda.getBanda());
			itemCat.setIdentificador(banda.getIdBanda().toString());
			
			bandasResult.add(itemCat);
		}
		return bandasResult;
	}
	
	public List<CatalogoDTO> consultaClase() {
		List<CatalogoDTO> clasesResult = new ArrayList<CatalogoDTO>();
		
		List<Clase> clases = claseMap.getAll();
		for (Clase clase : clases) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(clase.getClase());
			itemCat.setIdentificador(clase.getIdClase().toString());
			
			clasesResult.add(itemCat);
		}
		return clasesResult;
	}
	
	public List<CatalogoDTO> consultaTipoUso() {
		List<CatalogoDTO> tiposUsoResult = new ArrayList<CatalogoDTO>();
		
		List<TipoUso> tiposUso = tipoUsoMap.getAll();
		for (TipoUso tipoUso : tiposUso) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(tipoUso.getTipoUso());
			itemCat.setIdentificador(tipoUso.getIdTipoUso().toString());
			
			tiposUsoResult.add(itemCat);
		}
		return tiposUsoResult;
	}
	
	public List<CatalogoDTO> consultaTipoUsoEstacion() {
		List<CatalogoDTO> tiposUsoEstacionResult = new ArrayList<CatalogoDTO>();
		
		List<TipoUsoEstacion> tiposUsoEstacion = tipoUsoEstacionMap.getAll();
		for (TipoUsoEstacion tipoUsoEstacion : tiposUsoEstacion) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(tipoUsoEstacion.getTipoUsoEstacion());
			itemCat.setIdentificador(tipoUsoEstacion.getIdTipoUsoEstacion().toString());
			
			tiposUsoEstacionResult.add(itemCat);
		}
		
		return tiposUsoEstacionResult;
	}

}
