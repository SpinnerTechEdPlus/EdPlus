/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceNiveau;
import entities.Niveau;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class ValidationDeSuppressionController implements Initializable {
    private Connection con;
    private Statement ste;
    private ServiceNiveau serN = new ServiceNiveau();
 

    @FXML
    private Label attention;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmation(ActionEvent event) {
    }

    @FXML
    private void abandonner(ActionEvent event) {
        
       
    }
    
}
