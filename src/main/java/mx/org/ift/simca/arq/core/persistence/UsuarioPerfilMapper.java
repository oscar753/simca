package mx.org.ift.simca.arq.core.persistence;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.arq.core.model.UsuarioPerfil;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad usuario_perfil
 */
public interface UsuarioPerfilMapper extends IMapper<UsuarioPerfil> {

    /**
     * Elimina al usuario con el número de Id especificado como argumento.
     *
     * @param idUser Número Id asociado a un usuario.
     *
     */
    void deleteByIdUsuario(Integer idUser);
    
}
