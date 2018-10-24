package mx.org.ift.simca.arq.core.service.security.adminPass;

import java.sql.Timestamp;


import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.persistence.UsuarioMapper;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * La clase RestorePasswordServiceImpl.
 *
*/
@Service(value = "restorePasswordService")
public class RestorePasswordServiceImpl implements RestorePasswordService {

    /**
     * Representa el valor inicial para la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Inyección del bean de spring llamado usuarioMapper para manejo de
     * operaciones de persistencia sobre la entidad 'Usuario'
     */
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public Usuario getUserBySid(String idSeguridad) {
        return usuarioMapper.getUserByIdSeguridad(idSeguridad);
    }

    @Override
    public void setNewPassword(Usuario usr, String nuevaClave) {
        usr.setClave(nuevaClave);
        usr.setFechaUltimoCambioClave(DateWrapper.now());
        long base = ContextUtils.getDate(2010, 1, 1);
        Timestamp ts = new Timestamp(base);
        usr.setVentanaParaIdSeguridad(ts);
        usr.setInstanteDeBloqueo(ts);
        usuarioMapper.update(usr);
    }
}
