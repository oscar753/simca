/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.service;

import java.io.File;
import java.io.Serializable;

public interface PruebaReporteService extends Serializable {
    
    /**
     * Genera un reporte de prueba
     * @return 
     */
    File generaReporte();
    
}
