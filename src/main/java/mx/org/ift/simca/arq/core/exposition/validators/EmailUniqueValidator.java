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
 * Validador usado para asegurarse que el email ingresado es válido y no se repite en la base de datos.
  */
@FacesValidator("mx.org.ift.simca.arq.core.EmailUniqueValidator")
public class EmailUniqueValidator extends BaseValidator implements Validator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUniqueValidator.class);
    
    private static final int MAX = 254;
    
    private static final String EMAIL_REQUIRED = "El '" + LABEL + "' es requerido";
    private static final String EMAIL_DUPLICATED = "El '" + LABEL + "' ingresado ya existe y está asociado a otro usuario";
    private static final String EMAIL_MAX_LEGTH = "El '" + LABEL + "' debe tener menos de " + MAX + " caracteres";
    private static final String EMAIL_VALIDATED_ERROR = "No es posible hacer la validación del '" + LABEL + "'";
    private static final String EMAIL_REGEXP;
    private static final String EMAIL_INVALID;
    
    static {
        RegExpBean regExpBean = findRegExp("PARSE_MAIL");
        if (regExpBean == null) {
            LOGGER.error("No se encontró la expresión regular PARSE_MAIL");
            EMAIL_REGEXP = "([\\w-_]+\\.)*[\\w-_]+\\@([\\w-_]+\\.)+[a-zA-Z]{2,3}";
            EMAIL_INVALID = "Debe ingresar un '" + LABEL + "' válido";
        } else {
            EMAIL_REGEXP = regExpBean.getRegExpString();
            EMAIL_INVALID = regExpBean.getErrorMessage();
        }
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            throwRequiredMessage(component, EMAIL_REQUIRED);
        } else if (value instanceof String) {
            String email = (String) value;
            if (email.isEmpty()) {
                throwRequiredMessage(component, EMAIL_REQUIRED);
            } else if (matched(EMAIL_REGEXP, email)) {
                if (email.length() > MAX) {
                    throwRequiredMessage(component, EMAIL_MAX_LEGTH);
                } else {
                    AuthService authService = (AuthService) BeanLocator.getBean("authService");
                    UsuarioService usuarioService = (UsuarioService) BeanLocator.getBean("usuarioService");

                    if (authService == null || usuarioService == null) {
                        throwRequiredMessage(component, EMAIL_VALIDATED_ERROR);
                    } else {
                        int usuarioPk = authService.getCurrentUserId();
                        Usuario usuario = usuarioService.getUserByCorreo(email);
                        if (usuario != null && (usuarioPk == 0 || usuario.getUsuarioPk() != usuarioPk)) {
                            throwRequiredMessage(component, EMAIL_DUPLICATED);
                        }
                    }
                }
            } else {
                throwRequiredMessage(component, EMAIL_INVALID);
            }
        }
    }
    
}
