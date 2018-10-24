package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import javax.faces.event.ActionEvent;
import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioService;
import mx.org.ift.simca.arq.core.service.security.passUtils.PasswordFacadeService;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("view")
public class RegistroSimpleMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistroSimpleMB.class);
    
    private Usuario usuario = new Usuario();
    private String confirmaClave;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PasswordFacadeService passwordFacadeService;

    public String getConfirmaClave() {
        return confirmaClave;
    }

    public void setConfirmaClave(String confirmaClave) {
        this.confirmaClave = confirmaClave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void registra(ActionEvent event) {
        boolean res;

        try {
            usuario.setClave(passwordFacadeService.getPasswordOnlyEncrypted(usuario.getClave(), usuario.getUsuario()));
            usuarioService.insertFullUser(new UsuarioInfo(usuario, null));

            res = true;
        } catch (Exception ex) {
            LOGGER.error("Error al realizar el registro", ex);
            ContextUtils.addErrorMsg("Ocurrió un error al realizar el registro, intente más tarde");
            res = false;
        }

        PrimeFacesRequestContext.addCallBackParam(res);
    }
}
