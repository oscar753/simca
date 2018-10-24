/**
 * 
 */
package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;

/**
 * @author KODE-LAP0077
 *
 */
public class CanalVirtualDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2574044121673824329L;
	
	private String identificador;
	private String numero;
	private String folioRPCUMCA;	
	private String programacion;
	private String multiprograma;
	private String mcmo;
	private String canalVirtual;
	
	private String chd;
	private String idSenial;
	private String grupo;	
	private String medio;	
	
	private CatalogoDTO contenido;
	private CatalogoDTO tipoUso;
	private CanalDTO canal;
	
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
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	/**
	 * @return the folioRPCUMCA
	 */
	public String getFolioRPCUMCA() {
		return folioRPCUMCA;
	}
	/**
	 * @param folioRPCUMCA the folioRPCUMCA to set
	 */
	public void setFolioRPCUMCA(String folioRPCUMCA) {
		this.folioRPCUMCA = folioRPCUMCA;
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
	 * @return the mcmo
	 */
	public String getMcmo() {
		return mcmo;
	}
	/**
	 * @param mcmo the mcmo to set
	 */
	public void setMcmo(String mcmo) {
		this.mcmo = mcmo;
	}
	/**
	 * @return the canalVirtual
	 */
	public String getCanalVirtual() {
		return canalVirtual;
	}
	/**
	 * @param canalVirtual the canalVirtual to set
	 */
	public void setCanalVirtual(String canalVirtual) {
		this.canalVirtual = canalVirtual;
	}
	/**
	 * @return the chd
	 */
	public String getChd() {
		return chd;
	}
	/**
	 * @param chd the chd to set
	 */
	public void setChd(String chd) {
		this.chd = chd;
	}
	/**
	 * @return the idSenial
	 */
	public String getIdSenial() {
		return idSenial;
	}
	/**
	 * @param idSenial the idSenial to set
	 */
	public void setIdSenial(String idSenial) {
		this.idSenial = idSenial;
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
	 * @return the contenido
	 */
	public CatalogoDTO getContenido() {
		return contenido;
	}
	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(CatalogoDTO contenido) {
		this.contenido = contenido;
	}
	/**
	 * @return the tipoUso
	 */
	public CatalogoDTO getTipoUso() {
		return tipoUso;
	}
	/**
	 * @param tipoUso the tipoUso to set
	 */
	public void setTipoUso(CatalogoDTO tipoUso) {
		this.tipoUso = tipoUso;
	}
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

}
