package mx.org.ift.simca.arq.core.service.register;

import mx.org.ift.simca.arq.core.model.Preregistro;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;
import mx.org.ift.simca.arq.core.persistence.PreregistroMapper;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * La clase CompletaRegistroServiceImpl.
 *
*/
@Service(value = "completaRegistroService")
public class CompletaRegistroServiceImpl implements CompletaRegistroService {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa un objeto para el Log de mensajes
     */
    private static final Logger LOG = LoggerFactory.getLogger(CompletaRegistroServiceImpl.class);
    /**
     * Inyección del bean de spring llamado preregistroMapper para manejo de operaciones de persistencia sobre la
     * entidad 'Preregistro'
     */
    @Autowired
    private PreregistroMapper preregistroMapper;
    /**
     * Inyección del bean de spring llamado usuarioMapper para manejo de operaciones de persistencia sobre la entidad
     * 'Usuario'
     */
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Preregistro getUserBySid(String idSeguridad) {
        return preregistroMapper.getByIdSeguridad(idSeguridad);
    }

    @Override
    public Usuario getUserByName(String usuario) {
        return usuarioService.getUserByName(usuario);
    }

    @Override
    @Transactional
    public boolean register(UsuarioInfo usuarioInfo) throws Exception {

        usuarioService.insertFullUser(usuarioInfo);

        // Borra la información del preregistro
        Preregistro pre = preregistroMapper.getByCorreo(usuarioInfo.getUsuario().getCorreo());
        preregistroMapper.delete(pre);
        LOG.debug("Efectuando un borrado dentro de la transaccion (última seccion de la transaccion)");

        return true;
    }
}
