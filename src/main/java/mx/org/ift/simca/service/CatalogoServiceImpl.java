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
import mx.org.ift.simca.model.TipoUso;
import mx.org.ift.simca.persistence.EstadoMapper;
import mx.org.ift.simca.persistence.PoblacionMapper;
import mx.org.ift.simca.persistence.TipoUsoMapper;

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
	private TipoUsoMapper tipoUsoMap;

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

}
