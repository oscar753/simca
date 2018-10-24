package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import mx.org.ift.simca.arq.core.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <p>Descripción</p>
 * ManageBean usado para albergar datos comunes.
 *
  */
@Controller
@Scope(value="singleton")
public class CommonDataMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Value("${max.inputText.length}")
    private Integer maxInputTextLength;
    
    @Value("${defaultTargetUrl}")
    private String defaultTargetUrl;

    @Value("${defaultFailureUrl}")
    private String defaultFailureUrl;
    
    @Value("${app.mode.light}")
    private boolean appModeLight;

    @Value("${render.gobal.growl}")
    private boolean renderGobalGrowl;
    
    @Autowired
    private AuthService authService;
    
	    @Value("${version.app}")
    private String versionApp;


    public String getVersionApp() {
        return versionApp;
    }

    @Value("${reporte.url}")
    private String reporteUrl;


    public String getReporteUrl() {
        return reporteUrl;
    }

	
    /**
     * Tamaño máximo usado en los inputText.
     *
     * @return Tamaño definido en la llave max.inputText.length del application.properties
     */
    public Integer getMaxInputTextLength() {
        return maxInputTextLength;
    }

    /**
     * Ruta default a la que se tiene que redirigir en caso de llegar a una página genérica
     * como las de error o información.
     * 
     * @return Valor de la llave defaultTargetUrl del archivo application.properties.
     */
    public String getDefaultTargetUrl() {
        return defaultTargetUrl;
    }

    /**
     * Ruta defualt a la que se manda en caso de error de autentificación.
     * 
     * @return Ruta para el error de autentificación.
     */
    public String getDefaultFailureUrl() {
        return defaultFailureUrl;
    }

    /**
     * Indica si el usuario no está logueado.
     * @return Indica si el usuario no está logueado.
     */
    public boolean isAnonymousUser() {
        return authService.isAnonymous();
    }
    
    /**
     * Indica si el usuario está logueado.
     * @return Indica si el usuario está logueado.
     */
    public boolean isLoggedUser() {
        return authService.isLogged();
    }
    
    /**
     * Indica si la aplicación está en modo light.
     * @return Indica si la aplicación está en modo light.
     */
    public boolean isAppModeLight() {
        return appModeLight;
    }

    /**
     * Indica si se debe renderear el growl global
     * @return Indica si se debe renderear el growl global
     */
    public boolean isRenderGobalGrowl() {
        return renderGobalGrowl;
    }
    
    /**    
     * Verifica si el usuario tiene, al menos, un rol de la lista.
     * @param roles Lista de roles separada por comas.
     * @return Verdadero en caso que tenga un rol de la lista, falso en otro caso.
     */
    public boolean isAnyRole(String roles) {
        if (authService.isAnonymous()) {
            return false;
        } else {
            String[] rolesArr = roles.split(",");
            
            Collection<String> autorities = authService.getCurrentUserAuthorities();
            
            for (String role : rolesArr) {
                for (String autority : autorities) {
                    if (autority.equalsIgnoreCase(role)) {
                        return true;
                    }
                }
            }
            
            return false;
        }
    }
    
    public String getUsuarioNombre() {
        String nombreUsuario="";
        if (!authService.isAnonymous()) {            
            nombreUsuario= authService.getCurrentUserName();
        } else{
            nombreUsuario="";
        }
        return nombreUsuario;
    }
    
    public String getFechaActual(){
        String fechaActual="";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date nvaFechaActual = new Date();
        fechaActual=formatoFecha.format(nvaFechaActual);
        return fechaActual;
    }
	
}
