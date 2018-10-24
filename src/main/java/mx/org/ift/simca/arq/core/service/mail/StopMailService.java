package mx.org.ift.simca.arq.core.service.mail;

import java.io.Serializable;

import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioDetalle;

/**
 * La Interfaz StopMailService.
 *
 */
public interface StopMailService extends Serializable {

    /**
     * Recupera el usuario mediante el sid que se le asigno.
     *
     * @param sid El sid
     * @return El usuario
     */
    Usuario getUser(String sid);

    /**
     * Detiene el envío de correo promocional a un usuario.
     *
     * @param idUsuario el id usuario
     * @return El usuario detalle
     */
    UsuarioDetalle stopMail(int idUsuario);
}
