package mx.org.ift.simca.arq.core.support.context;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Clase de utileria.
 *
*/
public class BaseContextUtils implements Serializable {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;

    /**
     * Por ser una clase utilitaria, el constructor no es público.
     */
    protected BaseContextUtils() {
    }
    
    /**
     * Agrega un mensaje de tipo Warning a los mensajes generales.
     *
     * @param summary Mensaje a agregar
     */
    public static void addMsg(String summary) {
        addMsg(buildWarnMessage(summary));
    }

    /**
     * Agrega un mensaje de tipo Warning a los mensajes generales.
     *
     * @param summary Resumen del mensaje
     * @param detail Detalle del mensaje
     */
    public static void addMsg(String summary, String detail) {
        addMsg(buildWarnMessage(summary, detail));
    }

    /**
     * Agrega un mensaje de tipo Warning a los mensajes generales.
     *
     * @param summary Resumen del mensaje
     * @param detail Detalle del mensaje
     * @param severity Severidad del Mensaje
     * @see javax.faces.application.FacesMessage.Severity
     */
    public static void addMsg(String summary, String detail, FacesMessage.Severity severity) {
        addMsg(new FacesMessage(severity, summary, detail));
    }

    /**
     * Agrega un mensaje de tipo Warning al componente dado.
     *
     * @param component Componen al que se le agregará el mensaje.
     * @param summary Mensaje a agregar
     */
    public static void addMsg(UIComponent component, String summary) {
        addMsg(component, buildWarnMessage(summary));
    }

    /**
     * Agrega un mensaje de tipo Warning al componente dado.
     *
     * @param component Componen al que se le agregará el mensaje.
     * @param summary Detalle del mensaje.
     * @param detail Severidad del Mensaje.
     */
    public static void addMsg(UIComponent component, String summary, String detail) {
        addMsg(component, buildWarnMessage(summary, detail));
    }

    /**
     * Agrega un mensaje de tipo Info a los mensajes generales.
     *
     * @param summary Resumen del error
     */
    public static void addInfoMsg(String summary) {
        addMsg(buildInfoMessage(summary));
    }

    /**
     * Agrega un mensaje de tipo Info a los mensajes generales.
     *
     * @param summary Resumen del error
     * @param detail Detalle del error
     */
    public static void addInfoMsg(String summary, String detail) {
        addMsg(buildInfoMessage(summary, detail));
    }

    /**
     * Agrega un mensaje de tipo Info al componente dado.
     *
     * @param component Componente al que se le adjuntará el error
     * @param summary Resumen del error
     */
    public static void addInfoMsg(UIComponent component, String summary) {
        addMsg(component, buildInfoMessage(summary));
    }

    /**
     * Agrega un mensaje de tipo Info al componente dado.
     *
     * @param component Componente al que se le adjuntará el error
     * @param summary Resumen del error
     * @param detail Detalle del error
     */
    public static void addInfoMsg(UIComponent component, String summary, String detail) {
        addMsg(component, buildInfoMessage(summary, detail));
    }

    /**
     * Agrega un mensaje de tipo Error a los mensajes generales.
     *
     * @param summary Resumen del error
     */
    public static void addErrorMsg(String summary) {
        addMsg(buildErrorMessage(summary));
    }

    /**
     * Agrega un mensaje de tipo Error a los mensajes generales.
     *
     * @param summary Resumen del error
     * @param detail Detalle del error
     */
    public static void addErrorMsg(String summary, String detail) {
        addMsg(buildErrorMessage(summary, detail));
    }

    /**
     * Agrega un mensaje de tipo Error al componente dado.
     *
     * @param component Componente al que se le adjuntará el error
     * @param summary Resumen del error
     */
    public static void addErrorMsg(UIComponent component, String summary) {
        addMsg(component, buildErrorMessage(summary));
    }

    /**
     * Agrega un mensaje de tipo Error al componente dado.
     *
     * @param component Componente al que se le adjuntará el error
     * @param summary Resumen del error
     * @param detail Detalle del error
     */
    public static void addErrorMsg(UIComponent component, String summary, String detail) {
        addMsg(component, buildErrorMessage(summary, detail));
    }

    /**
     * Agrega un mensaje a los mensajes generales.
     *
     * @param facesMessage Mensaje de faces a agregar.
     */
    public static void addMsg(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    /**
     * Agrega un mensaje al componente dado.
     *
     * @param component Componente al que se le agregará el mensaje
     * @param facesMessage Mensaje de faces a agregar
     */
    public static void addMsg(UIComponent component, FacesMessage facesMessage) {
        if (component == null) {
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } else {
            FacesContext.getCurrentInstance().addMessage(component.getClientId(FacesContext.getCurrentInstance()), facesMessage);
        }
    }

    /**
     * Crea un mensaje de faces de tipo Info.
     *
     * @param summary Texto del mensaje de faces.
     * @return Mensaje de faces de tipo Info.
     */
    public static FacesMessage buildInfoMessage(String summary) {
        return buildInfoMessage(summary, null);
    }

    /**
     * Crea un mensaje de faces de tipo Warning.
     *
     * @param summary Texto del mensaje de faces.
     * @return Mensaje de faces de tipo Warning.
     */
    public static FacesMessage buildWarnMessage(String summary) {
        return buildWarnMessage(summary, null);
    }

    /**
     * Crea un mensaje de faces de tipo Error.
     *
     * @param summary Texto del mensaje de faces.
     * @return Mensaje de faces de tipo Error.
     */
    public static FacesMessage buildErrorMessage(String summary) {
        return buildErrorMessage(summary, null);
    }

    /**
     * Crea un mensaje de faces de tipo Info.
     *
     * @param summary Resumen del mensaje.
     * @param detail Detalle del mensaje.
     * @return Mensaje de faces de tipo Info.
     */
    public static FacesMessage buildInfoMessage(String summary, String detail) {
        return new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }

    /**
     * Crea un mensaje de faces de tipo Warning.
     *
     * @param summary Resumen del mensaje.
     * @param detail Detalle del mensaje.
     * @return Mensaje de faces de tipo Warning.
     */
    public static FacesMessage buildWarnMessage(String summary, String detail) {
        return new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
    }

    /**
     * Crea un mensaje de faces de tipo Error.
     *
     * @param summary Resumen del mensaje.
     * @param detail Detalle del mensaje.
     * @return Mensaje de faces de tipo Error.
     */
    public static FacesMessage buildErrorMessage(String summary, String detail) {
        return new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }
}
