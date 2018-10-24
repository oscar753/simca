package mx.org.ift.simca.arq.core.support;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

/**
 * Clase utilitaria que sirva para generar archivos pdf a partir del reporte (de jasper reports) dado.
*/
public class ReportSupport {
    
    /**
     * Al ser una clase utilitaria, el constructor es privado.
     */
    private ReportSupport() {
        
    }
    
    public static File getAsPdfFile(File jasperFile, Connection connection) throws JRException, IOException {
        return getAsPdfFile(jasperFile, new HashMap(), connection);
    }
    
    public static File getAsPdfFile(File jasperFile, Map parameters, Connection connection) throws JRException, IOException {
        JasperReport jasperReport = getJasperReport(jasperFile, parameters);
        return getAsPdfFile(jasperReport, parameters, connection);
    }
        
    protected static File getAsPdfFile(JasperReport jasperReport, Map parameters, Connection connection) throws JRException, IOException {
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);
        return getPdfFile(print);
    }
    
    public static File getAsPdfFile(File jasperFile, JRDataSource dataSource) throws JRException, IOException {
        return getAsPdfFile(jasperFile, new HashMap(), dataSource);
    }
    
    public static File getAsPdfFile(File jasperFile, Map parameters, JRDataSource dataSource) throws JRException, IOException {
        JasperReport jasperReport = getJasperReport(jasperFile, parameters);
        return getAsPdfFile(jasperReport, parameters, dataSource);
    }
    
    protected static File getAsPdfFile(JasperReport jasperReport, Map parameters, JRDataSource dataSource) throws JRException, IOException {
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return getPdfFile(print);
    }
    
    public static byte[] getAsPdf(File jasperFile, Connection connection) throws JRException, IOException {
        return getAsPdf(jasperFile, new HashMap(), connection);
    }
    
    public static byte[] getAsPdf(File jasperFile, Map parameters, Connection connection) throws JRException, IOException {
        JasperReport jasperReport = getJasperReport(jasperFile, parameters);
        return getAsPdf(jasperReport, parameters, connection);
    }
    
    protected static byte[] getAsPdf(JasperReport jasperReport, Map parameters, Connection connection) throws JRException, IOException {
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);
        return JasperExportManager.exportReportToPdf(print);
    }
    
    public static byte[] getAsPdf(File jasperFile, JRDataSource dataSource) throws JRException, IOException {
        return getAsPdf(jasperFile, new HashMap(), dataSource);
    }
    
    public static byte[] getAsPdf(File jasperFile, Map parameters, JRDataSource dataSource) throws JRException, IOException {
        JasperReport jasperReport = getJasperReport(jasperFile, parameters);
        return getAsPdf(jasperReport, parameters, dataSource);
    }
    
    protected static byte[] getAsPdf(JasperReport jasperReport, Map parameters, JRDataSource dataSource) throws JRException, IOException {
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(print);
    }
    
    private static File getPdfFile(JasperPrint print) throws IOException, JRException {
        File tmp = File.createTempFile("reporte", ".pdf");
        JasperExportManager.exportReportToPdfFile(print, tmp.getAbsolutePath());
        return tmp;
    }
    
    private static JasperReport getJasperReport(File jasperFile, Map parameters) throws JRException {
        if (parameters != null) {
            SimpleFileResolver fileResolver = new SimpleFileResolver(jasperFile.getParentFile());
            //parameters.put("SUBREPORT_DIR", jasperFile.getParentFile());
            // TODO: Cambiar por una forma reciente
            parameters.put(JRParameter.REPORT_FILE_RESOLVER, fileResolver);
        }
        
        return (JasperReport) JRLoader.loadObject(jasperFile);
    }
    
}
