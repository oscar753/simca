/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.simca.model;

import java.io.Serializable;
/**
 *
 * @author 
 */
public class Ejercicio implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer idEjercicio;
    private Integer ejercicio;
    private Integer vigente;

    public Ejercicio() {
    }

    public Ejercicio(Integer idEjercicio, Integer ejercicio, Integer vigente) {
        this.idEjercicio = idEjercicio;
        this.ejercicio = ejercicio;
        this.vigente = vigente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.idEjercicio != null ? this.idEjercicio.hashCode() : 0);
        hash = 29 * hash + (this.ejercicio != null ? this.ejercicio.hashCode() : 0);
        hash = 29 * hash + (this.vigente != null ? this.vigente.hashCode() : 0);
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
        final Ejercicio other = (Ejercicio) obj;
        if (this.idEjercicio != other.idEjercicio && (this.idEjercicio == null || !this.idEjercicio.equals(other.idEjercicio))) {
            return false;
        }
        return true;
    }

    public Integer getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Integer getVigente() {
        return vigente;
    }

    public void setVigente(Integer vigente) {
        this.vigente = vigente;
    }

    
    
}
