/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.Password;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author medam
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfEnable;
    @FXML
    private TextField tfRole;
    @FXML
    private Button btnValider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveUser(ActionEvent event)  {
        try {
            String username=tfUsername.getText();
            String email=tfEmail.getText();
            String password=tfPassword.getText();
            
            String passCrypt = Password.hashPassword(tfPassword.getText().trim());

            int enable=1;
            String role="a:1:{i:0;s:10:\"ROLE_ECOLE\";}";
            
            User user=new User(username, username, email, email, passCrypt, enable, role, username);
            ServiceUser SU=new ServiceUser();
            SU.ajouterEcole(user);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
