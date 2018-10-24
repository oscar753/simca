package mx.org.ift.simca.arq.core.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import mx.org.ift.simca.arq.core.model.AdminPerfilBag;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;

/**
 * La Interfaz AdminPerfilBagService.
 */
public interface AdminPerfilBagService extends Serializable {

    /**
     * Recupera el admin perfil bag actual.
     *
     * @param username Nombre de usuario
     *
     * @return El admin perfil bag
     */
    List<AdminPerfilBag> getAdminPerfilBag(String username);

    /**
     * Guarda perfiles, se encarga de guardar el perfil de un usuario con uno o varios roles.
     *
     * @param usrId El Id del usuario
     * @param idsRoles Lista de Id de los roles asociados al usuario
     */
    void guardaPerfiles(int usrId, HashSet<Integer> idsRoles);

    /**
     * Recupera el perfiles de un usuario.
     *
     * @param usuarioInfoSelected Información seleccionada del usuario
     *
     * @return Lista de perfiles
     */
    List<AdminPerfilBag> getPerfiles(UsuarioInfo usuarioInfoSelected);

    /**
     * Delete user, se encarga de borrar a un usuario del sistema.
     *
     * @param idUser Número de Id del usuario
     */
    void deleteUser(int idUser,boolean status);

    /**
     * Elimina al usuario cuyo ID es el dado y también elimina a las dependencias que este tiene en la base de datos
     *
     * @param idUser El id del usuario
     */
    void deleteFisico(int idUser);
}
