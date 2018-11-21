/**
 * 
 */
package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.CanalVirtualFormulario;

/**
 * @author KODE-LAP0077
 *
 */
public interface CanalVirtualFormularioMapper extends IMapper<CanalVirtualFormulario> {

	/**
	 * 
	 * @param idCanal
	 * @param idCanalVirtual
	 * @return
	 */
	List<CanalVirtualFormulario> getByCanalVirtual(@Param("idCanal") Integer idCanal, @Param("idCanalVirtual") Integer idCanalVirtual);
}
