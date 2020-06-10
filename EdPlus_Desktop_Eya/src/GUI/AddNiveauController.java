/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.FormValidation;
import Services.ServiceNiveau;
import com.itextpdf.text.DocumentException;
import entities.Niveau;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
 * @author Eya
 */
public class AddNiveauController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfNivScolaire;
    @FXML
    private TextField tfUserID;
    @FXML
    private Button btnAjouter;
    private Label LBLNBP;
    @FXML
    private Label LBLWAE;
    @FXML
    private TableColumn<Niveau, Integer> id;
    @FXML
    private TableView<Niveau> idTabNiv;
    @FXML
    private TableColumn<Niveau, String> idNom;
    @FXML
    private TableColumn<Niveau, Integer> idNivScolaire;

    @FXML
    private TableColumn<Niveau, Integer> idUser_id;
    @FXML
    private Label LBLNOM;
    @FXML
    private Label LBLNS;
    @FXML
    private Button btnconfirmer;
    @FXML
    private Button idPDF;
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
            (btnconfirmer).setVisible(false);
            ServiceNiveau serN = new ServiceNiveau();
            ObservableList<Niveau> l;
            l = serN.readAll();

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            idNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idNivScolaire.setCellValueFactory(new PropertyValueFactory<>("nivScolaire"));
            idUser_id.setCellValueFactory(new PropertyValueFactory<>("user_id"));

            idTabNiv.setItems(recherche(l));

          
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());

        }

    }

    private SortedList<Niveau> recherche(ObservableList niveau) {
        FilteredList<Niveau> filteredData = new FilteredList<>(niveau, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(n -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (n.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(n.getNivScolaire()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(n.getUser_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Niveau> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTabNiv.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        return sortedData;
    }

    @FXML
    private void AjouterNiveau(ActionEvent event) {

        try {
            boolean testNom = FormValidation.textFieldNotEmpty(tfNom, LBLNOM, "Ce message ne doit contenir que des lettres");
            boolean testNS = FormValidation.textFieldTypeNumber(tfNivScolaire, LBLNS, "Veuilez saisir que des chiffres");

            if (testNom && testNS) {
                String nom = tfNom.getText();
                int nivScolaire = Integer.parseInt(tfNivScolaire.getText());
                int user_id = Integer.parseInt(tfUserID.getText());

                Niveau n = new Niveau(nom, nivScolaire, user_id);
                ServiceNiveau serN = new ServiceNiveau();

                serN.ajouter1(n);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajouter");
                alert.setHeaderText(null);
                alert.setContentText("Niveau ajouté !");
                alert.showAndWait();
                // idTabNiv.getItems().clear();
                ObservableList<Niveau> l;
                l = serN.readAll();
                idTabNiv.setItems(recherche(l));
            }

        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }

    @FXML
    private void ItemSelected(MouseEvent event) throws ParseException {
        Niveau n = new Niveau();
        idTabNiv.getSelectionModel().getSelectedItem();
        tfNom.setText(n.getNom());
        tfNivScolaire.setText(Integer.toString(n.getNivScolaire()));
        tfUserID.setText(Integer.toString(n.getUser_id()));

    }

    @FXML
    private void ModifierNiveau(ActionEvent event) {

        ServiceNiveau serN = new ServiceNiveau();
        Niveau n = idTabNiv.getSelectionModel().getSelectedItem();
        tfNom.setText(n.getNom());
        tfNivScolaire.setText(String.valueOf(n.getNivScolaire()));
        tfUserID.setText(String.valueOf(n.getUser_id()));
        int id = n.getId();
        (btnconfirmer).setVisible(true);

        btnconfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    boolean testNom = FormValidation.textFieldNotEmpty(tfNom, LBLNOM, "Veuilez saisir le nom du niveau");
                    boolean testNS = FormValidation.textFieldTypeNumber(tfNivScolaire, LBLNS, "Veuilez saisir que des chiffres");
                    if (testNS && testNom) {
                        String nom = tfNom.getText();
                        int nivScolaire = Integer.parseInt(tfNivScolaire.getText());
                        int user_id = Integer.parseInt(tfUserID.getText());

                        Niveau n = new Niveau(id, nom, nivScolaire, user_id);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("modifier");
                        alert.setHeaderText(null);
                        alert.setContentText("Niveau mis à jour !");
                        alert.showAndWait();

                        serN.update(n);

                        tfNom.clear();
                        tfNivScolaire.clear();
                        tfUserID.clear();
                        //idTabNiv.getItems().clear();
                        ObservableList<Niveau> l;
                        l = serN.readAll();
                        idTabNiv.setItems(recherche(l));
                        (btnconfirmer).setVisible(false);
                    }
                } catch (SQLException ex) {
                    System.out.println("erreur" + ex.getMessage());
                }

            }
        });
    }

    @FXML
    private void SupprimerNiveau(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous confirmer la suppression?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                ServiceNiveau serN = new ServiceNiveau();
                serN.delete(idTabNiv.getSelectionModel().getSelectedItem());
                //idTabNiv.getItems().clear();
                ObservableList<Niveau> l;
                l = serN.readAll();
                idTabNiv.setItems(recherche(l));
            } catch (SQLException ex) {
                System.out.println("erreur" + ex.getMessage());
            }
        }
    }

    @FXML
    private void PDF(ActionEvent event) {

        ServiceNiveau serN = new ServiceNiveau();
        try {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PDF");
                alert.setHeaderText(null);
                alert.setContentText("PDF avec succée");
                alert.showAndWait();
            serN.sendPDF();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(AddNiveauController.class.getName()).log(Level.SEVERE, null, ex);
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
