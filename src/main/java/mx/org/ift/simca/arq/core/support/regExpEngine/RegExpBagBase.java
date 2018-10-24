package mx.org.ift.simca.arq.core.support.regExpEngine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase RegExpBagBase.
 *
 */
public class RegExpBagBase implements Serializable {

    /**
     * Representa el valor inicial para la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa una lista de beans de expresiones regulares
     */
    private List<RegExpBean> bag = new ArrayList<RegExpBean>();

    /**
     * Agrega una expressión a la mochila de expresiones
     *
     * @param name Nombre de la expressión que se va agregar
     * @param regExpString Expression regular asociada
     * @param errorMessage Mensaje de error asociado a la expresión regular
     */
    protected void add(String name, String regExpString, String errorMessage) {
        RegExpBean reb = new RegExpBean(name, regExpString, errorMessage);
        bag.add(reb);
    }

    /**
     *
     * @return
     */
    public List<RegExpBean> getBag() {
        return this.bag;
    }
}
