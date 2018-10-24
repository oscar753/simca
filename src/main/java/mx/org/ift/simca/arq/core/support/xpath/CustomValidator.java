package mx.org.ift.simca.arq.core.support.xpath;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import mx.org.ift.simca.arq.core.support.Convert;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import mx.org.ift.simca.arq.core.support.regExpEngine.RegExpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * La clase CustomValidator.
 *
*/
public class CustomValidator implements Serializable {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa un objeto para el Log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomValidator.class);
    /**
     * Representa el objeto disponible para el Parser de objetos DOM
     */
    private JDOMParser jdom;
    private static final String LABEL_KEY = "\\[label\\]";
    private static final String MIN_VALUE_KEY = "\\[minVal\\]";
    private static final String MAX_VALUE_KEY = "\\[maxVal\\]";
    private static final String MIN_LENGTH_KEY = "\\[minLen\\]";
    private static final String MAX_LENGTH_KEY = "\\[maxLen\\]";
    private static final String MIN_DATE_KEY = "\\[minDate\\]";
    private static final String MAX_DATE_KEY = "\\[maxDate\\]";

    /**
     * Crea una nueva instancia custom validator.
     *
     * @param validationFilename el validation filename
     */
    public CustomValidator(String validationFilename) {
        InputStream is = null;
        try {
            is = CustomValidator.class.getClassLoader().getResourceAsStream(validationFilename);
            if (is != null) {
               this.jdom = new JDOMParser(is);
            } else {
                throw new RuntimeException("[XSD File does not exists] [JDOMParser::Constructor] ->" + validationFilename);
            }
            
        } finally {
            try {
                if (is != null) {
                  is.close();
                }
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CustomValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param iStream
     */
    public CustomValidator(InputStream iStream) {
        this.jdom = new JDOMParser(iStream);
    }

    /**
     * Lleva a cabo la evaluación del valor y el nombre
     *
     * @param value Valor a revisar
     * @param name Nombre del valor a revisar
     * @return cadena de texto con la evaluación
     */
    private String checa(String value, String name) {
        try {
            evaluate(value, name);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Valida los datos del componente que se le ha pasado.
     *
     * @param ui el componente
     * @return true, si es exitoso
     */
    public boolean validaDatos(UIComponent ui) {
        String[] names = getAttributeNamesForValidation();
        for (String xmlName : names) {
            String widgetName = getWidgetName(xmlName);
            if (widgetName != null) {
                UIComponent cmp = ui.findComponent(widgetName);
                if (cmp != null) {
                    String objValue = getObjValue(cmp);
                    if (objValue != null) {
                        String message = checa(objValue, xmlName);
                        if (message != null) {
                            ContextUtils.addErrorMsg(cmp, message);
                            if (cmp instanceof UIInput) {
                                UIInput uiInput = (UIInput) cmp;
                                uiInput.setValid(false);
                            }
                        }
                    }// if onjValue
                }// if cmp
            } else {
                LOGGER.warn("El objeto: {} definido en el XML no existe en el formulario", xmlName);
            }// if widgetName
        }
        return ContextUtils.countMessages() < 1;
    }

    /**
     * Obtiene el valor de un objeto dado
     *
     * @param cmp Componente de Interfaz de usuario que se desea consultar
     * @return el valor del objeto si es encontrado
     */
    private String getObjValue(UIComponent cmp) {
        try {
            UIInput input = (UIInput) cmp;
            Object objValue = input.getValue();
            if (objValue != null) {
                return objValue.toString();
            }
            return null;
        } catch (Exception ex) {
            LOGGER.error("Error al recuperar el valor del componente", ex);
            return null;
        }
    }

    private void test(boolean expression, String message) throws Exception {
        if (expression) {
            throw new Exception(message);
        }
    }

    /**
     * Realiza la evaluación especificada
     *
     * @param valor Valor que se desea evaluar
     * @param name Nombre o llave asociada al valor
     * @throws Exception general
     */
    private void evaluate(String valor, String name) throws Exception {
        String root = jdom.getRoot(name);
        String tipo = jdom.getType(name);

        if (root == null || tipo == null) {
            throw new Exception("No existe la llave " + name);
        }
        int len = valor.length();

        String label = "{" + jdom.getNodeValue(root + "@label") + "}";

        if (tipo.equals("/data-type-text")) {
            String er = jdom.getNodeValue(root + tipo + "/@regExp");
            if (!er.trim().equalsIgnoreCase("NO_PARSE") && len != 0) {
                RegExpUtil instance = RegExpUtil.getInstance();
                String[] expresiones = instance.getRegexp(er).split(RegExpUtil.DIV);
                boolean b = false;
                for (String expresion : expresiones) {
//                   Pattern p = Pattern.compile(expresion);
//                   b = b || !p.matcher(valor).matches(); // primera OR segunda OR ....
                    b = b || valor.matches(expresion);
                }
                if (!b) { // Si ninguno de las exprsiones se ajustó al valor dado, entonces b==false
                    String origErr = jdom.getNodeValue(root + tipo + "/@regExpErr");
                    if (origErr.equals("DEFAULT_MSG")) {
                        throw new Exception(instance.getRegexpErrorMessage(er, label));
                    } else {
                        throw new Exception(origErr.replaceAll(LABEL_KEY, label));
                    }
                }
            }

            String minLen = jdom.getNodeValue(root + tipo + "/@minLen");
            test(len < Convert.toInt(minLen),
                    jdom.getNodeValue(root + tipo + "/@minLenErr").replaceAll(LABEL_KEY, label).replaceAll(MIN_LENGTH_KEY, minLen));

            String maxLen = jdom.getNodeValue(root + tipo + "/@maxLen");
            test(len > Convert.toInt(maxLen),
                    jdom.getNodeValue(root + tipo + "/@maxLenErr").replaceAll(LABEL_KEY, label).replaceAll(MAX_LENGTH_KEY, maxLen));

        }// end if data-type-text


        if (tipo.equals("/data-type-date")) {
            test(!Convert.isDate(valor),
                    jdom.getNodeValue(root + tipo + "/@typeErr").replaceAll(LABEL_KEY, label));

            String minDate = jdom.getNodeValue(root + tipo + "/@minDate");
            test(Convert.toDate(valor).getTime() < Convert.toDate(minDate).getTime(),
                    jdom.getNodeValue(root + tipo + "/@minDateErr").replaceAll(LABEL_KEY, label).replaceAll(MIN_DATE_KEY, minDate));

            String maxDate = jdom.getNodeValue(root + tipo + "/@maxDate");
            test(Convert.toDate(valor).getTime() > Convert.toDate(maxDate).getTime(),
                    jdom.getNodeValue(root + tipo + "/@maxDateErr").replaceAll(LABEL_KEY, label).replaceAll(MAX_DATE_KEY, maxDate));

        }// end if data-type-date


        if (tipo.equals("/data-type-int")) {
            test(!Convert.isInt(valor),
                    jdom.getNodeValue(root + tipo + "/@typeErr").replaceAll(LABEL_KEY, label));

            String minVal = jdom.getNodeValue(root + tipo + "/@minVal");
            test(Convert.toInt(valor) < Convert.toInt(minVal),
                    jdom.getNodeValue(root + tipo + "/@minValErr").replaceAll(LABEL_KEY, label).replaceAll(MIN_VALUE_KEY, minVal));

            String maxVal = jdom.getNodeValue(root + tipo + "/@maxVal");
            test(Convert.toInt(valor) > Convert.toInt(maxVal),
                    jdom.getNodeValue(root + tipo + "/@maxValErr").replaceAll(LABEL_KEY, label).replaceAll(MAX_VALUE_KEY, maxVal));

        }// end if data-type-int


        if (tipo.equals("/data-type-long")) {
            test(!Convert.isLong(valor),
                    jdom.getNodeValue(root + tipo + "/@typeErr").replaceAll(LABEL_KEY, label));

            String minVal = jdom.getNodeValue(root + tipo + "/@minVal");
            test(Convert.toLong(valor) < Convert.toLong(minVal),
                    jdom.getNodeValue(root + tipo + "/@minValErr").replaceAll(LABEL_KEY, label).replaceAll(MIN_VALUE_KEY, minVal));

            String maxVal = jdom.getNodeValue(root + tipo + "/@maxVal");
            test(Convert.toLong(valor) > Convert.toLong(maxVal),
                    jdom.getNodeValue(root + tipo + "/@maxValErr").replaceAll(LABEL_KEY, label).replaceAll(MAX_VALUE_KEY, maxVal));

        }// end if data-type-long


        if (tipo.equals("/data-type-decimal")) {
            test(!Convert.isDouble(valor),
                    jdom.getNodeValue(root + tipo + "/@typeErr").replaceAll(LABEL_KEY, label));

            String minVal = jdom.getNodeValue(root + tipo + "/@minVal");
            test(Convert.toDouble(valor) < Convert.toDouble(minVal),
                    jdom.getNodeValue(root + tipo + "/@minValErr").replaceAll(LABEL_KEY, label).replaceAll(MIN_VALUE_KEY, minVal));

            String maxVal = jdom.getNodeValue(root + tipo + "/@maxVal");
            test(Convert.toDouble(valor) > Convert.toDouble(maxVal),
                    jdom.getNodeValue(root + tipo + "/@maxValErr").replaceAll(LABEL_KEY, label).replaceAll(MAX_VALUE_KEY, maxVal));

        }// end if data-type-long

        //Evaluaciones de llaves que deben ser iguales
        //evalueAllEquals(ui, name, valor, label);

    }// fin de evaluate *******************************************************

    private String[] getAttributeNamesForValidation() {
        return jdom.getAttributeNamesForValidation();
    }

//    private String[] getWidgetNamesForValidation() {
//        return jdom.getWidgetNamesForValidation();
//    }
    private String getWidgetName(String attName) {
        return jdom.getWidgetName(attName);
    }
}// fin de clase ***
