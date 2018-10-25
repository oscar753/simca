package mx.org.ift.simca.model;

import java.io.Serializable;

public class Clase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4028325849202258190L;

	private Integer idClase;
	private String clase;
	
	public Integer getIdClase() {
		return idClase;
	}
	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	
}
