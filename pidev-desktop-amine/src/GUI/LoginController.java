/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceUser;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author medam
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button btnLogin;
    
    
 private User loggedUser;
 private static LoginController instance;
 public static final Map<Integer, User> USERS = new HashMap<>();
    @FXML
    private ImageView Logo;
    @FXML
    private StackPane stage;
    @FXML
    private ImageView imgProgress;
    @FXML
    private Hyperlink Newaccount;

 public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }
    
    
      public User getLoggedUser() {
        return loggedUser;
    }
      
      
   
 
 
 
 
 
 
 
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) {
         ServiceUser myServices=new ServiceUser();
        String mdp=tfpassword.getText();
        String username=tfusername.getText();
        
        
        String errorMessage = "";

        if (username == null || username.length() == 0) {
            errorMessage += "Username invalide \n";
        }

        if (mdp  == null || mdp.length() == 0) {
            errorMessage += "Mot de passe invalide \n";
        }

        if (errorMessage.length() != 0) {

       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error valeur");
            alert.setHeaderText("Invalide input");
            alert.setContentText(errorMessage);
            alert.show();
        }else{
        
        Boolean pas=myServices.verifierpassword(mdp, username);
         if (myServices.chercherUtilisateurBylogin(username) && pas==true){
             
             System.out.println("hedha el role "+UserSession.getUser().getRoles());
              FXMLLoader fxmlLoader = new FXMLLoader();
             if (Objects.equals(UserSession.getUser().getRoles(), new String("a:1:{i:0;s:10:\"ROLE_ECOLE\";}")) || Objects.equals(UserSession.getUser().getRoles(), new String("a:0:{}"))) 
             {
                 fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
             }
             
           if (Objects.equals(UserSession.getUser().getRoles(), new String("a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}")))
             {
                 fxmlLoader = new FXMLLoader(getClass().getResource("homeEtudiant.fxml"));
             }
           
            if (Objects.equals(UserSession.getUser().getRoles(), new String("a:1:{i:0;s:15:\"ROLE_PROFESSEUR\";}")))
             {
                 fxmlLoader = new FXMLLoader(getClass().getResource("homeProf.fxml"));
             }
             
     
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
         else{
                          System.out.println("chay !!");

         }
        
    }
    }

    @FXML
    private void newaccount(ActionEvent event) {
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inscription1.fxml"));
        
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
