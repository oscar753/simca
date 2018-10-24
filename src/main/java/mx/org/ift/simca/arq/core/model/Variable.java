package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

public class Variable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4227194539330436183L;
	
	private String identificador;
	private String descripcion;
	
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
