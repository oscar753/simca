package mx.org.ift.simca.arq.core.service.security.adminPass;

import java.io.Serializable;

import mx.org.ift.simca.arq.core.model.Usuario;

/**
 * La Interfaz RestorePasswordService.
 *
*/
public interface RestorePasswordService extends Serializable {

    /**
     * Recupera el sid asignado al id de seguridad pasado.
     *
     * @param idSeguridad el id seguridad
     * @return El user by sid
     */
    Usuario getUserBySid(String idSeguridad);

    /**
     * Establece la nueva contraseña.
     *
     * @param usr el usr
     * @param nuevaClave el nueva clave
     */
    void setNewPassword(Usuario usr, String nuevaClave);
}
