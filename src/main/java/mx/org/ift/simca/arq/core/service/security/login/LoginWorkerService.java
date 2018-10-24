package mx.org.ift.simca.arq.core.service.security.login;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import org.springframework.security.core.userdetails.UserDetails;
import mx.org.ift.simca.arq.core.model.Usuario;

/**
 * Interfaz LoginWorkerService, define el comportamiento de una intancia que implementa la interfaz
 *
*/
public interface LoginWorkerService extends Serializable {

    /**
     * Reinicia el contador de intentos fallidos y reinicia el instante de bloqueo para una cuenta específica
     * determinada por un cierto 'username'.
     *
     * @param username Nombre del usuario
     * @return Valor de los errores limpiados
     */
    int cleanErrors(String username);

    /**
     * Para el usuario 'username' determina cuál fue la razón (Exception ex) por la cual el login falló Y con base en
     * ello, genera un mensaje de tipo FacesMessage que es devuelto para ser pintado en la interfaz web
     * cliente.<br/><br/>
     *
     * Adicionalmente, bloquea al usuario si es que este ya alcanzó su máximo numero de intentos de login fallidos.
     *
     * @param ex Exception que se va a lanzar
     * @param username Nombre del usuario que provocó el error
     * @return Un mensaje de JSF
     */
    FacesMessage getErrorMessage(Exception ex, String username);

    /**
     * Método usado para obtener un usuario desde el mapper de LoginService.
     *
     * @param username Nombre del usuario que se desea obtener
     * @return Usuario obtenido de la base de datos
     */
    Usuario getUsuario(String username);

    /**
     * Bloquea, si es necesario, a un usuario después de intentar varias veces ingresar con una clave incorrecta.
     *
     * @param e Exception que se va a lanzar
     * @param username Nombre del usuario que se esta revisando
     * @return int : El tipo de error detectado, una constante de la clase
     */
    int revisa(Exception e, String username);

    /**
     * Bloquea, si es necesario, a un usuario después de intentar varias veces ingresar con una clave incorrecta.
     *
     * @param e Exception que se va a lanzar
     * @param usr Usuario que se va a revisar
     * @return int : El tipo de error detectado, una constante de la clase
     */
    int revisa(Exception e, Usuario usr);

    /**
     * Determina si un correo electrónico existe como correo de algún usuario.
     *
     * @param email Correo electrónico
     * @return verdadero si este correo le pertenece a algún usuario, falso en otro cas
     */
    boolean userMailExists(String email);

    /**
     * Determina si un nombre existe como el nombre de algún usuario.
     *
     * @param name Nombre que se desea comprobar
     * @return verdadero si este nombre le pertenece a algún usuario, falso en otro cas
     */
    boolean userNameExists(String name);

    /**
     * Obtiene de la base de datos el Usuario cuyo id haya sido dado.
     *
     * @param id Id del usuario que se desea obtener
     * @return Objeto de tipo Usuario
     */
    Usuario getUserByIdInt(int id);

    /**
     * Obtiene de la base de datos el Usuario cuyo correo haya sido dado.
     *
     * @param correo Correo electrónico
     * @return Objeto de tipo Usuario
     */
    Usuario getUserByCorreo(String correo);

    /**
     * Obtiene de la base de datos el Usuario cuyo nombre haya sido dado.
     *
     * @param name Nombre del usuario que se desea buscar
     * @return Objeto de tipo Usuario
     */
    Usuario getUserByName(String name);

    /**
     * Obtiene de la base de datos el Usuario cuyo nombre haya sido dado.
     *
     * @param username Nombre de usuario a cargar
     * @return Objeto de tipo Usuario
     */
    UserDetails loadUserByUsername(String username);

    /**
     * Obtiene de la base de datos el Usuario cuyo 'user' haya sido dado, siempre y cuando el saltedPassword sea el
     * correcto.
     *
     * @param user Usuario que se verificara
     * @param saltedPassword Contraseña del usuario (ya aplicado el Salt)
     * @return Objeto de tipo Usuario
     */
    Usuario credentialsOk(String user, String saltedPassword);
}
