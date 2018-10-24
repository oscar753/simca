package mx.org.ift.simca.arq.core.service.mail;

import java.io.File;
import javax.mail.internet.InternetAddress;

import mx.org.ift.simca.arq.core.support.MailEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * <p>Descripción:</p> Clase que inicializa las propiedades del MailEngine y
 * sirve como bridge para que dede spring se inyecte la información requerida.
 * Adicionalmente, provve acceso a los métodos sobrecargados "addMessage".
 *
 * El método público "notificar" será invocado periódicamente por un cron de
 * spring y será responsable de enviar la cola de mensajes almacenados y en
 * espera de ser enviados.
 */
public class NotificationServiceImpl implements NotificationService {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa el objeto de soporte para el log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);
    /**
     * Representa el motor de envío de correo
     */
    private MailEngine mailEngine = null;

    /**
     * Constructor que inicializa los parámetros del motro de envío de mensajes
     * de correo.
     *
     * @param active Si es falso, no envía los correos. Sólo simula su envío.
     *
     * @param from A quién va dirigido el correo.
     *
     * @param mailSender Inyección de spring como soporte para el motor de envío
     * de correo
     *
     * @param context Nombre del contexto
     */
    public NotificationServiceImpl(Boolean active, InternetAddress from, JavaMailSenderImpl mailSender, String context) {
        init(active, from, mailSender, context, 0);
    }

    /**
     * Constructor que inicializa los parámetros del motro de envío de mensajes
     * de correo.
     *
     * @param active Si es falso, no envía los correos. Sólo simula su envío.
     *
     * @param from A quién va dirigido el correo.
     *
     * @param mailSender Inyección de spring como soporte para el motor de envío
     * de correo
     *
     * @param context Indica el URL del servidor con todo y el contexto de la aplicación, en caso de que la aplicación esté en la raíz
     *
     * @param maxQueueSize Tamaño máximo de la cola de mensajes
     */
    public NotificationServiceImpl(Boolean active, InternetAddress from, JavaMailSenderImpl mailSender, String context, int maxQueueSize) {
        init(active, from, mailSender, context, maxQueueSize);
    }

    /**
     * Crea una nueva instancia notification service impl.
     *
     * @param imsp Instancia de InitMailServiceParams
     */
    public NotificationServiceImpl(InitMailServiceParams imsp) {
        init(imsp.isActive(), imsp.getFrom(), imsp.getMailSender(), imsp.getFullServerURL(), imsp.getMaxQueueSize());
    }

    /**
     * Inicializa los valores de una instancia de la clase
     *
     * @param active Disponibilidad de servicio
     *
     * @param from Origen del mensaje
     *
     * @param mailSender Emisor del mensaje
     *
     * @param fullServerURL Indica el URL del servidor con todo y el contexto de la aplicación, en caso de que la aplicación esté en la raíz
     *
     * @param maxQueueSize Tamaño máximo de la cola de mensajes
     */
    private void init(Boolean active, InternetAddress from, JavaMailSenderImpl mailSender, String fullServerURL, int maxQueueSize) {
        LOGGER.info("Constructor de NotificacionServiceImpl [OK]");
        this.mailEngine = MailEngine.getInstance(fullServerURL, maxQueueSize);
        if (this.mailEngine.getMailSender() == null) {
            LOGGER.info("Asignando estado a MailEngine");
            //MailEngine.setTrunk(context);
            this.mailEngine.setActive(active);
            this.mailEngine.setFrom(from);
            this.mailEngine.setMailSender(mailSender);
            LOGGER.info("Estado asignado");
        }
    }

    @Override
    public void notificar() {
        this.mailEngine.sendAllMessages();
    }

    @Override
    public void addMessage(String to, String subject, String text) {
        mailEngine.addMessage(to, subject, text);
    }

    @Override
    public void addMessage(String[] to, String subject, String text) {
        mailEngine.addMessage(to, subject, text);
    }

    @Override
    public void addMessage(String to, String bcc, String subject, String text) {
        mailEngine.addMessage(to, bcc, subject, text);
    }

    @Override
    public void addMessage(String to, String subject, String text, File... attachments) {
        mailEngine.addMessage(to, subject, text, attachments);
    }

    @Override
    public void addMessage(String to, String[] bcc, String subject, String text, File... attachments) {
        mailEngine.addMessage(to, bcc, subject, text, attachments);
    }

    @Override
    public void addMessage(String to, String bcc, String subject, String text, File... attachments) {
        mailEngine.addMessage(to, bcc, subject, text, attachments);
    }

    @Override
    public void addMessage(String[] to, String[] bcc, String subject, String text, File... attachments) {
        mailEngine.addMessage(to, bcc, subject, text, attachments);
    }
}
