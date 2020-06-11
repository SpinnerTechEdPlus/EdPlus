

package edplusentities;

import edplusservices.ExamenServices;
import java.time.LocalDate;
import java.time.LocalTime;

public class Seance {

    private int id;
    private int salle;
    private String salleName;
    private int matiere;
    private String matiereName;
    private int professeur;
    private String professeurName;
    private int classe;
    private String classeName;
    private LocalDate jour;
    private LocalTime debut;
    private LocalTime fin;

    public String getSalleName() {
        return salleName;
    }

    public void setSalleName(String salleName) {
        this.salleName = salleName;
    }

    public String getMatiereName() {
        return matiereName;
    }

    public void setMatiereName(String matiereName) {
        this.matiereName = matiereName;
    }

    public String getProfesseurName() {
        return professeurName;
    }

    public void setProfesseurName(String professeurName) {
        this.professeurName = professeurName;
    }

    public String getClasseName() {
        return classeName;
    }

    public void setClasseName(String classeName) {
        this.classeName = classeName;
    }

    public Seance(int id, int salle, int matiere, int professeur, int classe, LocalDate jour, LocalTime debut, LocalTime fin) {
        this.id = id;
        this.salle = salle;
        this.matiere = matiere;
        this.professeur = professeur;
        this.classe = classe;
        this.jour = jour;
        this.debut = debut;
        this.fin = fin;
        
        ExamenServices eS = new ExamenServices();
        this.salleName = eS.getSalleName(this.salle);
        this.matiereName = eS.getMatiereName(this.matiere);
        this.professeurName = eS.getProfesseurName(this.professeur);
        this.classeName = eS.getClasseName(this.classe);
    }

    public Seance(int salle, int matiere, int professeur, int classe, LocalDate jour, LocalTime debut, LocalTime fin) {
        this.salle = salle;
        this.matiere = matiere;
        this.professeur = professeur;
        this.classe = classe;
        this.jour = jour;
        this.debut = debut;
        this.fin = fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalle() {
        return salle;
    }

    public void setSalle(int salle) {
        this.salle = salle;
    }

    public int getMatiere() {
        return matiere;
    }

    public void setMatiere(int matiere) {
        this.matiere = matiere;
    }

    public int getProfesseur() {
        return professeur;
    }

    public void setProfesseur(int professeur) {
        this.professeur = professeur;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public LocalTime getDebut() {
        return debut;
    }

    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Seance{" + "id=" + id + ", salle=" + salle + ", matiere=" + matiere + ", professeur=" + professeur + ", classe=" + classe + ", jour=" + jour + ", debut=" + debut + ", fin=" + fin + '}';
    }
    
    
}
