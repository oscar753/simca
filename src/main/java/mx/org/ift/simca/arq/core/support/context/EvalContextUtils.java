package mx.org.ift.simca.arq.core.support.context;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

public class EvalContextUtils extends BaseContextUtils {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Expresión regular para comparar un email.
     */
    public static final String MAIL_REG_EX = "([\\w-_]+\\.)*[\\w-_]+\\@([\\w-_]+\\.)+[a-zA-Z]{2,3}";
    /**
     * La constante NAME_REG_EX.
     */
    public static final String NAME_REG_EX = "[a-zA-ZÀ-ÖØ-öø-ÿ]+(( |\\-)[a-zA-ZÀ-ÖØ-öø-ÿ]+)*";

    /**
     * Por ser una clase utilitaria, el constructor no es público.
     */
    protected EvalContextUtils() {
    }

    /**
     * Eval min num.
     *
     * @param edad el edad
     * @param minNum el min num
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalMinNum(int edad, int minNum, String message, UIComponent ui) {
        boolean poca = edad < minNum;
        if (poca) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return poca;
    }

    /**
     * Eval min num.
     *
     * @param edad la edad
     * @param minNum el numero minimo
     * @param message el mensaje
     * @param ui el componente
     * @return true, si es exitoso
     */
    public static boolean evalMinNum(int edad, int minNum, FacesMessage message, UIComponent ui) {
        boolean poca = edad < minNum;
        if (poca) {
            ContextUtils.addMsg(ui, message);
        }
        return poca;
    }

    /**
     * Evalúa el el máximo valor.
     *
     * @param edad el edad
     * @param maxNum el max num
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalMaxNum(int edad, int maxNum, String message, UIComponent ui) {
        boolean mucha = edad >= maxNum;
        if (mucha) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return mucha;
    }

    /**
     * Evalúa el mínimo valor.
     *
     * @param edad la edad
     * @param maxNum el número máximo
     * @param message el mensaje
     * @param ui el componente UI
     * @return true, si es exitoso
     */
    public static boolean evalMaxNum(int edad, int maxNum, FacesMessage message, UIComponent ui) {
        boolean mucha = edad >= maxNum;
        if (mucha) {
            ContextUtils.addMsg(ui, message);
        }
        return mucha;
    }

    /**
     * Evalua el tamaño máximo
     *
     * @param source el source
     * @param maxLen el max len
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalMaxLen(String source, int maxLen, String message, UIComponent ui) {
        boolean largo = source.length() > maxLen;
        if (largo) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return largo;
    }

    /**
     * Eval max len.
     *
     * @param source el source
     * @param maxLen el max len
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalMaxLen(String source, int maxLen, FacesMessage message, UIComponent ui) {
        boolean largo = source.length() > maxLen;
        if (largo) {
            ContextUtils.addMsg(ui, message);
        }
        return largo;
    }

    /**
     * Eval min len.
     *
     * @param source el source
     * @param minLen el min len
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalMinLen(String source, int minLen, String message, UIComponent ui) {
        boolean cortito = source.length() < minLen;
        if (cortito) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return cortito;
    }

    /**
     * Eval min len.
     *
     * @param source el source
     * @param minLen el min len
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalMinLen(String source, int minLen, FacesMessage message, UIComponent ui) {
        boolean cortito = source.length() < minLen;
        if (cortito) {
            ContextUtils.addMsg(ui, message);
        }
        return cortito;
    }

    /**
     * Eval non eq.
     *
     * @param src1 el src1
     * @param src2 el src2
     * @param message el message
     * @param ui1 el ui1
     * @param ui2 el ui2
     * @return true, si es exitoso
     */
    public static boolean evalNonEq(String src1, String src2, String message, UIComponent ui1, UIComponent ui2) {
        boolean notEquals = !src1.equals(src2);
        if (notEquals) {
            ContextUtils.addErrorMsg(ui1, message);
            ContextUtils.addErrorMsg(ui2, message);
        }
        return notEquals;
    }

    /**
     * Eval non eq.
     *
     * @param src1 el src1
     * @param src2 el src2
     * @param message el message
     * @param ui1 el ui1
     * @param ui2 el ui2
     * @return true, si es exitoso
     */
    public static boolean evalNonEq(String src1, String src2, FacesMessage message, UIComponent ui1, UIComponent ui2) {
        boolean notEquals = !src1.equals(src2);
        if (notEquals) {
            ContextUtils.addMsg(ui1, message);
            ContextUtils.addMsg(ui2, message);
        }
        return notEquals;
    }

    /**
     * Eval empty2.
     *
     * @param source el source
     * @param messageKey el message key
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalEmpty2(String source, String messageKey, UIComponent ui) {
        return evalEmpty(source, RB.getString(messageKey), ui);
    }
    /**
     * Representa el bundle recursos de la mensajeria con texto
     */
    private static final ResourceBundle RB = ResourceBundle.getBundle("i18n/textMessages");

    /**
     * Eval empty.
     *
     * @param source el source
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalEmpty(String source, String message, UIComponent ui) {
        boolean isEmpty = source == null || source.trim().length() < 1;
        if (isEmpty) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return isEmpty;
    }

    /**
     * Eval empty.
     *
     * @param source el source
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalEmpty(String source, FacesMessage message, UIComponent ui) {
        boolean isEmpty = source == null || source.trim().length() < 1;
        if (isEmpty) {
            ContextUtils.addMsg(ui, message);
        }
        return isEmpty;
    }

    /**
     * Evalua si una expresión es o no nula. Si es nula, regresa falso. Si es no
     * nula, regresa verdadero. En caso de no ser nula, agrega un mensaje al
     * contexto de JSF
     *
     * @param source Expresión a ser evaluada
     * @param message Mensaje para agregar en caso de que source!=null es
     * verdadero
     * @param ui Componente al que se le va a agregar el mensaje de JSF
     * @return bolean indicando la evaluación de 'source'
     */
    public static boolean evalNotNull(Object source, String message, UIComponent ui) {
        boolean isNotNull = source != null;
        if (isNotNull) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return isNotNull;
    }

