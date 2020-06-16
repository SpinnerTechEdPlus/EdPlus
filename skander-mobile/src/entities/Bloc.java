/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author MBM info
 */
public class Bloc {
    private int id;
    private String nom;
    private int nbetages;

    public Bloc(int id, String nom, int nbetages) {
        this.id = id;
        this.nom = nom;
        this.nbetages = nbetages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbetages() {
        return nbetages;
    }

    public void setNbetages(int nbetages) {
        this.nbetages = nbetages;
    }

    @Override
    public String toString() {
        return "Bloc{" + "id=" + id + ", nom=" + nom + ", nbetages=" + nbetages + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Bloc other = (Bloc) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
}
