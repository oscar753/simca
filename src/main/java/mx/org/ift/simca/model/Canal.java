/**
 * 
 */
package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * @author cesar.agustin
 *
 */
public class Canal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7740266554904324535L;

	private Long idCanal;
	
	private String folioRFC;
	
	private String distintivo;
	
	private Long idEstado;
	
	private Long idPoblacion;
	
	private Long idConcesionario;
	
	private String grupo;
	
	private Poblacion poblacion;
	
	private Estado estado;
	
	private Concesionario concesionario;

	/**
	 * @return the idCanal
	 */
	public Long getIdCanal() {
		return idCanal;
	}

	/**
	 * @param idCanal the idCanal to set
	 */
	public void setIdCanal(Long idCanal) {
		this.idCanal = idCanal;
	}

	/**
	 * @return the folioRFC
	 */
	public String getFolioRFC() {
		return folioRFC;
	}

	/**
	 * @param folioRFC the folioRFC to set
	 */
	public void setFolioRFC(String folioRFC) {
		this.folioRFC = folioRFC;
	}

	/**
	 * @return the distintivo
	 */
	public String getDistintivo() {
		return distintivo;
	}

	/**
	 * @param distintivo the distintivo to set
	 */
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}

	/**
	 * @return the idEstado
	 */
	public Long getIdEstado() {
		return idEstado;
	}

	/**
	 * @param idEstado the idEstado to set
	 */
	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	/**
	 * @return the idPoblacion
	 */
	public Long getIdPoblacion() {
		return idPoblacion;
	}

	/**
	 * @param idPoblacion the idPoblacion to set
	 */
	public void setIdPoblacion(Long idPoblacion) {
		this.idPoblacion = idPoblacion;
	}

	/**
	 * @return the idConcesionario
	 */
	public Long getIdConcesionario() {
		return idConcesionario;
	}

	/**
	 * @param idConcesionario the idConcesionario to set
	 */
	public void setIdConcesionario(Long idConcesionario) {
		this.idConcesionario = idConcesionario;
	}

	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the poblacion
	 */
	public Poblacion getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the concesionario
	 */
	public Concesionario getConcesionario() {
		return concesionario;
	}

	/**
	 * @param concesionario the concesionario to set
	 */
	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}
	
	
}
