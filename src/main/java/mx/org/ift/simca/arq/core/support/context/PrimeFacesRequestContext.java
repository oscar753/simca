package mx.org.ift.simca.arq.core.support.context;

import org.primefaces.context.RequestContext;

/**
 * <p>Descripción:</p>
 * Acceso a la solicitud de la página de Java Server Faces
 *
*/
public final class PrimeFacesRequestContext {

    private PrimeFacesRequestContext() {
        // Prevent instantiation
        // Optional: throw an exception e.g. AssertionError
        // if this ever *is* called
    }

    /**
     * Resume el estatus final de la ejecución de un componente.
     *
     * @param exito true para operacion exitosa
     */
    public static void addCallBackParam(boolean exito) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("exito", exito);
    }
}
