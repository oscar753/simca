package mx.org.ift.simca.arq.core.support.context;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.org.ift.simca.arq.core.support.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>Descripción:</p>
 * Clase utilitaria que expone una gran cantidad de métodos de propósito general
 * en el manejo del contexto de Faces.
*/
public class ContextUtils extends EvalContextUtils {

    /**
     * Representa el valor inicial de la versión del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constante para anclar el guardado y recuperacion del actual IP del
     * cliente
     */
    private static final String STORE_CURRENT_IP_ADDRESS = "MATI_STORE_CURRENT_IP_ADDRESS_GOOSE";
    /**
     * Constante para anclar la excepcion que pudiera ser disparada durante el
     * proceso de login
     */
    private static final String LOGIN_FACES_MESSAGE_VARNAME = "MATI_LoginFacesMessageVarNameOnSession_GOOSE";
    /**
     * La constante FORMATO_DDMMYYYY.
     */
    public static final String FORMATO_DDMMYYYY = "dd/MM/yyyy";
    /**
     * La constante FORMATO_DDMMYYYYHHMMSS.
     */
    public static final String FORMATO_DDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
    /**
     * La constante FORMATO_YYYYMMDD_HHMMSS.
     */
    public static final String FORMATO_YYYYMMDD_HHMMSS = "yyyyMMdd_HHmmss";
    /**
     * La constante CURRENT_LOGGING_USER.
     */
    public static final String CURRENT_LOGGING_USER = "intendedUserToLogin_FROM THIS APPLICATION";
    /**
     * La constante countries.
     */
    public static final Map<String, Object> COUNTRIES;
    /**
     * Constante para identificar al usuario 'anonimo'
     */
    public static final String ANONYMOUS = "anonymousUser";
    /**
     * Logger para los mensajes.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContextUtils.class);

    static {
        COUNTRIES = new LinkedHashMap<String, Object>();
        COUNTRIES.put("English", new Locale("en", "US")); //label, value
        COUNTRIES.put("French", new Locale("fr", "CA"));
        COUNTRIES.put("Spanish", new Locale("es", "MX"));
        //COUNTRIES.put("Chinese", Locale.SIMPLIFIED_CHINESE); // tambien sirve !!!
        COUNTRIES.put("Chinese", new Locale("zh", "CN"));
    }

    /**
     * Por ser una clase utilitaria, el constructor no es público.
     */
    protected ContextUtils() {
    }

    /**
     * Se asigna a una nueva localidad
     *
     * @param newLocaleValue (String) es la nueva localidad que se desea
     * establecer
     */
    public static void setNewLocal(String newLocaleValue) {
        FacesContext facesContext = getFacesContext();
        if (facesContext == null) {
            LOGGER.error("No se puede cambiar el locale de Java Server Faces.");
        } else {
            for (Map.Entry<String, Object> entry : COUNTRIES.entrySet()) {
                if (entry.getValue().toString().equals(newLocaleValue)) {
                    facesContext.getViewRoot().
                            setLocale((Locale) entry.getValue());
                }
            }
        }
    }

    /**
     * Se almacena el nombre de usuario en SessionMap
     *
     * @param widgetName (String) Es el nombre del Widget que contiene al nombre
     * del usuario
     * @return username (String) el nombre del usuario
     */
    public static String setUserNameToMemory(String widgetName) {
        String username = ContextUtils.getRequest().getParameter(widgetName);
        if (username != null) {
            setValueToSessionMap(CURRENT_LOGGING_USER, username);
        }
        return username;
    }

    /**
     *
     */
    public static void cleanLoginFacesMessageVarname() {
        setMessageToMem(LOGIN_FACES_MESSAGE_VARNAME, null);
    }

    /**
     *
     * @param msg
     */
    public static void setLoginFacesMessageVarname(FacesMessage msg) {
        setMessageToMem(LOGIN_FACES_MESSAGE_VARNAME, msg);
    }

    /**
     *
     * @return
     */
    public static String getCurrentIPAddress() {
        return getObjectFromSession(STORE_CURRENT_IP_ADDRESS, "NoAplica");
    }

    /**
     *
     * @return
     */
    public static String getUserNameFromMemory() {
        return getObjectFromSession(CURRENT_LOGGING_USER, ANONYMOUS);
    }

