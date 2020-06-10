/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class HomeController implements Initializable {

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
    @FXML
    private AnchorPane stage;

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
         try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
            Scene Scene = new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            closeStage();
        } catch (IOException ex) {
           System.out.println("erreur" + ex.getMessage());
        }
    }

    @FXML
    private void GoEtudiant(ActionEvent event) {
         try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AddUserEtudiant.fxml"));
            Scene Scene = new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            closeStage();
        } catch (IOException ex) {
           System.out.println("erreur" + ex.getMessage());
        }
    }

    @FXML
    private void GoNiveau(ActionEvent event) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AddNiveau.fxml"));
            Scene Scene = new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            closeStage();
        } catch (IOException ex) {
           System.out.println("erreur" + ex.getMessage());
        }
    }

    @FXML
    private void GoClasse(ActionEvent event) {
         try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AddClasse.fxml"));
            Scene Scene = new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            closeStage();
        } catch (IOException ex) {
           System.out.println("erreur" + ex.getMessage());
        }
    }

    @FXML
    private void GoMatiere(ActionEvent event) {
         try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AddMatiere.fxml"));
            Scene Scene = new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
            closeStage();
        } catch (IOException ex) {
           System.out.println("erreur" + ex.getMessage());
        }
    }

    @FXML
    private void GoLogout(ActionEvent event) {
        closeStage();
        
    }
      private void closeStage() {
        ((Stage) stage.getScene().getWindow()).close();
    }
}
