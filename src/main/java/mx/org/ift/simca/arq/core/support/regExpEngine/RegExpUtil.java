package mx.org.ift.simca.arq.core.support.regExpEngine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La clase RegExpUtil.
 *
*/
public final class RegExpUtil implements Serializable {

    /**
     * Representa el valor inicial de la versión del serial.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa el diccionario de expressiones como una tabla hash.
     */
    private Map<String, String> expresiones = new HashMap<String, String>();
    /**
     * Representa el diccionario de mensajes como una tabla hash.
     */
    private Map<String, String> messages = new HashMap<String, String>();
    /**
     * Representa una única instancia de la utilería, utilizado para el patrón
     * singleton.
     */
    private static RegExpUtil instance;
    /**
     * La constante DIV.
     */
    public static final String DIV = "--[OR]--";

    /**
     * Crea una nueva instancia de la clase.
     */
    private RegExpUtil() {
        RegExpBag reb = new RegExpBag();
        List<RegExpBean> bag = reb.getBag();
        for (RegExpBean bean : bag) {
            expresiones.put(bean.getName(), bean.getRegExpString());
            messages.put(bean.getName(), bean.getErrorMessage());
        }
    }

    /**
     * Obtiene una única instancia (Singleton).
     *
     * @return instancia de RegExpUtil
     */
    public static RegExpUtil getInstance() {
        if (instance == null) {
            instance = new RegExpUtil();
        }
        return instance;
    }

    /**
     * Recupera el regexp error message.
     *
     * @param regexpName el regexp name
     * @param label el label
     * @return El regexp error message
     */
    public String getRegexpErrorMessage(String regexpName, String label) {
        String text = getRegexp(regexpName, messages);
        return text.replace("[label]", label);
    }

    /**
     * Recupera el regexp.
     *
     * @param regexpName el regexp name
     * @return El regexp
     */
    public String getRegexp(String regexpName) {
        return getRegexp(regexpName, expresiones);
    }

    /**
     * Obtiene la expresión regular
     *
     * @param regexpName Nombre de la expresion regular
     * @param bag Mochila contenedora de la expresión regular
     * @return la expressión regular asociada
     */
    private String getRegexp(String regexpName, Map<String, String> bag) {
        String r = bag.get(regexpName);
        if (r == null) {
            return regexpName;
        }
        return r;
    }
}
