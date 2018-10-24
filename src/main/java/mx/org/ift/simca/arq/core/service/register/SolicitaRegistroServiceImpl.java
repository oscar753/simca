package mx.org.ift.simca.arq.core.service.register;

import javax.annotation.PostConstruct;
import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.model.Preregistro;
import mx.org.ift.simca.arq.core.persistence.PreregistroMapper;
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
 * La clase SolicitaRegistroServiceImpl.
 */
@Service(value = "solicitaRegistroService")
public class SolicitaRegistroServiceImpl implements SolicitaRegistroService {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa un objeto para el registro de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SolicitaRegistroServiceImpl.class);
    /**
     * Obtiene el mail.preregistro.url del archivo de configuración y lo asigna
     */
    @Value("${mail.preregistro.url}")
    private String preregistroUrl;
    /**
     * Obtiene el mail.sid.lenght del archivo de configuración y lo asigna
     */
    @Value("${mail.sid.length}")
    private int sidLength;
    /**
     * Directorio que contiene los templates de correo
     */
    @Value("${mail.resources}")
    private String mailResources;
    /**
     * Archivo a utilizar como skeleton para el correo de prerregistro.
     */
    @Value("${mail.preregistro.template}")
    private String mailPreregistroTemplate;
    /**
     * Inyección del bean de spring llamado preregistroMapper para manejo de
     * operaciones de persistencia sobre la entidad 'preRegistro'
     */
    @Autowired
    private PreregistroMapper preregistroMapper;
    /**
     * Inyección del bean de spring llamado notificationService para manejo de
     * operaciones de persistencia sobre la entidad 'NotificationService'
     */
    @Autowired
    private NotificationService notificationService;
    /**
     * Inyección del bean de spring llamado usuarioMapper para manejo de
     * operaciones de persistencia sobre la entidad 'Usuario'
     */
    @Autowired
    private UsuarioMapper usuariomapper;
    /**
     * Parámetros del email.
     */
    @Autowired
    private InitMailServiceParams initMailServiceParams;

    private String mailTemplateContent;
    
    @PostConstruct
    public void init() {
        // Recupera la plantilla de envío de correos de recuperación de claves
        mailTemplateContent = MailEngine.getTextFromResource(mailResources + mailPreregistroTemplate);
    }
    
    @Override
    public boolean alreadyRegistered(String correo) {
        return usuariomapper.getUserByCorreo(correo) != null;
    }

    @Override
    public void enviaSolicitud(String correo, String serverUrl) {
        // Antes de hacer nada, se va a depurar la tabla "preregistro" borrando todos
        // los registros que tengan 7 dias o mas de antiguedad. El 7 se puede sacar del
        // archivo de propiedades, sin embargo, por el momento, se quedará aqui.
        DateWrapper dw = new DateWrapper(7);
        preregistroMapper.deleteOldones(dw);

        // Ya hay un correo que solicita registro en la base de datos?
        Preregistro preReg = preregistroMapper.getByCorreo(correo);

        // Si no hay, habra que crear un objeto de tipo Preregistro
        if (preReg == null) {
            preReg = new Preregistro();
        }

        // Crea el folio que irá pegado al 'Asunto'
        StringBuilder subject = new StringBuilder("Procedimiento para concluir solicitud de registro.");
        subject.append(" folio: ");
        subject.append(System.currentTimeMillis());

        // Genera llave única de 24 horas y registrarla en la base de datos
        String llave = Crypt.getRandomString(sidLength);
        LOGGER.debug("Llave generada para {} : {}", new Object[]{correo, llave});

        preReg.setCorreo(correo);
        preReg.setIdSeguridad(llave);
        preReg.setVentanaParaIdSeguridad(DateWrapper.now());

        // guarda o actualiza en la base de datos
        if (preReg.getPreregistroPk() == null) {
            LOGGER.debug("Insertando un nuevo registro en la tabla preregistro");
            LOGGER.debug("Correo solicitante: {}", preReg.getCorreo());
            LOGGER.debug("Id de seguridad: {}", preReg.getIdSeguridad());
            LOGGER.debug("Id de seguridad: {}", preReg.getVentanaParaIdSeguridad());
            preregistroMapper.insert(preReg);
        } else {
            LOGGER.debug("Reenviando una invitacion a un usuario que ya existía");
            preregistroMapper.update(preReg);
        }

        // sustituye comodines y coloca resultado en la variable 'texto':
        String url = MailEngine.buildURL(llave, this.preregistroUrl + "?sid=", "Completa registro", serverUrl);
        String texto = mailTemplateContent.replace("#$", url);

        // Graba lo que se va a enviar, este o no activo el servicio de mail
        if (initMailServiceParams.isActive()) {
            LOGGER.debug("Texto:\n{}", texto);
        } else {
            LOGGER.info("Texto:\n{}", texto);
        }

        // envía mensaje por correo electrónico
        notificationService.addMessage(correo, subject.toString(), texto);
    }
}
