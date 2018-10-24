package mx.org.ift.simca.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import mx.org.ift.simca.arq.core.model.UsuarioInfo;
import mx.org.ift.simca.arq.core.service.report.ReporteService;
import mx.org.ift.simca.arq.core.service.security.adminUser.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PruebaReporteServiceImpl implements PruebaReporteService {
    
    private static final String FILE = "/jasper/prueba.jasper";

    private Random random = new Random();
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ReporteService reporteService;
    
    @Override
    public File generaReporte() {
        List<UsuarioInfo> usuariosInfo = usuarioService.getUsuariosInfo();
        
        for (UsuarioInfo usuarioInfo : usuariosInfo) {
            usuarioInfo.getUsuario().setContadorIntentosFallidos(random.nextInt(10));
        }
        
        return reporteService.getReportePdf(PruebaReporteServiceImpl.class.getResource(FILE).getFile(), new HashMap<String, Object>(), new ArrayList<Object>(usuariosInfo));
    }
    
}
