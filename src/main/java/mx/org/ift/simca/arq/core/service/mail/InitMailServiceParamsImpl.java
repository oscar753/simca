package mx.org.ift.simca.arq.core.service.mail;

import javax.annotation.PostConstruct;
import javax.mail.internet.InternetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * Implementación de InitMailServiceParamsImpl que extiende la funcionalidad de
 * InitMailServiceParams
 */
@Service("initMailServiceParams")
public class InitMailServiceParamsImpl implements InitMailServiceParams {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa el objeto de soporte para el Log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InitMailServiceParamsImpl.class);
    /**
     * Obtiene el valor mail.active del archivo de propiedades y lo asigna a active
     */
    @Value("${mail.active}")
    private boolean active;
    /**
     * Obtiene el valor mail.username del archivo de propiedades y lo asigna a
     * from
     */
    @Value("${mail.from}")
    private InternetAddress from;
    /**
     * Obtiene el valor de mail.url del archivo de propiedades y lo asigna al
     * context
     */
    @Value("${mail.url}")
    private String context;
    /**
     * Obtiene el valor de mail.maxQueueSize del archivo de propiedades y lo
     * asigna al maxQueueSize
     */
    @Value("${mail.maxQueueSize}")
    private int maxQueueSize;
    /**
     * Directorio en donde se guardan los templates para los emails.
     */
    @Value("${mail.resources}")
    private String mailResources;
    /**
     * Inyección del bean de spring llamado mailSender para manejo de los
     * servicios sobre la entidad 'JavaMailSenderImpl'
     */
    @Autowired
    private transient JavaMailSenderImpl mailSender;

    /**
     * Se invoca el método setInitMailServiceParams de la super clase
     * InitMailServiceParams con los siguientes parámetros: active, from,
     * mailSender, context, maxQueueSize. Adicionalmente, se manda imprimir al
     * "Log" el estado que será asignado al motor de envío de correo
     * electrónico.
     */
    @PostConstruct
    public void init() {
        LOGGER.info("Non calculated context: -->{}<-----", context);
        LOGGER.info("Is mail active? ---------->{}<-----", active);
        LOGGER.info("Who is the sender? ------->{}<-----", from);
        LOGGER.info("Max mail queue size: ----->{}<-----", maxQueueSize);
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public InternetAddress getFrom() {
        return from;
    }

    @Override
    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    @Override
    public String getFullServerURL() {
        return context;
    }

    @Override
    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    @Override
    public String getMailResources() {
        return mailResources;
    }

}
