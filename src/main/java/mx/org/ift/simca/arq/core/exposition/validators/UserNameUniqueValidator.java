package mx.org.ift.simca.arq.core.exposition.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.service.security.AuthService;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioService;
import mx.org.ift.simca.arq.core.support.BeanLocator;
import mx.org.ift.simca.arq.core.support.regExpEngine.RegExpBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validador utilizado para verificar que el nombre de usuario (login) tenga una
 * longitud determinada y no esté duplicado en la base de datos.
 */
@FacesValidator("mx.org.ift.simca.arq.core.UserNameUniqueValidator")
public class UserNameUniqueValidator extends BaseValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserNameUniqueValidator.class);
    
    private static final int MIN = 5;
    private static final int MAX = 50;
    private static final String USER_NAME_REQUIRED = "El '" + LABEL + "' es requerido";
    private static final String USER_NAME_DUPLICATED = "El '" + LABEL + "' ingresado ya existe y está asociado a otro usuario";
    private static final String USER_NAME_MIN_LENGTH = "El '" + LABEL + "' debe tener menos de " + MIN + " caracteres";
    private static final String USER_NAME_MAX_LENGTH = "El '" + LABEL + "' debe tener menos de " + MAX + " caracteres";
    private static final String USER_NAME_VALIDATED_ERROR = "No es posible hacer la validación del '" + LABEL + "'";
    private static final String USER_NAME_REGEXP;
    private static final String USER_NAME_INVALID;

    static {
        RegExpBean regExpBean = findRegExp("PARSE_LOGIN_NAME");
        if (regExpBean == null) {
            LOGGER.error("No se encontró la expresión regular PARSE_LOGIN_NAME");
            USER_NAME_REGEXP = "^[a-zA-Z]+([\\._\\-]?\\w+)*$";
            USER_NAME_INVALID = "El '" + LABEL
                    + "' sólo permite letras no latinas, dígitos, punto, güión bajo y güión medio, adicionalmente se pide que inici con una letra y medir de "
                    + MIN + " a " + MAX + " caracteres";
        } else {
            USER_NAME_REGEXP = regExpBean.getRegExpString();
            USER_NAME_INVALID = regExpBean.getErrorMessage();
        }
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            throwRequiredMessage(component, USER_NAME_REQUIRED);
        } else if (value instanceof String) {
            String userName = (String) value;

            if (matched(USER_NAME_REGEXP, userName)) {
                if (userName.length() < MIN) {
                    throwValidationMessage(component, USER_NAME_MIN_LENGTH);
                } else if (userName.length() > MAX) {
                    throwValidationMessage(component, USER_NAME_MAX_LENGTH);
                } else {
                    AuthService authService = (AuthService) BeanLocator.getBean("authService");

                    if (authService == null) {
                        throwValidationMessage(component, USER_NAME_VALIDATED_ERROR);
                    } else {

                        String currentUserName = authService.getCurrentUserName();

                        if (currentUserName.equals(AuthService.ANONYMOUS)) {
                            UsuarioService usuarioService = (UsuarioService) BeanLocator.getBean("usuarioService");

                            Usuario usuario = usuarioService.getUserByName(userName);

                            if (usuario != null) {
                                throwValidationMessage(component, USER_NAME_DUPLICATED);
                            }
                        }
                    }
                }
            } else {
                throwValidationMessage(component, USER_NAME_INVALID);
            }
        }
    }
}
