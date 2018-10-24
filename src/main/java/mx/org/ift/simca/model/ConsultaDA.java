/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.model;

import java.io.Serializable;
/**
 *
 * @author KODE
 */
public class ConsultaDA implements Serializable{
    
    private static final long serialVersionUID = 1L;    
    private Integer     idConsultaDA;
    private String      descripcion;

    public ConsultaDA() {
    }

    public ConsultaDA(Integer idConsultaDA, String descripcion) {
        this.idConsultaDA = idConsultaDA;
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.idConsultaDA != null ? this.idConsultaDA.hashCode() : 0);
        hash = 31 * hash + (this.descripcion != null ? this.descripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConsultaDA other = (ConsultaDA) obj;
        if (this.idConsultaDA != other.idConsultaDA && (this.idConsultaDA == null || !this.idConsultaDA.equals(other.idConsultaDA))) {
            return false;
        }
        return true;
    }

    public Integer getIdConsultaDA() {
        return idConsultaDA;
    }

    public void setIdConsultaDA(Integer idConsultaDA) {
        this.idConsultaDA = idConsultaDA;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
