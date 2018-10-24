/*
 * .
 * ManagedBean asociado a cambiar datos del usuario
 *
 */
package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioDetalle;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioDetalleService;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioService;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import mx.org.ift.simca.arq.core.support.xpath.CustomValidator;
import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


@Controller
@Scope(value = "view")
public class UsuarioDetalleMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDetalleMB.class);
    
    @Autowired
    private UsuarioDetalleService usuarioDetalleService;
    @Autowired
    private UsuarioService usuarioService;
    private CustomValidator customValidator;
    // Clases del modelo
    private UsuarioDetalle usuarioDetalle;
    private Usuario usuario;
    // Clases auxiliares
    private String authName;
    private String originalMail;

    /**
     * Inicializa al bean.
     */
    @PostConstruct
    public void init() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        this.authName = au.getName();
        this.usuario = usuarioService.getUserByName(authName);
        int idUsuario = this.usuario.getUsuarioPk();
        this.originalMail = usuario.getCorreo();

        this.usuarioDetalle = usuarioDetalleService.cargaDetalle(idUsuario);
        if (this.usuarioDetalle == null) {
            this.usuarioDetalle = new UsuarioDetalle();
            this.usuarioDetalle.setUsuarioFk(idUsuario);
        }

        String validator = UsuarioDetalleMB.class.getResource("/validation/Usuario.xml").getFile();
        customValidator = new CustomValidator(validator);
    }

    /**
     * UsuarioDetalle seleccionado.
     *
     * @return UsuarioDetalle seleccionado.
     */
    public UsuarioDetalle getUsuarioDetalle() {
        return usuarioDetalle;
    }

    /**
     * Usuario seleccionado.
     *
     * @return Usuario seleccionado.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Método que es invocado desde la página UsuarioDetalle.xhtml.
     *
     * Se manda a llamar al presionar el botón "Registra datos personales".
     * Valida que los datos sean correctos, si son correctos actualiza en el
     * sistema los datos del usuario. Si no cumple con alguna validación envía
     * un mensaje en pantalla.
     *
     * @param ae Se utiliza para obtener los componentes de la vista y se
     * aplican validaciones.
     */
    public void guarda(ActionEvent ae) {
        boolean flag = false;

        UIComponent component = ae.getComponent();

        customValidator.validaDatos(component);

        if (this.usuario.getCorreo() != null && !this.usuario.getCorreo().isEmpty()
                && !this.usuario.getCorreo().equalsIgnoreCase(this.originalMail)) {
            Usuario tmp = usuarioService.getUserByCorreo(this.usuario.getCorreo());
            ContextUtils.evalTrue(tmp != null && !tmp.getUsuarioPk().equals(usuario.getUsuarioPk()),
                    "El correo electrónico ya se encuentra registrado",
                    component.findComponent("email"));
        }

        if (ContextUtils.countMessages() < 1) {
            LOGGER.debug("La validacion ha pasado con exito");
            UsuarioInfo usuarioInfo = new UsuarioInfo(usuario, usuarioDetalle);
            try {
                usuarioService.updateUser(usuarioInfo, usuario.getCorreo());
                flag = true;
            } catch (Exception ex) {
                LOGGER.error("Error al actualizar la información del usuario", ex);
                flag = false;
            }
        }

        PrimeFacesRequestContext.addCallBackParam(flag);
    }
}
