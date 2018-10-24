package mx.org.ift.simca.arq.core.exposition.validators;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;
import mx.org.ift.simca.arq.core.support.regExpEngine.RegExpBag;
import mx.org.ift.simca.arq.core.support.regExpEngine.RegExpBean;

/**
 * Base para los validadores, proveé un método común para agregar el mensaje de
 * error.
 */
public abstract class BaseValidator {

    /**
     * Llave a reemplazar en los mensajes.
     */
    protected static final String LABEL = "[label]";
    
    /**
     * La llave a reemplazar para ser usada como expresión regular.
     */
    private static final String LABEL_REMPLACE = "\\[label\\]";

    /**
     * Lanza el mensaje de error para ser usado por jsf en su framework de
     * mensajes. Está planeado para el caso en que la validación por no capturar
     * un valor.
     *
     * @param uic Componente inspeccionado.
     * @param message Mensaje a enviar (en caso de que no se defina uno en la
     * vista).
     */
    protected void throwRequiredMessage(UIComponent uic, String message) {
        throwMessage(uic, message, "requiredMessage");
    }

    /**
     * Lanza el mensaje de error para ser usado por jsf en su framework de
     * mensajes. Está planaeado para el caso en que falle la validación en el
     * texto capturado.
     *
     * @param uic Componente inspeccionado.
     * @param message Mensaje a enviar (en caso de que no se defina uno en la
     * vista).
     */
    protected void throwValidationMessage(UIComponent uic, String message) {
        throwMessage(uic, message, "validatorMessage");
    }

    /**
     * Reemplaza la llave de la constante LABEL encontrada en el mensaje a
     * enviar.
     *
     * @param message Mensaje a enviar.
     * @param label Texto que reemplaza la llave.
     * @return Mensaje con el texto reemplazado.
     */
    protected String getReplacedMessage(String message, String label) {
        return getReplacedMessage(message, LABEL_REMPLACE, label);
    }

    protected String getReplacedMessage(String message, String key, String replaceText) {
        return message.replaceAll(key, replaceText);
    }
    
    private void throwMessage(UIComponent uic, String defaultMessage, String attributeName) {
        String uicMessage = (String) uic.getAttributes().get(attributeName);

        String label = getLabel(uic);

        String msgText;

        if (uicMessage == null) {
            StringBuilder stbd = new StringBuilder();

            stbd.append(label).append(": ").append(getReplacedMessage(defaultMessage, label));

            msgText = stbd.toString();
        } else {
            msgText = uicMessage;
        }

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgText, msgText);
        throw new ValidatorException(msg);
    }
    
    protected String getLabel(UIComponent uic) {
        String label = (String) uic.getAttributes().get("label");

        if (label == null) {
            label = uic.getId();
        }
        
        return label;
    }
    
    protected static RegExpBean findRegExp(String name) {
        RegExpBag regExpBag = new RegExpBag();
        
        List<RegExpBean> regExpList = regExpBag.getBag();
        
        for (RegExpBean regExpBean : regExpList) {
            if (regExpBean.getName().equalsIgnoreCase(name)) {
                return regExpBean;
            }
        }
        
        return null;
    }
    
    protected boolean matched(String regExp, String value) {
        Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    
}
