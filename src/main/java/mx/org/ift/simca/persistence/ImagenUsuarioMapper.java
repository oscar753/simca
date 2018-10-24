
package mx.org.ift.simca.persistence;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.ImagenUsuario;
import java.util.List;

/**
 * <p>Descripción:</p>
 * Interface 'Mapper' MyBatis asociado a la entidad imagen_usuario 
*/
public interface ImagenUsuarioMapper extends IMapper<ImagenUsuario> {
    
    /**
     * Recupera todas las ImagenUsuario que tengan el usuarioPk dado.
     * @param usuarioPk ID del usuario.
     * @return Lista de imágenes.
     */
    List<ImagenUsuario> getAllByUsuarioPk(int usuarioPk);
    
    /**
     * Elimina todas las entradas que tengan el id de la imagen dada.
     * @param imagenPk ID de la imagen.
     */
    void deleteAllFromImagenPk(int imagenPk);
}
