/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Eya
 */
public class Matiere {
    private int id;
    private String nom;
    private int niveau_id;
    private String nom_image;

    public Matiere() {
    }

    public Matiere(String nom, int niveau_id, String nom_image) {
        this.nom = nom;
        this.niveau_id = niveau_id;
        this.nom_image = nom_image;
    }

    public Matiere(int id, String nom, int niveau_id, String nom_image) {
        this.id = id;
        this.nom = nom;
        this.niveau_id = niveau_id;
        this.nom_image = nom_image;
    }

    @Override
    public String toString() {
        return "Matiere{" + "id=" + id + ", nom=" + nom + ", niveau_id=" + niveau_id + ", nom_image=" + nom_image + '}'+"\n";
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

    public int getNiveau_id() {
        return niveau_id;
    }

    public void setNiveau_id(int niveau_id) {
        this.niveau_id = niveau_id;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }
    
    
    
}
