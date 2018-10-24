/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.model.Ejercicio;
import mx.org.ift.simca.persistence.ConsultaDatosMapper;
/**
 *
 * @author KODE
 */
@Service
public class ConsultaDatosServiceImpl implements ConsultaDatosService{
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaDatosServiceImpl.class);
    
    
    @Autowired
    private ConsultaDatosMapper consultaDatosMapper;
    
    /**
     * 
     * @param prmVigentes filtro correspondiente al estatus de los ejercicios, e.g. 1= vigentes; 0= inactivos
     * @return lista de ejercicios fiscales
     */
    @Override
    public List<Ejercicio> extraeEjercicios(Integer prmVigentes) {
        List<Ejercicio> lstEjercicios= new ArrayList();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("vigenciaEjercicios", prmVigentes);
        consultaDatosMapper.obtenEjercicios(param);
        lstEjercicios = (List<Ejercicio>)param.get("o_acc_cur");
        
        return lstEjercicios;
    }

}
