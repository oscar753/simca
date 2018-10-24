package mx.org.ift.simca.arq.core.service.security.adminUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import mx.org.ift.simca.arq.core.enums.PerfilEnum;
import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.model.Perfil;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioDetalle;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;
import mx.org.ift.simca.arq.core.model.UsuarioPerfil;
import mx.org.ift.simca.arq.core.persistence.PerfilMapper;
import mx.org.ift.simca.arq.core.persistence.UsuarioMapper;
import mx.org.ift.simca.arq.core.persistence.UsuarioPerfilMapper;
import mx.org.ift.simca.arq.core.service.security.login.LoginWorkerService;
import mx.org.ift.simca.arq.core.support.Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * La clase UsuarioServiceImpl.
 *
*/
@Service(value = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    /**
     * Representa el valor inicial de la versión del serial.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa un objeto para el Log de mensajes
     */
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    /**
     * Inyección del bean de spring llamado loginWorkerService.
     */
    @Autowired
    private LoginWorkerService loginWorkerService;
    /**
     * Inyección del bean de spring llamado usuarioMapper para manejo de operaciones de persistencia sobre la entidad
     * 'usuario'.
     */
    @Autowired
    private UsuarioMapper usuarioMapper;
    /**
     * Inyección del bean de spring llamado UsuarioDetalleService para manejo de operaciones de persistencia sobre la
     * entidad 'UsuarioDetalle'.
     */
    @Autowired
    private UsuarioDetalleService usuarioDetalleService;
    /**
     * Inyección del bean de spring llamado usuarioPerfilMapper para manejo de operaciones de persistencia sobre la
     * entidad 'UsuarioPerfil'.
     */
    @Autowired
    private UsuarioPerfilMapper usuarioPerfilMapper;
    /**
     * Inyección del bean de spring llamado perfilMapper para manejo de operaciones de persistencia sobre la entidad
     * 'perfil'.
     */
    @Autowired
    private PerfilMapper perfilMapper;

    @Override
    public List<UsuarioInfo> getUsuariosInfo() {
        ArrayList<UsuarioInfo> usuariosInfo = new ArrayList<UsuarioInfo>();

        // Recuperamos la lista de usuarios de la base de datos
        List<Usuario> usuarios = usuarioMapper.getAll();
        UsuarioDetalle usuarioDetalle;

        // Para cada usuario completamos la información para desplegarla en
        // pantalla
        for (Usuario usuario : usuarios) {
            //List<GrantedAuthority> perfiles = null;//usuarioDAO.getPerfilUsr(usuario.getUsername());
            //usuario.setPerfiles(perfiles);

            usuarioDetalle = usuarioDetalleService.cargaDetalle(usuario.getUsuarioPk());

            if (usuarioDetalle == null) {
                usuarioDetalle = new UsuarioDetalle(usuario.getUsuarioPk());
            }
            usuariosInfo.add(new UsuarioInfo(usuario, usuarioDetalle));
        }

        return usuariosInfo;
    }

    private HashSet<Integer> buildDefaultRoles() {
        HashSet<Integer> roles = new HashSet<Integer>();
        roles.add(PerfilEnum.USER.getId());
        return roles;
    }
    
    @Override
    @Transactional
    public void insertFullUser(UsuarioInfo usuarioInfo) throws Exception {
        this.insertFullUser(usuarioInfo, buildDefaultRoles());
    }

    @Override
    @Transactional
    public void insertFullUser(UsuarioInfo usuarioInfo, HashSet<Integer> idsPerfil) throws Exception {
        if (usuarioInfo.getUsuarioDetalle() == null) {
            LOG.debug("El usuarioDetalle era nulo, se usará uno dummy");
            usuarioInfo.setUsuarioDetalle(buildUsuarioDetalleDummy(usuarioInfo.getUsuario().getUsuario()));
        }
        
        if (idsPerfil == null || idsPerfil.isEmpty()) {
            LOG.debug("No se especificó una lista de roles, se usará la lista default.");
            idsPerfil = buildDefaultRoles();
        }
        
        LOG.debug("Iniciando transaccion ACID");
        fillExtraData(usuarioInfo.getUsuario());

        // Inserta al usuario
        usuarioMapper.insert(usuarioInfo.getUsuario());
        LOG.debug("Efectuando la primera insercion dentro de la transaccion para la tabla Usuario");

        // Agrega privilegios al usuario recién creado:
        //int id = UsuarioServiceImpl.PERFIL_USUARIO;
        // Segunda parte de la transacción
        for (Integer idPerfil : idsPerfil) {
            usuarioPerfilMapper.insert(buildUsuarioPerfil(usuarioInfo.getUsuario().getUsuarioPk(), idPerfil));
        }

        LOG.debug("Efectuando la segunda insercion dentro de la transaccion para la tabla Perfil");

        usuarioInfo.getUsuarioDetalle().setUsuarioFk(usuarioInfo.getUsuario().getUsuarioPk());
        
        usuarioDetalleService.persiste(usuarioInfo.getUsuarioDetalle());
        LOG.debug("Efectuando la tercer insercion dentro de la transaccion para la tabla UsusarioDetalle");

    }

    private void fillExtraData(Usuario usr) {
        // Se llenan los datos no nulos faltantes
        usr.setInstanteDeBloqueo(DateWrapper.getTimeAgo(2009));
        usr.setFechaCreacion(DateWrapper.now());
        usr.setHabilitado(true);
        usr.setCredencialNoExpirada(true);
        usr.setCuentaNoBloqueada(true);
        usr.setCuentaNoExpirada(true);
        usr.setIdDeSeguridad(Crypt.getRandomString(50));
        usr.setVentanaParaIdSeguridad(usr.getInstanteDeBloqueo());
        usr.setFechaUltimoAcceso(DateWrapper.now());
        usr.setFechaUltimoCambioClave(DateWrapper.now());
    }

    private UsuarioPerfil buildUsuarioPerfil(Integer usuarioPk, Integer perfilFk) {
        return new UsuarioPerfil(usuarioPk, perfilFk);
    }
    
    private UsuarioDetalle buildUsuarioDetalleDummy(String login) {
        UsuarioDetalle usuarioDetalle = new UsuarioDetalle();
        
        usuarioDetalle.setNombre(login);
        usuarioDetalle.setApPaterno(login);
        usuarioDetalle.setDireccion("");
        usuarioDetalle.setTelefonos("");
        usuarioDetalle.setMandaCorreoPromo(false);
        
        return usuarioDetalle;
    }

    @Override
    @Transactional
    public void updateUser(UsuarioInfo usuarioInfoSelected, String email) throws Exception {
        if (usuarioInfoSelected.getUsuario().getUsuarioPk() == null) {
            Usuario usuario = getUserByCorreo(usuarioInfoSelected.getUsuario().getCorreo());
            if (usuario == null) {
                throw new Exception("No es posible actualizar al usuario debido a que no tiene id y no fue encontrado por medio de su usuario");
            } else {
                usuarioInfoSelected.getUsuario().setUsuarioPk(usuario.getUsuarioPk());
            }
        }
        
        usuarioInfoSelected.getUsuario().setCorreo(email);
        usuarioMapper.update(usuarioInfoSelected.getUsuario());
                
        UsuarioDetalle uis = usuarioInfoSelected.getUsuarioDetalle();
        
        if (uis.getUsuarioFk() == null) {
            uis.setUsuarioFk(usuarioInfoSelected.getUsuario().getUsuarioPk());
        }
        
        usuarioDetalleService.persiste(uis);
    }

    @Override
    public void update(Usuario usuario) {
        usuarioMapper.update(usuario);
    }
    
    @Override
    public List<Perfil> getPerfiles() {
        return perfilMapper.getAll();
    }

    @Override
    public List<Perfil> getPerfiles(String userName) {
        return perfilMapper.getUserPerfiles(userName);
    }

    @Override
    public boolean userMailExists(String email) {
        return loginWorkerService.userMailExists(email);
    }

    @Override
    public boolean userNameExists(String name) {
        return loginWorkerService.userNameExists(name);
    }

    @Override
    public Usuario getUserByName(String name) {
        return loginWorkerService.getUserByName(name);
    }

    @Override
    public Usuario getUserByCorreo(String correo) {
        return loginWorkerService.getUserByCorreo(correo);
    }

    @Override
    public Usuario getUserByIdInt(int id) {
        return loginWorkerService.getUserByIdInt(id);
    }

    @Override
    public Usuario credentialsOk(String user, String saltedPassword) {
        return loginWorkerService.credentialsOk(user, saltedPassword);
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String string) {
        return loginWorkerService.loadUserByUsername(string);
    }
}
