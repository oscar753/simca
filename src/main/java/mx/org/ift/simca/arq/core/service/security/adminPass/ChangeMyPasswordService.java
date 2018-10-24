package mx.org.ift.simca.arq.core.service.security.adminPass;

import java.io.Serializable;

import mx.org.ift.simca.arq.core.model.Usuario;

/**
 * La Interfaz ChangeMyPasswordService.
 *
*/
public interface ChangeMyPasswordService extends Serializable {

    /**
     * Cambia clave.
     *
     * @param usr el usr
     * @param nuevaClave el nueva clave
     */
    void cambiaClave(Usuario usr, String nuevaClave);

    /**
     * Recupera el current logged username.
     *
     * @return El current logged username
     */
    Usuario getCurrentLoggedUsername();
    
}
