/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.User;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceUser;

/**
 *
 * @author medam
 */
public class Pidev  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    
     
            ServiceUser serU = new ServiceUser();
           /* //String role="a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}";

           // User u=new User(102,"newAmine","newAmine", "aaannn@a.a","aaannn@a.a", "passwordnew",1,"a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}","newAmine");
     
            try {
                //serU.update(u);
                serU.delete(u);
            serU.readAll("\"a:1:{i:0;s:13:\\\"ROLE_ETUDIANT\\\";}\"");
             List<User> list1 = serU.readAll("\"a:1:{i:0;s:13:\\\"ROLE_ETUDIANT\\\";}\"");
                         System.out.println(list1);
        } catch (SQLException ex) {
             System.out.println(ex);
        }
            
            
             /*   try {
            serU.ajouter(u);
        } catch (SQLException ex) {
             System.out.println(ex);
        }*/
            
    
}}
