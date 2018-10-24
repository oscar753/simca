package mx.org.ift.simca.arq.core.persistence;

import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.arq.core.model.Preregistro;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad preregistro
 */
public interface PreregistroMapper extends IMapper<Preregistro> {

    /**
     * Obtiene un pre-registro realizando la búsqueda por correo electrónico.
     *
     * @param correo Dirección de correo electrónico.
     *
     * @return El pre-registro encontrado asociado a la dirección de correo 
     * electrónico.
     */
    Preregistro getByCorreo(String correo);

    /**
     * Obtiene un pre-registro realizando la búsqueda por Id de Seguridad.
     *
     * @param idSeguridad Id de seguridad del usuario.
     *
     * @return El pre-registro encontrado asociado al Id de seguridad.
     */
    Preregistro getByIdSeguridad(String idSeguridad);

    /**
     * Realiza la tarea de eliminar los objetos antiguos.
     *
     * @param dateWrapper Instancia de DateWrapper.
     */
    void deleteOldones(DateWrapper dateWrapper);
}
