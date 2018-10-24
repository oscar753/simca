package mx.org.ift.simca.arq.core.persistence;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.arq.core.model.Configuracion;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad configuracion
 */
public interface ConfiguracionMapper extends IMapper<Configuracion> {
    
    Configuracion findByKey(String key);
    
}
