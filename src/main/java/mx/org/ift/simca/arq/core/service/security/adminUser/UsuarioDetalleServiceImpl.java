package mx.org.ift.simca.arq.core.service.security.adminUser;

import mx.org.ift.simca.arq.core.model.UsuarioDetalle;
import mx.org.ift.simca.arq.core.persistence.UsuarioDetalleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * La clase UsuarioDetalleServiceImpl.
 *
*/
@Service
public class UsuarioDetalleServiceImpl implements UsuarioDetalleService {

    /**
     * Representa el valor inicial de la versión del serial.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Logger usado para la clase.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDetalleServiceImpl.class);
    /**
     * Inyección del bean de spring llamado usuarioDetalleMapper para manejo de operaciones de persistencia sobre la
     * entidad 'UsuarioDetalle'.
     */
    @Autowired
    private UsuarioDetalleMapper usuarioDetalleMapper;

    @Override
    public void persiste(UsuarioDetalle usuarioDetalle) {

        if (usuarioDetalle.getUsuarioFk() == null) {
            LOGGER.error("No es posible hacer el guardado del detalle del usuario, pues no tiene una llave asignada.");
        } else if (usuarioDetalleMapper.getById(usuarioDetalle) == null) {
            usuarioDetalleMapper.insert(usuarioDetalle);
        } else {
            usuarioDetalleMapper.update(usuarioDetalle);
        }
    }

    @Override
    public UsuarioDetalle cargaDetalle(int id) {
        UsuarioDetalle ud = new UsuarioDetalle(id);
        return usuarioDetalleMapper.getById(ud);
    }
}
