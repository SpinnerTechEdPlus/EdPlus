

package edplusservices;

import edplusentities.Seance;
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

public class SeanceServices {

    Connection cnx; 
    
      public SeanceServices() {
        cnx = MyConnection.getInstance().getCnx();
    }
      
    public void add(Seance s) {
        try {
            String requete = "INSERT INTO seance (salle,matiere,professeur,classe,jour,debut,fin)"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, s.getSalle()+""); 
            pst.setString(2, s.getMatiere()+""); 
            pst.setString(3, s.getProfesseur()+""); 
            pst.setString(4, s.getClasse()+""); 
            pst.setString(5, s.getJour().toString()); 
            pst.setString(6, s.getDebut().toString()); 
            pst.setString(7, s.getFin().toString()); 
            pst.executeUpdate(); 
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }  
    
    
    public void edit(Seance s) {
        try {
            String requete = "UPDATE seance SET salle = ?, matiere = ?, professeur = ?, classe = ?, jour = ?, debut = ?, fin = ? WHERE id = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, s.getSalle()+""); 
            pst.setString(2, s.getMatiere()+""); 
            pst.setString(3, s.getProfesseur()+""); 
            pst.setString(4, s.getClasse()+""); 
            pst.setString(5, s.getJour().toString()); 
            pst.setString(6, s.getDebut().toString()); 
            pst.setString(7, s.getFin().toString()); 
            pst.setString(8, s.getId()+""); 
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void remove(int id) {
        try {
            String requete = "DELETE FROM seance WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, id+""); 
            pst.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<Seance> listByClasse(int classe){
        List<Seance> myList = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT * FROM seance where classe = "+classe;
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Seance s = new Seance(rs.getInt("id"),rs.getInt("salle"),rs.getInt("matiere"),rs.getInt("professeur"),rs.getInt("classe"),rs.getDate("jour").toLocalDate(),rs.getTime("debut").toLocalTime(),rs.getTime("fin").toLocalTime());
                myList.add(s);
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
         return myList;
    }


     public List<Seance> list(){
        List<Seance> myList = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT * FROM seance"  ;
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Seance s = new Seance(rs.getInt("id"),rs.getInt("salle"),rs.getInt("matiere"),rs.getInt("professeur"),rs.getInt("classe"),rs.getDate("jour").toLocalDate(),rs.getTime("debut").toLocalTime(),rs.getTime("fin").toLocalTime());
                myList.add(s);
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
         return myList;
    }
     
}
