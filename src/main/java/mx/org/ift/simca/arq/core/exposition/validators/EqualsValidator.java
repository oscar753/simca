package mx.org.ift.simca.arq.core.exposition.validators;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validador utilizado para verificar que el objeto actual es igual a otro
 * objeto (buscado por medio del componente).
  */
@FacesValidator("mx.org.ift.simca.arq.core.EqualsValidator")
public class EqualsValidator extends BaseValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(EqualsValidator.class);
    
    private static final String PARAM_NAME = "compareToId";
    private static final String LABEL_COMPARE = "#"+"#"+"SECOND_LABEL"+"#"+"#";
    private static final String NOT_EQUALS = "El " + LABEL + " debe coincidir con " + LABEL_COMPARE;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        String compareToId = (String) component.getAttributes().get(PARAM_NAME);

        if (value == null || !(value instanceof String)) {
            LOGGER.error("El objeto a validar era nulo o el valor no era el tipo esperado, con id: {}", component.getId());
        } else if (compareToId == null) {
            LOGGER.error("No se encontró el parámetro {} con el ID del componente a comparar.", PARAM_NAME);
        } else {
            UIComponent compareTo = component.findComponent(compareToId);

            if (compareTo == null) {
                LOGGER.error("No se encontró el componente a comparar, con id: {}", compareToId);
            } else if (compareTo instanceof UIInput) {
                UIInput input = (UIInput) compareTo;
                Object obj = input.getValue();

                if (obj instanceof String) {
                    if (!value.equals(obj)) {
                        String message = getReplacedMessage(NOT_EQUALS, LABEL_COMPARE, getLabel(compareTo));
                        throwValidationMessage(component, message);
                    }
                } else {
                    LOGGER.warn("No se puede realizar la comprarción, pues el valor del objeto a comprar no era del tipo String. Con id: {}", compareToId);
                }
            }
        }


    }
}
