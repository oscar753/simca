package mx.org.ift.simca.arq.core.service;

import mx.org.ift.simca.arq.core.enums.TipoConfiguracionEnum;
import mx.org.ift.simca.arq.core.model.Configuracion;
import mx.org.ift.simca.arq.core.persistence.ConfiguracionMapper;
import mx.org.ift.simca.arq.core.support.ConfiguracionNotFoundException;
import mx.org.ift.simca.arq.core.support.TipoConfiguracionNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("configuracionService")
public class ConfiguracionServiceImpl implements ConfiguracionService {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private ConfiguracionMapper configuracionMapper;

    @Override
    public void save(Configuracion configuracion) {
        if(configuracion.getConfiguracionPk() == null) {
            configuracionMapper.insert(configuracion);
        } else {
            Configuracion tmp = configuracionMapper.getById(configuracion);
            if (tmp == null) {
                configuracionMapper.insert(configuracion);
            } else {
                configuracionMapper.update(configuracion);
            }
        }
    }

    @Override
    public void delete(int configuracionPk) {
        configuracionMapper.delete(new Configuracion(configuracionPk));
    }
    
    @Override
    public String getStrConfig(String key) throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException {
        Configuracion configuracion = findConfiguracion(key, TipoConfiguracionEnum.STRING);
        return configuracion.getValorStr();
    }

    @Override
    public int getIntConfig(String key) throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException {
        Configuracion configuracion = findConfiguracion(key, TipoConfiguracionEnum.INTEGER);
        return configuracion.getValorInt();
    }

    @Override
    public float getFltConfig(String key) throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException {
        Configuracion configuracion = findConfiguracion(key, TipoConfiguracionEnum.FLOAT);
        return configuracion.getValorFlt();
    }

    @Override
    public boolean getBolConfig(String key) throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException {
        Configuracion configuracion = findConfiguracion(key, TipoConfiguracionEnum.BOOLEAN);
        return configuracion.getValorBol();
    }

    private Configuracion findConfiguracion(String key, TipoConfiguracionEnum tipoConfiguracionEnum) 
            throws ConfiguracionNotFoundException, TipoConfiguracionNotValidException {
        Configuracion configuracion = configuracionMapper.findByKey(key);

        if (configuracion == null) {
            throw new ConfiguracionNotFoundException("No se encontró la configuracion con la llave " + key, key);
        } else {
            if (configuracion.getTipoConfiguracionFk() == tipoConfiguracionEnum.getTipoConfiguacionPk()) {
                return configuracion;
            } else {
                throw new TipoConfiguracionNotValidException("La configuracion para la llave"
                        + key + ", no era de tipo solicitado", configuracion.getTipoConfiguracionFk());
            }
        }
    }

}