    /**
     * Se obtiene un objeto almacenado en la sesión
     *
     * @param key (String) la llave del objeto almacenado
     * @param def (String) el valor de objeto
     * @return def (String) el objeto recuperado
     */
    public static String getObjectFromSession(String key, String def) {
        Object obj = getSession().getAttribute(key);
        if (obj != null) {
            return obj.toString();
        }
        return def;
    }

    /**
     *
     * @param ip
     */
    public static void setCurrentIPAddress(String ip) {
        getSession().setAttribute(STORE_CURRENT_IP_ADDRESS, ip);
    }

    /**
     * Se obtiene el mensaje MATI_LoginFacesMessageVarNameOnSession_GOOSE
     *
     * @return FacesMesssage, el objeto FacesMessage que se mostrará
     */
    public static FacesMessage getLoginFacesMessageVarname() {
        Object obj = getMessageFromMem(LOGIN_FACES_MESSAGE_VARNAME);
        if (obj != null) {
            return (FacesMessage) obj;
        }
        return null;
    }

    /**
     *
     * @param key
     * @return
     */
    public static Object getMessageFromMem(String key) {
        return getSessionMap().get(key);
    }

    /**
     *
     * @param key
     * @param fm
     */
    public static void setMessageToMem(String key, FacesMessage fm) {
        getSessionMap().put(key, fm);
    }

