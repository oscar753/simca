package mx.org.ift.simca.arq.core.service.security.adminUser;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import mx.org.ift.simca.arq.core.model.Perfil;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;

/**
 * La Interfaz UsuarioService.
 *
*/
public interface UsuarioService extends Serializable {

    /**
     * Guarda un usuario en la base de datos sin perfiles.
     *
     * @param usuarioInfo Información del usuario a registrar.
     * @throws Exception el exception
     */
    void insertFullUser(UsuarioInfo usuarioInfo) throws Exception;

    /**
     * Guarda un usuario en la base de datos con perfiles.
     *
     * @param usuarioInfo Información del usuario a registrar.
     * @param idsPerfil el ids perfil a asignar al usuario nuevo.
     * @throws Exception el exception
     */
    void insertFullUser(UsuarioInfo usuarioInfo, HashSet<Integer> idsPerfil) throws Exception;

    /**
     * Actualiza los datos de un usuario.
     *
     * @param usuarioInfoSelected el usuario info selected
     * @param email el email
     * @throws Exception el exception
     */
    void updateUser(
            UsuarioInfo usuarioInfoSelected,
            String email) throws Exception;
    /*
     void updateUser(
     UsuarioInfo usuarioInfoSelected,
     String email, boolean roles) throws Exception;
     */

    /**
     * Actualiza la información del usuario.
     * 
     * @param usuario Usuario previamente insertado.
     */
    void update(Usuario usuario);
    
    /**
     * Obtiene la información de los usuarios
     *
     * @return lista con la información de los usuarios
     */
    List<UsuarioInfo> getUsuariosInfo();

    /**
     * Se emplea para saber si existe un usuario con el correo electrónico que se pasa como parámetro.
     *
     * @param email el email
     * @return true, si es exitoso
     */
    boolean userMailExists(String email);

    /**
     *
     * Se emplea para saber si el nombre de un usuario existe en la base de datos.
     *
     * @param name el name
     * @return true, si es exitoso
     */
    boolean userNameExists(String name);

    /**
     * Consulta el usuario por su nombre
     *
     * @param name el name
     * @return El user by name
     */
    Usuario getUserByName(String name);

    /**
     * Consulta a un usuario por su correo electrónico
     *
     * @param correo el correo
     * @return El user by correo
     */
    Usuario getUserByCorreo(String correo);

    /**
     * Recupera a un usuario por su id de usuario.
     *
     * @param id el id
     * @return El user by id int
     */
    Usuario getUserByIdInt(int id);

    /**
     * Obtiene todos los perfiles
     *
     * @return Listado de los perfiles
     */
    List<Perfil> getPerfiles();

    /**
     * Recupera los perfiles de un usuario
     *
     * @param userName el user name
     * @return El perfiles
     */
    List<Perfil> getPerfiles(String userName);

    /**
     * Indica si las credenciales que le son pasadas corresponden a un usuario válido o no.
     *
     * Notar que el password (o clave) YA DEBE VENIR "hasheada", es decir, que este método NO recibe la clave "plana"
     * sino ya con su MD5 o SHA o whatever clave salteada.
     *
     * @param user nombre del usuario en el sistema (login)
     * @param saltedPassword password cifrado
     * @return id del usuario en el sistema, null en otro caso
     */
    Usuario credentialsOk(String user, String saltedPassword);
}
