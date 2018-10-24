package mx.org.ift.simca.service;

import mx.org.ift.simca.model.Imagen;
import mx.org.ift.simca.model.ImagenUsuario;
import mx.org.ift.simca.persistence.ImagenMapper;
import mx.org.ift.simca.persistence.ImagenUsuarioMapper;
import java.util.ArrayList;
import java.util.List;
import mx.org.ift.simca.arq.core.service.security.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagenUsuarioServiceImpl implements ImagenUsuarioService {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImagenUsuarioServiceImpl.class);
    
    @Autowired
    private AuthService authService;
    @Autowired
    private ImagenMapper imagenMapper;
    @Autowired
    private ImagenUsuarioMapper imagenUsuarioMapper;
    
    @Override
    public List<Imagen> getAllImagesByUserLogged() {
        int usuarioPk = authService.getCurrentUserId();
        
        if (usuarioPk > 0) {
            List<ImagenUsuario> imagenesUsuario = imagenUsuarioMapper.getAllByUsuarioPk(usuarioPk);
            
            List<Imagen> res = new ArrayList<Imagen>();
            
            for (ImagenUsuario imagenUsuario : imagenesUsuario) {
                res.add(imagenMapper.getById(new Imagen(imagenUsuario.getImagenIdImagen())));
            }
            
            return res;
        } else {
            LOGGER.debug("No hay un usuario loggeado");
            return new ArrayList<Imagen>(0);
        }
    }

    @Override
    @Transactional
    public void deleteImage(int imagePk) {
        imagenUsuarioMapper.deleteAllFromImagenPk(imagePk);
        imagenMapper.delete(new Imagen(imagePk));
    }

    @Override
    @Transactional
    public void addImageForUserLogger(Imagen imagen) {
        int usuarioPk = authService.getCurrentUserId();
        
        if (usuarioPk <= 0) {
            LOGGER.debug("No existe un usuario logueado, no se insertará la imágen");
        } else if (imagen.getImagenPk() != null) {
            LOGGER.debug("La imagen ya tenía un id: {}, no se hará ninguna acción", imagen.getImagenPk());
        } else {
            imagenMapper.insert(imagen);
            
            ImagenUsuario imagenUsuario = new ImagenUsuario(usuarioPk, imagen.getImagenPk());
            imagenUsuarioMapper.insert(imagenUsuario);
        }
    }
    
}
