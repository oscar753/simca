package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad bitacora
 *
  */
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer bitacoraPk;
    private int tipoBitacoraFk;
    private String ip;
    private Date eventDate;
    private String username;
    private String extraInfo;

    /**
     * Constructor default de la clase.
     */
    public Bitacora() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     *
     * @param bitacoraPk Llave primaria.
     */
    public Bitacora(Integer bitacoraPk) {
        this.bitacoraPk = bitacoraPk;
    }

    /**
     * Constructor con todos los atributos de la clase.
     *
     * @param bitacoraPk Llave primaria.
     * @param tipoBitacoraFk ID del tipo de entrada en la bitácora.
     * @param ip IP desde donde se origina la operación.
     * @param eventDate Fecha de la operación.
     * @param username Login del usuario (en el caso no sea una operación
     * anónima).
     * @param extraInfo Información extra.
     */
    public Bitacora(Integer bitacoraPk, int tipoBitacoraFk, String ip, Date eventDate, String username, String extraInfo) {
        this.bitacoraPk = bitacoraPk;
        this.tipoBitacoraFk = tipoBitacoraFk;
        this.ip = ip;
        this.eventDate = eventDate;
        this.username = username;
        this.extraInfo = extraInfo;
    }

    /**
     * Llave primaria.
     *
     * @return Llave primaria.
     */
    public Integer getBitacoraPk() {
        return bitacoraPk;
    }

    /**
     * Llave primaria.
     *
     * @param bitacoraPk Llave primaria.
     */
    public void setBitacoraPk(Integer bitacoraPk) {
        this.bitacoraPk = bitacoraPk;
    }

    /**
     * ID del tipo de entrada en la bitácora.
     *
     * @return ID del tipo de entrada en la bitácora.
     */
    public int getTipoBitacoraFk() {
        return tipoBitacoraFk;
    }

    /**
     * ID del tipo de entrada en la bitácora.
     *
     * @param tipoBitacoraFk ID del tipo de entrada en la bitácora.
     */
    public void setTipoBitacoraFk(int tipoBitacoraFk) {
        this.tipoBitacoraFk = tipoBitacoraFk;
    }

    /**
     * IP desde donde se origina la operación.
     *
     * @return IP desde donde se origina la operación.
     */
    public String getIp() {
        return ip;
    }

    /**
     * IP desde donde se origina la operación.
     *
     * @param ip IP desde donde se origina la operación.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Fecha de la operación.
     *
     * @return Fecha de la operación.
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * Fecha de la operación.
     *
     * @param eventDate Fecha de la operación.
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Login del usuario (en el caso no sea una operación anónima).
     *
     * @return Login del usuario (en el caso no sea una operación anónima).
     */
    public String getUsername() {
        return username;
    }

    /**
     * Login del usuario (en el caso no sea una operación anónima).
     *
     * @param username Login del usuario (en el caso no sea una operación
     * anónima).
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Información extra.
     *
     * @return Información extra.
     */
    public String getExtraInfo() {
        return extraInfo;
    }

    /**
     * Información extra.
     *
     * @param extraInfo Información extra.
     */
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
