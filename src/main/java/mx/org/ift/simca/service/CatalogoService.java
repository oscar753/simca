/**
 * 
 */
package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;

/**
 * @author KODE-LAP0077
 *
 */
public interface CatalogoService extends Serializable{
	
	List<CatalogoDTO> consultaPoblacion();
	
	List<CatalogoDTO> consultaPoblacionEstado(int idEstado);
	
	List<CatalogoDTO> consultaEstado();

	List<CatalogoDTO> consultaBanda();
	
	List<CatalogoDTO> consultaClase();
	
	List<CatalogoDTO> consultaTipoUso();
		
	List<CatalogoDTO> consultaTipoUsoEstacion();
	
	List<CatalogoDTO> consultaTipoFrecuencia();
	
	List<CatalogoDTO> consultaConcesionario();
	
	List<CatalogoDTO> consultaTipoContenido();

	List<CatalogoDTO> consultaGrupoRadio();
	
	List<CatalogoDTO> consultaCanal();
	
	List<CatalogoDTO> consultaGrupo();

	List<CatalogoDTO> consultaEstatus();

	List<CatalogoDTO> consultaTercero();

}
