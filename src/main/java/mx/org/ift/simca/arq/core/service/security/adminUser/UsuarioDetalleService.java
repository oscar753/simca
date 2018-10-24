package mx.org.ift.simca.arq.core.service.security.adminUser;

import java.io.Serializable;
import mx.org.ift.simca.arq.core.model.UsuarioDetalle;

/**
 * La Interfaz UsuarioDetalleService.
 *
*/
public interface UsuarioDetalleService extends Serializable {

    /**
     * Guarda el detalle de un usuario en la base de datos.
     *
     * @param usuarioDetalle el usuario detalle
     */
    void persiste(UsuarioDetalle usuarioDetalle);

    /**
     * Carga el detalle de un usuario.
     *
     * @param id el id
     * @return El usuario detalle
     */
    UsuarioDetalle cargaDetalle(int id);
}
