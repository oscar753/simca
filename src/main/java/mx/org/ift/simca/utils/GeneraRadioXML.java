package mx.org.ift.simca.utils;

import java.io.Serializable;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.org.ift.simca.exposition.AddEstacionProgramMB;
import mx.org.ift.simca.exposition.dto.CoberturaRadioDTO;
import mx.org.ift.simca.exposition.dto.CoberturaXMLDTO;
import mx.org.ift.simca.exposition.dto.EstacionDTO;
import mx.org.ift.simca.exposition.dto.EstacionXMLDTO;
import mx.org.ift.simca.exposition.dto.FormularioXMLDTO;
import mx.org.ift.simca.exposition.dto.GrupoRadioXMLDTO;
import mx.org.ift.simca.exposition.dto.PoblacionXMLDTO;
import mx.org.ift.simca.exposition.dto.PreguntaXMLDTO;
import mx.org.ift.simca.exposition.dto.PreguntasXMLDTO;
import mx.org.ift.simca.exposition.dto.RadioXMLDTO;
import mx.org.ift.simca.model.TipoPregunta;

public class GeneraRadioXML implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4067136904284602433L;

	private static final Logger LOG = LoggerFactory.getLogger(AddEstacionProgramMB.class);

	public static void generaEstacionXML(EstacionDTO estacionDTO, List<TipoPregunta> tipoPreguntas) {
		try {

			RadioXMLDTO radioXMLDTO = new RadioXMLDTO();

			GrupoRadioXMLDTO grupoRadioXMLDTO = new GrupoRadioXMLDTO();
			grupoRadioXMLDTO.setFolioElectronico(estacionDTO.getFolioRPCUMCA());
			grupoRadioXMLDTO.setIdConcesionario(estacionDTO.getIdConcesionario() != null ? estacionDTO.getIdConcesionario().toString() : "");

			radioXMLDTO.setGrupoRadioXMLDTO(grupoRadioXMLDTO);

			EstacionXMLDTO estacionXMLDTO = new EstacionXMLDTO();

			estacionXMLDTO.setFolioElectronico(estacionDTO.getFolioRPCUMCA());
			estacionXMLDTO.setIdSenial("");
			estacionXMLDTO.setIdClase(estacionDTO.getIdClase() != null ? estacionDTO.getIdClase().toString() : "");
			estacionXMLDTO.setIdTipoUsoEstacion(
					estacionDTO.getIdTipoUsoEstacion() != null ? estacionDTO.getIdTipoUsoEstacion().toString() : "");
			estacionXMLDTO.setDistintivo(estacionDTO.getDistintivo());
			estacionXMLDTO.setIdBanda(estacionDTO.getIdBanda() != null ? estacionDTO.getIdBanda().toString() : "");
			estacionXMLDTO.setIdTipoFrecuencia(
					estacionDTO.getIdTipoFrecuencia() != null ? estacionDTO.getIdTipoFrecuencia().toString() : "");
			
			if ((estacionDTO.getIdBanda() != null ? estacionDTO.getIdBanda().toString() : "").equals("1")) {
				System.out.println("seteando am");
				estacionXMLDTO.setFrecuencia(estacionDTO.getFrecuenciaAM());
			}
			else {
				System.out.println("seteando fm");
				estacionXMLDTO.setFrecuencia(estacionDTO.getFrecuenciaFM());
			}
			estacionXMLDTO.setFecIniVigencia(estacionDTO.getVigenciaIni() != null
					? new SimpleDateFormat("dd/MM/yyyy").format(estacionDTO.getVigenciaIni())
					: "");
			estacionXMLDTO.setFecFinVigencia(estacionDTO.getVigenciaFin() != null
					? new SimpleDateFormat("dd/MM/yyyy").format(estacionDTO.getVigenciaFin())
					: "");

			radioXMLDTO.setEstacionXMLDTO(estacionXMLDTO);

			CoberturaXMLDTO coberturaXMLDTO = new CoberturaXMLDTO();
			List<PoblacionXMLDTO> poblaciones = new ArrayList<PoblacionXMLDTO>();

			for (CoberturaRadioDTO coberturaRadioDTO : estacionDTO.getCoberturasRadioDTO()) {
				PoblacionXMLDTO poblacionXMLDTO = new PoblacionXMLDTO();
				poblacionXMLDTO.setEstado(coberturaRadioDTO.getEstado().getIdentificador());
				poblacionXMLDTO.setMunicipio(coberturaRadioDTO.getMunicipio().getIdentificador());
				poblaciones.add(poblacionXMLDTO);
			}

			coberturaXMLDTO.setPoblaciones(poblaciones);
			radioXMLDTO.setCoberturaXMLDTO(coberturaXMLDTO);
			List<PreguntaXMLDTO> preguntasXMLDTO = new ArrayList<PreguntaXMLDTO>();

			for (TipoPregunta tipoPregunta : tipoPreguntas) {
				PreguntaXMLDTO preguntaXMLDTO = new PreguntaXMLDTO();
				preguntaXMLDTO.setIdPregunta(tipoPregunta.getIdTipoPregunta() != null ? tipoPregunta.getIdTipoPregunta().toString() : null);
				preguntaXMLDTO.setDescPregunta(tipoPregunta.getPregunta());
				switch (tipoPregunta.getIdTipoPregunta()) {

				case 1:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdTipoEstacion() != null ? estacionDTO.getIdTipoEstacion().toString() : "");
					break;
				case 2:
					preguntaXMLDTO.setRespuesta(estacionDTO.getNombreProgramacion());
					break;
				case 3:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdMultiprograma() != null ? estacionDTO.getIdMultiprograma().toString() : "");
					break;
				case 4:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdBDINE() != null ? estacionDTO.getIdBDINE().toString() : "");
					break;
				case 5:
					preguntaXMLDTO
							.setRespuesta(estacionDTO.getCentroVMINE() != null ? new SimpleDateFormat("dd/MM/yyyy").format(estacionDTO.getCentroVMINE()) : "");
					break;
				case 6:
					preguntaXMLDTO.setRespuesta(estacionDTO.getNomCanalINE());
					break;
				case 7:
					preguntaXMLDTO.setRespuesta(estacionDTO.getNomComINE());
					break;
				case 8:
					preguntaXMLDTO.setRespuesta(estacionDTO.getTipoMonitoreoINE());
					break;
				case 9:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdBdAuditsa() != null ? estacionDTO.getIdBdAuditsa().toString() : "");
					break;
				case 10:
					preguntaXMLDTO.setRespuesta(estacionDTO.getNombreCanalAuditsa());
					break;
				case 11:
					preguntaXMLDTO.setRespuesta(estacionDTO.getProgramAuditsa());
					break;
				case 12:
					preguntaXMLDTO.setRespuesta(estacionDTO.getLocalidadAuditsa());
					break;
				case 13:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdMonitoreoServExt() != null ? estacionDTO.getIdMonitoreoServExt().toString() : "");
					break;
				case 14:
					preguntaXMLDTO.setRespuesta(estacionDTO.getLocServExt());
					break;
				case 15:
					preguntaXMLDTO.setRespuesta(estacionDTO.getNomComServExt());
					break;
				case 16:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdProgramInfantil() != null ? estacionDTO.getIdProgramInfantil().toString() : "");
					break;
				case 17:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdObligAcces() != null ? estacionDTO.getIdObligAcces().toString() : "");
					break;
				case 18:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdMecanAcces() != null ? estacionDTO.getIdMecanAcces().toString() : "");
					break;
				case 19:
					preguntaXMLDTO.setRespuesta(estacionDTO.getIdMedioPublico() != null ? estacionDTO.getIdMedioPublico().toString() : "");
					break;
				case 20:
					preguntaXMLDTO.setRespuesta(estacionDTO.getInstPubFed());
					break;
				}

				preguntasXMLDTO.add(preguntaXMLDTO);
			}

			FormularioXMLDTO formularioXMLDTO = new FormularioXMLDTO();
			PreguntasXMLDTO preguntasXMLDTOParent = new PreguntasXMLDTO();

			preguntasXMLDTOParent.setPreguntaXMLDTO(preguntasXMLDTO);
			formularioXMLDTO.setPreguntasXMLDTO(preguntasXMLDTOParent);

			radioXMLDTO.setFormularioXMLDTO(formularioXMLDTO);

			JAXBContext jaxbContext = JAXBContext.newInstance(RadioXMLDTO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(radioXMLDTO, sw);
			String xmlString = sw.toString();
			LOG.info(xmlString);

		} catch (JAXBException e) {
			System.out.println("Error en el método generaEstacionXML: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
