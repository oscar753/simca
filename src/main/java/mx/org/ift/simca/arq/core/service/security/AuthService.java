package mx.org.ift.simca.arq.core.service.security;

import java.io.Serializable;
import java.util.Collection;

/**
 * AuthService.java - Publicita las funciones necesarias para autorizar a los usuarios firmados.
 */
public interface AuthService extends Serializable {

    /**
     * Representa el valor por defecto de un usuario anónimo o sin autenticar.
     */
    String ANONYMOUS = "anonymousUser";

    /**
     * Regresa el identificador único del usuario
     *
     * @return Identificador del usuario actualmente conectado.
     */
    int getCurrentUserId();

    /**
     * Regresa el nombre del usuario actualmente conectado.
     *
     * @return Nombre del usuario actualmente conectado.
     */
    String getCurrentUserName();

    /**
     * Indica si el usuario es anónimo.
     *
     * @return true si el usuario es anónimo y false en caso contrario.
     */
    boolean isAnonymous();

    /**
     * Indica si el usuario está firmado al sistema.
     *
     * @return True si el usuario está firmado al sistema y false en caso contrario.
     */
    boolean isLogged();

    /**
     * Devuelve los permisos con que cuenta el usuario firmado.
     *
     * @return Permisos del usuario.
     */
    Collection<String> getCurrentUserAuthorities();
}
