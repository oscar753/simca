package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

/**
 * La clase UsuarioInfo.
 */
public class UsuarioInfo implements Serializable {

    /**
     * Representa el valor inicial de la version serial.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa al usuario.
     */
    private Usuario usuario;
    /**
     * Representa el detalle del usuario.
     */
    private UsuarioDetalle usuarioDetalle;

    /**
     * Crea una nueva instancia usuario info.
     *
     * @param usuario el usuario
     * @param usuarioDetalle el usuario detalle
     */
    public UsuarioInfo(Usuario usuario, UsuarioDetalle usuarioDetalle) {
        this.usuario = usuario;
        this.usuarioDetalle = usuarioDetalle;
    }

    /**
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public UsuarioDetalle getUsuarioDetalle() {
        return usuarioDetalle;
    }

    /**
     *
     * @param usuarioDetalle
     */
    public void setUsuarioDetalle(UsuarioDetalle usuarioDetalle) {
        if (usuarioDetalle != null) {
            this.usuarioDetalle = usuarioDetalle;
        } else {
            this.usuarioDetalle = new UsuarioDetalle();
        }
    }
}
