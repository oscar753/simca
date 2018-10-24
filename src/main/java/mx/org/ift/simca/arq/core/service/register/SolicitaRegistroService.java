package mx.org.ift.simca.arq.core.service.register;

import java.io.Serializable;

/**
 * La Interfaz SolicitaRegistroService.
 */
public interface SolicitaRegistroService extends Serializable {

    /**
     * Envia la solicitud de registro.
     *
     * @param correo Correo electrónico del destinatario
     * @param serverUrl La dirección del servidor reportada por el request (con todo y contexto)
     */
    void enviaSolicitud(String correo, String serverUrl);

    /**
     * verifica si el correo electrónico ya se encuentra registrado en la base
     * de datos.
     *
     * @param correo Correo electrónico del destinatario
     * @return true, si es exitoso
     */
    boolean alreadyRegistered(String correo);
}
