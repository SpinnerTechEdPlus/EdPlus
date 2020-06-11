package edplusservices;

import edplusentities.Examen;
import edplusutils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamenServices {

    Connection cnx;

    public ExamenServices() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void add(Examen e) {
        try {
            String requete = "INSERT INTO examen (salle,matiere,classe,horaire,semestre)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, e.getSalle() + "");
            pst.setString(2, e.getMatiere() + "");
            pst.setString(3, e.getClasse() + "");
            pst.setString(4, e.getHoraire().toString());
            pst.setString(5, e.getSemestre());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void edit(Examen e) {
        try {
            String requete = "UPDATE examen SET salle = ?, matiere=?, classe=?, horaire=?, semestre=? WHERE id = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, e.getSalle() + "");
            pst.setString(2, e.getMatiere() + "");
            pst.setString(3, e.getClasse() + "");
            pst.setString(4, e.getHoraire().toString());
            pst.setString(5, e.getSemestre());
            pst.setString(6, e.getId() + "");
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void remove(int id) {
        try {
            String requete = "DELETE FROM examen WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, id + "");
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Examen> listByClasse(int classe) {
        List<Examen> myList = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT * FROM examen where classe = " + classe;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Examen e = new Examen(rs.getInt("id"), rs.getInt("salle"), rs.getInt("matiere"), rs.getInt("classe"), rs.getTimestamp("horaire").toLocalDateTime(), rs.getString("semestre"));

                myList.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return myList;
    }

    public List<Examen> list() {
        List<Examen> myList = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT * FROM examen ";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Examen e = new Examen(rs.getInt("id"), rs.getInt("salle"), rs.getInt("matiere"), rs.getInt("classe"), rs.getTimestamp("horaire").toLocalDateTime(), rs.getString("semestre"));

                myList.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return myList;
    }

    public List<Integer> getSallesIds() {
        List<Integer> myList = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT id FROM salle";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myList.add(rs.getInt("id"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return myList;
    }

    public String getSalleName(int id) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT nom FROM salle where id = " + id;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getString("nom");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }

    public String getMatiereName(int id) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT nom FROM matiere where id = " + id;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getString("nom");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }

    public String getClasseName(int id) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT nom FROM classe where id = " + id;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getString("nom");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }

    public String getProfesseurName(int id) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT nom FROM professeur where id = " + id;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getString("nom");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }

    public String getEtudiantName(int id) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT nom FROM etudiant where id = " + id;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getString("nom");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }

    public String getExamenName(int id) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT matiere.nom , examen.semestre FROM examen INNER JOIN matiere ON matiere.id = examen.matiere where examen.id = " + id;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return (rs.getString("nom") + " - Semestre " + rs.getString("semestre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }

    public List<String> getAllClasses() {

        List<String> myList = new ArrayList();
        try {

            Statement st = cnx.createStatement();
            String requete = "SELECT id , nom FROM classe";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myList.add(rs.getInt("id") + " - " + rs.getString("nom"));
            }

            return myList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return myList;
        } 
    }
    
     public List<String> getAllMatieres() {

        List<String> myList = new ArrayList();
        try {

            Statement st = cnx.createStatement();
            String requete = "SELECT id , nom FROM matiere";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myList.add(rs.getInt("id") + " - " + rs.getString("nom"));
            }

            return myList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return myList;
        } 
    }
     
     
      public List<String> getAllSalles() {

        List<String> myList = new ArrayList();
        try {

            Statement st = cnx.createStatement();
            String requete = "SELECT id , nom FROM salle";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myList.add(rs.getInt("id") + " - " + rs.getString("nom"));
            }

            return myList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return myList;
        } 
    }
      
        public List<String> getAllProfesseurs() {

        List<String> myList = new ArrayList();
        try {

            Statement st = cnx.createStatement();
            String requete = "SELECT id , nom FROM professeur";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myList.add(rs.getInt("id") + " - " + rs.getString("nom"));
            }

            return myList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return myList;
        } 
    }
        
          public List<String> getAllEtudiants() {

        List<String> myList = new ArrayList();
        try {

            Statement st = cnx.createStatement();
            String requete = "SELECT id , nom FROM etudiant";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myList.add(rs.getInt("id") + " - " + rs.getString("nom"));
            }

            return myList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return myList;
        } 
    }
          
          
          public int getComboBoxesConversion(String classeName,String convertedName) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT id FROM "+ classeName + " where CONCAT(id, ' - ', nom) = '" + convertedName+"'";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
        return -1;
    } 
          
          public int getExamenComboBoxesConversion(String convertedName) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT examen.id FROM examen INNER JOIN matiere ON matiere.id = examen.matiere where CONCAT(matiere.nom, ' - Semestre ', examen.semestre) = '" + convertedName+"'";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
        return -1;
    }
          
          
          public String doComboBoxesConversion(String classeName,int id) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT id , nom FROM "+ classeName + " where id = " + id;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getInt("id")+" - "+rs.getString("nom");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }
     
          public String doExamenComboBoxesConversion(int id) {
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT matiere.nom , examen.semestre FROM examen INNER JOIN matiere ON matiere.id = examen.matiere where examen.id = "+ id ;
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                return rs.getString("nom") + " - Semestre " + rs.getString("semestre");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
        return "";
    }
          
          public List<String> getAllExamens() {

        List<String> myList = new ArrayList();
        try {

            Statement st = cnx.createStatement();
            String requete = "SELECT matiere.nom , examen.semestre FROM examen INNER JOIN matiere ON matiere.id = examen.matiere ";
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myList.add(rs.getString("nom") + " - Semestre " + rs.getString("semestre"));
            }

            return myList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return myList;
        } 
    }
           

}
