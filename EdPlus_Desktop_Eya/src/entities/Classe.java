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
public class Classe {

    private int id;
    private int niveau_id;
    private int num;
    private int nbrEtudiant;
    private String nom;

    public Classe() {
    }

    public Classe(int id, int niveau_id, int num, int nbrEtudiant, String nom) {
        this.id = id;
        this.niveau_id = niveau_id;
        this.num = num;
        this.nbrEtudiant = nbrEtudiant;
        this.nom = nom;
    }

    public Classe(int num, int nbrEtudiant, String nom) {

        this.num = num;
        this.nbrEtudiant = nbrEtudiant;
        this.nom = nom;
    }

    public Classe(int niveau_id, int num, int nbrEtudiant, String nom) {
        this.niveau_id = niveau_id;
        this.num = num;
        this.nbrEtudiant = nbrEtudiant;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Classe{" + "id=" + id + ", niveau_id=" + niveau_id + ", num=" + num + ", nbrEtudiant=" + nbrEtudiant + ", nom=" + nom + '}' + "\n";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNiveau_id(int niveau_id) {
        this.niveau_id = niveau_id;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setNbrEtudiant(int nbrEtudiant) {
        this.nbrEtudiant = nbrEtudiant;
    }

    public int getId() {
        return id;
    }

    public int getNiveau_id() {
        return niveau_id;
    }

    public int getNum() {
        return num;
    }

    public int getNbrEtudiant() {
        return nbrEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
