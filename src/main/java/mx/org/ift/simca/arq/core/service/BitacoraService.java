package mx.org.ift.simca.arq.core.service;

import java.util.Date;
import mx.org.ift.simca.arq.core.enums.TipoBitacoraEnum;

public interface BitacoraService {

    /**
     * Escribe en la bitácora del sistema un echo relevante
     *
     * @param bitacoraEnum El tipo de bitácora que se está guardando
     * @param ip Es la ip del usuario
     * @param date Es la fecha en que ocurrió el evento relevante
     * @param username Es el nombre del usuario que genero un evento relevante
     * @param extraInfo Es información que es relevante para el evento.
     */
    void write(TipoBitacoraEnum bitacoraEnum, String ip, Date date, String username, String extraInfo);
}
