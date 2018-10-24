package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad usuario_detalle
 */
public class UsuarioDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer usuarioFk;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String telefonos;
    private String direccion;
    private boolean mandaCorreoPromo;

    /**
     * Constructor default de la clase.
     */
    public UsuarioDetalle() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     * @param usuarioFk Llave primaria.
     */
    public UsuarioDetalle(Integer usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    /**
     * Llave primaria.
     * @return Llave primaria.
     */
    public Integer getUsuarioFk() {
        return usuarioFk;
    }

    /**
     * Llave primaria.
     * @param usuarioFk Llave primaria.
     */
    public void setUsuarioFk(Integer usuarioFk) {
        this.usuarioFk = usuarioFk;
    }

    /**
     * Nombre de pila.
     * @return Nombre de pila.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Nombre de pila.
     * @param nombre Nombre de pila.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Apellido paterno.
     * @return Apellido paterno.
     */
    public String getApPaterno() {
        return apPaterno;
    }

    /**
     * Apellido paterno.
     * @param apPaterno Apellido paterno.
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    /**
     * Apellido materno.
     * @return Apellido materno.
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * Apellido materno.
     * @param apMaterno Apellido materno.
     */
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    /**
     * Números telefónicos.
     * @return Números telefónicos.
     */
    public String getTelefonos() {
        return telefonos;
    }

    /**
     * Números telefónicos.
     * @param telefonos Números telefónicos.
     */
    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * Dirección física.
     * @return Dirección física.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Dirección física.
     * @param direccion Dirección física.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Enviar correo electrónico de promoción.
     * @return Enviar correo electrónico de promoción.
     */
    public boolean isMandaCorreoPromo() {
        return mandaCorreoPromo;
    }

    /**
     * Enviar correo electrónico de promoción.
     * @param mandaCorreoPromo Enviar correo electrónico de promoción.
     */
    public void setMandaCorreoPromo(boolean mandaCorreoPromo) {
        this.mandaCorreoPromo = mandaCorreoPromo;
    }
}
