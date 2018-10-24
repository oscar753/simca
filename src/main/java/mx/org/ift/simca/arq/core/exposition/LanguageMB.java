package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import mx.org.ift.simca.arq.core.support.context.ContextUtils;

/**
 * <p>Descripción:</p>
 * Clase auxiliar para su uso desde el front-end. Permite obtener y definir un
 * idioma (o código de localización) determinado para su uso en la presentación
 * de mensajes obtenidos desde un archivo de recursos adaptado para soportar
 * localización.
  */
@ManagedBean
@SessionScoped
public class LanguageMB implements Serializable {
    private static final long serialVersionUID = 1L;
    private String localeCode;

    /**
     * Guarda en un mapa el catálogo de países.
     *
     * @return mapa con los países en catálogo
     */
    public Map<String, Object> getCountriesInMap() {
        return ContextUtils.COUNTRIES;
    }

    /**
     * Regresa el código aplicable al país e idioma.
     *
     * @return código del país e idioma, como cadena de caracteres
     */
    public String getLocaleCode() {
        return localeCode;
    }

    /**
     * Establece el código del país e idioma.
     *
     * @param localeCode el código aplicable al país e idioma
     */
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    /**
     * Establece un código nuevo de país e idioma.
     *
     * @param e Evento de código de país e idioma cambiado
     */
    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        ContextUtils.setNewLocal(newLocaleValue);
    }

    /**
     * Regresa el país al cual hace referencia el código de país e idioma.
     *
     * @return El país como cadena de caracteres.
     */
    public String getLocale() {
        return ContextUtils.getRequest().getLocale().getCountry();
    }

    /**
     * Metodo que traduce los mensajes de una cadena a español.
     * La informacion traducida corresponde a mensajes de violaciones a
     * las reglas de creacion usuarios y passwords
     * @param s Cadena a traducir.
     * @return Retorna una cadena traducida al español
     */
    public static String translate(String s) {
        String resultado = s;
        resultado = resultado.replace(" digit caracteres.", " dígitos.");
        resultado = resultado.replace(" non-alphanumeric caracteres.", " caracteres no alfanuméricos.");
        resultado = resultado.replace(" alphanumeric caracteres.", " caracteres alfabéticos.");
        resultado = resultado.replace(" uppercase caracteres.", " mayúsculas.");
        resultado = resultado.replace(" lowercase caracteres.", " minúsculas.");
        return resultado;
    }
}
