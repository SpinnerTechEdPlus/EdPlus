/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

//import java.sql.Date;

/**
 *
 * @author medam
 */
public class User {
    private int id;
    private String username;
   // private String username_canonical;
    private String email;
    //private String email_canonical;
    private String password;
    private int enabled;
    //private Date last_login;
    private String roles;
    
    //private int user_id;
    //private int matiere_id;
    //private int classe_id;
    //private String searchName;
    //private int tel;

    public User() {
    }
    
    
    /*public User(int id, String username, String username_canonical, String email, String email_canonical, String password, int enabled, Date last_login, String roles, int user_id, int matiere_id, int classe_id, String searchName, int tel) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.enabled = enabled;
        this.last_login = last_login;
        this.roles = roles;
        this.user_id = user_id;
        this.matiere_id = matiere_id;
        this.classe_id = classe_id;
        this.searchName = searchName;
        this.tel = tel;
    }*/

    public User(int id, String username, String email, String password, int enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        
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

  /*  public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }*/

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

    /*public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }*/

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    /*public int getUser_id() {
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
    }*/
    
    @Override
    public boolean equals(Object obj) {
        return ((User)obj).getId() == this.getId();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + '}';
    }

    
    
    
    
    
}
