package mx.org.ift.simca.arq.core.support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase utilitaria que proveé algunos métodos para interactuar con las clases de IO.
*/
public final class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /**
     * Encoding usado en el aplicación: UTF-8
     */
    public static final String DEFAULT_ENCONDING = "UTF-8";
    
    private FileUtils() {
    }

    /**
     * Cierra de forma segura el FileInputStream
     *
     * @param fis
     */
    public static void close(InputStream fis) {
        if (fis == null) {
            LOGGER.warn("Se intentó cerrar un inputStream nulo");
        } else {
            try {
                fis.close();
            } catch (Exception ex) {
                LOGGER.error("Error al cerrar el inputStrema", ex);
            }
        }
    }

    /**
     * Cierra de forma segura el FileReader
     *
     * @param fr
     */
    public static void close(FileReader fr) {
        if (fr == null) {
            LOGGER.warn("Se intentó cerrar un fileReader nulo");
        } else {
            try {
                fr.close();
            } catch (Exception ex) {
                LOGGER.error("Error al cerrar el fileReader", ex);
            }
        }
    }

    /**
     * Cierra de forma segura el InputStreamReader
     *
     * @param isr
     */
    public static void close(InputStreamReader isr) {
        if (isr == null) {
            LOGGER.warn("Se intentó cerrar un InputStreamReader nulo");
        } else {
            try {
                isr.close();
            } catch (Exception ex) {
                LOGGER.error("Error al cerrar el InputStreamReader", ex);
            }
        }
    }
    
    /**
     * Recupera un InputStreamReader a partir de la ruta dada. Intenta usar la codificación
     * UFT-8, en caso de error se usa la default del sistema.
     * 
     * @param filePath
     * @return 
     * @throws FileNotFoundException 
     */
    public static InputStreamReader getInputStream(String filePath) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        try {
            return new InputStreamReader(fis, DEFAULT_ENCONDING);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.warn("No fue posible usar la codificación UTF-8, se usará la default del sistema", ex);
            return new InputStreamReader(fis, Charset.defaultCharset());
        }
    }
}
