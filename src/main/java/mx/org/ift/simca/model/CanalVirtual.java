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
	private String folioRpcUmca;
	private Integer canalDigital;
	private Double canalAsignado;
	private String programacion;
	private String mcMo;
	private Integer idTipoUso;
	private Integer idTipoContenido;
	private Integer idCalidad;
	private String multiprogramacion;
	private Double tasaTrans;
	private Integer idMetodoCompr;
	private Integer idTerceroBenef;
	private Integer idEstatus;
	private String primerAsignacion;
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
	/**
	 * @return the folioRpcUmca
	 */
	public String getFolioRpcUmca() {
		return folioRpcUmca;
	}
	/**
	 * @param folioRpcUmca the folioRpcUmca to set
	 */
	public void setFolioRpcUmca(String folioRpcUmca) {
		this.folioRpcUmca = folioRpcUmca;
	}
	/**
	 * @return the canalDigital
	 */
	public Integer getCanalDigital() {
		return canalDigital;
	}
	/**
	 * @param canalDigital the canalDigital to set
	 */
	public void setCanalDigital(Integer canalDigital) {
		this.canalDigital = canalDigital;
	}
	/**
	 * @return the canalAsignado
	 */
	public Double getCanalAsignado() {
		return canalAsignado;
	}
	/**
	 * @param canalAsignado the canalAsignado to set
	 */
	public void setCanalAsignado(Double canalAsignado) {
		this.canalAsignado = canalAsignado;
	}
	/**
	 * @return the mcMo
	 */
	public String getMcMo() {
		return mcMo;
	}
	/**
	 * @param mcMo the mcMo to set
	 */
	public void setMcMo(String mcMo) {
		this.mcMo = mcMo;
	}
	/**
	 * @return the idTipoUso
	 */
	public Integer getIdTipoUso() {
		return idTipoUso;
	}
	/**
	 * @param idTipoUso the idTipoUso to set
	 */
	public void setIdTipoUso(Integer idTipoUso) {
		this.idTipoUso = idTipoUso;
	}
	/**
	 * @return the idTipoContenido
	 */
	public Integer getIdTipoContenido() {
		return idTipoContenido;
	}
	/**
	 * @param idTipoContenido the idTipoContenido to set
	 */
	public void setIdTipoContenido(Integer idTipoContenido) {
		this.idTipoContenido = idTipoContenido;
	}
	/**
	 * @return the idCalidad
	 */
	public Integer getIdCalidad() {
		return idCalidad;
	}
	/**
	 * @param idCalidad the idCalidad to set
	 */
	public void setIdCalidad(Integer idCalidad) {
		this.idCalidad = idCalidad;
	}
	/**
	 * @return the multiprogramacion
	 */
	public String getMultiprogramacion() {
		return multiprogramacion;
	}
	/**
	 * @param multiprogramacion the multiprogramacion to set
	 */
	public void setMultiprogramacion(String multiprogramacion) {
		this.multiprogramacion = multiprogramacion;
	}
	/**
	 * @return the tasaTrans
	 */
	public Double getTasaTrans() {
		return tasaTrans;
	}
	/**
	 * @param tasaTrans the tasaTrans to set
	 */
	public void setTasaTrans(Double tasaTrans) {
		this.tasaTrans = tasaTrans;
	}
	/**
	 * @return the idMetodoCompr
	 */
	public Integer getIdMetodoCompr() {
		return idMetodoCompr;
	}
	/**
	 * @param idMetodoCompr the idMetodoCompr to set
	 */
	public void setIdMetodoCompr(Integer idMetodoCompr) {
		this.idMetodoCompr = idMetodoCompr;
	}
	/**
	 * @return the idTerceroBenef
	 */
	public Integer getIdTerceroBenef() {
		return idTerceroBenef;
	}
	/**
	 * @param idTerceroBenef the idTerceroBenef to set
	 */
	public void setIdTerceroBenef(Integer idTerceroBenef) {
		this.idTerceroBenef = idTerceroBenef;
	}
	/**
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}
	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}
	/**
	 * @return the primerAsignacion
	 */
	public String getPrimerAsignacion() {
		return primerAsignacion;
	}
	/**
	 * @param primerAsignacion the primerAsignacion to set
	 */
	public void setPrimerAsignacion(String primerAsignacion) {
		this.primerAsignacion = primerAsignacion;
	}
	
}
