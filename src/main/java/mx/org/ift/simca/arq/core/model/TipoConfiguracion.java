package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad tipo_configuracion 
 */
public class TipoConfiguracion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer tipoConfiguracionPk;
    private String nombre;
    private String descripcion;

    /**
     * Constructor default de la clase.
     */
    public TipoConfiguracion() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     */
    public TipoConfiguracion(Integer tipoConfiguracionPk) {
        this.tipoConfiguracionPk = tipoConfiguracionPk;
    }

    public Integer getTipoConfiguracionPk() {
        return tipoConfiguracionPk;
    }

    public void setTipoConfiguracionPk(Integer tipoConfiguracionPk) {
        this.tipoConfiguracionPk = tipoConfiguracionPk;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

}
