/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author medam
 */
public class HomeProfController implements Initializable {

    @FXML
    private AnchorPane stage;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Label username;
    @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        username.setText("Name : "+UserSession.getUser().getUsername());
        email.setText("Email : "+UserSession.getUser().getEmail());
        
    }    

    @FXML
    private void GoHome(ActionEvent event) {
    }

    @FXML
    private void GoLogout(ActionEvent event) {
        closeStage();
        
    }
      private void closeStage() {
        ((Stage) stage.getScene().getWindow()).close();
    }
    
}
