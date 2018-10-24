package mx.org.ift.simca.arq.core.service.mail;

import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioDetalle;
import mx.org.ift.simca.arq.core.persistence.UsuarioDetalleMapper;
import mx.org.ift.simca.arq.core.persistence.UsuarioMapper;
import mx.org.ift.simca.arq.core.support.Crypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * La clase StopMailServiceImpl.
*/
@Service
public class StopMailServiceImpl implements StopMailService {

    private static final long serialVersionUID = 1L;
    /**
     * Inyección del bean de spring llamado um para manejo de operaciones de
     * persistencia sobre la entidad 'Usuario'
     */
    @Autowired
    private UsuarioMapper um;
    /**
     * Inyección del bean de spring llamado udm para manejo de operaciones de
     * persistencia sobre la entidad 'UsuarioDetalle'
     */
    @Autowired
    private UsuarioDetalleMapper udm;

    /**
     * Regresa un objeto de tipo Usuario (ya con estado) con base en la
     * información (encriptada) que le es pasada como argumento. Si el argumento
     * es vacío o inválido, retorna null.
     *
     * @param sid Cadena encriptada
     * @return usuario asociado al valor desencriptado de la cadena que se le
     * pasa
     */
    @Override
    public Usuario getUser(String sid) {
        String correo = Crypt.decripta(sid);
        if (correo == null) {
            return null;
        }
        Usuario usr = um.getUserByCorreo(correo);
        return usr;
    }

    @Override
    public UsuarioDetalle stopMail(int idUsuario) {
        UsuarioDetalle usuarioDetalle = new UsuarioDetalle(idUsuario);
        usuarioDetalle = udm.getById(usuarioDetalle);
        usuarioDetalle.setMandaCorreoPromo(false);
        udm.update(usuarioDetalle);
        return usuarioDetalle;
    }
}
