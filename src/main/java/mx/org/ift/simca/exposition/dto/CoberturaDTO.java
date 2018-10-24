/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class CoberturaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1269755983930323204L;

	private CatalogoDTO estado;
	
	private CatalogoDTO municipio;

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
