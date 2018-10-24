package mx.org.ift.simca.arq.core.support;

import java.io.Serializable;
import java.security.MessageDigest;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Descripción:</p>
 * Clase utilitaria que expone servicios de encriptación, decriptación y
 * generación de aleatorias cadenas de caracteres. Prácticamente la totalidad de
 * métodos son estáticos, lo que permite su utilización de una manera mas
 * cómoda. Actualmente soporta encripciones de una sola vía MD5 y SHA.
*/
public class Crypt implements Serializable {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa el valor inicial del subKey todos los elementos
     */
    private static final String SUB_KEY = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ$#";
    /**
     * Logger para los mensajes.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Crypt.class);

    /**
     * Por ser una clase utilitaria, el constructor no es público.
     */
    protected Crypt() {
    }

    /**
     * Codifica una cadena en un formato específico dado.
     *
     * @param source Cadena fuente
     * @param alg el alg
     * @return Cadena fuente codificada acorde al formato dado
     */
    public static String algorithm(String source, String alg) {
        try {
            MessageDigest md = MessageDigest.getInstance(alg);
            byte[] bytes = md.digest(source.getBytes(FileUtils.DEFAULT_ENCONDING));
            return getString(bytes);
        } catch (Exception ex) {
            LOGGER.error("Error la hacer el cifrado", ex);
            return null;
        }
    }

    /**
     * Obtiene como string los elementos contenidos en el arreglo de bytes
     *
     * @param bytes Arreglo de bytes
     * @return cadena con los elementos traducidos de la lista de bytes
     */
    private static String getString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            sb.append((int) (0x00FF & b));
            if (i + 1 < bytes.length) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    /**
     * Codifica una cadena en formato MD5.
     *
     * @param source Cadena fuente
     * @return MD5 de la cadena fuente
     */
    public static String md5(String source) {
        return algorithm(source, "MD5");
    }

    /**
     * Codifica una cadena en formato SHA.
     *
     * @param source Cadena fuente
     * @return SHA de la cadena fuente
     */
    public static String sha(String source) {
        return algorithm(source, "SHA");
    }

    /**
     * Genera una cadena aleatoria del tamaño definido por el parámetro entero
     * 'tam' usando combinaciones de letras tomadas de un universo de 64
     * caracteres diferentes. De esta forma, si getRandomString es invocado con
     * tam = 10, se obtiene una cadena de tamaño 10 con una probabilidad de
     * ocurrencia de 1 entre 64^10 = (2^6)^10 = 2^60
     *
     * @param tam : Tamaño de la cadena de respuesta
     * @return String : Cadena aleatoria del tamaño 'tam'
     */
    public static String getRandomString(int tam) {
        // 64 Characters:
        String seed = SUB_KEY;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tam; i++) { // take 'tam' characters
            int pos = CustomRandom.nextInt(seed.length() - 1);
            sb.append(seed.substring(pos, pos + 1));
        }
        return sb.toString();// return a string with 64^tam options = (2^6)^tam = 2^(6*tam) options
    }

    /**
     * Encripta.
     *
     * @param key el key
     * @param data el data
     * @return El string
     */
    public static String encripta(String key, String data) {
        return getEncryptor(key).encrypt(data);
    }

    /**
     * Decripta.
     *
     * @param data el data
     * @return El string
     */
    public static String decripta(String data) {
        return decripta(SUB_KEY, data);
    }

    /**
     * Encripta.
     *
     * @param data el data
     * @return El string
     */
    public static String encripta(String data) {
        return encripta(SUB_KEY, data);
    }

    /**
     * Decripta.
     *
     * @param key el key
     * @param data el data
     * @return El string
     */
    public static String decripta(String key, String data) {
        try {
            return getEncryptor(key).decrypt(data);
        } catch (Exception ex) {
            LOGGER.error("Error al decifrar la cadena", ex);
            return null;
        }
    }

    /**
     * Obtiene un encriptor
     *
     * @param key Clave que se desea cifrar
     * @return instancia del encriptador
     */
    private static StandardPBEStringEncryptor getEncryptor(String key) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setStringOutputType("hexadecimal");
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor;
    }
}
