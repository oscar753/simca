package mx.org.ift.simca.arq.core.support;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: Convert.java</p>
 *
 * <p>Description: Maneja conversiones de datos. Es capaz de convertir a un tipo
 * específico, objetos que le son pasados como parámetros formales a sus métodos
 * y ofrece la funcionalidad de determinar si un objeto es de cierto tipo.</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author 
 * @version 
 */
public class Convert implements Serializable {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Instancia del log de sistema.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Convert.class);

    /**
     * Por ser una clase utilitaria, el constructor no es público.
     */
    protected Convert() {
    }

    /**
     * Convierte una cadena en Int.
     *
     * @param testValue la cadena a convertir
     * @return El int valor convertido a Int o nul si no es posible hacer la
     * conversión.
     */
    public static Integer toInt(String testValue) {
        try {
            return Integer.parseInt(testValue);
        } catch (Exception ex) {
            LOGGER.debug("Error al convertir a un número", ex);
            return null;
        }
    }

    /**
     * Transforma una cadena dada a formato java.util.Date.
     *
     * @param testValue la cadena que se desea convertir.
     * @return El java.util.Date la fecha en formato Date o null si no es
     * posible hacer la conversión.
     */
    public static Date toDate(String testValue) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");            
            return dateFormat.parse(testValue);
        } catch (Exception ex) {
            LOGGER.debug("Error al parsear la fecha", ex);
            return null;
        }
    }

    /**
     * Convierte una cadena en double.
     *
     * @param testValue la cadena String
     * @return El valor en double
     */
    public static Double toDouble(String testValue) {
        try {
            return Double.parseDouble(testValue);
        } catch (Exception ex) {
            LOGGER.debug("Error al convertir a un número", ex);
            return null;
        }
    }

    /**
     * Convierte un string en long.
     *
     * @param testValue la cadena a convertir
     * @return El valor convertido en long
     */
    public static Long toLong(String testValue) {
        try {
            return Long.parseLong(testValue);
        } catch (Exception ex) {
            LOGGER.debug("Error al convertir a un número", ex);
            return null;
        }
    }

    /**
     * Informa si una cadena tiene representación como entero. true : si tiene
     * representación en flotante false: si no la tiene.
     *
     * @param value String expresión a ser evaluada
     * @return boolean
     */
    public static boolean isInt(String value) {
        return toInt(value) != null;
    }

    /**
     * Informa si una cadena tiene representación como fecha. true : si tiene
     * representación en flotante false: si no la tiene.
     *
     * @param value String expresión a ser evaluada
     * @return boolean
     */
    public static boolean isDate(String value) {
        return toDate(value) != null;
    }

    /**
     * Informa si una cadena tiene representación como double.
     *
     * @param value String expresión a ser evaluada
     * @return true : si tiene representación en flotante false: si no la tiene.
     */
    public static boolean isDouble(String value) {
        return toDouble(value) != null;
    }

    /**
     * Informa si una cadena tiene representación como entero largo.
     *
     * @param value String expresión a ser evaluada
     * @return boolean
     */
    public static boolean isLong(String value) {
        return toLong(value) != null;
    }
}// Fin de la clase Convert ***************************************************
