/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.Password;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author medam
 */
public class Inscription1Controller implements Initializable {

    @FXML
    private StackPane stage;
    @FXML
    private TextField tfusername;
    @FXML
    private ImageView Logo;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnInscription;
    @FXML
    private Hyperlink Login;
    @FXML
    private TextField tfemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Inscription(ActionEvent event) {
        
        String username=tfusername.getText();
            String email=tfemail.getText();
            String password=tfpassword.getText();
            
            String passCrypt = Password.hashPassword(tfpassword.getText().trim());

            int enable=1;
            String role="a:1:{i:0;s:10:\"ROLE_ECOLE\";}";
            
            User user=new User(username, username, email, email, passCrypt, enable, role, username);
            ServiceUser SU=new ServiceUser();
        try {
            SU.ajouterEcole(user);
        } catch (SQLException ex) {
            Logger.getLogger(Inscription1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(SU.chercherUtilisateurBylogin(username))
        {
            
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
           Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    Stage stage = new Stage();
  //  stage.initModality(Modality.APPLICATION_MODAL);
    //stage.initStyle(StageStyle.UNDECORATED);
    //stage.setTitle("Prof");
    stage.setScene(new Scene(root1));  
    stage.show();
    closeStage();
        }
        
        
        
        
        
        
        
    }
    

    @FXML
    private void Login(ActionEvent event) {

FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login3.fxml"));
        
        Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    Stage stage = new Stage();
  //  stage.initModality(Modality.APPLICATION_MODAL);
    //stage.initStyle(StageStyle.UNDECORATED);
    //stage.setTitle("Prof");
    stage.setScene(new Scene(root1));  
    stage.show();
    closeStage();
        
        
    }
    
    private void closeStage() {
        ((Stage) stage.getScene().getWindow()).close();
    }
    
    
}
