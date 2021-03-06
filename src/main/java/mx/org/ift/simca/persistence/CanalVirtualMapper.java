package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.CanalVirtual;

public interface CanalVirtualMapper extends IMapper<CanalVirtual> {
	
	/**
	 * 
	 * @param distintivo
	 * @param idConcesionario
	 * @param canalProg
	 * @return
	 */
	List<CanalVirtual> getDinamico(@Param("distintivo") String distintivo, @Param("idConcesionario") Integer idConcesionario, @Param("canalProg") String canalProg);
	
	/**
	 * 
	 * @param idConcesionario
	 * @return
	 */
	List<CanalVirtual> getEditar(@Param("idCanalVirtual") Integer idCanalVirtual);

	/**
	 * 
	 * @param canalVirtualXML
	 * @param usuarioNombre
	 */
	void insertCanalVirtual(@Param("modulo") String modulo, @Param("canalVirtualXML") String canalVirtualXML, @Param("usuarioNombre") String usuarioNombre);

	/**
	 * 
	 * @param modulo
	 * @param canalVirtualXML
	 * @param usuarioNombre
	 */
	void updateCanalVirtual(@Param("modulo") String modulo, @Param("canalVirtualXML") String canalVirtualXML, @Param("usuarioNombre")String usuarioNombre);

}
