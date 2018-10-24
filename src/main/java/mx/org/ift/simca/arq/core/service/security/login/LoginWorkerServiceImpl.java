package mx.org.ift.simca.arq.core.service.security.login;

import javax.faces.application.FacesMessage;
import javax.security.auth.login.AccountExpiredException;
import mx.org.ift.simca.arq.core.enums.TipoBitacoraEnum;
import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.model.Usuario;
import mx.org.ift.simca.arq.core.persistence.UsuarioMapper;
import mx.org.ift.simca.arq.core.support.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de LoginWorkerService
 *
*/
@Service(value = "loginWorkerService")
public class LoginWorkerServiceImpl implements LoginWorkerService {

    /**
     * Representa el valor inicial de la versión del serial.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Usado cuando la excepción no fue contemplada.
     */
    private static final int NO_CONTEMPLADO = -1;
    /**
     * Usado cuando el usuario no existe.
     */
    private static final int PASSWORD_INCORRECTO_SIMPLE = 0;
    /**
     * Usado cuando el usuario existe y el password es incorrecto.
     */
    private static final int PASSWORD_INCORRECTO = 1;
    /**
     * Usado cuando el usuario se bloquea por primera vez.
     */
    private static final int USR_BLOQUEADO = 2;
    /**
     * Usado cuando el usuario ya estaba bloqueado y continua bloqueado.
     */
    private static final int USR_AUN_BLOQUEADO = 3;
    /**
     * Usado cuando la cuanta está expirada.
     */
    private static final int CUENTA_EXPIRADA = 4;
    /**
     * La constante OK_CLEAN_ERRORS.
     */
    public static final int OK_CLEAN_ERRORS = 10;
    /**
     * La constante ERROR_CLEAN_ERRORS.
     */
    public static final int ERROR_CLEAN_ERRORS = 11;
    /**
     * Inyección del bean de spring llamado usuarioMapper para manejo de operaciones de persistencia sobre la entidad
     * 'Usuario'.
     */
    @Autowired
    private UsuarioMapper usuarioMapper;
    /**
     * Representa el número de intentos permitidos.
     */
    @Value("${login.maxInvalidTries}")
    private int intentosPermitidos;
    /**
     * Representa el tiempo que una cuenta se bloquea por exceder el máximo de intentos fallidos de autenticación.
     */
    @Value("${login.blokedWindowTime}")
    private long tiempoBloqueoCastigo;
    /**
     * Representa los días que una clave puede ser de vieja como máximo.
     */
    @Value("${login.maxPassowdLive}")
    private int maxPassowdLive;

    @Override
    public boolean userNameExists(String name) {
        return usuarioMapper.getUserByName(name) != null;
    }

    @Override
    public boolean userMailExists(String email) {
        return usuarioMapper.getUserByCorreo(email) != null;
    }

    @Override
    public Usuario getUserByCorreo(String correo) {
        return usuarioMapper.getUserByCorreo(correo);
    }

    @Override
    public Usuario getUserByName(String name) {
        return usuarioMapper.getUserByName(name);
    }

    @Override
    public int cleanErrors(String username) {
        if (username == null) {
            return ERROR_CLEAN_ERRORS;
        } else {
            Usuario usr = usuarioMapper.getUserByName(username);
            if (usr != null) {
                usr.setContadorIntentosFallidos(0);
                usr.setInstanteDeBloqueo(DateWrapper.getTimeAgo(2009));
                usr.setFechaUltimoAcceso(DateWrapper.now());
                usuarioMapper.update(usr);
                return OK_CLEAN_ERRORS;
            } else {
                return ERROR_CLEAN_ERRORS;
            }
        }
    }

