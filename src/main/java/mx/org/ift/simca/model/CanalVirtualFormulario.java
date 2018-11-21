/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class CanalVirtualFormulario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1147806736248131855L;
	
	private Integer idCanal;
	private Integer idPregunta;
	private Integer idCanalVirtual;
	private Integer idTipoFormulario;
	private String valor;
	
	/**
	 * @return the idCanal
	 */
	public Integer getIdCanal() {
		return idCanal;
	}
	/**
	 * @param idCanal the idCanal to set
	 */
	public void setIdCanal(Integer idCanal) {
		this.idCanal = idCanal;
	}
	/**
	 * @return the idPregunta
	 */
	public Integer getIdPregunta() {
		return idPregunta;
	}
	/**
	 * @param idPregunta the idPregunta to set
	 */
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
	/**
	 * @return the idCanalVirtual
	 */
	public Integer getIdCanalVirtual() {
		return idCanalVirtual;
	}
	/**
	 * @param idCanalVirtual the idCanalVirtual to set
	 */
	public void setIdCanalVirtual(Integer idCanalVirtual) {
		this.idCanalVirtual = idCanalVirtual;
	}
	/**
	 * @return the idTipoFormulario
	 */
	public Integer getIdTipoFormulario() {
		return idTipoFormulario;
	}
	/**
	 * @param idTipoFormulario the idTipoFormulario to set
	 */
	public void setIdTipoFormulario(Integer idTipoFormulario) {
		this.idTipoFormulario = idTipoFormulario;
	}
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
