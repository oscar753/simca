/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Usuario
 */
@Stateless
public class UsuarioLoggeadoMB implements Serializable{
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(UsuarioLoggeadoMB.class.getName());

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public static Integer idUsuarioLoggeado;
    
    public UsuarioLoggeadoMB() {
        idUsuarioLoggeado=0;
    }

    /**
     * @return the idUsuarioLoggeado
     */
    public static Integer getIdUsuarioLoggeado() {
        return idUsuarioLoggeado;
    }

    /**
     * @param aIdUsuarioLoggeado the idUsuarioLoggeado to set
     */
    public static void setIdUsuarioLoggeado(Integer aIdUsuarioLoggeado) {
        idUsuarioLoggeado = aIdUsuarioLoggeado;
    }
}
