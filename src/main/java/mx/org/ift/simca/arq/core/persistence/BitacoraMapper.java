package mx.org.ift.simca.arq.core.persistence;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.arq.core.model.Bitacora;
import mx.org.ift.simca.arq.core.model.DateWrapper;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad bitacora
 */
public interface BitacoraMapper extends IMapper<Bitacora> {

    /**
     * Actualiza los campos de la bitácora, salvo el campo action.
     * @param bitacora Instancia con los valores a actualizar.
     */
    void upeventDate(Bitacora bitacora);
    
    /**
     * Realiza la tarea de limpieza de un objeto de tipo DateWrapper.
     *
     * @param dateWrapper Instancia de DateWrapper.
     */
    void clean(DateWrapper dateWrapper);
}
