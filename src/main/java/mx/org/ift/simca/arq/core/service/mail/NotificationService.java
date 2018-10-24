package mx.org.ift.simca.arq.core.service.mail;

import java.io.File;
import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * Interface asociada a la clase NotificacionServiceImpl. Ver la documentación
 * de la clase asociada para mayor referencia.
 */
public interface NotificationService extends Serializable {

    /**
     * Agrega el message de notificación para un solo usuario.
     *
     * @param to El usuario destino
     *
     * @param subject El asunto del correo
     *
     * @param text El texto del correo electrónico
     */
    void addMessage(String to, String subject, String text);

    /**
     * Agrega el message de notificación para varios usuario.
     *
     * @param to [] Los destinatarios
     *
     * @param subject El asunto del correo
     *
     * @param text El texto del correo electrónico
     */
    void addMessage(String[] to, String subject, String text);

    /**
     * Agrega el message de notificación con copia para un usuario.
     *
     * @param to El destinatario
     *
     * @param bcc El destinatario copia
     *
     * @param subject El asunto del correo
     *
     * @param text El texto del correo electrónico
     */
    void addMessage(String to, String bcc, String subject, String text);

    /**
     * Agrega el message con documentos adjuntos.
     *
     * @param to El destinatario
     *
     * @param subject El asunto del correo
     *
     * @param text El texto del correo
     *
     * @param attachments [] Los documentos adjuntos
     */
    void addMessage(String to, String subject, String text, File... attachments);

    /**
     * Agrega el message con documentos adjuntos y para varios usuarios.
     *
     * @param to [] Los destinatarios
     *
     * @param bcc [] El destinatario copia
     *
     * @param subject El asunto del correo
     *
     * @param text El texto del correo
     *
     * @param attachments [] Los documentos adjuntos
     */
    void addMessage(String to, String[] bcc, String subject, String text, File... attachments);

    /**
     * Agrega el message de correo electrónico.
     *
     * @param to El usuario destino
     *
     * @param bcc El destinatarios copia
     * @param subject El asunto del mensaje
     *
     * @param text El text el texto del mensaje
     *
     * @param attachments [] Los documentos adjuntos
     */
    void addMessage(String to, String bcc, String subject, String text, File... attachments);

    /**
     * Agrega el message.
     *
     * @param to [] Los destinatarios
     *
     * @param bcc [] Los destinatarios copia
     *
     * @param subject El asunto del correo
     *
     * @param text El texto del correo
     *
     * @param attachments [] Los documentos adjuntos
     */
    void addMessage(String[] to, String[] bcc, String subject, String text, File... attachments);

    /**
     * Envía todos los mensajes que esperan en la cola de envíos.<br/>
     * También notifica de ello vía un mensaje al archivo de log.
     */
    void notificar();
}
