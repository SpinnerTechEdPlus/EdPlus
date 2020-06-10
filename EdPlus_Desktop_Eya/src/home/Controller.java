/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class Controller implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnProf;
    @FXML
    private Button btnEtudiant;
    @FXML
    private Button BtnNiveau;
    @FXML
    private Button btnClasse;
    @FXML
    private Button btnMatieres;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlOverview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoHome(ActionEvent event) {
    }

    @FXML
    private void GoProf(ActionEvent event) {
    }

    @FXML
    private void GoEtudiant(ActionEvent event) {
    }

    @FXML
    private void GoNiveau(ActionEvent event) {
    }

    @FXML
    private void GoClasse(ActionEvent event) {
    }

    @FXML
    private void GoMatiere(ActionEvent event) {
    }

    @FXML
    private void GoLogout(ActionEvent event) {
    }
    
}
