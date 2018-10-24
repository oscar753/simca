package mx.org.ift.simca.arq.core.service.security.passUtils;

import java.util.ArrayList;
import java.util.List;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * Utilidad que permite encriptar cadenas y adicionalmente puede verificar la
 * fortaleza de la cadena que se desea encriptar.
 *
 * OJO: ESTE "Spring-Bean" HA SIDO DECLARADO EN EL ARCHIVO:
 * applicationContext.xml junto con el bean de: passwordStrengthService y
 * passwordStrengthService2
*/
public class PasswordFacadeServiceImpl implements PasswordFacadeService {

    private static final long serialVersionUID = 1L;
    
    /**
     * Representa un codificador de contraseñas
     */
    @Autowired
    private transient PasswordEncoder passwordEncoder;
    /**
     * Obtiene el valor customSalt del archivo de configuración y lo asigna
     */
    @Value("${customSalt}")
    private String customSalt;
    /**
     * Representa el servicio de validación de fortaleza de una contraseña
     */
    private PasswordStrengthService passwordStrengthService = null;

    /**
     * No establece ningún servicio de fortaleza de claves.
     */
    public PasswordFacadeServiceImpl() {
    }

    /**
     * Permite establecer un servicio de verificacion fortaleza de claves.
     *
     * @param passwordStrengthService el password strength service
     */
    public PasswordFacadeServiceImpl(PasswordStrengthService passwordStrengthService) {
        this.passwordStrengthService = passwordStrengthService;
    }

    /**
     * Retorna una cadena que contiene el "salt" a ser usado en los procesos de
     * encripción de los encoders de passwords
     *
     * @return Retorna el "salt" a utilizar con base en el usuario logueado
     */
    private String getSaltForLoggedUser() {
        return getSalt(ContextUtils.getCurrentUserName());
    }

    /**
     * Construye la frase para encripción del password.
     *
     * @param user Usuario para el cual se va a obtener el salero
     * @return Retorna la concatenación del "salt" con un nombre dado
     */
    private String getSalt(String user) {
        StringBuilder stbd = new StringBuilder();
        stbd.append(user).append(customSalt);
        return stbd.toString();
    }

    /**
     * Encripta una cadena dada, con base en el encoder de encripción
     * determinado.
     *
     * @param password el password
     * @return cadena encriptada.
     */
    @Override
    public String getPasswordOnlyEncrypted(String password) {
        return getEncryptedText(password, getSaltForLoggedUser());
    }
    
    /**
     * Lleva a cabo la tarea de codificar una contraseña utilizando la frase
     * como salero y obtiene el texto codificado
     *
     * @param password Contraseña
     * @param phrase Frase para la inicialización del vector
     * @return contraseña cifrada
     */
    @Override
    public String getPasswordOnlyEncrypted(String password, String phrase) {
        return getEncryptedText(password, getSalt(phrase));
    }

    /**
     * Obtiene el texto especificado codificado
     *
     * @param text Texto o cadena que se desea codificar
     * @param obj Instancia asociada
     * @return texto codificado
     */
    private String getEncryptedText(String text, Object obj) {
        return passwordEncoder.encodePassword(text, obj);
    }

    /**
     * Encripta una cadena que debe de cumplir con ciertos criterios de
     * fortaleza. Si la cadena dada no cumple esos criterios, entonces dispara
     * una excepción que contiene la lista de criterios no cumplidos.
     *
     * @param password el password
     * @return cadena encriptada.
     * @throws PasswordValidationException el password validation exception
     */
    @Override
    public String getPasswordEncrypted(String password) throws PasswordValidationException {
        return getPasswordOnlyEncrypted(null, password);
    }

    @Override
    public String getPasswordEncrypted(String login, String password) throws PasswordValidationException {
        if (passwordStrengthService != null && !passwordStrengthService.customPasswordStrengthStrategy(password)) {
            ArrayList<String> errores = new ArrayList<String>();
            errores.add("El password no cumple con las reglas de fortaleza necesarias");
            throw new PasswordValidationException(errores);
        }
        
        if (login != null) {
            return getPasswordOnlyEncrypted(password, login);
        } else {
            return getPasswordOnlyEncrypted(password);
        }
    }

    /**
     * Retorna la encripción del argumento dado y si hubo alguna infracción a
     * las reglas de fortaleza, regresa una cadena nula e inserta en
     * <code>message</code> la lista de errores.
     *
     * @param password el password
     * @param passwordValidationErrorList el password validation error list
     * @return El password encrypted
     */
    @Override
    public String getPasswordEncrypted(String password, List<String> passwordValidationErrorList) {
        return getPasswordEncrypted(null, password, passwordValidationErrorList);
    }

    @Override
    public String getPasswordEncrypted(String login, String password, List<String> passwordValidationErrorList) {
        String res;
        try {
            if (login != null) {
                res = getPasswordEncrypted(login, password);
            } else {
                res = getPasswordEncrypted(password);
            }
        } catch (PasswordValidationException ex) {
            res = null;
            buildMessage(ex, passwordValidationErrorList);
        }
        return res;
    }

    /**
     * Construye una cadena que concatena una serie de errores de validación de
     * fortaleza de password.
     *
     * @param exception Objeto de tipo PasswordValidationException
     * @param passwordValidationErrorList Listado de posibles errores de
     * validación
     */
    private void buildMessage(PasswordValidationException exception, List<String> passwordValidationErrorList) {
        if (exception != null && exception.getPasswordValidationErrorList() != null) {
            for (String message : exception.getPasswordValidationErrorList()) {
                if (message != null && message.trim().length() > 0) {
                    passwordValidationErrorList.add(message);
                }
            }
        }
    }
}// fin de clase *****
