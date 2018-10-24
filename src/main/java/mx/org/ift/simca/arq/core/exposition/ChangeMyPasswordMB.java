package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.service.security.adminPass.ChangeMyPasswordService;
import mx.org.ift.simca.arq.core.service.security.passUtils.PasswordFacadeService;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;

import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <p>Descripción:</p>
 * Clase ManagedBean que atienda a la vista ChangeMyPassword.xhtml Permite cambiar el password del usuario.
 *
 * @version 1.0
 */
@Controller
@Scope(value = "view")
public class ChangeMyPasswordMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ChangeMyPasswordService changeMyPasswordService;
    @Autowired
    private PasswordFacadeService passwordFacadeService;
    
    private transient ResourceBundle bundle = ResourceBundle.getBundle("i18n/textMessages");
    private String claveActual;
    private String nuevaClave;
    private String confirmaClave;

    /**
     * Password actual.
     * @return Password actual.
     */
    public String getClaveActual() {
        return claveActual;
    }

    /**
     * Password actual.
     * @param claveActual Password actual.
     */
    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    /**
     * Nuevo password.
     * @return Nuevo password.
     */
    public String getNuevaClave() {
        return nuevaClave;
    }

    /**
     * Nuevo password.
     * @param nuevaClave Nuevo password.
     */
    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    /**
     * Confirmación del nuevo password.
     * @return Confirmación del nuevo password.
     */
    public String getConfirmaClave() {
        return confirmaClave;
    }

    /**
     * Confirmación del nuevo password.
     * @param confirmaClave Confirmación del nuevo password.
     */
    public void setConfirmaClave(String confirmaClave) {
        this.confirmaClave = confirmaClave;
    }

    /**
     * Metodo que atiende al evento cambiaClave activado al presionar el boton "Cambiar clave de acceso". Este metodo es
     * llamado desde una pagina JSF. Valida que la clave capturada en la pagina ChangeMyPassword.xhtml cumpla con las
     * reglas establecidas para una clave nueva. En caso de cumplir, almacena la nueva clave. Si no cumple con las
     * reglas la clave capturada, se agrega un mensaje a la lista de mensajes a desplegar en pantalla.
     *
     * @param ae Este parametro se usa para obtener los componentes asociados a la vista y realizar validaciones con la
     * clave que desea establecer el usuario.
     *
     *
     */
    public void cambiaClave(ActionEvent ae) {
        UIComponent component = ae.getComponent();
        UIComponent claveActualComponent = component.findComponent("claveActual");
        UIComponent nuevaClaveComponent = component.findComponent("nuevaClave");
        UIComponent confirmaClaveComponent = component.findComponent("confirmaClave");
        UIComponent generalMessageComponent = component.findComponent("msg");

        Usuario usr = changeMyPasswordService.getCurrentLoggedUsername();
        if (usr == null) {
            ContextUtils.addErrorMsg(generalMessageComponent, "Ingrese al sistema para cambiar su clave", 
                    "Ingrese al sistema para cambiar su clave");
            return;
        }

        List<String> notValidPassword = new ArrayList<String>();
        notValidPassword.add(bundle.getString(PasswordFacadeService.PASSWORD_STRENGTH_FAIL_MSG));
        String nuevaClaveEnc = passwordFacadeService.getPasswordEncrypted(usr.getUsername(), nuevaClave, notValidPassword);
        String claveActualEnc = passwordFacadeService.getPasswordOnlyEncrypted(claveActual);

        // La lógica se se puede apreciar abajo, no puede ir en un "Service" ya
        // que prácticamente cada dos lineas se encuentra un "addMsg"
        if (claveActual.isEmpty() && confirmaClave.isEmpty() && nuevaClave.isEmpty()) {
            ContextUtils.addErrorMsg(generalMessageComponent, "Error al cambiar la clave",
                    "Todos los datos son requeridos (clave actual, nueva clave y confirma clave)");
        } else {
            if (claveActual.isEmpty()) {
                ContextUtils.addErrorMsg(claveActualComponent, "Error al cambiar la clave",
                        "La información correspondiente al campo clave actual es requerida");
            } else if (!claveActualEnc.equals(usr.getPassword())) {
                ContextUtils.addErrorMsg(claveActualComponent, "Error al cambiar la clave", "La clave actual no es correcta");
            }

            if (nuevaClave.isEmpty()) {
                ContextUtils.addErrorMsg(nuevaClaveComponent, "Error al cambiar la clave",
                        "La información correspondiente al campo nueva clave es requerida");
            } else if (claveActualEnc != null && nuevaClaveEnc != null && claveActualEnc.equals(nuevaClaveEnc)) {
                ContextUtils.addErrorMsg(nuevaClaveComponent, "Error al cambiar la clave",
                        "La nueva clave tiene que ser distinta que la clave actual");
            } else if (claveActualEnc != null && nuevaClaveEnc == null) {
                //StringBuilder msgBuilder = new StringBuilder();
                for (String msg : notValidPassword) {
                    //msgBuilder.append("-").append(LanguageMB.translate(msg)).append("\n");
                    String traduccion = LanguageMB.translate(msg);
                    ContextUtils.addErrorMsg(traduccion);
                }
            }

            if (confirmaClave.isEmpty()) {
                ContextUtils.addErrorMsg(confirmaClaveComponent, "Error al cambiar la clave",
                        "La información correspondiente al campo confirma clave es requerida");
            } else if (!nuevaClave.isEmpty() && !this.confirmaClave.equals(this.nuevaClave)) {
                ContextUtils.addErrorMsg(confirmaClaveComponent, "Error al cambiar la clave",
                        "La clave y su confirmación no coinciden");
            }
        }
        boolean flag = ContextUtils.countMessages() == 0;
        if (flag) {
            changeMyPasswordService.cambiaClave(usr, nuevaClaveEnc);
        }
        PrimeFacesRequestContext.addCallBackParam(flag);
    }
}
