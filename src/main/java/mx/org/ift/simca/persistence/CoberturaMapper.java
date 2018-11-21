/**
 * 
 */
package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.Cobertura;

/**
 * @author KODE-LAP0077
 *
 */
public interface CoberturaMapper extends IMapper<Cobertura> {

	/**
	 * 
	 * @param idCanalVirtual
	 * @return
	 */
	List<Cobertura> getByCanalVirtual(@Param("idCanalVirtual") Integer idCanalVirtual);
}
