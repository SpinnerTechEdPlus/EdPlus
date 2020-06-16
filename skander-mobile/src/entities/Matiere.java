

package entities;

public class Matiere {
 private int id ;
 private String nom ; 

    public int getId() {
        return id;
    }

    public Matiere(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return  nom ;
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
 
 
}
