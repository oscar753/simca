package mx.org.ift.simca.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.CoberturaRadio;

public interface CoberturaRadioMapper extends IMapper<CoberturaRadio>{
	/**
	 * 
	 * @param folioElectronico
	 * @param idSenial
	 * @return
	 */
	List<CoberturaRadio> obtenCoberturas(@Param("folioElectronico") String folioElectronico, @Param("idSenial") Integer idSenial);
}
