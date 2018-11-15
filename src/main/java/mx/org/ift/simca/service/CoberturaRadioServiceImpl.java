/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.org.ift.simca.exposition.dto.CatalogoDTO;
import mx.org.ift.simca.exposition.dto.CoberturaRadioDTO;
import mx.org.ift.simca.model.CoberturaRadio;
import mx.org.ift.simca.persistence.CoberturaRadioMapper;
/**
 *
 * @author KODE
 */
@Service
public class CoberturaRadioServiceImpl implements CoberturaRadioService{
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(CoberturaRadioServiceImpl.class);
    
    
    @Autowired
    private CoberturaRadioMapper coberturaRadioMapper;
    
    /**
     * 
     * @param prmVigentes filtro correspondiente al estatus de los ejercicios, e.g. 1= vigentes; 0= inactivos
     * @return lista de ejercicios fiscales
     */
    @Override
    public List<CoberturaRadioDTO> buscarCoberturas(String folioElectronico) {
        List<CoberturaRadioDTO> coberturasRadioDTO= new ArrayList<CoberturaRadioDTO>();
        
        List<CoberturaRadio> coberturasRadio= new ArrayList<CoberturaRadio>();
        try {
        	coberturasRadio = coberturaRadioMapper.obtenCoberturas(folioElectronico);
        	for(CoberturaRadio coberturaRadio : coberturasRadio) {
        		CoberturaRadioDTO itemCat = new CoberturaRadioDTO();
        		
        		CatalogoDTO itemCatEdo = new CatalogoDTO();
        		itemCatEdo.setIdentificador(coberturaRadio.getEstado().getIdEstado().toString());
        		itemCatEdo.setDescripcion(coberturaRadio.getEstado().getEstado());
        		
        		CatalogoDTO itemCatPob = new CatalogoDTO();
        		itemCatPob.setIdentificador(coberturaRadio.getPoblacion().getIdPoblacion().toString());
        		itemCatPob.setDescripcion(coberturaRadio.getPoblacion().getPoblacion());
        		
        		itemCat.setEstado(itemCatEdo);
        		itemCat.setMunicipio(itemCatPob);
        		coberturasRadioDTO.add(itemCat);
        	}
        	
        } catch (Exception e) {
			System.out.println("Error en el método buscarOpciones: " + e.getMessage());
			return coberturasRadioDTO;
		}
        return coberturasRadioDTO;
    }

}
