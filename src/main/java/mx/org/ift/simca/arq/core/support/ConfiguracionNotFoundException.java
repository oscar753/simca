package mx.org.ift.simca.arq.core.support;

public class ConfiguracionNotFoundException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    private String keyNotFound;
    
    public ConfiguracionNotFoundException(String message) {
        super(message);
    }
    
    public ConfiguracionNotFoundException(String message, String keyNotFound) {
        super(message);
        this.keyNotFound = keyNotFound;
    }

    public String getKeyNotFound() {
        return keyNotFound;
    }
    
}
