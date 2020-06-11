

package edplusservices;

import edplusentities.Note;
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

public class NoteServices {

    Connection cnx; 
    
      public NoteServices() {
        cnx = MyConnection.getInstance().getCnx();
    }
      
    public void add(Note n) {
        try {
            String requete = "INSERT INTO note (etudiant,examen,note)"
                    + "VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, n.getEtudiant()+""); 
            pst.setString(2, n.getExamen()+"");
            pst.setString(3, n.getNote()+"");
            pst.executeUpdate(); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }  
    
    
    public void edit(Note n) {
        try {
            String requete = "UPDATE note SET etudiant = ?, examen = ?, note = ? WHERE id = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, n.getEtudiant()+""); 
            pst.setString(2, n.getExamen()+"");
            pst.setString(3, n.getNote()+"");
            pst.setString(4, n.getId()+""); 
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void remove(int id) {
        try {
            String requete = "DELETE FROM note WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, id+""); 
            pst.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<Note> listByExamen(int examen){
        List<Note> myList = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT * FROM note where examen = "+examen;
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Note n = new Note(rs.getInt("id"),rs.getInt("etudiant"),rs.getInt("examen"),rs.getFloat("note"));
                myList.add(n);
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
         return myList;
    }
    
     public List<Note> list(){
        List<Note> myList = new ArrayList();
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT * FROM note ";
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                Note n = new Note(rs.getInt("id"),rs.getInt("etudiant"),rs.getInt("examen"),rs.getFloat("note"));
                myList.add(n);
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
         return myList;
    }
    
    
     
}
