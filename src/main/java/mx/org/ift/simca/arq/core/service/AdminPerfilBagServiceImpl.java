package mx.org.ift.simca.arq.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.org.ift.simca.arq.core.model.AdminPerfilBag;
import mx.org.ift.simca.arq.core.model.Perfil;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;
import mx.org.ift.simca.arq.core.model.UsuarioPerfil;
import mx.org.ift.simca.arq.core.persistence.PerfilMapper;
import mx.org.ift.simca.arq.core.persistence.UsuarioMapper;
import mx.org.ift.simca.arq.core.persistence.UsuarioPerfilMapper;

/**
 * La clase AdminPerfilBagServiceImpl lleva a cabo la implementación de la definición de la Interfaz
 * AdminPerfilBagService.
*/
@Service(value = "adminPerfilBagService")
public class AdminPerfilBagServiceImpl implements AdminPerfilBagService {

    private static final long serialVersionUID = 1L;
    /**
     * Inyección del bean de spring llamado perfilMapper para manejo de operaciones de persistencia sobre la entidad
     * 'perfil'.
     */
    @Autowired
    private transient PerfilMapper perfilMapper;
    /**
     * Inyección del bean de spring llamado usuarioPerfilMapper para el manejo de operaciones de persistencia sobre la
     * entidad UsuarioPerfil.
     */
    @Autowired
    private transient UsuarioPerfilMapper usuarioPerfilMapper;
    /**
     * Inyección del bean de spring llmado usuarioMapper para el manejo de operaciones de persistencia sobre la entidad
     * Usuario.
     */
    @Autowired
    private transient UsuarioMapper usuarioMapper;

    @Override
    public List<AdminPerfilBag> getPerfiles(UsuarioInfo usuarioInfoSelected) {
        if (usuarioInfoSelected == null) {
            return new ArrayList<AdminPerfilBag>();
        }
        String username = usuarioInfoSelected.getUsuario().getUsername();
        return getAdminPerfilBag(username);
    }

    @Override
    @Transactional
    public void guardaPerfiles(int usrId, HashSet<Integer> idsRoles) {
        usuarioPerfilMapper.deleteByIdUsuario(usrId);
        UsuarioPerfil usuarioPerfil;
        for (Integer idRol : idsRoles) {
            usuarioPerfil = new UsuarioPerfil(usrId, idRol);
            usuarioPerfilMapper.insert(usuarioPerfil);
        }
    }

    @Override
    public List<AdminPerfilBag> getAdminPerfilBag(String username) {
        List<AdminPerfilBag> result = new ArrayList<AdminPerfilBag>();

        List<Perfil> lista = perfilMapper.getUserPerfiles(username);
        List<Perfil> all = perfilMapper.getAll();

        for (Perfil p : all) {
            boolean pertenece = buscaEn(lista, p.getAuthority());
            result.add(new AdminPerfilBag(p, pertenece));
        }
        return result;
    }

    /**
     * Se busca si existe la autoridad dentro de la lista.
     *
     * @param lista Es la lista en la cual es posible que este la autoridad
     * @param authority Es la autoridad que se desea encontrar
     * @return boolean, true si la autoridad se encuentra el la lista, false si la autoridad no se encuentra en la
     * lista.
     */
    private boolean buscaEn(List<Perfil> lista, String authority) {
        for (Perfil p : lista) {
            if (p.getAuthority().equals(authority)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteUser(int idUser,boolean status) {
        Usuario usr = new Usuario();
        usr.setUsuarioPk(idUser);
        usr.setHabilitado(status);
        usuarioMapper.delete(usr);
        //System.out.println("Usuario borrado: " + idUser);
    }

    @Override
    @Transactional
    public void deleteFisico(int idUser) {
        usuarioMapper.deleteFisicoA(idUser);
        usuarioMapper.deleteFisicoB(idUser);
        usuarioMapper.deleteFisicoC(idUser);
    }
}
