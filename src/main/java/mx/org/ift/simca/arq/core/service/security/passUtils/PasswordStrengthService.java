package mx.org.ift.simca.arq.core.service.security.passUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import edu.vt.middleware.password.CharacterRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.MessageResolver;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.PasswordGenerator;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.UppercaseCharacterRule;
import mx.org.ift.simca.arq.core.support.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio que proporciona métodos para validar la fortaleza de un password.
 */
public abstract class PasswordStrengthService implements Serializable {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa un objeto para el Log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordStrengthService.class);

    /**
     * Custom password strength strategy empleado para configurar la fortaleza
     * de una contraseña.
     *
     * @param password el password
     * @return true, si es exitoso
     * @throws PasswordValidationException el password validation exception
     */
    public abstract boolean customPasswordStrengthStrategy(String password) throws PasswordValidationException;

    /**
     * Recupera el international password validator.
     *
     * @param bundle la etiqueta
     * @param ruleList la lista de reglas
     * @return El international password validator es la validadcion
     * internacional del password
     */
    public PasswordValidator getInternationalPasswordValidator(String bundle, List<Rule> ruleList) {
        FileInputStream fis = null;
        try {
            String basePath = PasswordStrengthService.class.getResource(bundle).getFile();
            fis = new FileInputStream(basePath);
            Properties props = new Properties();
            props.load(fis);
            MessageResolver resolver = new MessageResolver(props);
            return new PasswordValidator(resolver, ruleList);
        } catch (IOException e) {
            LOGGER.warn("Falló la recuperación del 'resolver' ({}) se emitirá información en inglés", bundle);
            return new PasswordValidator(ruleList);
        } finally {
            FileUtils.close(fis);
        }
    }
    
    /**
     * Recupera el autogenerated password, el cual es el password generado.
     *
     * @return El autogenerated password
     */
    public String getAutogeneratedPassword() {
        // create a password generator
        PasswordGenerator generator = new PasswordGenerator();
        // create character rules to generate passwords with
        List<CharacterRule> rules = new ArrayList<CharacterRule>();
        rules.add(new DigitCharacterRule(1));
        rules.add(new NonAlphanumericCharacterRule(1));
        rules.add(new UppercaseCharacterRule(1));
        rules.add(new LowercaseCharacterRule(1));
        return generator.generatePassword(8, rules);
    }
}
