package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad perfil
  */
public class Perfil implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer perfilPk;
    private String codigoPerfil;
    private String descripcionPerfil;

    /**
     * Constructor default de la clase.
     */
    public Perfil() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     * @param perfilPk Llave primaria.
     */
    public Perfil(Integer perfilPk) {
        this.perfilPk = perfilPk;
    }

    /**
     * Llave primaria.
     * @return Llave primaria.
     */
    public Integer getPerfilPk() {
        return perfilPk;
    }

    /**
     * Llave primaria.
     * @param perfilPk Llave primaria.
     */
    public void setPerfilPk(Integer perfilPk) {
        this.perfilPk = perfilPk;
    }

    /**
     * Identificador de texto del perfil.
     * @return Identificador de texto del perfil.
     */
    public String getCodigoPerfil() {
        return codigoPerfil;
    }

    /**
     * Identificador de texto del perfil.
     * @param codigoPerfil Identificador de texto del perfil.
     */
    public void setCodigoPerfil(String codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    /**
     * Descripción breve.
     * @return Descripción breve.
     */
    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    /**
     * Descripción breve.
     * @param descripcionPerfil Descripción breve.
     */
    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    /**
     * Identificador de texto del perfil para spring.
     * @return Identificador de texto del perfil para spring.
     */
    @Override
    public String getAuthority() {
        return codigoPerfil;
    }
}
