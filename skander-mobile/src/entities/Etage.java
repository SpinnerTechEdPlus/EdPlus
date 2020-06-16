/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MBM info
 */
public class Etage {
    private int id;
    private int numero;
    private int nbsalle;

    public Etage(int id, int numero, int nbsalle) {
        this.id = id;
        this.numero = numero;
        this.nbsalle = nbsalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNbsalle() {
        return nbsalle;
    }

    public void setNbsalle(int nbsalle) {
        this.nbsalle = nbsalle;
    }

    @Override
    public String toString() {
        return "Etage{" + "id=" + id + ", numero=" + numero + ", nbsalle=" + nbsalle + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.numero;
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
        final Etage other = (Etage) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }
    
    
    
}
