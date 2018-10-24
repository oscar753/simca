package mx.org.ift.simca.arq.core.service.report;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import mx.org.ift.simca.arq.core.support.ReportSupport;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("reporteService")
public class ReporteServiceImpl implements ReporteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReporteService.class);
    
    @Resource(name="dataSource")
    private DataSource dataSource;
    
    @Override
    public File getReportePdf(String reportePath, Map<String, Object> properties) {
        File res;
        Connection connection = null;
        
        try {
            connection = dataSource.getConnection();
            
            File jasperFile = new File(reportePath);
            
            if(properties == null) {
                res = ReportSupport.getAsPdfFile(jasperFile, connection);
            } else {
                res = ReportSupport.getAsPdfFile(jasperFile, properties, connection);
            }
            
        } catch (IOException ex) {
            LOGGER.error("Error al generar el reporte", ex);
            res = null;
        } catch (JRException ex) {
            LOGGER.error("Error al generar el reporte", ex);
            res = null;
        } catch (SQLException ex) {
            LOGGER.error("Error al generar el reporte", ex);
            res = null;
        } finally {
            close(connection);
        }
        
        return res;
    }
    
    private void close(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            LOGGER.error("Error al cerrar la conexión", ex);
        }
    }

    @Override
    public byte[] getReporte(String reportePath, Map<String, Object> properties) {
        //Sería parecido a la otra implementación
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public File getReportePdf(String reportePath, Map<String, Object> properties, List<Object> databeans) {
        File res;
        
        try {
            File jasperFile = new File(reportePath);
            res = ReportSupport.getAsPdfFile(jasperFile, properties, new JRBeanCollectionDataSource(databeans));
        } catch (IOException ex) {
            LOGGER.error("Error al generar el reporte", ex);
            res = null;
        } catch (JRException ex) {
            LOGGER.error("Error al generar el reporte", ex);
            res = null;
        }
        
        return res;
    }

}
