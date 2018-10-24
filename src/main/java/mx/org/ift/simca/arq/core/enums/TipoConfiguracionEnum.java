package mx.org.ift.simca.arq.core.enums;

/**
 * Enum para mapear los tipos de configuraciones existentes.
 * 
 */
public enum TipoConfiguracionEnum {

    STRING(1),
    INTEGER(2),
    FLOAT(3),
    BOOLEAN(4);
    
    private int tipoConfiguracionPk;

    private TipoConfiguracionEnum(int tipoConfiguracionPk) {
        this.tipoConfiguracionPk = tipoConfiguracionPk;
    }

    public int getTipoConfiguacionPk() {
        return tipoConfiguracionPk;
    }

    public static TipoConfiguracionEnum find(int tipoConfiguracionPk) {
        switch (tipoConfiguracionPk) {
            case 1:
                return STRING;
            case 2:
                return INTEGER;
            case 3:
                return FLOAT;
            case 4:
                return BOOLEAN;
            default:
                return null;
        }
    }
}
