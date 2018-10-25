package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

public class GrupoRadioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 86356278405807545L;

	private String folioElectronico;
	private CatalogoDTO concesionario;
	
	public String getFolioElectronico() {
		return folioElectronico;
	}
	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}
	public CatalogoDTO getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(CatalogoDTO concesionario) {
		this.concesionario = concesionario;
	}
	
}
