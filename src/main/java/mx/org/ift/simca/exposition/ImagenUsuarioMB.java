package mx.org.ift.simca.exposition;

import mx.org.ift.simca.model.Imagen;
import mx.org.ift.simca.service.ImagenUsuarioService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;
import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("view")
public class ImagenUsuarioMB implements Serializable {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ImagenUsuarioMB.class);
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ImagenUsuarioService imagenUsuarioService;
    
    private List<Imagen> imagenes;
    private Imagen imagenSelected;
    
    @PostConstruct
    public void init() {
        fillData();
    }
    
    private void fillData() {
        imagenes = imagenUsuarioService.getAllImagesByUserLogged();
        imagenSelected = null;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public Imagen getImagenSelected() {
        return imagenSelected;
    }

    public void setImagenSelected(Imagen imagenSelected) {
        this.imagenSelected = imagenSelected;
    }

    public void handleFileUpload(FileUploadEvent event) {
        boolean exito;
        
        try {
            UploadedFile uploadedFile = event.getFile();

            Imagen imagen = new Imagen();
            imagen.setContenido(uploadedFile.getContents());
            imagen.setMimeType(uploadedFile.getContentType());
            imagen.setNombre(uploadedFile.getFileName());
            
            imagenUsuarioService.addImageForUserLogger(imagen);
            
            fillData();
            
            exito = true;
        } catch (Exception ex) {
            LOGGER.error("Error al subir la imagen", ex);
            ContextUtils.addErrorMsg("Ocurrió un error al subir la imagen");
            exito = false;
        }
        
        PrimeFacesRequestContext.addCallBackParam(exito);
    }
    
}
