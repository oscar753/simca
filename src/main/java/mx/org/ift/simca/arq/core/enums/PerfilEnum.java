 package mx.org.ift.simca.arq.core.enums;

/**
 * Enum para mapear los dos perfiles (roles) básicos.
 *
  * @version 1.0
 */
public enum PerfilEnum {
    
    /**
     *
     */
    ADMIN(1, "PERFIL_ADMIN"),
    /**
     *
     */
    USER(2, "PERFIL_USER");
    
    private int id;
    private String codigo;

    private PerfilEnum(int id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    /**
     * Recupera el ID del Perfil.
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Recupera el código del Perfil.
     * @return 
     */
    public String getCodigo() {
        return codigo;
    }
    
}
