package mx.org.ift.simca.arq.core.service;

import java.io.Serializable;
import mx.org.ift.simca.arq.core.model.Configuracion;
import mx.org.ift.simca.arq.core.support.ConfiguracionNotFoundException;
import mx.org.ift.simca.arq.core.support.TipoConfiguracionNotValidException;

public interface ConfiguracionService extends Serializable {
    
    /**
     * Inserta o actualiza una configuración.
     * 
     * @param configuracion a insertar u actualizar.
     */
    void save(Configuracion configuracion);
    
    /**
     * Borra una configuración.
     * 
     * @param configuracionPk ID de la configuración a borrar.
     */
    void delete(int configuracionPk);
    
    /**
     * Obtiene una configuración de tipo String desde la base de datos.
     * 
     * @param key Llave que identifica la configuración.
     * @return Valor de la configuración.
     * @throws ConfiguracionNotFoundException En caso de que la configuración no exista en la base de datos.
     * @throws TipoConfiguracionNotValidException En caso de que la configuración no sea del tipo String.
     */
    String getStrConfig(String key) throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException;
    
    /**
     * Obtiene una configuración de tipo Integer desde la base de datos.
     * 
     * @param key Llave que identifica la configuración.
     * @return Valor de la configuración.
     * @throws ConfiguracionNotFoundException En caso de que la configuración no exista en la base de datos.
     * @throws TipoConfiguracionNotValidException En caso de que la configuración no sea del tipo Integer.
     */
    int getIntConfig(String key) throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException;

    /**
     * Obtiene una configuración de tipo Float desde la base de datos.
     * 
     * @param key Llave que identifica la configuración.
     * @return Valor de la configuración.
     * @throws ConfiguracionNotFoundException En caso de que la configuración no exista en la base de datos.
     * @throws TipoConfiguracionNotValidException En caso de que la configuración no sea del tipo Float.
     */
    float getFltConfig(String key) throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException;

    /**
     * Obtiene una configuración de tipo Boolean desde la base de datos.
     * 
     * @param key Llave que identifica la configuración.
     * @return Valor de la configuración.
     * @throws ConfiguracionNotFoundException En caso de que la configuración no exista en la base de datos.
     * @throws TipoConfiguracionNotValidException En caso de que la configuración no sea del tipo Boolean.
     */
    boolean getBolConfig(String key) throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException;
    
}
