package mx.org.ift.simca.arq.core.support;

import java.io.Serializable;
import java.util.Date;
import mx.org.ift.simca.arq.core.enums.TipoBitacoraEnum;

import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.service.BitacoraService;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>Descripción:</p>
 * Clase utilitaria que sirve para guardar en un medio persistente como archivos
 * planos o una base de datos, la información relevante en puntos específicos
 * del aplicativo.
*/
public class Audit implements Serializable {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = -4767707067549780246L;
    /**
     * Representa el valor inicial del usuario anónimo
     */
    private static final String ANONYMOUS = "anonymousUser";
    /**
     * Representa un objeto para el log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Audit.class);
    /**
     * La constante CURRENT_IP_VALUE.
     */
    public static final String CURRENT_IP_VALUE = "CURRENT_IP_VALUE-4767707067549780246L";

    /**
     * Por ser una clase utilitaria, el constructor no es público.
     */
    protected Audit() {
    }
    
    /**
     * Almacena la información dada acorde a la lógica definida.
     *
     * @param accion Entero asociado a la familia relacionada con la acción
     * @param extraInfo el extra info
     */
    public static void write(TipoBitacoraEnum bitacoraEnum, String extraInfo) {
        Object obj = ContextUtils.getValueFromSessionMap(CURRENT_IP_VALUE);
        String ip;

        if (obj == null) {
            ip = "localhost";
        } else {
            ip = obj.toString();
        }

        String currentUsername = getCurrentUserName();
        write(
                currentUsername,
                ip,
                DateWrapper.now(),
                bitacoraEnum,
                extraInfo);
    }

    /**
     * Write escribe en la bitacora de la base de datos el registro de un
     * evento. Por ejemplo, una solicitud de registro
     *
     * @param accion la acción que ocurrió y que se va a guardar
     */
    public static void write(TipoBitacoraEnum bitacoraEnum) {
        write(bitacoraEnum, "NO-EXTRA-INFO");
    }

    /**
     * Lleva a cabo la tarea de escribir una bitácora con los datos
     * especificados como argumentos
     *
     * @param username Nombre del usuario
     * @param ip Numero de IP
     *
     * @param date Fecha en la que se esta llevando a cabo la tarea
     * @param action Acción que se desea registrar
     *
     * @param extraInfo Información adicional para registar en la bitácora
     */
    private static void write(String username, String ip, Date date, TipoBitacoraEnum bitacoraEnum, String extraInfo) {
        LOGGER.debug("{}___{}___{}___{}___{}", new Object[] {username, ip, date, bitacoraEnum.getId(), extraInfo});
        BitacoraService bitacoraService = (BitacoraService) BeanLocator.getBean("bitacoraService");
        bitacoraService.write(bitacoraEnum, ip, date, username, extraInfo);
    }

    /**
     * Obtiene el nombre del usuario actual que se desea auditar
     *
     * @return el nombre del usuario
     */
    private static String getCurrentUserName() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        if (au == null) {
            return ANONYMOUS;
        } else {
            return au.getName();
        }
    }
}
