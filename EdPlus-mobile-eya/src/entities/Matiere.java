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
    private String path;

    public Matiere() {
    }

    public Matiere(String nom, int niveau_id, String path) {
        this.nom = nom;
        this.niveau_id = niveau_id;
        this.path = path;
    }

    public Matiere(int id, String nom, int niveau_id, String path) {
        this.id = id;
        this.nom = nom;
        this.niveau_id = niveau_id;
        this.path = path;
    }

    @Override
    public String toString() {
        return "Matiere{" + "id=" + id + ", nom=" + nom + ", niveau_id=" + niveau_id + ", path=" + path + '}' + "\n";
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
