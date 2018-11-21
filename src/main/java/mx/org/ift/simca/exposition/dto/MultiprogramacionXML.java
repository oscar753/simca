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
@XmlRootElement(name = "XML")
@XmlType(propOrder = { "canal", "canal_virtual", "coberturas", "multiprograma", "formulario" })
public class MultiprogramacionXML implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7575685053213237536L;
	
	@XmlElement(name="CANAL")
	private CanalDTO canal = new CanalDTO();
	
	@XmlElement(name="CANAL_VIRTUAL")
	private CanalVirtualDTO canal_virtual = new CanalVirtualDTO();
	
	@XmlElement(name="MULTIPROGRAMA")
	private MultiprogramacionDTO multiprograma = new MultiprogramacionDTO();

	@XmlElement(name="COBERTURAS")
	private CoberturasXMLDTO coberturas = new CoberturasXMLDTO();
	
	@XmlElement(name="FORMULARIO")
	private FormularioXMLDTO formulario = new FormularioXMLDTO();

	/**
	 * @return the canal
	 */
	public CanalDTO getCanal() {
		return canal;
	}

	/**
	 * @param canal the canal to set
	 */
	public void setCanal(CanalDTO canal) {
		this.canal = canal;
	}

	/**
	 * @return the canal_virtual
	 */
	public CanalVirtualDTO getCanal_virtual() {
		return canal_virtual;
	}

	/**
	 * @param canal_virtual the canal_virtual to set
	 */
	public void setCanal_virtual(CanalVirtualDTO canal_virtual) {
		this.canal_virtual = canal_virtual;
	}	

	/**
	 * @return the coberturas
	 */
	public CoberturasXMLDTO getCoberturas() {
		return coberturas;
	}

	/**
	 * @param coberturas the cobertura to set
	 */
	public void setCoberturas(CoberturasXMLDTO coberturas) {
		this.coberturas = coberturas;
	}

	/**
	 * @return the multiprograma
	 */
	public MultiprogramacionDTO getMultiprograma() {
		return multiprograma;
	}

	/**
	 * @param multiprograma the multiprograma to set
	 */
	public void setMultiprograma(MultiprogramacionDTO multiprograma) {
		this.multiprograma = multiprograma;
	}

	/**
	 * @return the formulario
	 */
	public FormularioXMLDTO getFormulario() {
		return formulario;
	}

	/**
	 * @param formulario the formulario to set
	 */
	public void setFormulario(FormularioXMLDTO formulario) {
		this.formulario = formulario;
	}
	
		
}
