package mx.org.ift.simca.model;

import java.io.Serializable;

/**
 * <p>Descripción:</p>
 * POJO asociado a la entidad imagen_usuario 
 *
*/
public class ImagenUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer usuarioUsuarioPk;
    private Integer imagenIdImagen;

    /**
     * Constructor default de la clase.
     */
    public ImagenUsuario() {
    }

    /**
     * Constructor con los atributos que conforman la llave de la clase.
     */
    public ImagenUsuario(Integer usuarioUsuarioPk, Integer imagenIdImagen) {
        this.usuarioUsuarioPk = usuarioUsuarioPk;
        this.imagenIdImagen = imagenIdImagen;
    }

    public Integer getUsuarioUsuarioPk() {
        return usuarioUsuarioPk;
    }

    public void setUsuarioUsuarioPk(Integer usuarioUsuarioPk) {
        this.usuarioUsuarioPk = usuarioUsuarioPk;
    }
    
    public Integer getImagenIdImagen() {
        return imagenIdImagen;
    }

    public void setImagenIdImagen(Integer imagenIdImagen) {
        this.imagenIdImagen = imagenIdImagen;
    }
    

}
