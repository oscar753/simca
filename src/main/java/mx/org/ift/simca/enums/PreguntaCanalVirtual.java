/**
 * 
 */
package mx.org.ift.simca.enums;

/**
 * @author KODE-LAP0077
 *
 */
public enum PreguntaCanalVirtual {

	PREGUNTA1("L�nea Fronteriza", 1),
	PREGUNTA2("En opini�n con FCC", 2),
	PREGUNTA3("Notificaci�n CV", 3),
	PREGUNTA4("Fecha de oficio de Notificaci�n", 4),
	PREGUNTA5("Nivel de Transmisi�n", 5),
	PREGUNTA6("Publicaci�n en listado", 6),
	PREGUNTA7("1a publicaci�n", 7),
	PREGUNTA8("Observaciones CV", 8),
	PREGUNTA9("Primer toma de Nota", 9),
	PREGUNTA10("Primer toma de Nota", 10),
	PREGUNTA11("Resoluci�n Multi", 11),
	PREGUNTA12("Fecha Resoluci�n Multi", 12),
	PREGUNTA13("Fecha de oficio de Notificaci�n", 13),
	PREGUNTA14("Modificaciones", 14),
	PREGUNTA15("Respuesta al Concesionario", 15),
	PREGUNTA16("Estatus de modificacion", 16),
	PREGUNTA17("Primera Publicaci�n", 17),
	PREGUNTA18("Publicaci�n en Listado", 18),
	PREGUNTA19("Observaciones Multiprogramaci�n", 19),
	PREGUNTA20("BD INE", 20),
	PREGUNTA21("Centro VM", 21),
	PREGUNTA22("Nombre canal INE", 22),
	PREGUNTA23("Nombre comercial INE", 23),
	PREGUNTA24("Tipo Monitoreo INE", 24),
	PREGUNTA25("BD AUDITSA", 25),
	PREGUNTA26("Nombre canal AUDITSA", 26),
	PREGUNTA27("Programaci�n AUDITSA", 27),
	PREGUNTA28("Localidad AUDITSA", 28),
	PREGUNTA29("Monitoreo servicio externo", 29),
	PREGUNTA30("Localidad servicio externo", 30),
	PREGUNTA31("Nombre comercial servicio externo", 31),
	PREGUNTA32("Tipo programacion infantil", 32),
	PREGUNTA33("Obligaciones accesibilidad", 33),
	PREGUNTA34("Mecanismo de accesibilidad", 34),
	PREGUNTA35("Medio p�blico", 35),
	PREGUNTA36("Instituci�n P�blico Federal", 36);
	
	private final String pregunta;
	private final int identificador;
	
	private PreguntaCanalVirtual(String pregunta, int identificador) {
		this.pregunta = pregunta;
		this.identificador = identificador;
	}

	/**
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}

	/**
	 * @return the identificador
	 */
	public int getIdentificador() {
		return identificador;
	}
	
}
