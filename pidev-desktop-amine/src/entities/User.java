/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author medam
 */
public class User {
       private static final Map<Integer, User> USERS = new HashMap<>();

    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private String password;
    private int enabled;
    private Date last_login;
    private String roles;
    
    private int user_id;
    private int matiere_id;
    private int classe_id;
    private String searchName;
    private int tel;

    public User() {
    }
     public User(int id) {
        this.id = id;
    }
     public static User of(int id) {
        User user = USERS.get(id);
        if (user == null) {
            user = new User(id);
            USERS.put(id, user);
        }
        return user;}

      public User(String username, String username_canonical, String email, String email_canonical, String password, int enabled, String roles, String searchName) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.searchName = searchName;
        
        
    }
     
     
 //ajouti beha prof   
 public User(String username, String email, String password, int enabled, String roles, String searchName,int matiere_id,int userId) {
        this.username = username;
        this.username_canonical = username;
        this.email = email;
        this.email_canonical = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.searchName = searchName;
        this.matiere_id=matiere_id;
        this.user_id=userId;
    }
  
 
 
 
 //updati beha prof
 
 public User(int id, String username, String username_canonical, String email, String email_canonical, String password, int enabled,String roles, String searchName, int matiere_id) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.searchName = searchName;
        this.matiere_id=matiere_id;
        //this.user_id=userId;
        
    }
 
 
 
 
 
 

    public User(String username, String username_canonical, String email, String email_canonical, String password, int enabled, String roles, String searchName,int matiere_id,int classe_id) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.searchName = searchName;
        this.matiere_id=matiere_id;
        this.classe_id=classe_id;
    }
    //ajouti beha etu
     public User(String username, String username_canonical, String email, String email_canonical, String password, int enabled, String roles,int classe_id,int userId) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.classe_id=classe_id;
        this.user_id=userId;
    }
     
      public User(int id,String username, String username_canonical, String email, String email_canonical, String password, int enabled, String roles,int classe_id) {
        this.id=id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.classe_id=classe_id;
    }


    
    public User(int id, String username, String username_canonical, String email, String email_canonical, String password, int enabled,String roles, String searchName, int matiere_id,int classe_id) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.searchName = searchName;
        this.matiere_id=matiere_id;
        this.classe_id=classe_id;
    }

    public User(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMatiere_id() {
        return matiere_id;
    }

    public void setMatiere_id(int matiere_id) {
        this.matiere_id = matiere_id;
    }

    public int getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(int classe_id) {
        this.classe_id = classe_id;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", password=" + password + ", enabled=" + enabled + ", last_login=" + last_login + ", roles=" + roles + ", user_id=" + user_id + ", matiere_id=" + matiere_id + ", classe_id=" + classe_id + ", searchName=" + searchName + ", tel=" + tel + '}'+"\n";
    }
    
    
    
    
    
}
