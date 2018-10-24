package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad preregistro
  */
public class Preregistro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer preregistroPk;
    private String correo;
    private String idSeguridad;
    private java.util.Date ventanaParaIdSeguridad;

    /**
     * Constructor default de la clase.
     */
    public Preregistro() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     * @param preregistroPk Llave primaria.
     */
    public Preregistro(Integer preregistroPk) {
        this.preregistroPk = preregistroPk;
    }

    /**
     * Llave primaria.
     * @return Llave primaria.
     */
    public Integer getPreregistroPk() {
        return preregistroPk;
    }

    /**
     * Llave primaria.
     * @param preregistroPk Llave primaria.
     */
    public void setPreregistroPk(Integer preregistroPk) {
        this.preregistroPk = preregistroPk;
    }

    /**
     * Correo electrónico.
     * @return Correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Correo electrónico.
     * @param correo Correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Identificador de texto único.
     * @return Identificador de texto único.
     */
    public String getIdSeguridad() {
        return idSeguridad;
    }

    /**
     * Identificador de texto único.
     * @param idSeguridad Identificador de texto único.
     */
    public void setIdSeguridad(String idSeguridad) {
        this.idSeguridad = idSeguridad;
    }

    /**
     * Tiempo en que es válido el idSeguridad.
     * @return Tiempo en que es válido el idSeguridad.
     */
    public java.util.Date getVentanaParaIdSeguridad() {
        return ventanaParaIdSeguridad;
    }

    /**
     * Tiempo en que es válido el idSeguridad.
     * @param ventanaParaIdSeguridad Tiempo en que es válido el idSeguridad.
     */
    public void setVentanaParaIdSeguridad(java.util.Date ventanaParaIdSeguridad) {
        this.ventanaParaIdSeguridad = ventanaParaIdSeguridad;
    }
}
