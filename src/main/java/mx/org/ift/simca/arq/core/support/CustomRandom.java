package mx.org.ift.simca.arq.core.support;

import java.io.Serializable;
import java.util.Random;

public class CustomRandom implements Serializable {

    /**
     * Representa el valor inicial de la version del serial
     */
    private static final long serialVersionUID = 1L;
    /**
     * Representa la instancia de un valor aleatorio
     */
    private static final Random RND = new Random(System.currentTimeMillis());

    /**
     * Por ser una clase utilitaria, el constructor no es público.
     */
    protected CustomRandom() {
    }
    
    /**
     *
     * @return
     */
    public static int nextInt() {
        return RND.nextInt();
    }

    /**
     *
     * @param n
     * @return
     */
    public static int nextInt(int n) {
        return RND.nextInt(n);
    }

    /**
     *
     * @return
     */
    public static long nextLong() {
        return RND.nextLong();
    }

    /**
     *
     * @return
     */
    public static double mathRandom() {
        return Math.random();
    }
}
