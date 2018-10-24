package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import mx.org.ift.simca.arq.core.service.security.AuthenticationService;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



/**
 * <p>Descripción</p>
 * ManagedBean asociado a los datos del usuario.
 *
 */
@Controller
@Scope("view")
public class LoginMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String LOGIN_VAR_NAME = "LOGIN_ON_SESSION";
    public static final String PASSWORD_VAR_NAME = "PASSWORD_ON_SESSION";
    
    private String usr;
    private String pwd;
    
    private String usuario;
    private String clave;
    
    private Integer idUsuario;
    
    @Value("${defaultFailureUrl}")
    private String defaultFailureUrl;
    @Value("${defaultTargetUrl}")
    private String defaultTargetUrl;
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    UsuarioService usuarioService;
    
   
    public UsuarioLoggeadoMB usuarioLoggeadoMB;

    @PostConstruct
    public void init() {
        usuario = (String) ContextUtils.getSessionMapObject(LOGIN_VAR_NAME);
        clave = (String) ContextUtils.getSessionMapObject(PASSWORD_VAR_NAME);
        idUsuario =0;
    }

    /**
     * Username (login).
     *
     * @return Username (login).
     */
    public String getUsr() {
        return usr;
    }

    /**
     * Username (login).
     *
     * @param usr Username (login).
     */
    public void setUsr(String usr) {
        this.usr = usr;
    }

    /**
     * Password.
     *
     * @return Password.
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Password.
     *
     * @param pwd Password.
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Usuario provisto desde la página de registro.
     *
     * @return Usuario provisto desde la página de registro.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Password provisto desde la página de registro.
     *
     * @return Password provisto desde la página de registro.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Ruta para el login exitoso.
     *
     * @return Ruta para el login exitoso.
     */
    public String login() {
        return log();
    }

    /**
     * Acción realizada para el login.
     *
     * @param ae Evento asociado a la acción.
     */
    public void login(ActionEvent ae) {
        if (this.usr.trim().length() < 1 || this.pwd.trim().length() < 1) {
            FacesMessage msg = new FacesMessage("El usuario y la clave son campos obligatorios");
            ContextUtils.addMsg(msg);
            return;
        }
        
        PrimeFacesRequestContext.addCallBackParam(log() != null);
        
        ContextUtils.removeSessionMapObject(LoginMB.LOGIN_VAR_NAME);
        ContextUtils.removeSessionMapObject(LoginMB.PASSWORD_VAR_NAME);
    }

    private String log() {
        if (authenticationService.login(usr, pwd.toCharArray())) {
            idUsuario=usuarioService.getUserByName(usr).getUsuarioPk();
            System.out.println("usuario en login "+idUsuario);
            usuarioLoggeadoMB.setIdUsuarioLoggeado(idUsuario);
            return defaultTargetUrl;
        }
        return null;
    }
     
    /**
     * Ruta para el logout.
     *
     * @return Ruta para el logout.
     */
    public String logout() {
        authenticationService.logout();
        return defaultFailureUrl;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
