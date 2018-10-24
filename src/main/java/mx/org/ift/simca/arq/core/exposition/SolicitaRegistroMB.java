/*
 * ManagedBean utilizado para manejar la solicitud de registro a este sitio
 */
package mx.org.ift.simca.arq.core.exposition;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import mx.org.ift.simca.arq.core.enums.TipoBitacoraEnum;

import mx.org.ift.simca.arq.core.service.register.SolicitaRegistroService;
import mx.org.ift.simca.arq.core.support.Audit;
import mx.org.ift.simca.arq.core.support.context.ContextUtils;

import mx.org.ift.simca.arq.core.support.context.PrimeFacesRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = "view")
public class SolicitaRegistroMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SolicitaRegistroMB.class);
    
    @Autowired
    private SolicitaRegistroService solicitaRegistroService;
    private boolean muestraModalPreregistro = false;
    private boolean muestraModalConfirma = false;
    
    private String correo;

    /**
     * Correo electrónico.
     * @return Correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Correo electrónico.
     * @param correo Correo electrónico.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Condiciona el mostrado de la pantalla modal de confirmación.
     * @return Condiciona el mostrado de la pantalla modal de confirmación.
     */
    public boolean isMuestraModalConfirma() {
        return muestraModalConfirma;
    }

    /**
     * Condiciona el mostrado de la pantalla modal de confirmación.
     * @return Condiciona el mostrado de la pantalla modal de confirmación.
     */
    public boolean isMuestraModalPreregistro() {
        return muestraModalPreregistro;
    }

    /**
     * Prepara el mostrado de la pantalla modal.
     * @param event Prepara el mostrado de la pantalla modal.
     */
    public void preparaModal(ActionEvent event) {
        muestraModalPreregistro = true;
    }

    /**
     * Metodo invocado desde la pagina SolicitarRegistro.xhtml al presionar el boton "Solicitar registro". Este metodo
     * revisa que el correo electronico no exista, si no existe el correo permite continuar con el proceso de alta. Si
     * el correo existe envia mensaje de error en pantalla.
     *
     * @param ae Este parametro se utiliza para recuperar el componente correo del front-end
     */
    public void solicita(ActionEvent ae) {
        boolean flag = false;
        UIComponent ui = ae.getComponent();
        UIComponent mailField = ui.findComponent("correo");

        ContextUtils.evalMalformedMail(
                this.correo,
                "Error de envío de correo. Correo mal formado.",
                mailField);

        if (ContextUtils.countMessages() < 1) {
            if (solicitaRegistroService.alreadyRegistered(this.correo)) {
                ContextUtils.evalTrue(true, "Usted ya está registrado.", mailField);
            } else {
                flag = true;
            }
        }

        if (ContextUtils.countMessages() < 1) {
            muestraModalConfirma = true;
            muestraModalPreregistro = false;
        } else {
            muestraModalConfirma = false;
            muestraModalPreregistro = true;
        }

        try {
            PrimeFacesRequestContext.addCallBackParam(flag);
        } catch (Exception ex) {
            //Presenta un nullPointer si es una petición no ajax
            LOGGER.error("NullPointer si es una petición no ajax", ex);
            //TODO Ver si se agrega el try dentro del método addCallBackParam
        }
    }

    /**
     * Metodo que se invoca en la pagina SolicitarRegistro.xhtml al presionar el boton "Si, es correcto". Este metodo
     * enviar un correo electronico al correo establecido con una liga para que el usuario pueda confirmar su registro.
     *
     * @param ae Este parametro no es utilizado en este metodo.
     */
    public void preregistra(ActionEvent ae) {
        LOGGER.info("Solicitud de registro enviada a {}", this.correo);
        Audit.write(TipoBitacoraEnum.REGISTRATION_APPLIED, this.correo + " ha silicitado registro");
        solicitaRegistroService.enviaSolicitud(
                this.correo,
                ContextUtils.getFullServerUrl());
    }
}
