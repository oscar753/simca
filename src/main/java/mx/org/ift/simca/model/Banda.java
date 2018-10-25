package mx.org.ift.simca.model;

import java.io.Serializable;

public class Banda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4028325849202258190L;

	private Integer idBanda;
	private String banda;
	
	public Integer getIdBanda() {
		return idBanda;
	}
	public void setIdBanda(Integer idBanda) {
		this.idBanda = idBanda;
	}
	public String getBanda() {
		return banda;
	}
	public void setBanda(String banda) {
		this.banda = banda;
	}
	
}
