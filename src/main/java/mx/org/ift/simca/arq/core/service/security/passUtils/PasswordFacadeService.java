package mx.org.ift.simca.arq.core.service.security.passUtils;

import java.io.Serializable;
import java.util.List;

/**
 * La Interfaz PasswordFacadeService.
 */
public interface PasswordFacadeService extends Serializable {

    /**
     * La constante PASSWORD_STRENGTH_FAIL_MSG.
     */
    String PASSWORD_STRENGTH_FAIL_MSG = "PasswordStrengthFailMsg";

    /**
     * Método utilizado para obtener el password con el cifrado determinado, no
     * se hace verificación alguna de la fortaleza del password.
     *
     * @param password Cadena a cifrar
     * @return Cadena cifrada
     */
    String getPasswordOnlyEncrypted(String password);
    
    /**
     * Método utilizado para obtener el password con el cifrado determinado, no
     * se hace verificación alguna de la fortaleza del password.
     *
     * @param password Cadena a cifrar
     * @param phrase Cadena extra para generar el cifrado
     * @return Cadena cifrada
     */
    String getPasswordOnlyEncrypted(String password, String phrase);

    /**
     * Método utilizado para obtener el password con el cifrado determinado en
     * la configuración de spring, adicionalmente se pasa por la verificación de
     * fortaleza del password.
     *
     * @param password Cadena a cifrar y, en el caso, verificar
     * @return Cadena cifrada
     * @throws PasswordValidationException Excepción lanzada en caso de que el
     * password no cumpla con la validación de fortaleza
     */
    String getPasswordEncrypted(String password) throws PasswordValidationException;

    /**
     * Método utilizado para obtener el password con el cifrado determinado en
     * la configuración de spring, adicionalmente se pasa por la verificación de
     * fortaleza del password.
     *
     * @param password Cadena a cifrar y, en el caso, verificar
     * @param passwordValidationErrorList el password validation error list
     * @return Cadena cifrada o nulo si la cadena no cumple con la validación
     */
    String getPasswordEncrypted(String password, List<String> passwordValidationErrorList);

    /**
     * Método utilizado para obtener el password con el cifrado determinado, no
     * se hace verificación alguna de la fortaleza del password.
     * 
     * @param login para el salt
     * @param password cadena a cifrar
     * @return  Cadena cifrada o nulo si la cadena no cumple con la validación
     * @throws PasswordValidationException 
     */
    String getPasswordEncrypted(String login, String password) throws PasswordValidationException;

    /**
     * Método utilizado para obtener el password con el cifrado determinado, no
     * se hace verificación alguna de la fortaleza del password.
     * 
     * @param login para el salt
     * @param password cadena a cifrar
     * @param passwordValidationErrorList listado de errores de validación
     * @return  Cadena cifrada o nulo si la cadena no cumple con la validación
     */
    String getPasswordEncrypted(String login, String password, List<String> passwordValidationErrorList);
}
