package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

/**
 * La clase AdminPerfilBag se emplea para almacenar la información relevante del perfil de un usuario.
 *
  */
public class AdminPerfilBag implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Representa el perfil a administrar.
     */
    private Perfil perfil;
    /**
     * Representa una bandera de decisión.
     */
    private boolean flag;

    /**
     *
     */
    public AdminPerfilBag() {
    }

    /**
     * Crea una nueva instancia admin perfil bag.
     *
     * @param perfil El perfil del usuario
     *
     * @param flag La variable que indica el estado del perfil del usuario. Puede ser activo o no activo
     */
    public AdminPerfilBag(Perfil perfil, boolean flag) {
        this.perfil = perfil;
        this.flag = flag;
    }

    /**
     *
     * @param perfil
     */
    public AdminPerfilBag(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     *
     * @return
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     *
     * @param perfil
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     *
     * @return
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     *
     * @param flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
