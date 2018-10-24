package mx.org.ift.simca.arq.core.support.listeners;

import javax.faces.application.FacesMessage;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import mx.org.ift.simca.arq.core.support.context.ContextUtils;

/**
 * <p>Descripción:</p>
 * Clase que intercepta la face de Faces antes de que ocurra el RENDER_RESPONSE con la finalidad de mostrar los mensajes
 * generados por LoginSuccessHandler y LoginFailureHandler.
*/
public class LoginErrorPhaseListener implements PhaseListener {

    /**
     * Representa el valor inicial de la versión del serial.
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @param arg0
     */
    @Override
    public void afterPhase(final PhaseEvent arg0) {
    }

    /**
     *
     * @return
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    /**
     *
     * @param arg0
     */
    @Override
    public void beforePhase(final PhaseEvent arg0) {
        FacesMessage msg = ContextUtils.getLoginFacesMessageVarname();
        if (msg != null) {
            ContextUtils.addMsg(msg);
            ContextUtils.cleanLoginFacesMessageVarname();
        }
    }
}
