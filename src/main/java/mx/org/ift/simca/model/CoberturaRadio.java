package mx.org.ift.simca.model;

import java.io.Serializable;

public class CoberturaRadio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6920101346898007179L;
	private String folioElectronico;
	private Integer idSenial;
	private Integer idEstado;
	private Integer idPoblacion;
	
	public String getFolioElectronico() {
		return folioElectronico;
	}
	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}
	public Integer getIdSenial() {
		return idSenial;
	}
	public void setIdSenial(Integer idSenial) {
		this.idSenial = idSenial;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public Integer getIdPoblacion() {
		return idPoblacion;
	}
	public void setIdPoblacion(Integer idPoblacion) {
		this.idPoblacion = idPoblacion;
	}
	
}
