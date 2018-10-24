package mx.org.ift.simca.exposition;

import mx.org.ift.simca.service.PruebaReporteService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javax.faces.event.ActionEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class PruebaReporteMB implements Serializable {

    @Autowired
    private PruebaReporteService pruebaReporteService;
    
    private StreamedContent streamedContent;

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void generaReporte(ActionEvent event) {
        File tmp = pruebaReporteService.generaReporte();

        if (tmp == null) {
            streamedContent = null;
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(tmp);

                streamedContent = new DefaultStreamedContent(fileInputStream, "application/pdf", "reporte.pdf");
            } catch (FileNotFoundException ex) {
                LoggerFactory.getLogger(this.getClass()).error("Ocurrió un error al generar al archivo", ex);
                streamedContent = null;
            } finally {
                tmp.delete();
            }
        }
    }
    
}
