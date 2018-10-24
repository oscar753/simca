package mx.org.ift.simca.arq.core.service;

import java.util.Date;
import mx.org.ift.simca.arq.core.enums.TipoBitacoraEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import mx.org.ift.simca.arq.core.model.Bitacora;
import mx.org.ift.simca.arq.core.model.DateWrapper;
import mx.org.ift.simca.arq.core.persistence.BitacoraMapper;

public class BitacoraServiceImpl implements BitacoraService {

    /**
     * Inyección del bean de spring llamado BitacoraService para manejo de
     * operaciones de los servicios sobre la entidad 'Bitacora'
     */
    @Autowired
    private transient BitacoraMapper bitacoraMapper;
    /**
     * Representa el número de días en la bitácora
     */
    @Value("${numDaysBitacoraFlush}")
    private int numDaysBitacoraFlush;

    @Override
    public void write(TipoBitacoraEnum bitacoraEnum, String ip, Date date, String username, String extraInfo) {
        DateWrapper dw = new DateWrapper(numDaysBitacoraFlush);
        Bitacora bitacora = new Bitacora(
                null,
                bitacoraEnum.getId(),
                ip,
                date,
                username,
                extraInfo);
        bitacoraMapper.insert(bitacora);
        bitacoraMapper.clean(dw);
    }
}