    private static FacesContext getFacesContext() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            LOGGER.warn("Se intentó acceder al FacesContext fuera del contexto de Java Server Faces, se regresará una instancia nula.");
        }
        return facesContext;
    }

    private static ExternalContext getExtCont() {
        FacesContext facesContext = getFacesContext();
        if (facesContext == null) {
            LOGGER.warn("No se puede acceder al contexto desde el FacesContext.");
            return null;
        } else {
            return getFacesContext().getExternalContext();
        }
    }

    /**
     * Regresa el nombre del usuario que ha iniciado sesión actualmente. Puede
     * ser "anónimo"
     *
     * @return Cadena con el nombre requerido
     */
    public static String getCurrentUserName() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        if (au == null) {
            return ANONYMOUS;
        } else {
            return au.getName();
        }
    }

    /**
     * Recupera el resource bundle7.
     *
     * @param name el name
     * @return El resource bundle7
     */
    public static ResourceBundle getResourceBundle7(String name) {
        FacesContext context = getFacesContext();
        if (context == null) {
            LOGGER.warn("No se puede recuperar el locale de Java Server Faces, se usará el defualt.");
            return ResourceBundle.getBundle(name);
        } else {
            Locale lang = context.getViewRoot().getLocale();
            //lang = new java.util.Locale("zh","CN");
            return ResourceBundle.getBundle(name, lang);
        }
    }

    /**
     * Crea el global faces message.
     *
     * @param summary el summary
     * @param detail el detail
     */
    public static void createGlobalFacesMessage(String summary, String detail) {
        FacesContext context = getFacesContext();
        if (context == null) {
            LOGGER.error("No se puede crear el mensaje.");
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
            context.addMessage(null, fm);
        }
    }

    /**
     * Recupera el int param.
     *
     * @param name el name
     * @return El int param
     */
    public static int getIntParam(String name) {
        Object obj = getParameter(name);
        if (obj == null) {
            return 0;
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExtCont().getRequest();
    }

    /**
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) getExtCont().getResponse();
    }

    /**
     *
     * @return
     */
    public static HttpSession getSession() {
        return (HttpSession) getExtCont().getSession(true);
    }

    /**
     * Recupera el session map object.
     *
     * @param name el name
     * @return El session map object
     */
    public static Object getSessionMapObject(String name) {
        return getSessionMap().get(name);
    }

    /**
     * Elimina un objeto del map de la sesión.
     *
     * @param name Nombre del objeto a remover.
     * @return El objeto removido o nulo.
     */
    public static Object removeSessionMapObject(String name) {
        return getSessionMap().remove(name);
    }

    private static Map<String, Object> getSessionMap() {
        return getExtCont().getSessionMap();
    }

    /**
     * Recupera el value from session map.
     *
     * @param key el key
     * @return El value from session map
     */
    public static Object getValueFromSessionMap(String key) {
        return getSessionMap().get(key);
    }

    /**
     * Establece el value to session map.
     *
     * @param key el key
     * @param obj el obj
     */
    public static void setValueToSessionMap(String key, Object obj) {
        getSessionMap().put(key, obj);
    }

    /**
     * Recupera un objeto del mapa del request (de FacesContext).
     *
     * @param key Nombre del objeto.
     * @return Objeto encontrado o nulo.
     */
    public static Object getRequestMapObject(String key) {
        return getRequestMap().get(key);
    }

    /**
     * Elimina un objeto del mapa del request (de FacesContext).
     *
     * @param key Nombre del objeto.
     * @return El objeto removido o nulo.
     */
    public static Object removeRequestMapObject(String key) {
        return getRequestMap().remove(key);
    }

    /**
     * Agrega un objeto al mapa del request (de FacesContext).
     *
     * @param key Nombre del objeto a agregar.
     * @param obj El objeto a agregar
     */
    public static void putRequestMapObject(String key, Object obj) {
        getRequestMap().put(key, obj);
    }

    private static Map<String, Object> getRequestMap() {
        return getExtCont().getRequestMap();
    }

    /**
     * Recupera el parameter.
     *
     * @param name el name
     * @return El parameter
     */
    public static String getParameter(String name) {
        Map<String, String> requestMap = getExtCont().getRequestParameterMap();
        return requestMap.get(name);
    }

    /**
     *
     * @return
     */
    public static ServletContext getServletContext() {
        return (ServletContext) getExtCont().getContext();
    }

    /**
     * Hace un recuento del número de mensajes agregados al contexto actual de
     * faces.
     *
     * @return Número de mensajes agregados al contexto actual de faces
     */
    public static int countMessages() {
        FacesContext facesContext = getFacesContext();
        if (facesContext == null) {
            LOGGER.error("No fue posible recuperar el número de mensajes.");
            return 0;
        } else {
            return getFacesContext().getMessageList().size();
        }
    }

    /**
     * Formatear fecha.
     *
     * @param date el date
     * @param formato el formato
     * @return El string
     */
    public static String formatearFecha(Date date, String formato) {
        String fecha;
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
        if (date == null) {
            fecha = "\\N";
        } else {
            fecha = formateador.format(date);
        }
        return fecha;
    }

    /**
     * Recupera el date.
     *
     * @param year el year
     * @param month el month
     * @param day el day
     * @return El date
     */
    public static long getDate(int year, int month, int day) {
        return getDate(year, month, day, 1, 1, 1);
    }

    /**
     * Recupera la fecha en long.
     *
     * @param year el year
     * @param month el month
     * @param day el day
     * @param hour el hour
     * @param minute el minute
     * @param second el second
     * @return El date
     */
    public static long getDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, second);
        return cal.getTimeInMillis();
    }

    /* *********************************************** */
    /**
     * Metodo que permite enviar una peticion a una url. Este metodo se utiliza
     * para poder enviar las peticiones de Login y Logout a Spring Securty 3.
     *
     * @param url Corresponde a la url relativa a donde se enviara la peticion
     * @throws ServletException Excepcion para el servelet
     * @throws IOException Exception para los datos de entrada y salida
     */
    public static void redirect(String url) throws ServletException, IOException {
        ServletRequest servletRequest = (ServletRequest) getRequest();
        ServletResponse servletResponse = (ServletResponse) getResponse();

        RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(url);

        dispatcher.forward(servletRequest, servletResponse);
        getFacesContext().responseComplete();
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static InputStream convertStringToInputStream(String in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(in.getBytes(FileUtils.DEFAULT_ENCONDING));
        return new ByteArrayInputStream(baos.toByteArray());
    }

    /**
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.defaultCharset().decode(bb).toString();
        } finally {
            stream.close();
        }
    }

    /**
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFileAsString(String filePath) throws IOException {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(FileUtils.getInputStream(filePath));
        char[] buf = new char[1024];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }

    /**
     * Por medio del FacesContext y el request intenta recuperar la URL del
     * servidor. Por tal motivo, sólo funciona dentro del contexto de JSF.
     *
     * @return
     */
    public static String getFullServerUrl() {
        ExternalContext externalContext = getExtCont();

        if (externalContext == null) {
            LOGGER.error("No se puede recuperar el request desde el FacesContext");
            return null;
        } else {
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            String originalURL = request.getRequestURL().toString();
            String[] parts = originalURL.split("/");

            if (parts.length < 3) {
                LOGGER.error("No se puede contruir la ruta desde el URL reportado: {}", originalURL);
                return null;
            } else {
                StringBuilder stbd = new StringBuilder();
                stbd.append(parts[0]).append("//").append(parts[2]);
                
                String contextPath = request.getContextPath();
                
                if (!contextPath.isEmpty()) {
                    stbd.append(contextPath);
                }
                
                return stbd.toString();
            }
        }
    }
}
