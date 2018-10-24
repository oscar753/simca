package mx.org.ift.simca.arq.core.support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * <p>Descripción:</p> Clase que implementa el servicio de mail.
 */
public final class MailEngine implements Serializable {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa un objeto para el Log de mensajes
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MailEngine.class);
    /**
     * Representa una lista de mensajes MIME
     */
    private transient List<MimeMessage> mimeMessagesBag = new ArrayList<MimeMessage>();
    /**
     * Representa el motor de mensajeria
     */
    private static MailEngine instance = new MailEngine();
    /**
     * Representa el remitente del servicio de correo
     */
    private transient JavaMailSenderImpl mailSender = null;
    /**
     * Indica si el servicio esta disponible
     */
    private boolean active;
    /**
     * Representa la dirección del remitente
     */
    private transient InternetAddress from;
    /**
     * Representa el contexto completo del mensaje
     */
    private static String fullContext;
    /**
     * Representa el valor máximo de mensajes en la cola
     */
    private static int maxQueueSize = 20;

    /**
     * En cumplimiento de la firma que el petrón Singleton demanda para el
     * constructor de la clase
     */
    private MailEngine() {
    }

    /**
     * Obtiene una única instancia de MailEngine.
     *
     * @param fullContextParam El contexto de la aplicación.
     * @param maxQueueSizeParam Tamaño máximo que la cola de mensajes puede
     * tener.
     * @return única instancia de MailEngine
     */
    public static MailEngine getInstance(String fullContextParam, int maxQueueSizeParam) {
        fullContext = fullContextParam;
        if (maxQueueSize < 1) {
            LOGGER.warn("No se especificó el tamaño máximo de la bolsa de correos, se usará un buffer de {} como tamaño por defecto", maxQueueSize);
        } else {
            maxQueueSize = maxQueueSizeParam;
        }
        return instance;
    }

    /**
     * Motor de envío de correo.
     *
     * @return Motor de envío de correo.
     */
    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    /**
     * Motor de envío de correo.
     *
     * @param mailSender Motor de envío de correo.
     */
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Dirección del remitente.
     *
     * @param from
     */
    public void setFrom(InternetAddress from) {
        this.from = from;
    }

    /**
     * Dirección del remitente.
     *
     * @return
     */
    public InternetAddress getFrom() {
        return this.from;
    }

    /**
     * Envío activo.
     *
     * @return Envío activo.
     */
    public boolean getActive() {
        return this.active;
    }

    /**
     * Envío activo.
     *
     * @param active Envío activo.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Agrega el message.
     *
     * @param to el to
     * @param subject el subject
     * @param text el text
     */
    public void addMessage(String to, String subject, String text) {
        addMessage(to, subject, text, new File[0]);
    }

    /**
     * Agrega el message.
     *
     * @param to []
     * @param subject el subject
     * @param text el text
     */
    public void addMessage(String[] to, String subject, String text) {
        addMessage(to, null, subject, text, new File[0]);
    }

    /**
     * Agrega el message.
     *
     * @param to el to
     * @param bcc el bcc
     * @param subject el subject
     * @param text el text
     */
    public void addMessage(String to, String bcc, String subject, String text) {
        addMessage(to, bcc, subject, text, new File[0]);
    }

    /**
     * Agrega el message.
     *
     * @param to el to
     * @param subject el subject
     * @param text el text
     * @param attachments [] archivos adjuntos.
     */
    public void addMessage(String to, String subject, String text, File... attachments) {
        String[] too = {to};
        addMessage(too, null, subject, text, attachments);
    }

    /**
     * Agrega el message.
     *
     * @param to []
     * @param bcc []
     * @param subject el subject
     * @param text el text
     * @param attachments []
     */
    public void addMessage(String to, String[] bcc, String subject, String text, File... attachments) {
        String[] too = {to};
        addMessage(too, bcc, subject, text, attachments);
    }

    /**
     * Agrega el message.
     *
     * @param to el to
     * @param bcc el bcc
     * @param subject el subject
     * @param text el text
     * @param attachments []
     */
    public void addMessage(String to, String bcc, String subject, String text, File... attachments) {
        String[] too = {to};
        String[] bccArray = {bcc};
        addMessage(too, bccArray, subject, text, attachments);
    }

    /**
     * Agrega el message.
     *
     * @param to []
     * @param bcc []
     * @param subject el subject
     * @param text el text
     * @param attachments []
     */
    public void addMessage(String[] to, String[] bcc, String subject, String text, File... attachments) {
        addMsg(to, bcc, subject, text, attachments);
    }

    /**
     * Envía un conjunto de correos que han sido almacenados en un contenedor
     * temporal administrado por spring.
     */
    public void sendAllMessages() {
        try {
            this.sendAllMessagesHelper();
        } catch (Exception ex) {
            LOGGER.error("Error en el servicio de envio de correos electronicos", ex);
        }
    }

    /**
     * Arma una cadena de un url adecuado y ajustado al contexto actual del
     * aplicativo que incluye un parámetro llamado 'sid' igualado a una llave
     * aleatoria
     *
     * @param key Clave de identificación
     * @param path Path del URL
     * @param message Mensaje asociado.
     * @return String : url armado acorde al contexto actua
     */
    public static String buildURL(String key, String path, String message, String fullServerUrlFromContext) {
        StringBuilder url = new StringBuilder();
        
        url.append("<a href='");
        
        if (fullServerUrlFromContext == null) {
            LOGGER.warn("No se proporcionó el URL del servidor desde el contexto, se usará el de la configuración del servidor: {}", fullContext);
            url.append(fullContext);
        } else {
            url.append(fullServerUrlFromContext);
        }
        
        url.append(path);
        url.append(key);
        url.append("'>");
        url.append(message);
        url.append("</a>");
        return url.toString();
    }

    /**
     * Retorna el texto contenido en el archivo que le es pasado como parámetro
     * formal. Busca el archivo a partir de la raiz del proyecto WEB.
     *
     * @param filename el filename
     * @return El text
     */
    public static String getText(String filename) {
        StringBuilder sb = new StringBuilder();
        InputStreamReader reader = null;
        try {
            reader = FileUtils.getInputStream(filename);
            int n;
            while ((n = reader.read()) != -1) {
                sb.append((char) n);
            }
        } catch (FileNotFoundException ex) {
            LOGGER.error("Error al recuperar texto", ex);
        } catch (IOException ex) {
            LOGGER.error("Error al recuperar texto", ex);
        } finally {
            FileUtils.close(reader);
        }
        return sb.toString();
    }

    /**
     * Retorna el texto contenido en el archivo que le es pasado como parámetro
     * formal. Busca el archivo a partir de la raiz del proyecto WEB.
     *
     * OJO La ruta: 'fileToBeRead' debe venir SIN '/' al principio ya que
     * Starter.getRealPath() trae '/' al final.
     *
     * @param realPath el real path
     * @param fileToBeRead el file to be read
     * @return El text
     * @deprecated Ver getTextFromResource
     */
    public static String getText(String realPath, String fileToBeRead) {
        return getText(realPath + fileToBeRead);
    }

    /**
     * Busca el recurso dentro del classpath, en caso de encontrarlo lo lee y lo
     * entrega en un String.
     *
     * @param resourcePath Ruta del recurso, ejemplo: /miArchivo.txt
     * @return El contenido del recurso o Nulo si no es posible leer o encontrar
     * el archivo.
     */
    public static String getTextFromResource(String resourcePath) {
        InputStream inputStream = MailEngine.class.getResourceAsStream(resourcePath);

        if (inputStream == null) {
            LOGGER.error("No fue posible encontrar el recurso {} dentro del classpath", resourcePath);
            return null;
        } else {
            InputStreamReader inputStreamReader = null;
            String res;

            try {
                StringBuilder stbd = new StringBuilder();

                inputStreamReader = new InputStreamReader(inputStream, "UTF8");
                while (inputStreamReader.ready()) {
                    stbd.append((char) inputStreamReader.read());
                }

                res = stbd.toString();
            } catch (IOException ex) {
                LOGGER.error("Error al intentar leer el archivo desde el classpath", ex);
                res = null;
            } finally {
                FileUtils.close(inputStreamReader);
            }

            return res;
        }
    }

    /**
     * Utilería de apoyo al envío (posiblemente masivo) de mensajes de correo
     * electrónico. Detecta si es requerido enviar mensajes y no en función del
     * estado de la bolsa de mensajes encolados. Adicionalmente, si la bandera
     * de 'activo' está en falso, sólo imprime en el log lo que se hubiera
     * enviado por correo, pero realmente no ocurre tal envío.
     *
     * @throws Exception Excepción general
     */
    private void sendAllMessagesHelper() throws Exception {
        if (mimeMessagesBag != null && mimeMessagesBag.size() > 0) {
            MimeMessage[] mimeMessages = mimeMessagesBag.toArray(new MimeMessage[1]);
            if (active) {
                String fecha = ContextUtils.formatearFecha(new Date(), ContextUtils.FORMATO_DDMMYYYYHHMMSS);
                LOGGER.info("Enviando: {} mensajes. Con fecha: {}", new Object[]{mimeMessages.length, fecha});
                mailSender.send(mimeMessages);
            } else {
                LOGGER.info("El servicio de notificación de correo electrónico está inactivo !!!");
                
                StringBuilder stbd = new StringBuilder();
                
                for (MimeMessage mimeMessage : mimeMessages) {
                    if (stbd.length() != 0) {
                        stbd.append("\n------------\n");
                    }
                    stbd.append("Subject: ").append(mimeMessage.getSubject()).append("\n");
                    stbd.append("To: ");
                    Address[] recipients = mimeMessage.getRecipients(RecipientType.TO);
                    for (Address addr : recipients) {
                        stbd.append(addr.toString()).append(" ");
                    }
                    stbd.append("\n");
                }
                
                LOGGER.info("Mensajes enviados:\n{}", stbd.toString());
            }
            mimeMessagesBag.clear();
            LOGGER.info("Mensajes enviados !!!!!!!!!");
        }
    }

    /**
     * Envío de email con varios attachments
     *
     * @param to correo electrónico del destinatario
     * @param bcc Con copia para los destinatarios.
     * @param subject asunto del mensaje
     * @param text cuerpo del mensaje
     * @param attachments ficheros que se anexarán al mensaje
     *
     * @throws RuntimeException Exception
     */
    private void addMsg(String[] to, String[] bcc, String subject, String text, File... attachments) {
        // asegurando la trazabilidad
        if (LOGGER.isDebugEnabled()) {
            final boolean usingPassword = !"".equals(mailSender.getPassword());

            StringBuilder sb = new StringBuilder();
            sb.append("Sending email to: '");
            for (String para : to) {
                sb.append(para);
                sb.append(", ");
            }
            sb.append("' [through host: '");
            sb.append(mailSender.getHost());
            sb.append(":");
            sb.append(mailSender.getPort());
            sb.append("', username: '");
            sb.append(mailSender.getUsername());
            sb.append("' usingPassword:");
            sb.append(usingPassword);
            sb.append("].");

            LOGGER.debug(sb.toString());
        }

        // plantilla para el envío de email
        final MimeMessage message = mailSender.createMimeMessage();

        try {
            // el flag a true indica que va a ser multipart
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // settings de los parámetros del envío
            //message.setContent(mensaje, "text/html; charset=\"big5\"");
            helper.setTo(to);
            if (bcc != null) {
                helper.setBcc(bcc);
            }
            helper.setSubject(subject);
            helper.setFrom(getFrom());

            // Hola                                    <pic>content/themes/images/icon_twitter.png</pic> Mundo
            // <img src='http://www.gus.com:8080/aplicativo/content/themes/images/icon_twitter.png' />
            String reemplazo = text;
            reemplazo = reemplazo.replaceAll("<pic>", "<img src='http:/" + fullContext);
            reemplazo = reemplazo.replaceAll("</pic>", "' />");
            helper.setText(reemplazo, true);

            // adjuntando los archivos indicados
            if (attachments != null && attachments.length > 0) {
                for (int i = 0; i < attachments.length; i++) {
                    FileSystemResource file = new FileSystemResource(attachments[i]);
                    helper.addAttachment(attachments[i].getName(), file);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("File '{}' attached.", file);
                    }
                }
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        mimeMessagesBag.add(message);

        if (mimeMessagesBag.size() >= maxQueueSize) {
            LOGGER.info("La bolsa de correos ha llegado al límite permitido por la aplicación, se iniciará el envío de correos");
            sendAllMessages();
        }
    }

}// ends class
