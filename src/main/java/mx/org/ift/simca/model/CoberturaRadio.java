package mx.org.ift.simca.model;

import java.io.Serializable;

public class CoberturaRadio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6920101346898007179L;
	private String folioElectronico;
	private Integer idSenial;
	private Estado estado;
	private Poblacion poblacion;
	
	public String getFolioElectronico() {
		return folioElectronico;
	}
	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}
	public Integer getIdSenial() {
		return idSenial;
	}
	public void setIdSenial(Integer idSenial) {
		this.idSenial = idSenial;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Poblacion getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}
	
}
