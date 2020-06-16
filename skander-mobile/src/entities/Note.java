

package entities;


public class Note {

    private int id ;
    private int etudiant;
    private String etudiantName;
    private int examen;
    private String examenName;
    private float note;

    public String getEtudiantName() {
        return etudiantName;
    }

    public void setEtudiantName(String etudiantName) {
        this.etudiantName = etudiantName;
    }

    public String getExamenName() {
        return examenName;
    }

    public void setExamenName(String examenName) {
        this.examenName = examenName;
    }

    public Note(int id, int etudiant, int examen, float note) {
        this.id = id;
        this.etudiant = etudiant;
        this.examen = examen;
        this.note = note;
      /*   
        ExamenServices eS = new ExamenServices();
        this.etudiantName = eS.getEtudiantName(this.etudiant);
        this.examenName = eS.getExamenName(this.examen);*/
    }

    public Note(int etudiant, int examen, float note) {
        this.etudiant = etudiant;
        this.examen = examen;
        this.note = note;
    }

     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(int etudiant) {
        this.etudiant = etudiant;
    }

    public int getExamen() {
        return examen;
    }

    public void setExamen(int examen) {
        this.examen = examen;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", etudiant=" + etudiant + ", examen=" + examen + ", note=" + note + '}';
    }
    
    
    
    
}
