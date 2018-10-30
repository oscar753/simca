package mx.org.ift.simca.model;

import java.io.Serializable;

public class CanalVirtual implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4007373906429277261L;

	private Integer idCanal;
	private Double idCanalVirtual;
	private Integer numCanalVirtual;
	private String programacion;
	private Canal canal;
	private TipoUso tipoUso;
	
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
	 * @return the idCanalVirtual
	 */
	public Double getIdCanalVirtual() {
		return idCanalVirtual;
	}
	/**
	 * @param idCanalVirtual the idCanalVirtual to set
	 */
	public void setIdCanalVirtual(Double idCanalVirtual) {
		this.idCanalVirtual = idCanalVirtual;
	}
	/**
	 * @return the numCanalVirtual
	 */
	public Integer getNumCanalVirtual() {
		return numCanalVirtual;
	}
	/**
	 * @param numCanalVirtual the numCanalVirtual to set
	 */
	public void setNumCanalVirtual(Integer numCanalVirtual) {
		this.numCanalVirtual = numCanalVirtual;
	}
	/**
	 * @return the programacion
	 */
	public String getProgramacion() {
		return programacion;
	}
	/**
	 * @param programacion the programacion to set
	 */
	public void setProgramacion(String programacion) {
		this.programacion = programacion;
	}
	/**
	 * @return the tipoUso
	 */
	public TipoUso getTipoUso() {
		return tipoUso;
	}
	/**
	 * @param tipoUso the tipoUso to set
	 */
	public void setTipoUso(TipoUso tipoUso) {
		this.tipoUso = tipoUso;
	}
	/**
	 * @return the canal
	 */
	public Canal getCanal() {
		return canal;
	}
	/**
	 * @param canal the canal to set
	 */
	public void setCanal(Canal canal) {
		this.canal = canal;
	}
		
}
