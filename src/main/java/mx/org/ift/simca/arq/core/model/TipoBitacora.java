package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad tipo_bitacora
 */
public class TipoBitacora implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer tipoBitacoraPk;
    private String codigo;
    private String descripcion;

    /**
     * Constructor default de la clase.
     */
    public TipoBitacora() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     */
    public TipoBitacora(Integer tipoBitacoraPk) {
        this.tipoBitacoraPk = tipoBitacoraPk;
    }

    public Integer getTipoBitacoraPk() {
        return tipoBitacoraPk;
    }

    public void setTipoBitacoraPk(Integer tipoBitacoraPk) {
        this.tipoBitacoraPk = tipoBitacoraPk;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
