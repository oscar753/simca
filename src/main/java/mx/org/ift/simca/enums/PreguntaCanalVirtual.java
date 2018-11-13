/**
 * 
 */
package mx.org.ift.simca.enums;

/**
 * @author KODE-LAP0077
 *
 */
public enum PreguntaCanalVirtual {

	PREGUNTA1("Línea Fronteriza", 1),
	PREGUNTA2("En opinión con FCC", 2),
	PREGUNTA3("Notificación CV", 3),
	PREGUNTA4("Fecha de oficio de Notificación", 4),
	PREGUNTA5("Nivel de Transmisión", 5),
	PREGUNTA6("Publicación en listado", 6),
	PREGUNTA7("1a publicación", 7),
	PREGUNTA8("Observaciones CV", 8),
	PREGUNTA9("Primer toma de Nota", 9),
	PREGUNTA10("Primer toma de Nota", 10),
	PREGUNTA11("Resolución Multi", 11),
	PREGUNTA12("Fecha Resolución Multi", 12),
	PREGUNTA13("Fecha de oficio de Notificación", 13),
	PREGUNTA14("Modificaciones", 14),
	PREGUNTA15("Respuesta al Concesionario", 15),
	PREGUNTA16("Estatus de modificacion", 16),
	PREGUNTA17("Primera Publicación", 17),
	PREGUNTA18("Publicación en Listado", 18),
	PREGUNTA19("Observaciones Multiprogramación", 19),
	PREGUNTA20("BD INE", 20),
	PREGUNTA21("Centro VM", 21),
	PREGUNTA22("Nombre canal INE", 22),
	PREGUNTA23("Nombre comercial INE", 23),
	PREGUNTA24("Tipo Monitoreo INE", 24),
	PREGUNTA25("BD AUDITSA", 25),
	PREGUNTA26("Nombre canal AUDITSA", 26),
	PREGUNTA27("Programación AUDITSA", 27),
	PREGUNTA28("Localidad AUDITSA", 28),
	PREGUNTA29("Monitoreo servicio externo", 29),
	PREGUNTA30("Localidad servicio externo", 30),
	PREGUNTA31("Nombre comercial servicio externo", 31),
	PREGUNTA32("Tipo programacion infantil", 32),
	PREGUNTA33("Obligaciones accesibilidad", 33),
	PREGUNTA34("Mecanismo de accesibilidad", 34),
	PREGUNTA35("Medio público", 35),
	PREGUNTA36("Institución Público Federal", 36);
	
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
