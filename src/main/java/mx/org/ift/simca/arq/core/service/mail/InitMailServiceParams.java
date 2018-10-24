package mx.org.ift.simca.arq.core.service.mail;

import java.io.Serializable;
import javax.mail.internet.InternetAddress;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * La clase InitMailServiceParams inicializa los parámetros para el servicio de
 * correo electrónico
 */
public interface InitMailServiceParams extends Serializable {

    /**
     * Indica si el correo está activo.
     * 
     * @return Indica si el correo está activo.
     */
    boolean isActive();

    /**
     * Indica la dirección de correo que se reportará como sender de los correos electrónicos.
     * 
     * @return Indica la dirección de correo que se reportará como sender de los correos electrónicos.
     */
    InternetAddress getFrom();

    /**
     * Indica el que envía el correo electrónico.
     * 
     * @return Indica el que envía el correo electrónico.
     */
    JavaMailSenderImpl getMailSender();

    /**
     * Indica el URL del servidor con todo y el contexto de la aplicación, en caso de que la aplicación esté en la raíz,
     * se omite el contexto. Ejemplo http://localhost/miApp o http://localhost.
     * 
     * @return Indica el URL del servidor con todo y el contexto de la aplicación. Ejemplo http://localhost/miApp.
     */
    String getFullServerURL();

    /**
     * Indica el tamaño máximo que puede medir la cola de correos.
     * 
     * @return Indica el tamaño máximo que puede medir la cola de correos.
     */
    int getMaxQueueSize();

    /**
     * Indica el directorio en el que se albergarán los templates de los correos electrónicos.
     * En tiempo de ejecución es un directorio dentro de WEB-INF/classes y debe iniciar con '/'.
     * 
     * @return Indica el directorio en el que se albergarán los templates de los correos electrónicos.
     */
    String getMailResources();
}
