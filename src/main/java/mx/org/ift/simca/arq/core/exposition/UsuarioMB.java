/*
 * .
 * ManagedBean asociado a los datos del usuario
 *
 */
package mx.org.ift.simca.arq.core.exposition;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import mx.org.ift.simca.arq.core.model.Perfil;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.model.UsuarioDetalle;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;
import mx.org.ift.simca.arq.core.service.AdminPerfilBagService;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioService;
import mx.org.ift.simca.arq.core.service.security.passUtils.PasswordFacadeService;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;
import mx.org.ift.simca.arq.core.support.xpath.CustomValidator;

@Controller
@Scope(value = "view")
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioMB.class);
    
    private transient ResourceBundle bundle = ResourceBundle.getBundle("i18n/textMessages");
    
    //Propiedades para la vista
    private UsuarioInfo usuarioInfoSelected;
    private boolean actionOk = false;
    private String login;
    private String passwordConf;
    private String email;
    
    private boolean borradoFisico;
    
    private List<UsuarioInfo> lista;
    private CustomValidator customValidator;
    
    private List<Perfil> perfiles;
    private String[] perfilesSelected = new String[0];
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AdminPerfilBagService adminPerfilBagService;
    
    @Autowired
    private PasswordFacadeService passwordFacadeService;
    
    
    
    public UsuarioLoggeadoMB usuarioLoggeadoMB;

    /**
     * Inicializa al bean.
     */
    @PostConstruct
    public void init() {
        String ss = "/validation/Usuario.xml";
        //String ss = UsuarioMB.class.getResource("/validation/Usuario.xml").getFile();
        this.customValidator = new CustomValidator(ss);
        lista = usuarioService.getUsuariosInfo();
        usuarioInfoSelected = new UsuarioInfo(new Usuario(), new UsuarioDetalle());
        perfiles = usuarioService.getPerfiles();
    }

    /**
     * Lista de usuarios.
     * @return Lista de usuarios.
     */
    public List<UsuarioInfo> getUsuariosInfo() {
        return lista;
    }

    /**
     * Confirmación del password.
     * @return Confirmación del password.
     */
    public String getPasswordConf() {
        return passwordConf;
    }

    /**
     * Confirmación del password.
     * @param passwordConf Confirmación del password.
     */
    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
    }

    /**
     * Usuario seleccionado.
     * @return Usuario seleccionado.
     */
    public UsuarioInfo getUsuarioInfoSelected() {
        return usuarioInfoSelected;
    }

	/**
     * Metodo que invoca automaticamente el metodo save
	  
																	   
     * @see #save(ActionEvent)
     */
    public void saveWithConfirm() {
        FacesContext  message = FacesContext.getCurrentInstance();
        try{
            String passwordEnc = passwordFacadeService.getPasswordOnlyEncrypted(usuarioInfoSelected.getUsuario().getUsuario() , usuarioInfoSelected.getUsuario().getUsuario());
            HashSet<Integer> idRoles = convertInt(perfilesSelected);

            usuarioInfoSelected.getUsuario().setClave(passwordEnc);
            usuarioInfoSelected.getUsuario().setCorreo(usuarioInfoSelected.getUsuario().getUsuario()+"@ift.org.com");
            usuarioInfoSelected.getUsuario().setFechaCreacion(new Date());
            usuarioInfoSelected.getUsuario().setPreguntaSecreta(usuarioInfoSelected.getUsuario().getUsuario());
            usuarioInfoSelected.getUsuario().setRespuestaSecreta(usuarioInfoSelected.getUsuario().getUsuario());
            
            
            usuarioInfoSelected.getUsuarioDetalle().setNombre(usuarioInfoSelected.getUsuario().getUsuario());
            usuarioInfoSelected.getUsuarioDetalle().setApPaterno(usuarioInfoSelected.getUsuario().getUsuario());
            usuarioInfoSelected.getUsuarioDetalle().setTelefonos("0");
            usuarioInfoSelected.getUsuarioDetalle().setDireccion("ift");
            
            if(!usuarioService.userMailExists(usuarioInfoSelected.getUsuario().getCorreo())){
                
            
                usuarioService.insertFullUser(usuarioInfoSelected, idRoles);
                message.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se creo exitosamente.", ""));
                usuarioInfoSelected.setUsuario(null);
                usuarioInfoSelected.setUsuarioDetalle(null);
                perfilesSelected = null;
            }else{
                message.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario ya existe.", ""));
            }
        }catch(Exception e){
            message.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo crear el usuario.", ""));
            LOGGER.error("No se pudo crear el usuario."+e);
        }
        
    }

    /**
     * Metodo que invoca automaticamente al metodo update.
     *
     * @param event se utiliza para pasar como parametro al metodo update
     * @see #update(ActionEvent)
     */
    public void updateWithConfirm(ActionEvent event) {
        update(event);
        PrimeFacesRequestContext.addCallBackParam(actionOk);
    }

    /**
     * Lista de perfiles disponibles.
     * @return Lista de perfiles disponibles.
     */
    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    /**
     * Perfiles seleccionados para el usuario.
     * @return Perfiles seleccionados para el usuario.
     */
    public String[] getPerfilesSelected() {
        return perfilesSelected;
    }

    /**
     * Perfiles seleccionados para el usuario.
     * @param perfilesSelected Perfiles seleccionados para el usuario.
     */
    public void setPerfilesSelected(String[] perfilesSelected) {
        this.perfilesSelected = perfilesSelected;
    }

    /**
     * Se permite borrado físico.
     * @param borradoFisico Se permite borrado físico.
     */
    public void setBorradoFisico(boolean borradoFisico) {
        this.borradoFisico = borradoFisico;
    }

    /**
     * Se permite borrado físico.
     * @return Se permite borrado físico.
     */
    public boolean isBorradoFisico() {
        return borradoFisico;
    }

    /**
     * Metodo que establece la informacion del usuario seleccionado. Ademas de establecer los perfiles disponibles del
     * usuario en el atributo perfilesSelected
     *
     * @param usuarioInfoSelected Informacion del usuario seleccionado
     * @see usuarioInfoSelected
     * @see perfilesSelected
     */
    public void setUsuarioInfoSelected(UsuarioInfo usuarioInfoSelected) {
        this.usuarioInfoSelected = usuarioInfoSelected;
        if (usuarioInfoSelected != null) {
            login = usuarioInfoSelected.getUsuario().getUsername();
            email = usuarioInfoSelected.getUsuario().getCorreo();
            perfilesSelected = convertStr(usuarioService.getPerfiles(usuarioInfoSelected.getUsuario().getUsername()));
        }
    }

    public void selectedUsuarioInfo(UsuarioInfo usuarioInfoSelected){
        this.usuarioInfoSelected = usuarioInfoSelected;
        perfilesSelected = convertStr(usuarioService.getPerfiles(usuarioInfoSelected.getUsuario().getUsername()));
    }
    
    /**
     * Metodo invocado desde la pagina DeleteUserDataPopup.xhtml al presionar el boton "Eliminar" Este metodo elimina
     * del sistema el usuario.
     *
     * @param ae Este metodo no se utiliza en este metodo.
     */
    public void deleteUser(ActionEvent ae) {
        Usuario usr = this.usuarioInfoSelected.getUsuario();
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (this.borradoFisico) {
            adminPerfilBagService.deleteFisico(usr.getUsuarioPk());
            lista = usuarioService.getUsuariosInfo();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Se borro exitosamente","" ));
        } else {
            boolean tmp = usuarioInfoSelected.getUsuario().isHabilitado();
            adminPerfilBagService.deleteUser(usr.getUsuarioPk(),!tmp);
            lista = usuarioService.getUsuariosInfo();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Se "+(tmp?"deshabilito":"habilito")+" exitosamente","" ));
        }
        
        PrimeFacesRequestContext.addCallBackParam(true);
    }

    /**
     * Metodo invocado desde la UserRolesPopup.xhtml al presionar el boton Guardar. Este metodo permite guardar los
     * perfiles seleccionados para el usuario.
     *
     * @param ae Este parametro no es utilizado en este metodo.
     */
    public void guardaPerfiles(ActionEvent ae) {
        Usuario usr = this.usuarioInfoSelected.getUsuario();
        adminPerfilBagService.guardaPerfiles(usr.getUsuarioPk(), convertInt(perfilesSelected));
        PrimeFacesRequestContext.addCallBackParam(true);
    }

    /**
     * Este metodo guarda los datos del usuario. Este metodo valida que los componentes cumplan con las reglas
     * establecidas para considerar valido un campo. Por ejemplo, password, email, etc.
     *
     * @param event Se utiliza para obtener los componentes asociados a la vista.
     */
    public void save(ActionEvent event) {
        UIComponent component = event.getComponent();
        this.actionOk = false;

        //Se realizan las validaciones sencillas
        this.customValidator.validaDatos(component);

        //Recuperamos los mensajes del archivo de propiedades
        String passwordConfNotEquals = bundle.getString("restricted.admin.users.common.passwordConfNotEquals");
        String loginAlreadyExists = bundle.getString("restricted.admon.users.common.loginAlreadyExists");
        String emailAlreadyExists = bundle.getString("restricted.admon.users.common.emailAlreadyExists");

        List<String> notValidPassword = new ArrayList<String>();
        notValidPassword.add(bundle.getString(PasswordFacadeService.PASSWORD_STRENGTH_FAIL_MSG));
        String passwordEnc = passwordFacadeService.getPasswordEncrypted(usuarioInfoSelected.getUsuario().getUsername(), usuarioInfoSelected.getUsuario().getPassword(), notValidPassword);

        //Realizamos algunas validaciones extras
        if (!usuarioInfoSelected.getUsuario().getPassword().isEmpty() && !passwordConf.isEmpty()) {
            ContextUtils.evalNonEq(
                    usuarioInfoSelected.getUsuario().getPassword(),
                    passwordConf,
                    passwordConfNotEquals,
                    component.findComponent("password"),
                    component.findComponent("passwordConf"));
        }

        //Se crean los mensajes de la fortaleza de password
        if (!usuarioInfoSelected.getUsuario().getPassword().isEmpty() && passwordEnc == null) {
            UIComponent passComponent = component.findComponent("password");
            String traduccion = LanguageMB.translate(notValidPassword.get(1));
            ContextUtils.addErrorMsg(passComponent, traduccion);
            //ContextUtils.addErrorMsg(passComponent, stbd.toString());
        }

        //Se realiza la validación con la base de datos
        if (!usuarioInfoSelected.getUsuario().getCorreo().isEmpty()) {
            ContextUtils.evalTrue(
                    usuarioService.userMailExists(usuarioInfoSelected.getUsuario().getCorreo()),
                    emailAlreadyExists,
                    component.findComponent("email"));
        }
        if (!usuarioInfoSelected.getUsuario().getUsuario().isEmpty()) {
            ContextUtils.evalTrue(
                    usuarioService.userNameExists(usuarioInfoSelected.getUsuario().getUsuario()),
                    loginAlreadyExists,
                    component.findComponent("usuario"));
        }

        if (ContextUtils.countMessages() < 1) {
            try {
                HashSet<Integer> idRoles = convertInt(perfilesSelected);
                usuarioInfoSelected.getUsuario().setClave(passwordEnc);
                usuarioService.insertFullUser(usuarioInfoSelected, idRoles);
                actionOk = true;
            } catch (Exception e) {
                actionOk = false;
                ContextUtils.addErrorMsg(e.getMessage(), e.getMessage());
                LOGGER.error("Error al insertar al usuario", e);
            }
        }
        PrimeFacesRequestContext.addCallBackParam(actionOk);
    }

    /**
     * Este metodo convierte los ids del parametro String[] idsStr a un HashSet<Integer>. Si se establece el parametro
     * boolean agregaDefault a true se agrega al HashSet el valor del atributo idPerfilUser.
     *
     * @param idsStr Arreglo con ids
     * @param agregaDefault true si se desea agregar el atributo idPersilUser
     * @return Retorna un HashSet con la informacion del arreglo convertida a Integer.
     */
    private HashSet<Integer> convertInt(String[] idsStr) {
        if (idsStr == null) {
            return new HashSet<Integer>(1);
        } else {
            HashSet<Integer> res = new HashSet<Integer>();

            for (int i = 0; i < idsStr.length; i++) {
                res.add(Integer.parseInt(idsStr[i]));
            }
            
            if(!res.contains(1)){ //Validamos perifl de usuario si no lo tiene lo agregamos
                res.add(1);
            }
            
            return res;
        }
    }

    /**
     * Este metodo convierte a un arreglo de String los ids de los Perfiles establecidos en el parametro List<Perfil>
     *
     * @param perfiles lista de perfiles a convertir
     * @return Retorna un arreglo de String con los ids de los Perfiles.
     */
    private String[] convertStr(List<Perfil> perfiles) {
        if (perfiles == null) {
            return new String[0];
        } else {
            String[] res = new String[perfiles.size()];

            for (int i = 0; i < perfiles.size(); i++) {
                res[i] = perfiles.get(i).getPerfilPk() + "";
            }

            return res;
        }
    }

    /**
     * Metodo utilizado para poder redireccionar una url. La implementacion utiliza dispatcher.forward
     *
     * @param url Cadena con la url a donde se enviara la peticion.
     * @throws ServletException
     * @throws IOException
     */
    public void redirect(String url) throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        ServletRequest servletRequest = (ServletRequest) context.getRequest();
        ServletResponse servletResponse = (ServletResponse) context.getResponse();

        RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(url);

        dispatcher.forward(servletRequest, servletResponse);
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Metodo utilizado en la pantalla EditUserDataPoppup.xhtml al presionar el boton guardar. Este metodo realiza
     * validaciones sobre los datos capturados y actualiza la informacion del usuario en el sistema.
     *
     * @param event Se utiliza para poder obtener los componentes asociados a la vista.
     */
    public void update(ActionEvent event) {
        UIComponent component = event.getComponent();
        actionOk = false;

        //Se realizan las validaciones sencillas
        this.customValidator.validaDatos(component);

        if (!email.equalsIgnoreCase(usuarioInfoSelected.getUsuario().getCorreo())) {
            String emailAlreadyExists = bundle.getString("restricted.admon.users.common.emailAlreadyExists");
            
            ContextUtils.evalTrue(
                    usuarioService.userMailExists(usuarioInfoSelected.getUsuario().getCorreo()),
                    emailAlreadyExists,
                    component.findComponent("email"));
        }
        
        if (ContextUtils.countMessages() < 1) {
            try {
                usuarioService.updateUser(usuarioInfoSelected, email);
                actionOk = true;
            } catch (Exception e) {
                actionOk = false;
            }
        }
    }

    /**
     * Borra al usuario.
     * @param event Evento asociado a la acción.
     */
    public void delete(ActionEvent event) {
        // TODO Ver como va a ser el borrado

        // usuarioDetalleMapper.delete(usuarioInfoSelected.getUsuarioDetalle());
        // usuarioMapper.delete(usuarioInfoSelected.getUsuario());
        usuarioInfoSelected = null;
    }
}
