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
@XmlRootElement(name="CANAL_VIRTUAL")
@XmlType(propOrder = {"no_canal_virtual", "id_senial", "folio_rpc_umca", "tipo_uso", "contenido", "canal_digital", "canal_virtual", 
		"programacion", "mc_mo", "medio", "primer_asignacion", "logo_b64"})
public class CanalVirtualDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2574044121673824329L;
	
	@XmlElement(name="NO_CANAL_VIRTUAL")
	private String no_canal_virtual;
	
	@XmlElement(name="ID_SENIAL")
	private String id_senial;	
	
	@XmlElement(name="FOLIO_RPC_UMCA")
	private String folio_rpc_umca;	
	
	@XmlElement(name="TIPO_USO")
	private String tipo_uso;	
	
	@XmlElement(name="CONTENIDO")
	private String contenido;	
	
	@XmlElement(name="CANAL_DIGITAL")
	private String canal_digital;	
	
	@XmlElement(name="CANAL_VIRTUAL")
	private String canal_virtual;	
	
	@XmlElement(name="PROGRAMACION")
	private String programacion;	
	
	@XmlElement(name="MC_MO")
	private String mc_mo;	
	
	@XmlElement(name="MEDIO")
	private String medio;	
	
	@XmlElement(name="PRIMER_ASIGNACION")
	private String primer_asignacion;	
	
	@XmlElement(name="LOGO_B64")
	private String logo_b64;
	
		
	/**
	 * @return the no_canal_virtual
	 */
	public String getNo_canal_virtual() {
		return no_canal_virtual;
	}

	/**
	 * @param no_canal_virtual the no_canal_virtual to set
	 */
	public void setNo_canal_virtual(String no_canal_virtual) {
		this.no_canal_virtual = no_canal_virtual;
	}

	/**
	 * @return the id_senial
	 */
	public String getId_senial() {
		return id_senial;
	}

	/**
	 * @param id_senial the id_senial to set
	 */
	public void setId_senial(String id_senial) {
		this.id_senial = id_senial;
	}

	/**
	 * @return the folio_rpc_umca
	 */
	public String getFolio_rpc_umca() {
		return folio_rpc_umca;
	}

	/**
	 * @param folio_rpc_umca the folio_rpc_umca to set
	 */
	public void setFolio_rpc_umca(String folio_rpc_umca) {
		this.folio_rpc_umca = folio_rpc_umca;
	}

	/**
	 * @return the tipo_uso
	 */
	public String getTipo_uso() {
		return tipo_uso;
	}

	/**
	 * @param tipo_uso the tipo_uso to set
	 */
	public void setTipo_uso(String tipo_uso) {
		this.tipo_uso = tipo_uso;
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	/**
	 * @return the canal_digital
	 */
	public String getCanal_digital() {
		return canal_digital;
	}

	/**
	 * @param canal_digital the canal_digital to set
	 */
	public void setCanal_digital(String canal_digital) {
		this.canal_digital = canal_digital;
	}

	/**
	 * @return the canal_virtual
	 */
	public String getCanal_virtual() {
		return canal_virtual;
	}

	/**
	 * @param canal_virtual the canal_virtual to set
	 */
	public void setCanal_virtual(String canal_virtual) {
		this.canal_virtual = canal_virtual;
	}

	/**
	 * @return the programacion
	 */
	public String getProgramacion() {
		return programacion;
	}

	/**
	 * @param programacion the programacion to set
	 */
	public void setProgramacion(String programacion) {
		this.programacion = programacion;
	}

	/**
	 * @return the mc_mo
	 */
	public String getMc_mo() {
		return mc_mo;
	}

	/**
	 * @param mc_mo the mc_mo to set
	 */
	public void setMc_mo(String mc_mo) {
		this.mc_mo = mc_mo;
	}

	/**
	 * @return the medio
	 */
	public String getMedio() {
		return medio;
	}

	/**
	 * @param medio the medio to set
	 */
	public void setMedio(String medio) {
		this.medio = medio;
	}

	/**
	 * @return the primer_asignacion
	 */
	public String getPrimer_asignacion() {
		return primer_asignacion;
	}

	/**
	 * @param primer_asignacion the primer_asignacion to set
	 */
	public void setPrimer_asignacion(String primer_asignacion) {
		this.primer_asignacion = primer_asignacion;
	}

	/**
	 * @return the logo_b64
	 */
	public String getLogo_b64() {
		return logo_b64;
	}

	/**
	 * @param logo_b64 the logo_b64 to set
	 */
	public void setLogo_b64(String logo_b64) {
		this.logo_b64 = logo_b64;
	}
	
		
}
