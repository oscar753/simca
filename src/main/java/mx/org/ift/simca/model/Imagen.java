package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad imagen 
 *
*/
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer imagenPk;
    private String mimeType;
    private String nombre;
    private byte[] contenido;

    /**
     * Constructor default de la clase.
     */
    public Imagen() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     */
    public Imagen(Integer imagenPk) {
        this.imagenPk = imagenPk;
    }

    public Integer getImagenPk() {
        return imagenPk;
    }

    public void setImagenPk(Integer imagenPk) {
        this.imagenPk = imagenPk;
    }
    
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }
    

}
