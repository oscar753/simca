package mx.org.ift.simca.arq.core.service.register;

import java.io.Serializable;

import mx.org.ift.simca.arq.core.model.Preregistro;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;

/**
 * La Interfaz CompletaRegistroService.
 *
*/
public interface CompletaRegistroService extends Serializable {

    /**
     * Recupera el user by sid que se emplea como ventana para que el usuario
     * termine su solicitud.
     *
     * @param idSeguridad El id seguridad
     * @return El user by sid
     */
    Preregistro getUserBySid(String idSeguridad);

    /**
     * Recupera el nombre del usuario en base a su nombre
     *
     * @param usuario Nombre del usuario deseado a encontrar
     *
     * @return El usuario encontrado con el nombre especificado como argumento.
     */
    Usuario getUserByName(String usuario);

    /**
     * Realiza la tarea de registrar al usuario indicando con un valor booleano
     * si la tarea se llevó a cabo sin errores
     *
     * @param usuarioInfo Información del usuario.
     * @return Valor booleano indicando si no hubo error durante la tarea
     *
     * @throws Exception Exception lanzada durante la operación
     */
    boolean register(UsuarioInfo usuarioInfo) throws Exception;
}
