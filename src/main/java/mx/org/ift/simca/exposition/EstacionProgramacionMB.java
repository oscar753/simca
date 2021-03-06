package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.model.Estacion;
import mx.org.ift.simca.service.CatalogoService;
import mx.org.ift.simca.service.EstacionService;

@Controller
@Scope("session")
public class EstacionProgramacionMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4567893375079648053L;

	private static Logger LOG = LoggerFactory.getLogger(EstacionProgramacionMB.class);

	@Autowired
	private EstacionService estacionService;

	@Autowired
	private CatalogoService catalogoService;
	
	private String concesionario;
	private String canalProg;
	private String idConcesionario;
	private String idDistintivo;
	private String idCanalProg;
	private String folioElectronico;
	private List<Estacion> estacionBD = new ArrayList<Estacion>();
	private List<CatalogoDTO> concesionariosDTO = new ArrayList<CatalogoDTO>();
	private List<Estacion> distintivos = new ArrayList<Estacion>();
	
	@PostConstruct
	public void init() {
		estacionBD.clear();
		concesionariosDTO.clear();
		concesionariosDTO = catalogoService.consultaConcesionario();
		distintivos = estacionService.buscarDistintivosRadio();
	}

	public void limpiar() {
		canalProg = "";
		concesionario = "";
		idConcesionario = "";
		idDistintivo = "";
		folioElectronico = "";
	}

	public void buscarEstacion() {
		estacionBD.clear();
		estacionBD = estacionService.buscarEstacionProgramacion(idDistintivo, idConcesionario, folioElectronico);

		if (estacionBD.isEmpty())
			LOG.info("No se encontraron estaciones");

		System.out.println("FINALIZO");

	}
	
//	public String modificar(Estacion estacion) {
//		System.out.println("seteando estacion select");
//		this.setEstacionSelect(estacion);
//		System.out.println("modificar: " + estacion.getDistintivo());
//		
//		return "modificaEstacionProgramacion.xhtml";
//	}
	
	public void borrar(Estacion estacion) {
		System.out.println("borrar: " + estacion.getDistintivo());
	}

	public String getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}

	public String getCanalProg() {
		return canalProg;
	}

	public void setCanalProg(String canalProg) {
		this.canalProg = canalProg;
	}

	public List<Estacion> getEstacionBD() {
		return estacionBD;
	}

	public void setEstacionBD(List<Estacion> estacionBD) {
		this.estacionBD = estacionBD;
	}

	public List<CatalogoDTO> getConcesionariosDTO() {
		return concesionariosDTO;
	}

	public void setConcesionariosDTO(List<CatalogoDTO> concesionariosDTO) {
		this.concesionariosDTO = concesionariosDTO;
	}

	public String getIdConcesionario() {
		return idConcesionario;
	}

	public void setIdConcesionario(String idConcesionario) {
		this.idConcesionario = idConcesionario;
	}

	public List<Estacion> getDistintivos() {
		return distintivos;
	}

	public void setDistintivos(List<Estacion> distintivos) {
		this.distintivos = distintivos;
	}

	public String getIdDistintivo() {
		return idDistintivo;
	}

	public void setIdDistintivo(String idDistintivo) {
		this.idDistintivo = idDistintivo;
	}

	public String getIdCanalProg() {
		return idCanalProg;
	}

	public void setIdCanalProg(String idCanalProg) {
		this.idCanalProg = idCanalProg;
	}

	public String getFolioElectronico() {
		return folioElectronico;
	}

	public void setFolioElectronico(String folioElectronico) {
		this.folioElectronico = folioElectronico;
	}
}
