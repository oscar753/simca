package mx.org.ift.simca.exposition.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.org.ift.simca.model.Opcion;

public class EstacionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6483812316190512455L;

	private String numero;
	private String folioRPCUMCA;
	private CatalogoDTO estado;
	private CatalogoDTO poblacion;
	private CatalogoDTO tipoClase;
	private CatalogoDTO tipoUsoEstacion;
	private String concesionario;
	private String distintivo;
	private CatalogoDTO banda;
	private String frecuenciaAM;
	private String frecuenciaFM;
	private Date vigenciaIni;
	private Date vigenciaFin;
	private CatalogoDTO tipoFrecuencia;
	
	private Integer item;

	private Integer idEstado;
	private Integer idPoblacion;
	private Integer idClase;
	private Integer idTipoUsoEstacion;
	private Integer idBanda;
	private Integer idTipoFrecuencia;

	private Integer idTipoEstacion;
	private Integer idMultiprograma;
	private Integer idBDINE;
	private Integer idBdAuditsa;
	private Integer idMonitoreoServExt;
	private Integer idProgramInfantil;
	private Integer idObligAcces;
	private Integer idMecanAcces;
	private Integer idMedioPublico;
	
	private Date centroVMINE;
	private String nomCanalINE;
	private String nomComINE;
	private String tipoMonitoreoINE;
	private String nombreCanalAuditsa;
	private String programAuditsa;
	private String localidadAuditsa;
	private String locServExt;
	private String nomComServExt;
	private String instPubFed;
	
	private GrupoRadioDTO grupoRadio;
	
	private List<CoberturaRadioDTO> coberturasRadioDTO = new ArrayList<CoberturaRadioDTO>();
	private EstacionFormularioDTO estacionFormularioDTO;
	
	private List<Opcion> opTipoEstacion = new ArrayList<Opcion>();
	private String nombreProgramacion;
	private List<Opcion> opMultiprograma = new ArrayList<Opcion>();
	private List<Opcion> opBDINE = new ArrayList<Opcion>();
	private List<Opcion> opBdAuditsa= new ArrayList<Opcion>();
	private List<Opcion> opMonitoreoServExt = new ArrayList<Opcion>();
	private List<Opcion> opProgramInfantil = new ArrayList<Opcion>();
	private List<Opcion> opObligAcces = new ArrayList<Opcion>();
	private List<Opcion> opMecanAcces= new ArrayList<Opcion>();
	private List<Opcion> opMedioPublico = new ArrayList<Opcion>();
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getFolioRPCUMCA() {
		return folioRPCUMCA;
	}
	public void setFolioRPCUMCA(String folioRPCUMCA) {
		this.folioRPCUMCA = folioRPCUMCA;
	}
	public CatalogoDTO getEstado() {
		return estado;
	}
	public void setEstado(CatalogoDTO estado) {
		this.estado = estado;
	}
	public CatalogoDTO getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(CatalogoDTO poblacion) {
		this.poblacion = poblacion;
	}
	public CatalogoDTO getTipoClase() {
		return tipoClase;
	}
	public void setTipoClase(CatalogoDTO tipoClase) {
		this.tipoClase = tipoClase;
	}
	public CatalogoDTO getTipoUsoEstacion() {
		return tipoUsoEstacion;
	}
	public void setTipoUsoEstacion(CatalogoDTO tipoUsoEstacion) {
		this.tipoUsoEstacion = tipoUsoEstacion;
	}
	public String getConcesionario() {
		return concesionario;
	}
	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}
	public String getDistintivo() {
		return distintivo;
	}
	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
	}
	public CatalogoDTO getBanda() {
		return banda;
	}
	public void setBanda(CatalogoDTO banda) {
		this.banda = banda;
	}
	public String getFrecuenciaAM() {
		return frecuenciaAM;
	}
	public void setFrecuenciaAM(String frecuenciaAM) {
		this.frecuenciaAM = frecuenciaAM;
	}
	public String getFrecuenciaFM() {
		return frecuenciaFM;
	}
	public void setFrecuenciaFM(String frecuenciaFM) {
		this.frecuenciaFM = frecuenciaFM;
	}
	public Date getVigenciaIni() {
		return vigenciaIni;
	}
	public void setVigenciaIni(Date vigenciaIni) {
		this.vigenciaIni = vigenciaIni;
	}
	public Date getVigenciaFin() {
		return vigenciaFin;
	}
	public void setVigenciaFin(Date vigenciaFin) {
		this.vigenciaFin = vigenciaFin;
	}
	public CatalogoDTO getTipoFrecuencia() {
		return tipoFrecuencia;
	}
	public void setTipoFrecuencia(CatalogoDTO tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
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
	public Integer getIdClase() {
		return idClase;
	}
	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
	}
	public Integer getIdTipoUsoEstacion() {
		return idTipoUsoEstacion;
	}
	public void setIdTipoUsoEstacion(Integer idTipoUsoEstacion) {
		this.idTipoUsoEstacion = idTipoUsoEstacion;
	}
	public Integer getIdBanda() {
		return idBanda;
	}
	public void setIdBanda(Integer idBanda) {
		this.idBanda = idBanda;
	}
	public Integer getIdTipoFrecuencia() {
		return idTipoFrecuencia;
	}
	public void setIdTipoFrecuencia(Integer idTipoFrecuencia) {
		this.idTipoFrecuencia = idTipoFrecuencia;
	}
	public Integer getIdTipoEstacion() {
		return idTipoEstacion;
	}
	public void setIdTipoEstacion(Integer idTipoEstacion) {
		this.idTipoEstacion = idTipoEstacion;
	}
	public Integer getIdMultiprograma() {
		return idMultiprograma;
	}
	public void setIdMultiprograma(Integer idMultiprograma) {
		this.idMultiprograma = idMultiprograma;
	}
	public Integer getIdBDINE() {
		return idBDINE;
	}
	public void setIdBDINE(Integer idBDINE) {
		this.idBDINE = idBDINE;
	}
	public Integer getIdBdAuditsa() {
		return idBdAuditsa;
	}
	public void setIdBdAuditsa(Integer idBdAuditsa) {
		this.idBdAuditsa = idBdAuditsa;
	}
	public Integer getIdMonitoreoServExt() {
		return idMonitoreoServExt;
	}
	public void setIdMonitoreoServExt(Integer idMonitoreoServExt) {
		this.idMonitoreoServExt = idMonitoreoServExt;
	}
	public Integer getIdProgramInfantil() {
		return idProgramInfantil;
	}
	public void setIdProgramInfantil(Integer idProgramInfantil) {
		this.idProgramInfantil = idProgramInfantil;
	}
	public Integer getIdObligAcces() {
		return idObligAcces;
	}
	public void setIdObligAcces(Integer idObligAcces) {
		this.idObligAcces = idObligAcces;
	}
	public Integer getIdMecanAcces() {
		return idMecanAcces;
	}
	public void setIdMecanAcces(Integer idMecanAcces) {
		this.idMecanAcces = idMecanAcces;
	}
	public Integer getIdMedioPublico() {
		return idMedioPublico;
	}
	public void setIdMedioPublico(Integer idMedioPublico) {
		this.idMedioPublico = idMedioPublico;
	}
	public Date getCentroVMINE() {
		return centroVMINE;
	}
	public void setCentroVMINE(Date centroVMINE) {
		this.centroVMINE = centroVMINE;
	}
	public String getNomCanalINE() {
		return nomCanalINE;
	}
	public void setNomCanalINE(String nomCanalINE) {
		this.nomCanalINE = nomCanalINE;
	}
	public String getNomComINE() {
		return nomComINE;
	}
	public void setNomComINE(String nomComINE) {
		this.nomComINE = nomComINE;
	}
	public String getTipoMonitoreoINE() {
		return tipoMonitoreoINE;
	}
	public void setTipoMonitoreoINE(String tipoMonitoreoINE) {
		this.tipoMonitoreoINE = tipoMonitoreoINE;
	}
	public String getNombreCanalAuditsa() {
		return nombreCanalAuditsa;
	}
	public void setNombreCanalAuditsa(String nombreCanalAuditsa) {
		this.nombreCanalAuditsa = nombreCanalAuditsa;
	}
	public String getProgramAuditsa() {
		return programAuditsa;
	}
	public void setProgramAuditsa(String programAuditsa) {
		this.programAuditsa = programAuditsa;
	}
	public String getLocalidadAuditsa() {
		return localidadAuditsa;
	}
	public void setLocalidadAuditsa(String localidadAuditsa) {
		this.localidadAuditsa = localidadAuditsa;
	}
	public String getLocServExt() {
		return locServExt;
	}
	public void setLocServExt(String locServExt) {
		this.locServExt = locServExt;
	}
	public String getNomComServExt() {
		return nomComServExt;
	}
	public void setNomComServExt(String nomComServExt) {
		this.nomComServExt = nomComServExt;
	}
	public String getInstPubFed() {
		return instPubFed;
	}
	public void setInstPubFed(String instPubFed) {
		this.instPubFed = instPubFed;
	}
	public GrupoRadioDTO getGrupoRadio() {
		return grupoRadio;
	}
	public void setGrupoRadio(GrupoRadioDTO grupoRadio) {
		this.grupoRadio = grupoRadio;
	}
	public List<CoberturaRadioDTO> getCoberturasRadioDTO() {
		return coberturasRadioDTO;
	}
	public void setCoberturasRadioDTO(List<CoberturaRadioDTO> coberturasRadioDTO) {
		this.coberturasRadioDTO = coberturasRadioDTO;
	}
	public EstacionFormularioDTO getEstacionFormularioDTO() {
		return estacionFormularioDTO;
	}
	public void setEstacionFormularioDTO(EstacionFormularioDTO estacionFormularioDTO) {
		this.estacionFormularioDTO = estacionFormularioDTO;
	}
	public List<Opcion> getOpTipoEstacion() {
		return opTipoEstacion;
	}
	public void setOpTipoEstacion(List<Opcion> opTipoEstacion) {
		this.opTipoEstacion = opTipoEstacion;
	}
	public String getNombreProgramacion() {
		return nombreProgramacion;
	}
	public void setNombreProgramacion(String nombreProgramacion) {
		this.nombreProgramacion = nombreProgramacion;
	}
	public List<Opcion> getOpMultiprograma() {
		return opMultiprograma;
	}
	public void setOpMultiprograma(List<Opcion> opMultiprograma) {
		this.opMultiprograma = opMultiprograma;
	}
	public List<Opcion> getOpBDINE() {
		return opBDINE;
	}
	public void setOpBDINE(List<Opcion> opBDINE) {
		this.opBDINE = opBDINE;
	}
	public List<Opcion> getOpBdAuditsa() {
		return opBdAuditsa;
	}
	public void setOpBdAuditsa(List<Opcion> opBdAuditsa) {
		this.opBdAuditsa = opBdAuditsa;
	}
	public List<Opcion> getOpMonitoreoServExt() {
		return opMonitoreoServExt;
	}
	public void setOpMonitoreoServExt(List<Opcion> opMonitoreoServExt) {
		this.opMonitoreoServExt = opMonitoreoServExt;
	}
	public List<Opcion> getOpProgramInfantil() {
		return opProgramInfantil;
	}
	public void setOpProgramInfantil(List<Opcion> opProgramInfantil) {
		this.opProgramInfantil = opProgramInfantil;
	}
	public List<Opcion> getOpObligAcces() {
		return opObligAcces;
	}
	public void setOpObligAcces(List<Opcion> opObligAcces) {
		this.opObligAcces = opObligAcces;
	}
	public List<Opcion> getOpMecanAcces() {
		return opMecanAcces;
	}
	public void setOpMecanAcces(List<Opcion> opMecanAcces) {
		this.opMecanAcces = opMecanAcces;
	}
	public List<Opcion> getOpMedioPublico() {
		return opMedioPublico;
	}
	public void setOpMedioPublico(List<Opcion> opMedioPublico) {
		this.opMedioPublico = opMedioPublico;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	
}
