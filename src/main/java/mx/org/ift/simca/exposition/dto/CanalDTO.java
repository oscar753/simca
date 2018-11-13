/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author KODE-LAP0077
 *
 */
@XmlRootElement(name="CANAL")
@XmlType(propOrder = { "identificador", "no_canal", "distintivo", "estado", "poblacion", "concesionario" })
public class CanalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7740266554904324535L;

	private String identificador;
	private String no_canal;
	private String distintivo;
	private String estado;
	private String poblacion;
	private String concesionario;
	
	
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
	 * @return the no_canal
	 */
	public String getNo_canal() {
		return no_canal;
	}
	/**
	 * @param no_canal the no_canal to set
	 */
	public void setNo_canal(String no_canal) {
		this.no_canal = no_canal;
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}
	/**
	 * @param poblacion the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	/**
	 * @return the concesionario
	 */
	public String getConcesionario() {
		return concesionario;
	}
	/**
	 * @param concesionario the concesionario to set
	 */
	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}
		
}
