package mx.org.ift.simca.arq.core.service.security.adminPass;

import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.persistence.UsuarioMapper;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * La clase ChangeMyPasswordServiceImpl.
 *
*/
@Service(value = "changeMyPasswordService")
public class ChangeMyPasswordServiceImpl implements ChangeMyPasswordService {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Inyección del bean de spring llamado usuarioMapper para manejo de
     * operaciones de persistencia sobre la entidad 'usuario'
     */
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public void cambiaClave(Usuario usr, String nuevaClave) {
        // Aqui tal vez sea buena idea mandar un correo que
        // indique que la clave ha sido cambiada, ¿o no?
        usr.setClave(nuevaClave);
        usr.setFechaUltimoCambioClave(DateWrapper.now());
        usuarioMapper.update(usr);
    }

    @Override
    public Usuario getCurrentLoggedUsername() {
        String name = ContextUtils.getCurrentUserName();
        return usuarioMapper.getUserByName(name);
    }
}
