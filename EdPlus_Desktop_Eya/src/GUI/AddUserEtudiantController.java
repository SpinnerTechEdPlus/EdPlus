/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.FormValidation;
import Services.Javamail;
import Services.Password;
import Services.ServiceUser;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author medam
 */
public class AddUserEtudiantController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private Pane EtufInfo;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label LBPW;
    @FXML
    private Label LBEmail;
    @FXML
    private Button Confirmer;
    @FXML
    private TableView<User> idTableEtu;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> idUsername;
    @FXML
    private TableColumn<User, String> idEmail;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ChoiceBox<String> sortChoice;
    @FXML
    private TextField filterField;
    @FXML
    private ImageView LogoImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            (Confirmer).setVisible(false);
            ServiceUser SU = new ServiceUser();
            ObservableList<User> user;
            user = SU.readAll("a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}");

            List<String> names = SU.readClasseName();
            choiceBox.getItems().addAll(names);
            sortChoice.getItems().addAll(names);

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            idUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            idEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            // idUser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));

            idTableEtu.setItems(recherche(user));

        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());

        }

    }

    private SortedList<User> recherche(ObservableList user) {
        FilteredList<User> filteredData = new FilteredList<>(user, b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user_ -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (user_.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (user_.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches email.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<User> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTableEtu.comparatorProperty());
        return sortedData;
    }

    @FXML
    private void AjouterEtudiant(ActionEvent event) {
        try {
            ServiceUser SU = new ServiceUser();

            String username = tfUsername.getText();
            String email = tfEmail.getText();

            //String password=tfPassword.getText();
            String passCrypt = Password.hashPassword(tfPassword.getText().trim());

            int enable = 1;
            String role = "a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}";

            //names mta3 classes
            String choice = choiceBox.getValue();

            //get classe id
            int classeId = SU.getClasseId(choice);

            boolean testEmail = FormValidation.textFieldTypeEmail(tfEmail, LBEmail, "Email invalide");
            boolean testPW = FormValidation.textFieldNotEmpty(tfPassword, LBPW, "Mot de passe invalide");
            if (testEmail && testPW) {

                User user = new User(username, username, email, email, passCrypt, enable, role, classeId);
                SU.ajouterEtu(user);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajouter");
                alert.setHeaderText(null);
                alert.setContentText("Etudiant ajouté");
                alert.showAndWait();

                Javamail.sendMail(email, "Student account created", "Your username is : " + username + "\nYour password is : " + tfPassword.getText());

                tfUsername.clear();
                tfPassword.clear();
                tfEmail.clear();
                //   idTableEtu.getItems().clear();
                ObservableList<User> userList;
                userList = SU.readAll("a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}");

                idTableEtu.setItems(recherche(userList));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ItemSelected(MouseEvent event) {
    }

    @FXML
    private void ModifierEtudiant(ActionEvent event) {
        ServiceUser SU = new ServiceUser();
        User u = idTableEtu.getSelectionModel().getSelectedItem();
        tfUsername.setText(u.getUsername());
        tfEmail.setText(u.getEmail());
        int id = u.getId();

        //nrodou button visible
        (Confirmer).setVisible(true);

        Confirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    boolean testEmail = FormValidation.textFieldTypeEmail(tfEmail, LBEmail, "Email invalide");
                    boolean testPW = FormValidation.textFieldNotEmpty(tfPassword, LBPW, "Mot de passe invalide");
                    if (testEmail && testPW) {
                        String username = tfUsername.getText();
                        String email = tfEmail.getText();
                        //String password=tfPassword.getText();

                        String passCrypt = Password.hashPassword(tfPassword.getText().trim());

                        int enable = 1;
                        String role = "a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}";
                        String choice = choiceBox.getValue();

                        //get matiere id
                        int classeId = SU.getClasseId(choice);
                        System.out.println(classeId);

                        User user = new User(id, username, username, email, email, passCrypt, enable, role, classeId);
                        System.out.println(user);

                        SU.updateEtu(user);
                        Javamail.sendMail(email, "Student account Updated", "Your username is : " + username + "\nYour password is : " + tfPassword.getText());
                        tfUsername.clear();
                        tfPassword.clear();
                        tfEmail.clear();
                        // idTableEtu.getItems().clear();
                        ObservableList<User> userList;
                        userList = SU.readAll("a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}");

                        idTableEtu.setItems(recherche(userList));
                        (Confirmer).setVisible(false);

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("update");
                        alert.setHeaderText(null);
                        alert.setContentText("Etudiant mis à jour !");
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    @FXML
    private void SupprimerEtudiant(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous confirmer la suppression?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            try {
                ServiceUser SU = new ServiceUser();
                SU.delete(idTableEtu.getSelectionModel().getSelectedItem());
                //idTableEtu.getItems().clear();
                ObservableList<User> userList;
                userList = SU.readAll("a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}");

                idTableEtu.setItems(recherche(userList));
            } catch (SQLException ex) {
                System.out.println("erreur" + ex.getMessage());
            }
        }

    }

    @FXML
    private void SortByClasse(ActionEvent event) {
        try {
            ServiceUser SU = new ServiceUser();
            String choice = sortChoice.getValue();

            //get classe id
            int classeId = SU.getClasseId(choice);
            // idTableEtu.getItems().clear();
            ObservableList<User> userList;
            userList = SU.readByClasse("a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}", classeId);

            idTableEtu.setItems(recherche(userList));

        } catch (SQLException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Reset(ActionEvent event) {
        try {
            ServiceUser SU = new ServiceUser();

            ObservableList<User> userList;
            userList = SU.readAll("a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}");

            idTableEtu.setItems(recherche(userList));
        } catch (SQLException ex) {
            Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void GoHome(MouseEvent event) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene Scene = new Scene(root);
            primaryStage.setScene(Scene);
            primaryStage.show();
             closeStage();
            
        } catch (IOException ex) {
           System.out.println("erreur" + ex.getMessage());
        }
    }
    
       private void closeStage() {
        ((Stage) pnlOverview1.getScene().getWindow()).close();
    }

}
