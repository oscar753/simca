/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author KODE-LAP0077
 *
 */
public class CanalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7740266554904324535L;

	private String identificador;
	private String folioRPC;
	private String distintivo;
	private CatalogoDTO poblacion;
	private CatalogoDTO estado;
	private CatalogoDTO concesionario;
	List<CoberturaDTO> coberturas;
	
		
	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}
	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	/**
	 * @return the folioRPC
	 */
	public String getFolioRPC() {
		return folioRPC;
	}
	/**
	 * @param folioRPC the folioRPC to set
	 */
	public void setFolioRPC(String folioRPC) {
		this.folioRPC = folioRPC;
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
	 * @return the poblacion
	 */
	public CatalogoDTO getPoblacion() {
		return poblacion;
	}
	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(CatalogoDTO poblacion) {
		this.poblacion = poblacion;
	}
	/**
	 * @return the estado
	 */
	public CatalogoDTO getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(CatalogoDTO estado) {
		this.estado = estado;
	}
	/**
	 * @return the concesionario
	 */
	public CatalogoDTO getConcesionario() {
		return concesionario;
	}
	/**
	 * @param concesionario the concesionario to set
	 */
	public void setConcesionario(CatalogoDTO concesionario) {
		this.concesionario = concesionario;
	}
	/**
	 * @return the coberturas
	 */
	public List<CoberturaDTO> getCoberturas() {
		return coberturas;
	}
	/**
	 * @param coberturas the coberturas to set
	 */
	public void setCoberturas(List<CoberturaDTO> coberturas) {
		this.coberturas = coberturas;
	}
	
		
}
