package mx.org.ift.simca.arq.core.service.security.passUtils;

import java.util.List;

/**
 * La clase PasswordValidationException.
 *
*/
public class PasswordValidationException extends Exception {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa la lista de mensajes de errores de validación
     */
    private List<String> passwordValidationErrorList;

    /**
     * Crea una nueva instancia password validation exception.
     *
     * @param passwordValidationErrorList el password validation error list
     */
    public PasswordValidationException(List<String> passwordValidationErrorList) {
        this.passwordValidationErrorList = passwordValidationErrorList;
    }

    /**
     * Obtiene la lista de mensajes de errores de validación
     *
     * @return Lista con los mensajes encontrados.
     */
    public List<String> getPasswordValidationErrorList() {
        return passwordValidationErrorList;
    }
}
