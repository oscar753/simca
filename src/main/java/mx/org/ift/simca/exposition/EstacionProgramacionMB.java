package mx.org.ift.simca.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.exposition.dto.EstacionDTO;
import mx.org.ift.simca.exposition.dto.GrupoRadioDTO;
import mx.org.ift.simca.model.Estacion;
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
	
	private String distintivo;
	private String concesionario;
	private String canalProg;
	private List<EstacionDTO> estacionDTOList = new ArrayList<EstacionDTO>();
	
	@PostConstruct
	public void init() {
		estacionDTOList.clear();
		canalProg = "Prueba";
	}
	
	public void limpiar() {
		canalProg = "";
		concesionario = "";
		distintivo = "";
	}
	
	public void buscarEstacion() {				
		List<Estacion> estacionBD = estacionService.buscarEstacionProgramacion(distintivo, concesionario, canalProg);
		estacionDTOList.clear();
		
		if  (estacionBD.isEmpty()) {
			LOG.info("No se encontraron estaciones");
		}
		else {
			EstacionDTO itemEstacionDTO = new EstacionDTO();
			CatalogoDTO catalogoDTO = new CatalogoDTO();
			GrupoRadioDTO grupoRadio = new GrupoRadioDTO();
			
			for (Estacion estacion : estacionBD) {
				itemEstacionDTO = new EstacionDTO();
				grupoRadio = new GrupoRadioDTO();
				
				itemEstacionDTO.setDistintivo(StringUtils.isNotBlank(estacion.getDistintivo())?estacion.getDistintivo():"");
				
				catalogoDTO.setDescripcion(StringUtils.isNotBlank(estacion.getPoblacion())?estacion.getPoblacion():"");
				itemEstacionDTO.setPoblacion(catalogoDTO);
				
				catalogoDTO = new CatalogoDTO();
				catalogoDTO.setDescripcion(StringUtils.isNotBlank(estacion.getEstado())?estacion.getEstado():"");
				itemEstacionDTO.setEstado(catalogoDTO);
				
				catalogoDTO = new CatalogoDTO();
				catalogoDTO.setDescripcion(StringUtils.isNotBlank(estacion.getTipoUsoEstacion())?estacion.getTipoUsoEstacion():"");
				itemEstacionDTO.setTipoUsoEstacion(catalogoDTO);
				
				catalogoDTO = new CatalogoDTO();
				catalogoDTO.setDescripcion(StringUtils.isNotBlank(estacion.getConcesionario())?estacion.getConcesionario():"");
				grupoRadio.setConcesionario(catalogoDTO);
				itemEstacionDTO.setGrupoRadio(grupoRadio);
				
				catalogoDTO = new CatalogoDTO();
				catalogoDTO.setDescripcion(StringUtils.isNotBlank(estacion.getBanda())?estacion.getBanda():"");
				itemEstacionDTO.setBanda(catalogoDTO);
				
				itemEstacionDTO.setFrecuencia(estacion.getFrecuencia() != null ? estacion.getFrecuencia().toString():"");
				
				estacionDTOList.add(itemEstacionDTO);
			}
		}
		
		
		System.out.println("FINALIZO");
		
		
	}

	public String getDistintivo() {
		return distintivo;
	}

	public void setDistintivo(String distintivo) {
		this.distintivo = distintivo;
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

	public List<EstacionDTO> getEstacionDTOList() {
		return estacionDTOList;
	}

	public void setEstacionDTOList(List<EstacionDTO> estacionDTOList) {
		this.estacionDTOList = estacionDTOList;
	}
	
}
