package mx.org.ift.simca.arq.core.persistence;

import java.util.List;
import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.arq.core.model.Perfil;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad perfil
 */
public interface PerfilMapper extends IMapper<Perfil> {

    /**
     * Obtiene los perfiles asociados al nombre de usuario especificado 
     * como argumento.
     *
     * @param username Nombre del usuario del cual se desean obtener los 
     * perfiles asociados.
     *
     * @return Lista de perfiles asociados al nombre de usuario.
     */
    List<Perfil> getUserPerfiles(String username);
}
