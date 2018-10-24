package mx.org.ift.simca.arq.core.service.security;

import java.util.ArrayList;
import java.util.Collection;

import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.persistence.UsuarioMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * AuthServiceImpl.java - Implementa las funciones necesarias para autorizar a los usuarios firmados.
*/
@Service("authService")
public class AuthServiceImpl implements AuthService {

    /**
     * Representa el valor inicial de la versión del serial.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Inyección del bean de spring llamado usuarioMapper para manejo de operaciones de persistencia sobre la entidad
     * 'Usuario'.
     */
    @Autowired
    private UsuarioMapper usuarioMapper;

    /**
     * Indica si el usuario es anónimo.
     *
     * @return True si el usuario es anónimo y false en caso contrario.
     */
    @Override
    public boolean isAnonymous() {
        return getCurrentUserName().equals(ANONYMOUS);
    }

    /**
     * Indica si el usuario está firmado al sistema.
     *
     * @return True si el usuario está firmado al sistema y false en caso contrario.
     */
    @Override
    public boolean isLogged() {
        return !isAnonymous();
    }

    /**
     * Regresa el nombre del usuario actualmente conectado.
     *
     * @return Nombre del usuario actualmente conectado.
     */
    @Override
    public String getCurrentUserName() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        if (au == null) {
            return ANONYMOUS;
        } else {
            return au.getName();
        }
    }

    /**
     * Devuelve los permisos con que cuenta el usuario firmado.
     *
     * @return Permisos del usuario.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<String> getCurrentUserAuthorities() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        Collection<String> result = new ArrayList<String>();
        if (au == null) {
            return result;
        }
        Collection<GrantedAuthority> granted = (Collection<GrantedAuthority>) au.getAuthorities();
        for (GrantedAuthority ga : granted) {
            result.add(ga.getAuthority());
        }
        return result;
    }

    /**
     * Regresa el identificador único del usuario.
     *
     * @return Identificador del usuario actualmente conectado.
     */
    @Override
    public int getCurrentUserId() {
        String name = getCurrentUserName();
        if (!isAnonymous()) {
            Usuario user = usuarioMapper.getUserByName(name);
            if (user == null) {
                return 0;
            } else {
                return user.getUsuarioPk();
            }
        }
        return 0;
    }
}
