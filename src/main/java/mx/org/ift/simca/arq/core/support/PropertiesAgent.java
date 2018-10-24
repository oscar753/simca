package mx.org.ift.simca.arq.core.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Descripción:</p>
 * Utilería para actualizar, en tiempo real, las propiedades del aplicativo en función de los cambios de un
 * determinado archivo de texto plano de properties.
*/
public class PropertiesAgent implements Serializable {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa un objeto para el log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesAgent.class);
    /**
     * Cadena para los errores.
     */
    private static final String ERROR_VALOR = "Error al recuperar el valor";
    /**
     * Representa el nombre del archivo
     */
    private String fileName;
    /**
     * Representa las propiedades
     */
    private Properties properties = new Properties();
    /**
     * Representa la ultima modificación
     */
    private long lastModified = 0L;

    /**
     * Constructor que carga incialmente a las propedades
     *
     * @param filePath : Ruta absoluta antes del nombre
     * @param singleFileName : Nombre del archivo de propiedades ubicado en la ruta dada
     */
    public PropertiesAgent(String filePath, String singleFileName) {
        this.fileName = filePath + singleFileName;
        init(this.fileName);
    }

    /**
     * Carga el atributo 'properties' con el contenido de un archivo de propiedades y controla la posibles excepciones
     * que pudieran resultar al no ser encontrado tal archivo o estar bloqueado por cualquier otra razón.
     *
     * @param fullFileName : Ruta absoluta completa del archivo de propiedades
     */
    private void init(String fullFileName) {
        File file = new File(fullFileName);
        InputStream in = null;
        this.lastModified = file.lastModified();
        try {
            LOGGER.info("cargando properties desde: {}", file.getCanonicalPath());
            in = new FileInputStream(file);
            this.properties.load(in);
        } catch (FileNotFoundException e) {
            LOGGER.error("Error al cargar el archivo de propiedades", e);
        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo de propiedades", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error("Error al cerrar el archivo de propiedades", e);
                }
            }
        }
    }

    /**
     * Recarga, por demanda, las propiedades y optimiza la recarga al revisar la última fecha de modificación de éstas.
     * Lo anterior hace calificar a este método para ser utilizado por un cron de actualización.
     *
     * @throws IOException Archivo bloqueado o existente, pero no disponible (seguridad, por ejemplo)
     */
    public void reloadProperties() throws IOException {
        File file = new File(this.fileName);
        long temp = file.lastModified();
        if (temp > this.lastModified) {
            init(this.fileName);
        }
    }

    /**
     * getter for the attribute properties
     *
     * @return this properties object
     */
    public Properties getProperties() {
        return this.properties;
    }

    /**
     * Gets a value from the resource file as a String.
     *
     * @param key To be taken
     * @return value associated to a key
     */
    public String getValue(String key) {
        try {
            return this.properties.get(key).toString();
        } catch (Exception e) {
            LOGGER.warn(ERROR_VALOR, e);
            return "Valo no encontrado en: " + this.fileName;
        }
    }

    /**
     * Retorna una propiedad de tipo 'String' acorde a una llave dada.
     *
     * @param key : Llave de búsqueda
     * @return String : valor asociado a la llave dada
     */
    public String getString(String key) {
        return getValue(key);
    }

    /**
     * Retorna una propiedad de tipo 'String' acorde a una llave dada.
     *
     * @param key : Llave de búsqueda
     * @return String : valor asociado a la llave dada
     */
    public String gStr(String key) {
        return getValue(key);
    }

    /**
     * Retorna una propiedad de tipo 'int' acorde a una llave dada.
     *
     * @param key : Llave de búsqueda
     * @return int : valor asociado a la llave dada
     */
    public int getInt(String key) {
        String s = getValue(key);
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            LOGGER.warn(ERROR_VALOR, e);
            return 0;
        }
    }

    /**
     * Retorna una propiedad de tipo 'boolean' acorde a una llave dada.
     *
     * @param key : Llave de búsqueda
     * @return boolean : valor asociado a la llave dada
     */
    public boolean gBool(String key) {
        String s = getValue(key);
        try {
            return Boolean.parseBoolean(s);
        } catch (Exception e) {
            LOGGER.warn(ERROR_VALOR, e);
            return false;
        }
    }
}// ends class PropertiesAgent *****
