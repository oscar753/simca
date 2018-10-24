package mx.org.ift.simca.arq.core.enums;

/**
 * Enum para mapear los tipos de bitacora existentes.
 * 
 */
public enum TipoBitacoraEnum {
    
    GENERAL(1),
    LOGIN_NOT_FOUND(2),
    EMAIL_NOT_FOUND(3),
    UNAUTHORIZED_ACCESS(4),
    INVALID_SID(5),
    USER_BLOCKED(6),
    MAIL_SENT(7),
    WRONG_PASSWORD(8),
    REGISTRATION_APPLIED(9),
    REGISTRATION_COMPLETED(10);
    
    private int id;

    private TipoBitacoraEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
}