    @Override
    public FacesMessage getErrorMessage(Exception ex, String username) {
        String un = " [" + username + "] ";
        Usuario usr = usuarioMapper.getUserByName(username);
        if (usr == null) {
            Audit.write(TipoBitacoraEnum.UNAUTHORIZED_ACCESS, translate("PASSWORD_INCORRECTO_SIMPLE_MSG") + un);
            return new FacesMessage(translate("PASSWORD_INCORRECTO_SIMPLE_MSG"));
        }

        int code = revisa(ex, usr);
        long instanteDeBloqueo = usr.getInstanteDeBloqueo().getTime();
        int contadorIntentosFallidos = usr.getContadorIntentosFallidos();

        String msg;
        if (code == PASSWORD_INCORRECTO_SIMPLE) {
            msg = translate("PASSWORD_INCORRECTO_SIMPLE_MSG");
            Audit.write(TipoBitacoraEnum.LOGIN_NOT_FOUND, msg + un);
        } else if (code == PASSWORD_INCORRECTO) {
            msg = translate("PASSWORD_INCORRECTO_MSG", contadorIntentosFallidos + "", intentosPermitidos + "");
            Audit.write(TipoBitacoraEnum.WRONG_PASSWORD, msg + un);
        } else if (code == USR_BLOQUEADO) {
            msg = translate("USR_BLOQUEADO_MSG", aMinutos(tiempoBloqueoCastigo));
            Audit.write(TipoBitacoraEnum.USER_BLOCKED, msg + un);
        } else if (code == USR_AUN_BLOQUEADO) {
            String restante = aMinutos(tiempoRestante(instanteDeBloqueo, tiempoBloqueoCastigo));
            msg = translate("USR_AUN_BLOQUEADO_MSG", restante);
            Audit.write(TipoBitacoraEnum.USER_BLOCKED, msg + un);
        } else if (code == CUENTA_EXPIRADA) {
            msg = translate("CUENTA_EXPIRADA_MSG");
            Audit.write(TipoBitacoraEnum.UNAUTHORIZED_ACCESS, msg + un);
        } else {
            msg = translate("ERROR_INESPERADO_MSG");
            Audit.write(TipoBitacoraEnum.GENERAL, msg + un);
        }
        return new FacesMessage(msg);
    }

    /**
     * Soporte para la función 'format'.
     *
     * @param key Clave para la traducción
     * @param args Argumentos para la traducción
     * @return Cadena Traducida
     */
    private String translate(String key, String... args) {
        String stringMessageFromPropertiesFile = obten(key);
        return format(stringMessageFromPropertiesFile, args);
    }

    /**
     * Obtiene de un archivo de propiedades cada mensaje en el idioma esperado.
     *
     * @param key Clave del objeto que se desea obtener
     * @return Retorna el valor asociado a la llave dada
     */
    private String obten(String key) {
        if (key.equals("PASSWORD_INCORRECTO_SIMPLE_MSG")) {
            return "Password Incorreto !";
        }
        if (key.equals("PASSWORD_INCORRECTO_MSG")) {
            return "Password Incorreto. Intento fallido numero: {0} de {1}";
        }
        if (key.equals("USR_BLOQUEADO_MSG")) {
            return "Usuario bloqueado por {0} minutos";
        }
        if (key.equals("USR_AUN_BLOQUEADO_MSG")) {
            return "Usuario aun bloqueado. Intente en: {0}";
        }
        if (key.equals("CUENTA_EXPIRADA_MSG")) {
            return "Cuenta expirada";
        }
        return "Error inesperado";
    }

    /**
     * Sustituye en una cadena dada (source) las coincidencias de {n} por el n-ésimo argumento dado en "params".
     *
     * @param source cadena original
     * @param params arreglo de cadenas
     * @return Cadena sustituida
     */
    private String format(String source, String... params) {
        if (params == null || params.length < 1) {
            return source;
        }
        for (int i = 0; i < params.length; i++) {
            source = source.replaceAll("\\{" + i + "\\}", params[i]);
        }
        return source;
    }

