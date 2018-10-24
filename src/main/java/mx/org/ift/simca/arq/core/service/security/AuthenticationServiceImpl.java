package mx.org.ift.simca.arq.core.service.security;

import java.util.HashMap;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.arq.core.service.security.login.LoginWorkerService;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import mx.org.ift.simca.arq.core.exposition.LDAPConfig;
import mx.org.ift.simca.arq.core.service.ldap.LDAPService;


/**
 * Implementa el servicio de autenticación de usuarios.
*/
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final long serialVersionUID = 1L;
    
    /**
     * Inyección del bean de spring llamado loginWorkerService.
     */
    @Autowired
    private LoginWorkerService loginWorkerService;
    
    LDAPConfig ldapConfig;
    /**
     * Representa al administrador de recursos para la autenticación.
     */
    @Resource(name = "authenticationManager")
    private transient AuthenticationManager authenticationManager;

    @Override
    public boolean login(String username, char[] password) {
        try {
            
            ldapConfig = new LDAPConfig();

            
            if(!username.equalsIgnoreCase("admin")){
                //authenticationManager.authenticate(a);
                // 15/08/2018.INI.MAL.Validar LDAP por WS
//                HashMap<String,Object> lista = ldapConfig.consultaUsrActiveDirectory(username, new String(password)); 
                HashMap<String, Object> lista = LDAPService.consultaUsrActiveDirectory(username, new String(password));
                // 15/08/2018.FIN.MAL.Validar LDAP por WS

                //if ((boolean)lista.get("autentificado")) {
                if (lista.get("autentificado").equals(true)) {    
                    UsernamePasswordAuthenticationToken au = new UsernamePasswordAuthenticationToken(username, username);
                    Authentication authenticate =authenticationManager.authenticate(au);
                    SecurityContextHolder.getContext().setAuthentication(authenticate);
                    loginWorkerService.cleanErrors(username);
                    return true;
                }else{
                    ContextUtils.addMsg(new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario y/o Contraseña invalidos!", ""));
                }
            }else{
                UsernamePasswordAuthenticationToken au = new UsernamePasswordAuthenticationToken(username, new String(password));
                    Authentication authenticate =authenticationManager.authenticate(au);
                    SecurityContextHolder.getContext().setAuthentication(authenticate);
                    loginWorkerService.cleanErrors(username);
                    return true;
            }
                
                
        } catch (AuthenticationException auth) {
            FacesMessage msg = loginWorkerService.getErrorMessage(auth, username);
            if(!username.equalsIgnoreCase("admin")){
                if(msg.getSummary().equalsIgnoreCase("Usuario y/o Contraseña invalidos!")){
                    msg.setSummary("El usuario no tiene perfil asignado.");
                }
                if(msg.getSummary().equalsIgnoreCase("Password Incorreto !")){
                    msg.setSummary("El usuario no existe en el sistema de datos abiertos");
                }                
            }

            ContextUtils.addMsg(msg);
            
        }
        return false;
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    
}
