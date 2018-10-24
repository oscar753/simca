/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.persistence;

import mx.org.ift.simca.arq.core.support.IMapper;
import mx.org.ift.simca.model.ConsultaDA;

import java.util.Map;

/**
 *
 * @author 
 */
public interface ConsultaDatosMapper extends IMapper<ConsultaDA> {
    void obtenEjercicios(Map<String, Object> parameter);
}
