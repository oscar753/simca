package mx.org.ift.simca.arq.core.model;

import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad configuracion 
 */
public class Configuracion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer configuracionPk;
    private int tipoConfiguracionFk;
    private String llave;
    private String valorStr;
    private Integer valorInt;
    private Float valorFlt;
    private Boolean valorBol;

    /**
     * Constructor default de la clase.
     */
    public Configuracion() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     */
    public Configuracion(Integer configuracionPk) {
        this.configuracionPk = configuracionPk;
    }

    public Integer getConfiguracionPk() {
        return configuracionPk;
    }

    public void setConfiguracionPk(Integer configuracionPk) {
        this.configuracionPk = configuracionPk;
    }
    
    public int getTipoConfiguracionFk() {
        return tipoConfiguracionFk;
    }

    public void setTipoConfiguracionFk(int tipoConfiguracionFk) {
        this.tipoConfiguracionFk = tipoConfiguracionFk;
    }
    
    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }
    
    public String getValorStr() {
        return valorStr;
    }

    public void setValorStr(String valorStr) {
        this.valorStr = valorStr;
    }
    
    public Integer getValorInt() {
        return valorInt;
    }

    public void setValorInt(Integer valorInt) {
        this.valorInt = valorInt;
    }
    
    public Float getValorFlt() {
        return valorFlt;
    }

    public void setValorFlt(Float valorFlt) {
        this.valorFlt = valorFlt;
    }
    
    public Boolean getValorBol() {
        return valorBol;
    }

    public void setValorBol(Boolean valorBol) {
        this.valorBol = valorBol;
    }
    

}
