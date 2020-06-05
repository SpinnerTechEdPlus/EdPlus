

package entities;
  


public class Examen {

    private int id;
    private int salle;
    private String salleName;
    private int matiere;
    private String matiereName;
    private int classe; 
    private String classeName;
    private String horaire;
    private String semestre;

    @Override
    public String toString() {
        return "\nExamen{" + "id=" + id + ", salle  "+salleName +"=" + salle + ", matiere  "+ matiereName +"=" +matiere + ", classe "+ classeName +"=" + classe + ", horaire=" + horaire + ", semestre=" + semestre + '}';
    }

    public String getSalleName() {
        return salleName;
    }

    public void setSalleName(String SalleName) {
        this.salleName = SalleName;
    }

    public String getMatiereName() {
        return matiereName;
    }

    public void setMatiereName(String matiereName) {
        this.matiereName = matiereName;
    }

    public String getClasseName() {
        return classeName;
    }

    public void setClasseName(String classeName) {
        this.classeName = classeName;
    }

    public Examen(int id, int salle, int matiere, int classe, String horaire, String semestre) {
        this.id = id;
        this.salle = salle;
        this.matiere = matiere;
        this.classe = classe;
        this.horaire = horaire;
        this.semestre = semestre;
        /*
        ExamenServices eS =  new ExamenServices();
        this.salleName = eS.getSalleName(this.salle);
        this.matiereName = eS.getMatiereName(this.matiere);
        this.classeName = eS.getClasseName(this.classe);
        */
     }

    public Examen(int salle, int matiere, int classe, String horaire, String semestre) {
        this.salle = salle;
        this.matiere = matiere;
        this.classe = classe;
        this.horaire = horaire;
        this.semestre = semestre;
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

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
   
}