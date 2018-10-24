package mx.org.ift.simca.arq.core.service.report;

import java.io.File;
import java.util.Map;
import java.util.List;

/**
 * Ejemplo de uso de JasperReport.
 * 
*/
public interface ReporteService {

    /**
     * Genera un reporte a partir del archivo jasper dado.
     * 
     * @param reportePath Ruta al archivo 
     * @param properties
     * @return 
     */
    File getReportePdf(String reportePath, Map<String, Object> properties);
    
    byte[] getReporte(String reportePath, Map<String, Object> properties);
    
    File getReportePdf(String reportePath, Map<String, Object> properties, List<Object> databeans);
    
}
