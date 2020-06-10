/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import pidev.DataBase;
// import CRUDInterface.Iservice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author medam
 */
public class ServiceUser{
    private Connection con;
    private Statement ste;

    public ServiceUser() {
        con = DataBase.getInstance().getConnection();

    }
    
    
    public void ajouterEcole(User u) throws SQLException
    {
    //PreparedStatement pre=con.prepareStatement("INSERT INTO `edplus`.`fos_user` ( `username`, `username_canonical`, `email`, `email_canonical`,  `password`, `enabled`, `roles`, `searchName`, `user_id`, `matiere_id`, `classe_id`, `last_login`, `tel`) VALUES ( ?, ?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?);");

    PreparedStatement pre=con.prepareStatement("INSERT INTO `edplus`.`fos_user` ( `username`,`username_canonical`,`email`,`email_canonical`,`password`,`enabled`,`roles`, `searchName`) VALUES (?, ? , ?, ?, ?,?,?,?);");
   
    pre.setString(1, u.getUsername());
    pre.setString(2, u.getUsername_canonical());
    pre.setString(3, u.getEmail());
    pre.setString(4, u.getEmail_canonical());
    pre.setString(5, u.getPassword());
    pre.setInt(6, u.getEnabled());
   // pre.setDate(9, u.getLast_login());
    pre.setString(7, u.getRoles());
   // pre.setInt(12, u.getUser_id());
   //pre.setInt(9, u.getMatiere_id());
  //  pre.setInt(11, u.getClasse_id());
    pre.setString(8, u.getSearchName());
   // pre.setInt(13, u.getTel());
    pre.executeUpdate();
                System.out.println("Insertion effectué avec succés");

    }
   
    
    public void ajouterProf(User u) throws SQLException
    {
    //PreparedStatement pre=con.prepareStatement("INSERT INTO `edplus`.`fos_user` ( `username`, `username_canonical`, `email`, `email_canonical`,  `password`, `enabled`, `roles`, `searchName`, `user_id`, `matiere_id`, `classe_id`, `last_login`, `tel`) VALUES ( ?, ?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?);");

    PreparedStatement pre=con.prepareStatement("INSERT INTO `edplus`.`fos_user` ( `username`,`username_canonical`,`email`,`email_canonical`,`password`,`enabled`,`roles`, `searchName`, `matiere_id`) VALUES ( ?, ?, ? , ?, ?, ?,?,?,?);");
   
    pre.setString(1, u.getUsername());
    pre.setString(2, u.getUsername_canonical());
    pre.setString(3, u.getEmail());
    pre.setString(4, u.getEmail_canonical());
    pre.setString(5, u.getPassword());
    pre.setInt(6, u.getEnabled());
   // pre.setDate(9, u.getLast_login());
    pre.setString(7, u.getRoles());
   // pre.setInt(12, u.getUser_id());
   pre.setInt(9, u.getMatiere_id());
  //  pre.setInt(11, u.getClasse_id());
    pre.setString(8, u.getSearchName());
   // pre.setInt(13, u.getTel());
    pre.executeUpdate();
                System.out.println("Insertion effectué avec succés");

    }
    

            

    

   
    public boolean delete(User u) throws SQLException {
            
        ste = con.createStatement();
        String requeteDelete = "DELETE FROM `fos_user` WHERE id = '"+ u.getId() +"'";
          
        
        if(ste.executeUpdate(requeteDelete) == 1)
        {
            System.out.println("Ce user a été supprimé avec succès");
        return true;   
        }
        else 
        {
            System.out.println("Ce user n'existe pas");
            return false;
        }
        }
    

   
    public boolean updateProf(User u) throws SQLException {
       
        ste = con.createStatement();
        String requeteDelete = "UPDATE `fos_user` SET `username`= '"+u.getUsername()+   "',`username_canonical`= '"+ u.getUsername_canonical()+   "',`email`= '"+ u.getEmail()+"'"+ ",`email_canonical`= '"+ u.getEmail_canonical()+"',`password`= '"+ u.getPassword()+"',`enabled`= '"+ u.getEnabled()+"',`roles`= '"+ u.getRoles()+"',`searchName`= '"+ u.getSearchName()+"',`matiere_id`= '"+ u.getMatiere_id()+"',`id`= '"+ u.getId()+"' WHERE id = '"+ u.getId() +"' ";
          
        
        if(ste.executeUpdate(requeteDelete) == 1)
        {
            System.out.println("User mis à jour !");
        return true;   
        }
        else 
        {
            System.out.println("Ce User n'existe pas");
            return false;
        }
        }
    
    
     public void ajouterEtu(User u) throws SQLException
    {
    //PreparedStatement pre=con.prepareStatement("INSERT INTO `edplus`.`fos_user` ( `username`, `username_canonical`, `email`, `email_canonical`,  `password`, `enabled`, `roles`, `searchName`, `user_id`, `matiere_id`, `classe_id`, `last_login`, `tel`) VALUES ( ?, ?, ?, ?,?, ?, ?,?, ?, ?,?, ?, ?);");

    PreparedStatement pre=con.prepareStatement("INSERT INTO `edplus`.`fos_user` ( `username`,`username_canonical`,`email`,`email_canonical`,`password`,`enabled`,`roles`, `classe_id`) VALUES ( ? , ?, ?, ?,?,?,?,?);");
   
    pre.setString(1, u.getUsername());
    pre.setString(2, u.getUsername_canonical());
    pre.setString(3, u.getEmail());
    pre.setString(4, u.getEmail_canonical());
    pre.setString(5, u.getPassword());
    pre.setInt(6, u.getEnabled());
   // pre.setDate(9, u.getLast_login());
    pre.setString(7, u.getRoles());
   // pre.setInt(12, u.getUser_id());
   pre.setInt(8, u.getClasse_id());
   // pre.setInt(9, u.getClasse_id());
    //pre.setString(8, u.getSearchName());
   // pre.setInt(13, u.getTel());
    pre.executeUpdate();
                System.out.println("Insertion effectué avec succés");

    }
     
     
     public boolean updateEtu(User u) throws SQLException {
       
        ste = con.createStatement();
        String requeteDelete = "UPDATE `fos_user` SET `username`= '"+u.getUsername()+   "',`username_canonical`= '"+ u.getUsername_canonical()+   "',`email`= '"+ u.getEmail()+"'"+ ",`email_canonical`= '"+ u.getEmail_canonical()+"',`password`= '"+ u.getPassword()+"',`enabled`= '"+ u.getEnabled()+"',`roles`= '"+ u.getRoles()+"',`classe_id`= '"+ u.getClasse_id()+"',`id`= '"+ u.getId()+"' WHERE id = '"+ u.getId() +"' ";
          
        
        if(ste.executeUpdate(requeteDelete) == 1)
        {
            System.out.println("User mis à jour !");
        return true;   
        }
        else 
        {
            System.out.println("Ce User n'existe pas");
            return false;
        }
        }
    


     
    public ObservableList<User> readAll(String s) throws SQLException {
    ObservableList<User> arr=FXCollections.observableArrayList();
    String req = "select * from fos_user where roles =?";
    PreparedStatement preparedStatement;
    
    
    
    
            
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet rs=preparedStatement.executeQuery();
        
    
    while (rs.next()) {                
         
              int id=rs.getInt(1);
              String nom=rs.getString(2);
              String nom2=rs.getString(3);
              String email=rs.getString(4);
              String email2=rs.getString(5);
              String pw=rs.getString(8);
              int enb=rs.getInt(6);
              String role=rs.getString(12);
              String sN=rs.getString(14);
              int matiereId=rs.getInt(15);
              int classId=rs.getInt(16);
                    
               
               User n=new User(id,nom,  nom2, email,email2,pw,enb,role,sN,matiereId,classId);
     arr.add(n);
     }
    return arr;
    }

