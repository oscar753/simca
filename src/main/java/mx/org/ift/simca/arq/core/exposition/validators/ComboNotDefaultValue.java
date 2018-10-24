package mx.org.ift.simca.arq.core.exposition.validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;

/**
 * Validador usado para asegurarse que los combos no toman el valor -1.
  */
@FacesValidator("mx.org.ift.simca.arq.core.ComboNotDefaultValue")
public class ComboNotDefaultValue extends BaseValidator implements Validator {

    private static final String DEFAULT_MSG = "Debe seleccionar un valor de la lista";

    /**
     * Validar si el combo no tomó el valor default (-1).
     *
     * @param fc FacesContext.
     * @param uic Componente jsf.
     * @param value Valor tomado por el componente.
     */
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) {
        if (value == null || !(value instanceof Integer) || ((Integer) value) == -1) {
            throwRequiredMessage(uic, DEFAULT_MSG);
        }
    }
}
