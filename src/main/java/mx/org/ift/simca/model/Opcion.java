package mx.org.ift.simca.model;

import java.io.Serializable;

public class Opcion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1579813676208514253L;
	private Integer idOpcion;
	private String opcion;
	
	public Integer getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

}
