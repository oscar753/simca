package mx.org.ift.simca.arq.core.service.security.adminPass;

import javax.annotation.PostConstruct;
import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.persistence.UsuarioMapper;
import mx.org.ift.simca.arq.core.service.mail.InitMailServiceParams;
import mx.org.ift.simca.arq.core.service.mail.NotificationService;
import mx.org.ift.simca.arq.core.support.Crypt;
import mx.org.ift.simca.arq.core.support.MailEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * La clase ForgotPasswordServiceImpl.
 *
 */
@Service(value = "forgotPasswordService")
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa un objeto para el Log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordServiceImpl.class);
    /**
     * Obtiene el mail.forgotPassword.url del archivo de configuración y lo
     * asigna
     */
    @Value("${mail.forgotPassword.url}")
    private String forgotPasswordUrl;
    /**
     * Obtiene el mail.stopMail.url del archivo de configuración y lo asigna
     */
    @Value("${mail.stopMail.url}")
    private String stopMailUrl;
    /**
     * Obtiene el mail.sid.length del archivo de configuración y lo asigna
     */
    @Value("${mail.sid.length}")
    private int sidLength;
    /**
     * Directorio que contiene los templates de correo
     */
    @Value("${mail.resources}")
    private String mailResources;
    /**
     * Archivo a utilizar como skeleton para el correo de forgot password.
     */
    @Value("${mail.forgot.template}")
    private String mailForgotTemplate;
    /**
     * Inyección del bean de spring llamado usuarioMapper para manejo de
     * operaciones de persistencia sobre la entidad 'Usuario'
     */
    @Autowired
    private UsuarioMapper usuarioMapper;
    /**
     * Inyección del bean de spring llamado NotificationService
     */
    @Autowired
    private NotificationService notificationService;
    /**
     * Parámetros del email.
     */
    @Autowired
    private InitMailServiceParams initMailServiceParams;

    private String mailTemplateContent;
    
    @PostConstruct
    public void init() {
        // Recupera la plantilla de envío de correos de recuperación de claves
        mailTemplateContent = MailEngine.getTextFromResource(mailResources + mailForgotTemplate);
    }
    
    @Override
    public void sendEmailToRestorePassword(Usuario usr, String correo, String serverUrl) {
        // Crea el folio que irá pegado al 'Asunto'
        StringBuilder subject = new StringBuilder("Procedimiento para restaurar credenciales olvidadas.");
        subject.append(" folio: ");
        subject.append(System.currentTimeMillis());

        // Genera llave única de 24 horas y registrarla en la base de datos
        String llave = Crypt.getRandomString(sidLength);
        LOGGER.debug("Llave generada para {}: {}", new String[] {correo, llave});
        usr.setIdDeSeguridad(llave);
        usr.setVentanaParaIdSeguridad(DateWrapper.now());
        usuarioMapper.update(usr);

        // sustituye comodines y coloca resultado en la variable 'texto':
        String url = MailEngine.buildURL(llave, this.forgotPasswordUrl + "?sid=", "Recupera claves", serverUrl);
        String texto = mailTemplateContent.replace("#$", url);
        url = MailEngine.buildURL(Crypt.encripta(correo), this.stopMailUrl + "?sid=", "Stop Mail at Once", serverUrl);
        String cadereplace="#"+"#";
        texto = texto.replace(cadereplace, url);
        

        // Graba lo que se va a enviar, este o no activo el servicio de mail
        if (initMailServiceParams.isActive()) {
            LOGGER.debug("Texto:\n{}", texto);
        } else {
            LOGGER.info("Texto:\n{}", texto);
        }

        // envía mensaje
        notificationService.addMessage(correo, subject.toString(), texto);
    }
}
