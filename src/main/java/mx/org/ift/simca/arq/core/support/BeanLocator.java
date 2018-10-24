package mx.org.ift.simca.arq.core.support;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Descripción:</p>
 * Clase utilitaria que sirve para recuperar un spring-bean desde cualquier
 * ubicación del aplicativo.
 */
public class BeanLocator implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanLocator.class);
    
    /**
     * Considera a todos los archicos ubicados bajo el directorio 'spring' de
     * extensión xml y cuyo nombre comience con: applicationContext
     */
    private static final ClassPathXmlApplicationContext APP_CONTEXT =
            new ClassPathXmlApplicationContext("spring/applicationContext*.xml");
    /**
     * Representa el valor inicial del BeanFactory o contexto de la aplicación
     */
    private static BeanFactory beanFactory = (BeanFactory) APP_CONTEXT;

    /**
     * Por ser una clase utilitaria, el constructor no es público.
     */
    protected BeanLocator() {
    }

    /**
     * Recupera un bean del contexto de spring.
     *
     * @param beanName el bean name
     * @return Objeto a ser casteado al bean adecuado
     */
    public static Object getBean(String beanName) {
        if (beanFactory == null) {
            LOGGER.warn("No se cargó el contexto de spring y no se puede localizar el bean solicitado.");
            return null;
        } else {
           return beanFactory.getBean(beanName);
        }
    }
}
/* El siguiente arreglo también es válido y la clase */
/* ClassPathXmlApplicationContext puede aceptarlo    */
/*
 private static final String[] appContextFilesArray = new String[] {
 "spring/applicationContext.xml",
 "spring/applicationContext-security.xml",
 "spring/applicationContext-persistence.xml",
 "spring/applicationContext-mail.xml"};
 */
