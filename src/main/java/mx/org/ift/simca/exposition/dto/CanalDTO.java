/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author KODE-LAP0077
 *
 */
@XmlRootElement(name="CANAL")
@XmlType(propOrder = { "id_canal", "identificador", "distintivo", "estado", "poblacion", "concesionario", "grupo" })
public class CanalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7740266554904324535L;

	@XmlElement(name="ID_CANAL")
	private String id_canal;
	
	@XmlElement(name="IDENTIFICADOR")
	private String identificador;	
	
	@XmlElement(name="DISTINTIVO")
	private String distintivo;
	
	@XmlElement(name="ESTADO")
	private String estado;
	
	@XmlElement(name="POBLACION")
	private String poblacion;
	
	@XmlElement(name="CONCESIONARIO")
	private String concesionario;
	
	@XmlElement(name="GRUPO")
	private String grupo;
	
	
	
	/**
	 * @return the id_canal
	 */
	public String getId_canal() {
		return id_canal;
	}
	/**
	 * @param id_canal the id_canal to set
	 */
	public void setId_canal(String id_canal) {
		this.id_canal = id_canal;
	}
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
	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}
	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}	
		
}
