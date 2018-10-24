package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import mx.org.ift.simca.arq.core.enums.TipoBitacoraEnum;

import mx.org.ift.simca.arq.core.model.Preregistro;
import mx.org.ift.simca.arq.core.service.register.CompletaRegistroService;
import mx.org.ift.simca.arq.core.service.security.passUtils.PasswordFacadeService;
import mx.org.ift.simca.arq.core.support.Audit;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import mx.org.ift.simca.arq.core.support.xpath.CustomValidator;

import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioDetalle;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <p>Descripción</p>
 * ManagedBean asociado a la vista Completar Registro.
 *
  */
@Controller
@Scope(value = "view")
public class CompletaRegistroMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletaRegistroMB.class);
    
    private transient ResourceBundle bundle = ResourceBundle.getBundle("i18n/textMessages");
    
    private boolean sidOk;
    private boolean expired;
    private boolean invalid;

    private UsuarioInfo usuarioInfo;
    private String confirmaClave;
    
    @Value("${usuario.minLength}")
    private int userMinLength;
    
    private Preregistro pre;
    
    @Autowired
    private CompletaRegistroService completaRegistroService;
    
    @Autowired
    private PasswordFacadeService passwordFacadeService;
    
    private CustomValidator customValidator;

    /**
     * Inicializa al bean.
     */
    @PostConstruct
    public void init() {
        String validatorXML = UsuarioMB.class.getResource("/validation/Usuario.xml").getFile();
        customValidator = new CustomValidator(validatorXML);
    }

    /**
     * El tamaño mínimo el nombre del usuario.
     * @return El tamaño mínimo el nombre del usuario.
     */
    public int getMinLength() {
        return userMinLength;
    }

    /**
     * El tamaño mínimo el nombre del usuario.
     * @param minLength El tamaño mínimo el nombre del usuario.
     */
    public void setMinLength(int minLength) {
        this.userMinLength = minLength;
    }

    /**
     * El sid es correcto.
     * @return El sid es correcto.
     */
    public boolean isSidOk() {
        return sidOk;
    }

    /**
     * El sid es correcto.
     * @param sidOk El sid es correcto.
     */
    public void setSidOk(boolean sidOk) {
        this.sidOk = sidOk;
    }

    /**
     * El sid está expirado.
     * @return El sid está expirado.
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * El sid está expirado.
     * @param expired El sid está expirado.
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    /**
     * El sid es válido.
     * @return El sid es válido.
     */
    public boolean isInvalid() {
        return invalid;
    }

    /**
     * El sid es válido.
     * @param invalid El sid es válido.
     */
    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    /**
     * Información del usuario
     * @return Información del usuario
     */
    public UsuarioInfo getUsuarioInfo() {
        return usuarioInfo;
    }

    /**
     * Confirmación del password.
     * @return Confirmación del password.
     */
    public String getConfirmaClave() {
        return confirmaClave;
    }

    /**
     * Confirmación del password.
     * @param confirmaClave Confirmación del password.
     */
    public void setConfirmaClave(String confirmaClave) {
        this.confirmaClave = confirmaClave;
    }

    /**
     * Método que limpia las variables que informan si esta expirada o es invalida o es invalido el ID de confirmacion.
     * Se usa en el modulo de completar registro
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
     * Método que limpia los datos de confirmar clave y respuesta secreta. Se usa en el modulo de completar registro
     *
     * @see confirmaClave
     * @see clave
     * @see respuestaSecreta
     *
     */
    private void cleanData() {
        this.confirmaClave = "";
        if (usuarioInfo != null) {
            this.usuarioInfo.getUsuario().setClave("");
            this.usuarioInfo.getUsuario().setRespuestaSecreta("");
        }
    }

    /**
     * Método que valida el sid en el proceso de confirmacion de registro. Valida que el sid tenga un tiempo de vigencia
     * valido. En caso de que haya expirado el tiempo, se informa con un mensaje que se muestra en
     * CompletaRegistro.xhtml En caso de que el tiempo sea valido, permite continuar con el proceso.
     *
     * @return Regresa una cadena vacia en caso de exito, en caso de un error al validar el tiempo de confirmacion,
     * estableciera mensajes de error.
     */
    public String getSid() {
        String sid = ContextUtils.getParameter("sid");
        pre = completaRegistroService.getUserBySid(sid);
        cleanFlags();
        if (pre == null) {
            Audit.write(TipoBitacoraEnum.INVALID_SID, "sid invalido en el proceso de confirmacion de registro");
            this.invalid = true;
        } else {
            long ventana = pre.getVentanaParaIdSeguridad().getTime();
            long currentTime = System.currentTimeMillis();
            long periodoGracia = 1000 * 60 * 60 * 5;// 5 horas **********************
            if (currentTime > (ventana + periodoGracia)) {
                Audit.write(TipoBitacoraEnum.INVALID_SID, "sid expirado en el proceso de confirmacion de registro");
                this.expired = true;
            } else {
                this.sidOk = true;
                this.usuarioInfo = new UsuarioInfo(new Usuario(), new UsuarioDetalle());
                this.usuarioInfo.getUsuario().setCorreo(pre.getCorreo());
            }
        }
        cleanData();
        return "";
    }

    /**
     * Método que es llamado al presionar el boton Completar registro en CompletaRegistro.xhtml. Realiza validaciones de
     * los datos capturados en el Front-end. Se valida que los passwords cumplan el formato establecido. Si no se
     * encuentran problemas en las validaciones se agrega el usuario. En caso de que no se cumpla con alguna validacion
     * se informa al usuario por medio de mensajes mostrados en pantalla.
     *
     * @param ae Este parametro se usa para obtener los componentes de la vista, al cual se le aplican validaciones.
     */
    public void registra(ActionEvent ae) {
        boolean flag = false;
        UIComponent ui = ae.getComponent();

        // Se realizan las validaciones básicas
        customValidator.validaDatos(ui);

        //Recuperamos los mensajes del archivo de propiedades
        String passwordConfNotEquals = bundle.getString("restricted.admin.users.common.passwordConfNotEquals");
        String loginAlreadyExists = bundle.getString("restricted.admon.users.common.loginAlreadyExists");

        List<String> notValidPassword = new ArrayList<String>();
        notValidPassword.add(bundle.getString(PasswordFacadeService.PASSWORD_STRENGTH_FAIL_MSG));


        /*
         String nuevaClaveEnc = null;
         if (passwordFacadeService.getPasswordEncrypted(usuario, clave, notValidPassword) != null) {
         //Se genera la clave con la nueva forma de encripción.
         nuevaClaveEnc = passwordFacadeService.getPasswordOnlyEncrypted(clave, usuario);
         }
         */
        String nuevaClaveEnc = passwordFacadeService.getPasswordEncrypted(usuarioInfo.getUsuario().getUsuario(),
                usuarioInfo.getUsuario().getClave(), notValidPassword);

        //Si "clave" es vacia OR "confirmaClave" es vacia: ERROR
        if (usuarioInfo.getUsuario().getClave().isEmpty() || confirmaClave.isEmpty()) {
            ContextUtils.evalTrue(
                    true,
                    "La clave y su confirmacion no pueden ser vacias",
                    ui.findComponent("password"));
        }

        //Si "clave" NO es vacia Y "confirmaClave" NO es vacia Y "clave <> confirmaClave": ERROR
        if (!usuarioInfo.getUsuario().getClave().isEmpty() && !confirmaClave.isEmpty()) {
            ContextUtils.evalNonEq(
                    usuarioInfo.getUsuario().getClave(),
                    confirmaClave,
                    passwordConfNotEquals,
                    ui.findComponent("password"),
                    ui.findComponent("passwordConf"));
        }

        // si "clave" NO es vacia y la fortaleza NO estuvo OK: ERROR
        if (!usuarioInfo.getUsuario().getClave().isEmpty() && nuevaClaveEnc == null) {
            UIComponent passComponent = ui.findComponent("password");
            StringBuilder stbd = new StringBuilder();
            for (String msg : notValidPassword) {
                String msg2 = LanguageMB.translate(msg);
                stbd.append("-").append(msg2).append("\n");
            }
            ContextUtils.addErrorMsg(passComponent, stbd.toString());
        }

        // Si el usuario YA existe: ERROR
        ContextUtils.evalNotNull(
                completaRegistroService.getUserByName(this.usuarioInfo.getUsuario().getUsuario()),
                loginAlreadyExists,
                ui.findComponent("usuario"));

        // Si NO hubo errores: BIEN !!!
        if (ContextUtils.countMessages() < 1) {
            LOGGER.debug("Efectuando registro");
            try {
                usuarioInfo.getUsuario().setClave(nuevaClaveEnc);
                usuarioInfo.getUsuarioDetalle().setNombre("");
                usuarioInfo.getUsuarioDetalle().setApPaterno("");
                usuarioInfo.getUsuarioDetalle().setApMaterno("");
                usuarioInfo.getUsuarioDetalle().setDireccion("");
                usuarioInfo.getUsuarioDetalle().setTelefonos("");
                
                completaRegistroService.register(this.usuarioInfo);
                flag = true;
                Audit.write(TipoBitacoraEnum.REGISTRATION_COMPLETED, "Registro exitoso para " + this.usuarioInfo.getUsuario().getUsuario());
            } catch (Exception ex) {
                LOGGER.error("Registro fallido.", ex);
            }
        }

        // Determina el estado de la ventana modal:
        PrimeFacesRequestContext.addCallBackParam(flag);
    }
}// UsuarioDetalle
