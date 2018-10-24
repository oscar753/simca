package mx.org.ift.simca.arq.core.support.regExpEngine;

import java.io.Serializable;

/**
 * La clase RegExpBean.
 *
*/
public class RegExpBean implements Serializable {

    /**
     * Representa el valor inicial de la version del serial.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa el nombre de la expression.
     */
    private String name;
    /**
     * Representa la expresión regular.
     */
    private String regExpString;
    /**
     * Representa el mensaje de error.
     */
    private String errorMessage;

    /**
     * Crea una nueva instancia reg exp bean.
     *
     * @param name el name
     * @param regExpString el reg exp string
     * @param errorMessage el error message
     */
    public RegExpBean(String name, String regExpString, String errorMessage) {
        this.name = name;
        this.regExpString = regExpString;
        this.errorMessage = errorMessage;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getRegExpString() {
        return regExpString;
    }

    /**
     *
     * @param regExpString
     */
    public void setRegExpString(String regExpString) {
        this.regExpString = regExpString;
    }

    /**
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
