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
@XmlRootElement(name = "MULTIPROGRAMACION")
@XmlType(propOrder = { "multiprograma", "calidad", "tasa_transf", "metodoCompr", "terceroBenf", "estatus" })
public class MultiprogramacionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5325040789005075382L;
	
	private String multiprograma;
	private String calidad;
	private String tasa_transf;
	private String metodoCompr;
	private String terceroBenf;
	private String estatus;
	
	
	/**
	 * @return the multiprograma
	 */
	public String getMultiprograma() {
		return multiprograma;
	}
	/**
	 * @param multiprograma the multiprograma to set
	 */
	public void setMultiprograma(String multiprograma) {
		this.multiprograma = multiprograma;
	}
	/**
	 * @return the calidad
	 */
	public String getCalidad() {
		return calidad;
	}
	/**
	 * @param calidad the calidad to set
	 */
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	/**
	 * @return the tasa_transf
	 */
	public String getTasa_transf() {
		return tasa_transf;
	}
	/**
	 * @param tasa_transf the tasa_transf to set
	 */
	public void setTasa_transf(String tasa_transf) {
		this.tasa_transf = tasa_transf;
	}
	/**
	 * @return the metodoCompr
	 */
	public String getMetodoCompr() {
		return metodoCompr;
	}
	/**
	 * @param metodoCompr the metodoCompr to set
	 */
	public void setMetodoCompr(String metodoCompr) {
		this.metodoCompr = metodoCompr;
	}
	/**
	 * @return the terceroBenf
	 */
	public String getTerceroBenf() {
		return terceroBenf;
	}
	/**
	 * @param terceroBenf the terceroBenf to set
	 */
	public void setTerceroBenf(String terceroBenf) {
		this.terceroBenf = terceroBenf;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
}