    /**
     * Convierte una cantidad de milisegundos a minutos.
     *
     * @param milisegundos Cantidad en milisegundos
     * @return Cantidad en minutos
     */
    private static String aMinutos(long milisegundos) {
        StringBuilder stbd = new StringBuilder();

        long segundos = Math.round(milisegundos / 1000D);

        stbd.append(Math.ceil(segundos / 60.0));
        int seg = ((int) segundos) % 60;

        if (seg > 0) {
            stbd.append(" minutos y ");
            if (seg < 10) {
                stbd.append("0");
            }
            stbd.append(seg);
            stbd.append(" segundos");
        } else {
            stbd.append(" minutos");
        }

        return stbd.toString();
    }

    /**
     * Calcula el tiempo que aún permanecerá un usuario bloqueado.
     *
     * @param instanteDeBloqueo Momento de tiempo en el cual se llevó a cabo el bloqueo
     * @param tiempoBloqueoCastigo Cantidad en tiempo durante el cual el usuario no podrá hacer el login.
     *
     * @return tiempo en milisegundos que un usuario permanecerá bloqueado
     */
    private static int tiempoRestante(long instanteDeBloqueo, long tiempoBloqueoCastigo) {
        long tmp = instanteDeBloqueo - System.currentTimeMillis() + tiempoBloqueoCastigo;
        if (tmp > 0) {
            return (int) tmp;
        } else {
            return 0;
        }
    }

    @Override
    public Usuario getUsuario(String username) {
        return usuarioMapper.getUserByName(username);
    }

    @Override
    public int revisa(Exception e, String username) {
        Usuario usr = usuarioMapper.getUserByName(username);
        if (usr != null) {
            long momentoCreacion = System.currentTimeMillis();
            boolean claveVieja = (System.currentTimeMillis() - momentoCreacion) > (1000 * 60 * 60 * 24 * maxPassowdLive);
            if (claveVieja) {
                return CUENTA_EXPIRADA;
            }
            return revisa(e, usr);
        }
        return PASSWORD_INCORRECTO_SIMPLE;
    }

    @Override
    public int revisa(Exception e, Usuario usr) {
        if (e instanceof BadCredentialsException) {
            if (usr != null) {
                int contador = usr.getContadorIntentosFallidos();
                usr.setContadorIntentosFallidos(contador + 1);
                usuarioMapper.update(usr);
                if (contador >= intentosPermitidos - 1) {
                    usr.setCuentaNoBloqueada(false);
                    usr.setInstanteDeBloqueo(DateWrapper.now());
                    usuarioMapper.update(usr);
                    return USR_BLOQUEADO;
                } else {
                    return PASSWORD_INCORRECTO;
                }
            } else {
                return PASSWORD_INCORRECTO_SIMPLE;
            }
        } else if (e instanceof LockedException) {
            return USR_AUN_BLOQUEADO;
        } else if (e instanceof AccountExpiredException) {
            return CUENTA_EXPIRADA;
        } else {
            return NO_CONTEMPLADO;
        }
    }

    @Override
    public Usuario getUserByIdInt(int id) {
        return usuarioMapper.getById(new Usuario(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usr = usuarioMapper.getUserByName(username);
        if (usr == null) {
            return new Usuario();
        }// aqui asumo que el password viene vacio
        // si esta bloqueado:
        if (!usr.isAccountNonLocked()) {
            long currentTime = System.currentTimeMillis();
            long instanteDeBloqueo = usr.getInstanteDeBloqueo().getTime();
            // y ya paso su tiempo de castigo:
            if (instanteDeBloqueo + tiempoBloqueoCastigo < currentTime) {
                // desbloquealo:
                usr.setCuentaNoBloqueada(true);
                usr.setContadorIntentosFallidos(0);
                //usr.setInstanteDeBloqueo(0);// esto no es obligatorio
                usr.setFechaUltimoAcceso(DateWrapper.now());
                usuarioMapper.update(usr);
            }
        }
        if (usr.getContadorIntentosFallidos() > intentosPermitidos) {
            usr.setClave("");
        }
        return usr;
    }

    @Override
    public Usuario credentialsOk(String user, String saltedPassword) {
        Usuario usuario = getUserByName(user);
        if (usuario != null && usuario.getPassword() != null && usuario.getPassword().equals(saltedPassword)) {
            return usuario;
        }
        return null;
    }
}