    /**
     * Eval not null.
     *
     * @param source el source
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalNotNull(Object source, FacesMessage message, UIComponent ui) {
        boolean isNotNull = source != null;
        if (isNotNull) {
            ContextUtils.addMsg(ui, message);
        }
        return isNotNull;
    }

    /**
     * Eval true.
     *
     * @param b el b
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalTrue(boolean b, String message, UIComponent ui) {
        if (b) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return b;
    }

    /**
     * Eval true.
     *
     * @param b el b
     * @param message el message
     * @param ui el ui
     * @return true, si es exitoso
     */
    public static boolean evalTrue(boolean b, FacesMessage message, UIComponent ui) {
        if (b) {
            ContextUtils.addMsg(ui, message);
        }
        return b;
    }

    /**
     * Usa evalMalformedExpression para evaluar si una cadena representa la
     * estructura de un correo electrónico. Adicionalmente, coloca un mensaje de
     * advertencia en los mensajes de JSF si la cadena no cumple con la
     * estructura estándar de correo electrónico.
     *
     * @param mailCandidate Cadena que tentativamente representa un correo
     * electrónico
     * @param message el message
     * @param ui el ui
     * @return true si la cadena candidata NO posee una estructura de correo
     * electrónico. False en caso de que si cumpla.
     */
    public static boolean evalMalformedMail(String mailCandidate, String message, UIComponent ui) {
        boolean malformed = evalMalformedMail(mailCandidate);
        if (malformed) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return malformed;
    }

    /**
     * Usa evalMalformedExpression para evaluar si una cadena representa la
     * estructura de un correo electrónico. Adicionalmente, coloca un mensaje de
     * advertencia en los mensajes de JSF si la cadena no cumple con la
     * estructura estándar de correo electrónico.
     *
     * @param mailCandidate Cadena que tentativamente representa un correo
     * electrónico
     * @param message Mensaje a agregar
     * @param ui Componente al que se le agregará el mensaje
     * @return true si la cadena candidata NO posee una estructura de correo
     * electrónico. False en caso de que si cumpla.
     */
    public static boolean evalMalformedMail(String mailCandidate, FacesMessage message, UIComponent ui) {
        boolean malformed = evalMalformedMail(mailCandidate);
        if (malformed) {
            ContextUtils.addMsg(ui, message);
        }
        return malformed;
    }

    /**
     * Evalua si una cadena tiene los caracteres de un nombre, en caso de que la
     * cadena esté vacía no se hace la evaluación.
     *
     * @param name Cadena a evaluar
     * @param message Mensaje a mostrar en caso de que la cadena no cumpla con
     * con la evaluación
     * @param ui Componente al que se le agregará el mensaje en forma de
     * advertencia
     * @return Resultado de la evaluación
     */
    public static boolean evalNotValidName(String name, String message, UIComponent ui) {
        boolean notValid = evalNotValidName(name);
        if (notValid) {
            ContextUtils.addErrorMsg(ui, message);
        }
        return notValid;
    }

    /**
     * Evalua si una cadena tiene los caracteres de un nombre, en caso de que la
     * cadena esté vacía no se hace la evaluación.
     *
     * @param name Cadena a evaluar
     * @param message Mensaje a agregar en caso de que la cadena no cumpla con
     * con la evaluación
     * @param ui Componente al que se le agregará el mensaje
     * @return Resultado de la evaluación
     */
    public static boolean evalNotValidName(String name, FacesMessage message, UIComponent ui) {
        boolean notValid = evalNotValidName(name);
        if (notValid) {
            ContextUtils.addMsg(ui, message);
        }
        return notValid;
    }

    /**
     * Usa evalMalformedExpression para evaluar si una cadena representa la
     * estructura de un correo electrónico.
     *
     * @param mailCandidate Cadena que tentativamente representa un correo
     * electrónico
     * @return true si la cadena candidata NO posee una estructura de correo
     * electrónico. False en caso de que SI cumpla.
     */
    public static boolean evalMalformedMail(String mailCandidate) {
        return evalMalformedExpression(mailCandidate, MAIL_REG_EX);
    }

    /**
     * Usa evalMalformedExpression para evaluar si una cadena representa un
     * nombre o apellido, en caso de que la cadena esté vacía no se hace la
     * evaluación.
     *
     * @param name Cadena a evaluar
     * @return Verdadero si la cadena NO posee los caracteres de un nombre o
     * apellido
     */
    public static boolean evalNotValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        } else {
            return evalMalformedExpression(name, NAME_REG_EX);
        }
    }

    /**
     * Evalúa si una cadena cumple con un patrón definido como expresión
     * regular.
     *
     * @param candidate Cadena candidata a ser evaluada
     * @param expression Expresión regular que debe cumplir la cadena candidata
     * @return true en caso de que la cadena candidata NO cumpla con la
     * expresión regular. False en caso de que SI cumpla.
     */
    public static boolean evalMalformedExpression(String candidate, String expression) {
//        Pattern p = Pattern.compile(expression);
//        return !p.matcher(candidate).matches();
        return !candidate.matches(expression);
    }
}
