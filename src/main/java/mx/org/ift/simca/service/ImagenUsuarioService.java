package mx.org.ift.simca.service;

import mx.org.ift.simca.model.Imagen;
import java.io.Serializable;
import java.util.List;

/**
 * Servicio utilizado para administrar las imágenes de un usuario.
*/
public interface ImagenUsuarioService extends Serializable {

    /**
     * Recupera la lista de imágenes que el usuario tiene asignadas.
     *
     * @return Lista con las imágenes del usuario.
     */
    List<Imagen> getAllImagesByUserLogged();

    /**
     * Borra una imagen por medio de su ID.
     *
     * @param imagePk ID de la imagen.
     * @exception En caso de un error de borrado, se lanza una excepción.
     */
    void deleteImage(int imagePk) throws Exception;

    /**
     * Agrega y asigna una imagen al usuario dado.
     *
     * @param imagen Información de la imagen.
     * @exception En caso de un error de inserción, se lanza una excepción.
     */
    void addImageForUserLogger(Imagen imagen) throws Exception;
}
