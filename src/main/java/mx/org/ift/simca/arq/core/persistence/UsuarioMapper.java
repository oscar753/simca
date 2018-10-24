package mx.org.ift.simca.arq.core.persistence;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.arq.core.model.Usuario;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad usuario
 */
public interface UsuarioMapper extends IMapper<Usuario> {

    /**
     * Obtiene un usuario realizando la búsqueda con el nombre de pila.
     *
     * @param usuario Nombre de pila del usuario.
     *
     * @return el usuario encontrado con el criterio de búsqueda.
     */
    Usuario getUserByName(String usuario);

    /**
     * Obtiene un usuario realizando la búsqueda con el correo electrónico.
     *
     * @param correo Dirección de correo electrónico.
     *
     * @return el usuario encontrado con el criterio de búsqueda.
     */
    Usuario getUserByCorreo(String correo);

    /**
     * Obtiene un usuario realizando la búsqueda con el Id de seguridad.
     *
     * @param idSeguridad Id de seguridad del usuario.
     *
     * @return el usuario encontrado con el criterio de búsqueda.
     */
    Usuario getUserByIdSeguridad(String idSeguridad);

    /**
     * Elimina al usuario cuyo ID es el dado y también elimina a las 
     * dependencias que este tiene en la base de datos.
     *
     * @param idUser El id del usuario
     */
    void deleteFisicoA(int idUser);

    /**
     *
     * @param idUser
     */
    void deleteFisicoB(int idUser);

    /**
     *
     * @param idUser
     */
    void deleteFisicoC(int idUser);
}
