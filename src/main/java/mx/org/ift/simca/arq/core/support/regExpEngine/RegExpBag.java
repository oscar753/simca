package mx.org.ift.simca.arq.core.support.regExpEngine;

import java.io.Serializable;

/**
 * La clase RegExpBag.
 *
*/
public class RegExpBag extends RegExpBagBase implements Serializable {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva instancia reg exp bag.
     */
    public RegExpBag() {
        // La idea es sacar las cadenas que vienen abajo, de un archivo XML usando XPath Queries
        this.add("PARSE_MAIL", "([\\w-_]+\\.)*[\\w-_]+\\@([\\w-_]+\\.)+[a-zA-Z]{2,3}", "Debe ingresar un [label] válido");
        this.add("PARSE_PHONE", "([\\w-\\s]|\\(|\\)|\\+||\\.)*", "La estructura de [label] para un teléfono es:...");
        this.add("PARSE_LATIN_TEXT", "([a-zA-Z0-9À-ÖØ-öø-ÿ]| |¿|\\?|\\!|¡|-|_|\\.|,)*", "La estructura de [label] para un texto latino es:...");
        this.add("PARSE_GENERAL_TEXT", "([\\w-_\\s])*", "La estructura de [label] para un texto general es:...");
        this.add("PARSE_RFC", "[A-Za-z]{4}[0-9]{6}([A-Za-z0-9]{3})?", "La estructura de [label] para un RFC es:...");
        this.add("PARSE_RFC_SEP", "[A-Za-z]{4}[0-9]{6}[A-Za-z0-9]{3}", "La estructura de [label] para un RFC SEP es:...");
        this.add("PARSE_CURP", "[A-Za-z]{4}[0-9]{6}[A-Za-z]{6}[0-9]{2}", "La estructura de [label] para un CURP es:...");
        this.add("PARSE_CP", "[0-9]{5}", "La estructura de [label] para un CP es:...");
        this.add("PARSE_COMBINED", "[A-Za-z]{4}[0-9]{6}([A-Za-z0-9]{3})?" + RegExpUtil.DIV + "([\\w-\\s]|\\(|\\)|\\+||\\.)*",
                "La estructura de [label] para un COMBINED es:...");
        this.add("PARSE_LOGIN_NAME", "^[a-zA-Z]+([\\._\\-]?\\w+)*$",
                "La estructura de [label] para un login debe ser: letras (sin acentos o caracteres latinos), "
                + "números, punto, guion y guion bajo; además debe inicia con una letra");
    }
}