    /*
    public boolean update(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

   /* @Override
    public List<User> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    
    public Boolean verifierpassword(String pword, String uname) {
        String s1 = "";
        String req = "Select password from fos_user where username= ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, uname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                s1 = resultSet.getString(1);
               // String s2=uname+"{"+s1+"}";
 
                  
              
                System.out.println("ili 3malnelou deccryptage==>"+Password.checkPassword(pword,s1));
                  //   System.out.println(uname);
                   System.out.println(s1);
                 
         
                if ( (Password.checkPassword(pword,s1))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }
    
    public boolean chercherUtilisateurBylogin(String s) {
        User user = null;
        String req = "select * from fos_user where username =?";
        PreparedStatement preparedStatement;
        try {
         preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 /* user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getString("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("Searchname"),
                        resultSet.getString("prenom"),
                      //  resultSet.getString("phone"),
                        resultSet.getString("date_naissance"),
                        resultSet.getString("date_inscription"),
                       resultSet.getString("genre"));*/
                 user=new User(resultSet.getInt("id"),
                         resultSet.getString("username"),
                         resultSet.getString("username_canonical"),
                         resultSet.getString("email"),
                         resultSet.getString("email_canonical"),
                         resultSet.getString("password"),
                         resultSet.getInt("enabled"),
                         resultSet.getString("roles"),
                         resultSet.getString("Searchname"),
                         resultSet.getInt("matiere_id"),
                         resultSet.getInt("classe_id")
                 );
                 
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        return true;
    }
    
    
    
     public ObservableList<String> readMatiereName() throws SQLException {
    ObservableList<String> arr=FXCollections.observableArrayList();
     String req = "select nom from matiere ";
        PreparedStatement preparedStatement;
        
         preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
         //   preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {                
         
              String Un=resultSet.getNString("nom");
              arr.add(Un);
            
    
    }
    
    return arr;
    
    
    
} 
     public int getMatiereId(String s) throws SQLException {
         int id=0;
         String req = "select id from matiere where nom=? ";
         PreparedStatement preparedStatement;
         
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {    
                 id=resultSet.getInt("id");
                
            }
            return id;
            
            
              
            
     
}
     
     public ObservableList<User> readByMatiere(String s,int m) throws SQLException {
    ObservableList<User> arr=FXCollections.observableArrayList();
    String req = "select * from fos_user where roles =? and matiere_id =?";
    PreparedStatement preparedStatement;
    
    
    
    
            
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, s);
            preparedStatement.setInt(2, m);

            
            ResultSet rs=preparedStatement.executeQuery();
        
    
    while (rs.next()) {                
         
              int id=rs.getInt(1);
              String nom=rs.getString(2);
              String nom2=rs.getString(3);
              String email=rs.getString(4);
              String email2=rs.getString(5);
              String pw=rs.getString(8);
              int enb=rs.getInt(6);
              String role=rs.getString(12);
              String sN=rs.getString(14);
              int matiereId=rs.getInt(15);
              int classId=rs.getInt(16);
                    
               
               User n=new User(id,nom,  nom2, email,email2,pw,enb,role,sN,matiereId,classId);
     arr.add(n);
     }
    return arr;
    }
     
     
     public ObservableList<String> readClasseName() throws SQLException {
    ObservableList<String> arr=FXCollections.observableArrayList();
     String req = "select nom from classe ";
        PreparedStatement preparedStatement;
        
         preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
         //   preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()) {                
         
              String Un=resultSet.getNString("nom");
              arr.add(Un);
            
    
    }
    
    return arr;
    
    
    
} 
     
      public int getClasseId(String s) throws SQLException {
         int id=0;
         String req = "select id from classe where nom=? ";
         PreparedStatement preparedStatement;
         
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {    
                 id=resultSet.getInt("id");
                
            }
            return id;
            
            
              
            
     
}
      
       public ObservableList<User> readByClasse(String s,int m) throws SQLException {
    ObservableList<User> arr=FXCollections.observableArrayList();
    String req = "select * from fos_user where roles =? and classe_id =?";
    PreparedStatement preparedStatement;
    
    
    
    
            
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, s);
            preparedStatement.setInt(2, m);

            
            ResultSet rs=preparedStatement.executeQuery();
        
    
    while (rs.next()) {                
         
              int id=rs.getInt(1);
              String nom=rs.getString(2);
              String nom2=rs.getString(3);
              String email=rs.getString(4);
              String email2=rs.getString(5);
              String pw=rs.getString(8);
              int enb=rs.getInt(6);
              String role=rs.getString(12);
              String sN=rs.getString(14);
              int matiereId=rs.getInt(15);
              int classId=rs.getInt(16);
                    
               
               User n=new User(id,nom,  nom2, email,email2,pw,enb,role,sN,matiereId,classId);
     arr.add(n);
     }
    return arr;
    }
       
       
       
       
     
     
}
