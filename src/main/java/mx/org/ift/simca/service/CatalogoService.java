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
	
	List<CatalogoDTO> consultaEstado();

	List<CatalogoDTO> consultaBanda();
	
	List<CatalogoDTO> consultaClase();
	
	List<CatalogoDTO> consultaTipoUso();
	
	List<CatalogoDTO> consultaTipoUsoEstacion();
}
