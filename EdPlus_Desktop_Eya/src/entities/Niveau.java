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
public class Niveau {
    
    
   
    private int id  ;
    private String nom ;
    private int nivScolaire  ;
    private int user_id  ;

    public Niveau(int id, String nom, int nivScolaire, int user_id) {
        this.id = id;
        this.nom = nom;
        this.nivScolaire = nivScolaire;
        this.user_id = user_id;
    }

    public Niveau(String nom, int nivScolaire, int user_id) {
        this.nom = nom;
        this.nivScolaire = nivScolaire;
        this.user_id = user_id;
    }

    public Niveau() {
    }

    @Override
    public String toString() {
        return "Niveau{" + "id" + id + ", nom=" + nom + ",nivScolaire=" + nivScolaire + ",user_id=" + user_id + '}'+"\n";
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNivScolaire() {
        return nivScolaire;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNivScolaire(int nivScolaire) {
        this.nivScolaire = nivScolaire;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
}
