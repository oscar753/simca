package mx.org.ift.simca.model;

import java.io.Serializable;

public class GrupoRadio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7483312993795413864L;
	
	private String folioElectronico;
	private Concesionario concesionario;
	
	public String getFolioElectronico() {
		return folioElectronico;
	}
	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}
	public Concesionario getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}
	
}
