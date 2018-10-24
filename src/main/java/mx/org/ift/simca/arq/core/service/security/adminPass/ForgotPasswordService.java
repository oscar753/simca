package mx.org.ift.simca.arq.core.service.security.adminPass;

import java.io.Serializable;

import mx.org.ift.simca.arq.core.model.Usuario;

/**
 * La Interfaz ForgotPasswordService.
 *
*/
public interface ForgotPasswordService extends Serializable {

    /**
     * Send email to restore password se encarga de envíar un correo electrónico
     * con un liga para recuperar la contraseña que el usuario tenía, se emplea
     * en el caso de uso Recuperar contraseña.
     *
     * @param usr el usr
     * @param correo el correo
     * @param serverUrl La dirección del servidor reportada por el request (con todo y contexto)
     */
    void sendEmailToRestorePassword(Usuario usr, String correo, String serverUrl);
}
