package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad usuario_perfil
 */
public class UsuarioPerfil implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer usuarioFk;
    private Integer perfilFk;

    /**
     * Constructor default de la clase.
     */
    public UsuarioPerfil() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     * @param usuarioFk Parte de la llave primaria.
     * @param perfilFk Parte de la llave primaria.
     */
    public UsuarioPerfil(Integer usuarioFk, Integer perfilFk) {
        this.usuarioFk = usuarioFk;
        this.perfilFk = perfilFk;
    }

    /**
     * Parte de la llave primaria.
     * @return
     */
    public Integer getUsuarioFk() {
        return usuarioFk;
    }

    /**
     * Parte de la llave primaria.
     * @param usuarioFk
     */
    public void setUsuarioFk(Integer usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    /**
     * Parte de la llave primaria.
     * @return
     */
    public Integer getPerfilFk() {
        return perfilFk;
    }

    /**
     * Parte de la llave primaria.
     * @param perfilFk
     */
    public void setPerfilFk(Integer perfilFk) {
        this.perfilFk = perfilFk;
    }
}
