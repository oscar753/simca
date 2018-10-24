/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;
import mx.org.ift.simca.model.Ejercicio;
/**
 *
 * @author KODE
 */
public interface ConsultaDatosService extends Serializable {
    List<Ejercicio>extraeEjercicios(Integer prmVigentes);
}
