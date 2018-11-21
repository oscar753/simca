/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.service;

import java.io.Serializable;
import java.util.List;

import mx.org.ift.simca.exposition.dto.CoberturaRadioDTO;
/**
 *
 * @author KODE
 */
public interface CoberturaRadioService extends Serializable {
    List<CoberturaRadioDTO> buscarCoberturas(String folioElectronico, Integer idSenial);
}
