/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.exposition;


import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import mx.org.ift.simca.model.Ejercicio;
import mx.org.ift.simca.service.ConsultaDatosService;

/**
 *
 * @author KODE
 */
@Controller
@Scope(value = "session")
public class ConsultaDatosMB implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaDatosMB.class);
    private static final long serialVersionUID = 1L;


    @Autowired
    private ConsultaDatosService consultaDatosService;
    
    private List<Ejercicio> listaEjercicios;
    private Ejercicio ejercicioSeleccionado;

    
    @PostConstruct
    public void init() {
        try {
            listaEjercicios = consultaDatosService.extraeEjercicios(1);
            ejercicioSeleccionado = listaEjercicios.get(0);
        } catch (Exception e) {
            LOGGER.error("Error al consultar la informacion en base de datos", e.getMessage());
        }
    }

    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    public Ejercicio getEjercicioSeleccionado() {
        return ejercicioSeleccionado;
    }

    public void setEjercicioSeleccionado(Ejercicio ejercicioSeleccionado) {
        this.ejercicioSeleccionado = ejercicioSeleccionado;
    }
   
    
    
}