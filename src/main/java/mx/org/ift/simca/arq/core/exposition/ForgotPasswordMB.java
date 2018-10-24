package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import mx.org.ift.simca.arq.core.enums.TipoBitacoraEnum;

import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.service.security.adminPass.ForgotPasswordService;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioService;
import mx.org.ift.simca.arq.core.support.Audit;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;

import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <p>Descripción</p>
 * ManagedBean asociado a los datos del usuario.
 *
  */
@Controller
@Scope(value = "view")
public class ForgotPasswordMB implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ForgotPasswordService forgotPasswordService;
    
    private String correo;

    /**
     * Correo electrónico.
     * @param correo Correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Correo electrónico.
     * @return Correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Notifica del envío exitoso de una liga de recuperación de clave de acceso siempre y cuando el texto capturado en
     * el campo "correo" posea una estructura de correo electrónico válida.
     *
     * Si la estructura es inválida, se notifica de ello y termina el ciclo.
     *
     * Adicionalmente, SI el correo dado existe en la base de datos, la liga es efectivamente enviada a tal correo. En
     * caso contrario, no hace más que la presentación del mensaje de 'notificación exitosa'.
     *
     * @param ae
     */
    public void enviaClaves(ActionEvent ae) {
        // El campo "correo" es obligatorio
        if (this.correo == null || this.correo.trim().length() < 1) {
            ContextUtils.addInfoMsg("El capo correo es obligatorio");
            return;
        }

        // Como el correo no fue vacio y el captcha si paso...
        UIComponent ui = ae.getComponent();


        // En validaciones como esta no conviene usar un archivo de validación XLM:
        boolean b = ContextUtils.evalMalformedMail(
                this.correo,
                "Error de envío de correo. Correo mal formado.",
                ui.findComponent("correo"));
        if (b) {
            return;
        }

        Usuario usr = usuarioService.getUserByCorreo(this.correo);

        if (usr != null) {
            forgotPasswordService.sendEmailToRestorePassword(
                    usr,
                    this.correo,
                    ContextUtils.getFullServerUrl());
            Audit.write(TipoBitacoraEnum.MAIL_SENT, "correo de recuperacion SI enviado a:" + this.correo);
        } else {
            String msg = "Mensaje para Bitácora: Se ingresó un correo (bien formado) que no existe en la base:" + this.correo;
            Audit.write(TipoBitacoraEnum.MAIL_SENT, msg);
        }
        // aqui se vale quitar esta linea y en el xhtml e invocar
        // la función de jScript de 'dlg.show()' directamente
        PrimeFacesRequestContext.addCallBackParam(true);
    }
}
