package mx.org.ift.simca.arq.core.support;

import mx.org.ift.simca.arq.core.enums.TipoConfiguracionEnum;

public class TipoConfiguracionNotValidException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private TipoConfiguracionEnum tipoConfiguracionEnum;

    public TipoConfiguracionNotValidException(String message) {
        super(message);
    }
    
    public TipoConfiguracionNotValidException(String message, int tipoConfiguracionPk) {
        super(message);
        tipoConfiguracionEnum = TipoConfiguracionEnum.find(tipoConfiguracionPk);
    }

    public TipoConfiguracionEnum getTipoConfiguracionEnum() {
        return tipoConfiguracionEnum;
    }
    
}
