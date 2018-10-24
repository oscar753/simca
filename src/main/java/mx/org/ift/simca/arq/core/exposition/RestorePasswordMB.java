package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.service.security.adminPass.RestorePasswordService;
import mx.org.ift.simca.arq.core.service.security.passUtils.PasswordFacadeService;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;

import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;


@Controller
@Scope(value = "view")
public class RestorePasswordMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private transient ResourceBundle bundle = ResourceBundle.getBundle("i18n/textMessages");
    
    @Autowired
    private RestorePasswordService restorePasswordService;
    
    @Autowired
    private PasswordFacadeService passwordFacadeService;
    
    @Value("${restore.password.ticketLiveTime}")
    private int ticketLiveTime;
    
    private boolean sidOk;
    private boolean expired;
    private boolean invalid;
    
    private String preguntaSecreta;
    private String respuestaSecreta;
    
    private String nuevaClave;
    private String confirmaClave;
    
    private Usuario usr;

    /**
     * Informa si el sid es correcto.
     * @return Informa si el sid es correcto.
     */
    public boolean isSidOk() {
        return this.sidOk;
    }

    /**
     * Informa si el sid está expirado.
     * @return Informa si el sid está expirado.
     */
    public boolean isExpired() {
        return this.expired;
    }

    /**
     * Informa si es válido el sid.
     * @return Informa si es válido el sid.
     */
    public boolean isInvalid() {
        return this.invalid;
    }

    /**
     * Pregunta secreta.
     * @param preguntaSecreta Pregunta secreta.
     */
    public void setPreguntaSecreta(String preguntaSecreta) {
        this.preguntaSecreta = preguntaSecreta;
    }

    /**
     * Pregunta secreta.
     * @return Pregunta secreta.
     */
    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    /**
     * Respuesta a la pregunta secreta.
     * @param respuestaSecreta Respuesta a la pregunta secreta.
     */
    public void setRespuestaSecreta(String respuestaSecreta) {
        this.respuestaSecreta = respuestaSecreta;
    }

    /**
     * Respuesta a la pregunta secreta.
     * @return Respuesta a la pregunta secreta.
     */
    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    /**
     * El nuevo password.
     * @param nuevaClave El nuevo password.
     */
    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    /**
     * El nuevo password.
     * @return El nuevo password.
     */
    public String getNuevaClave() {
        return nuevaClave;
    }

    /**
     * La confirmación del password.
     * @param confirmaClave La confirmación del password.
     */
    public void setConfirmaClave(String confirmaClave) {
        this.confirmaClave = confirmaClave;
    }

    /**
     * La confirmación del password.
     * @return La confirmación del password.
     */
    public String getConfirmaClave() {
        return confirmaClave;
    }

    /**
     * Metodo que es llamado desde la pagina restorePassword.xhtml al presionar el boton "Establecer nueva clave de
     * acceso". Este metodo valida que el password no sea igual al anterior y que cumpla con las reglas para generar un
     * nuevo password.
     *
     * @param ae Este parametro se utiliza para obtener los componente de la vista y realizar las validaciones. Si no se
     * encuentran errores se establece el nuevo password. Si se encuentran errores se mostraran en pantalla.
     */
    public void creaNuevaClave(ActionEvent ae) {
        boolean flag = false;
        UIComponent ui = ae.getComponent();

        List<String> notValidPassword = new ArrayList<String>();
        notValidPassword.add(bundle.getString(PasswordFacadeService.PASSWORD_STRENGTH_FAIL_MSG));
        // Porque en la ayuda de la funcion getPasswordEncrypted si se ven los acentos?
        String nuevaClaveEnc = passwordFacadeService.getPasswordEncrypted(usr.getUsername(), nuevaClave, notValidPassword);
        //nuevaClaveEnc = passwordFacadeService.getPasswordOnlyEncrypted(nuevaClave);

        ContextUtils.evalTrue(
                nuevaClaveEnc != null && nuevaClaveEnc.equals(usr.getPassword()),
                "La nueva clave no puede ser igual a la anterior",
                ui.findComponent("generalInfo"));
        if (nuevaClaveEnc == null) {
            for (String msg : notValidPassword) {
                String msg2 = LanguageMB.translate(msg);
                ContextUtils.addErrorMsg(msg2, msg2);
            }
        }
//        ContextUtils.evalTrue(
//                nuevaClaveEnc==null,
//                "La clave dada no cumple con las reglas de fortaleza establecidas",
//                ui.findComponent("generalInfo"));
//        ContextUtils.evalMinLen(
//                nuevaClave,
//                minLength,
//                "La longitud mínima para clave debe ser " + minLength,
//                ui.findComponent("generalInfo"));


        ContextUtils.evalEmpty(
                nuevaClave,
                "El campo 'Nueva clave' no puede ser vacío",
                ui.findComponent("generalInfo"));
        ContextUtils.evalEmpty(
                respuestaSecreta,
                "El campo 'Respuesta secreta' no puede ser vacío",
                ui.findComponent("generalInfo"));
        ContextUtils.evalTrue(
                !nuevaClave.equals(confirmaClave),
                "La clave y su confirmación deben ser iguales",
                ui.findComponent("generalInfo"));

        ContextUtils.evalTrue(
                !this.respuestaSecreta.equals(this.usr.getRespuestaSecreta()),
                "La respuesta a su pregunta secreta es incorrecta",
                ui.findComponent("generalInfo"));

        if (ContextUtils.countMessages() < 1) {
            restorePasswordService.setNewPassword(this.usr, nuevaClaveEnc);
            flag = true;
        }
        PrimeFacesRequestContext.addCallBackParam(flag);
    }

    /**
     * Limpia los valores de sidOK, expired e invalid
     *
     * @see sidOk
     * @see expired
     * @see invalid
     */
    private void cleanFlags() {
        this.sidOk = false;
        this.expired = false;
        this.invalid = false;
    }

    /**
     * Limpia los valores de confirmaClave, nuevaClave y respuestaSecreta
     *
     * @see confirmaClave
     * @see nuevaClave
     * @see respuestaSecreta
     */
    private void cleanData() {
        this.confirmaClave = "";
        this.nuevaClave = "";
        this.respuestaSecreta = "";
    }

    /**
     * Metodo que valida el parametro sid. Este metodo valida que el tiempo enviado como parametro en la liga de
     * recuperar password sea valido. Si es valido establece la pregunta secreta, en caso contrario, muestra mensaje
     * indicando que ha expirado el tiempo de validez para la liga.
     *
     * @return Retorna una cadena vacia.
     */
    public String getSid() {
        String sid = ContextUtils.getParameter("sid");
        usr = restorePasswordService.getUserBySid(sid);
        cleanFlags();
        if (usr == null) {
            this.invalid = true;
        } else {
            long ventana = usr.getVentanaParaIdSeguridad().getTime();
            long currentTime = System.currentTimeMillis();
            long periodoGracia = 1000 * 60 * 60 * ticketLiveTime;
            if (currentTime > (ventana + periodoGracia)) {
                this.expired = true;
            } else {
                this.sidOk = true;
                this.preguntaSecreta = usr.getPreguntaSecreta();
            }
        }
        cleanData();
        return "";
    }
}
