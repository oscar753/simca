/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class CoberturaRadioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7149975955277328825L;
	
	private String folioElectronico;
	
	private int idSenial;

	private CatalogoDTO estado;
	
	private CatalogoDTO municipio;

	public String getFolioElectronico() {
		return folioElectronico;
	}

	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}

	public int getIdSenial() {
		return idSenial;
	}

	public void setIdSenial(int idSenial) {
		this.idSenial = idSenial;
	}

	public CatalogoDTO getEstado() {
		return estado;
	}

	public void setEstado(CatalogoDTO estado) {
		this.estado = estado;
	}

	public CatalogoDTO getMunicipio() {
		return municipio;
	}

	public void setMunicipio(CatalogoDTO municipio) {
		this.municipio = municipio;
	}

	
}
