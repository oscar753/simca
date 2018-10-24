package mx.org.ift.simca.arq.core.service.security;

import java.io.Serializable;

public interface AuthenticationService extends Serializable {

    /**
     * Lleva a cabo el proceso de autenticación del usuario, tomando como argumentos el nombre de usuario y contraseña.
     *
     * @param username Nombre de usuario
     *
     * @param password Clave o contraseña del usuario
     *
     * @return true si los valores son correctos y se pudo autenticar
     */
    boolean login(String username, char[] password);

    /**
     * Lleva a cabo la tarea de registar la salida del usuario.
     */
    void logout();
}
