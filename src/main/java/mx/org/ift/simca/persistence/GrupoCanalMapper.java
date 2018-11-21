/**
 * 
 */
package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.GrupoCanal;

/**
 * @author KODE-LAP0077
 *
 */
public interface GrupoCanalMapper extends IMapper<GrupoCanal> {
	
	/**
	 * 
	 * @param idCanal
	 * @return
	 */
	List<GrupoCanal> getByCanal(@Param("idCanal") Integer idCanal);

}
