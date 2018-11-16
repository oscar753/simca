/**
 * 
 */
package mx.org.ift.simca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.model.Banda;
import mx.org.ift.simca.model.Canal;
import mx.org.ift.simca.model.Clase;
import mx.org.ift.simca.model.Concesionario;
import mx.org.ift.simca.model.Estado;
import mx.org.ift.simca.model.Estatus;
import mx.org.ift.simca.model.Grupo;
import mx.org.ift.simca.model.GrupoRadio;
import mx.org.ift.simca.model.Poblacion;
import mx.org.ift.simca.model.Tercero;
import mx.org.ift.simca.model.TipoContenido;
import mx.org.ift.simca.model.TipoFrecuencia;
import mx.org.ift.simca.model.TipoUso;
import mx.org.ift.simca.model.TipoUsoEstacion;
import mx.org.ift.simca.persistence.BandaMapper;
import mx.org.ift.simca.persistence.CanalMapper;
import mx.org.ift.simca.persistence.ClaseMapper;
import mx.org.ift.simca.persistence.ConcesionarioMapper;
import mx.org.ift.simca.persistence.EstadoMapper;
import mx.org.ift.simca.persistence.EstatusMapper;
import mx.org.ift.simca.persistence.GrupoMapper;
import mx.org.ift.simca.persistence.GrupoRadioMapper;
import mx.org.ift.simca.persistence.PoblacionMapper;
import mx.org.ift.simca.persistence.TerceroMapper;
import mx.org.ift.simca.persistence.TipoContenidoMapper;
import mx.org.ift.simca.persistence.TipoFrecuenciaMapper;
import mx.org.ift.simca.persistence.TipoUsoEstacionMapper;
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
	private BandaMapper bandaMap;
	
	@Autowired
	private ClaseMapper claseMap;
	
	@Autowired
	private CanalMapper canalMap;
	
	@Autowired
	private TipoUsoMapper tipoUsoMap;
	
	@Autowired
	private TipoUsoEstacionMapper tipoUsoEstacionMap;
	
	@Autowired
	private TipoFrecuenciaMapper tipoFrecuenciaMap;
	
	@Autowired
	private ConcesionarioMapper concesionarioMap;

	@Autowired
	private GrupoRadioMapper grupoRadioMap;
	
	@Autowired
	private TipoContenidoMapper contenidoMap;
	
	@Autowired
	private GrupoMapper grupoMap;
	
	@Autowired
	private EstatusMapper estatusMap;
	
	@Autowired
	private TerceroMapper terceroMap;

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
	
	public List<CatalogoDTO> consultaPoblacionEstado(int idEstado) {
		List<CatalogoDTO> poblacionesResult = new ArrayList<CatalogoDTO>();
		
		List<Poblacion> poblaciones =  poblacionMap.obtenPoblacionEstado(idEstado);
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
	
	public List<CatalogoDTO> consultaTipoFrecuencia() {
		List<CatalogoDTO> tipoFrecuenciaResult = new ArrayList<CatalogoDTO>();
		
		List<TipoFrecuencia> tiposFrecuencia = tipoFrecuenciaMap.getAll();
		for (TipoFrecuencia tipoFrecuencia : tiposFrecuencia) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(tipoFrecuencia.getTipoFrecuencia());
			itemCat.setIdentificador(tipoFrecuencia.getIdTipoFrecuencia().toString());
			
			tipoFrecuenciaResult.add(itemCat);
		}
		
		return tipoFrecuenciaResult;
	}

	public List<CatalogoDTO> consultaConcesionario() {
		List<CatalogoDTO> concesionariosResult = new ArrayList<CatalogoDTO>();
		
		List<Concesionario> concesionarios = concesionarioMap.getAll();
		for (Concesionario concesionario : concesionarios) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setIdentificador(concesionario.getIdConcesionario().toString());
			itemCat.setDescripcion(concesionario.getNomConcesionario());
			
			concesionariosResult.add(itemCat);
		}
		
		return concesionariosResult;
	}
	
	public List<CatalogoDTO> consultaGrupoRadio() {
		List<CatalogoDTO> gruposRadioResult = new ArrayList<CatalogoDTO>();
		
		List<GrupoRadio> gruposRadio = grupoRadioMap.getAll();
		for (GrupoRadio grupoRadio : gruposRadio) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setIdentificador(grupoRadio.getFolioElectronico());
			itemCat.setDescripcion(grupoRadio.getConcesionario().getNomConcesionario());
			
			gruposRadioResult.add(itemCat);
		}
		return gruposRadioResult;
	}
	
	public List<CatalogoDTO> consultaCanal() {
		List<CatalogoDTO> canalesResult = new ArrayList<CatalogoDTO>();
		try {
			
			List<Canal> canales = canalMap.getDinamico(null, null, null);
			for (Canal canal : canales) {
				CatalogoDTO itemCat = new CatalogoDTO();
				itemCat.setIdentificador(canal.getIdCanal().toString());
				itemCat.setDescripcion(canal.getConcesionario().getNomConcesionario());
				
				canalesResult.add(itemCat);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return canalesResult;
	}

	public List<CatalogoDTO> consultaTipoContenido() {
		List<CatalogoDTO> tipContenidoResult = new ArrayList<CatalogoDTO>();
		
		List<TipoContenido> tiposContenido = contenidoMap.getAll();
		for (TipoContenido tipoContenido : tiposContenido) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(tipoContenido.getTipoContenido());
			itemCat.setIdentificador(tipoContenido.getIdTipoContenido().toString());
			
			tipContenidoResult.add(itemCat);
		}
		
		return tipContenidoResult;
	}

	@Override
	public List<CatalogoDTO> consultaGrupo() {
		List<CatalogoDTO> grupoResult = new ArrayList<CatalogoDTO>();
		
		List<Grupo> grupos = grupoMap.getAll();
		for (Grupo grupo : grupos) {
			CatalogoDTO itemCat = new CatalogoDTO();
			itemCat.setDescripcion(grupo.getGrupo());
			itemCat.setIdentificador(grupo.getIdGrupo().toString());
			
			grupoResult.add(itemCat);
		}
		
		return grupoResult;
	}

	public List<CatalogoDTO> consultaEstatus() {
		List<CatalogoDTO> estatusResult = new ArrayList<CatalogoDTO>();
		
		List<Estatus> estatus = estatusMap.getAll();
		
		for (Estatus estatusBD : estatus) {
			CatalogoDTO itemCat = new CatalogoDTO();
			
			itemCat.setIdentificador(estatusBD.getIdEstatus().toString());
			itemCat.setDescripcion(estatusBD.getEstatus());
			
			estatusResult.add(itemCat);
		}
		
		return estatusResult;
	}

	public List<CatalogoDTO> consultaTercero() {
		List<CatalogoDTO> tercerosResult = new ArrayList<CatalogoDTO>();
		
		List<Tercero> terceros = terceroMap.getAll();
		
		for (Tercero terceroBD : terceros) {
			CatalogoDTO itemCat = new CatalogoDTO();
			
			itemCat.setIdentificador(terceroBD.getIdTercero().toString());
			itemCat.setDescripcion(terceroBD.getTercero());
			
			tercerosResult.add(itemCat);
		}
		return tercerosResult;
	}
}

