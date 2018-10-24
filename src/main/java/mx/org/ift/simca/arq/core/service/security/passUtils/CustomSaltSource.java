package mx.org.ift.simca.arq.core.service.security.passUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Representa un salero personalizado
 *
*/
@Service("saltSource")
public class CustomSaltSource implements SaltSource {

    /**
     * Obtiene el valor customSalt de archivo de configuración y lo asigna
     */
    @Value("${customSalt}")
    private String customSalt;

    /**
     *
     * @param user
     * @return
     */
    @Override
    public Object getSalt(UserDetails user) {
        return user.getUsername() + customSalt;
    }
}
