/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KODE-LAP0077
 *
 */
public class MultiprogramacionXML implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7575685053213237536L;
	
	private CanalDTO canal = new CanalDTO();
	
	private CanalVirtualDTO canal_virtual = new CanalVirtualDTO();
	
	private MultiprogramacionDTO multiprograma = new MultiprogramacionDTO();

	private List<CoberturaDTO> cobertura = new ArrayList<CoberturaDTO>();
	
	private List<PreguntaDTO> formulario = new ArrayList<PreguntaDTO>();

	/**
	 * @return the canal
	 */
	public CanalDTO getCanal() {
		return canal;
	}

	/**
	 * @param canal the canal to set
	 */
	public void setCanal(CanalDTO canal) {
		this.canal = canal;
	}

	/**
	 * @return the canal_virtual
	 */
	public CanalVirtualDTO getCanal_virtual() {
		return canal_virtual;
	}

	/**
	 * @param canal_virtual the canal_virtual to set
	 */
	public void setCanal_virtual(CanalVirtualDTO canal_virtual) {
		this.canal_virtual = canal_virtual;
	}

	/**
	 * @return the cobertura
	 */
	public List<CoberturaDTO> getCobertura() {
		return cobertura;
	}

	/**
	 * @param cobertura the cobertura to set
	 */
	public void setCobertura(List<CoberturaDTO> cobertura) {
		this.cobertura = cobertura;
	}

	/**
	 * @return the formulario
	 */
	public List<PreguntaDTO> getFormulario() {
		return formulario;
	}

	/**
	 * @param formulario the formulario to set
	 */
	public void setFormulario(List<PreguntaDTO> formulario) {
		this.formulario = formulario;
	}

	/**
	 * @return the multiprograma
	 */
	public MultiprogramacionDTO getMultiprograma() {
		return multiprograma;
	}

	/**
	 * @param multiprograma the multiprograma to set
	 */
	public void setMultiprograma(MultiprogramacionDTO multiprograma) {
		this.multiprograma = multiprograma;
	}
		
}
