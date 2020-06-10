/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.FormValidation;
import Services.ServiceMatiere;

import com.itextpdf.text.DocumentException;
import entities.Matiere;
import entities.Niveau;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Eya
 */
public class AddMatiereController implements Initializable {

    String s;

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField filterField;
    @FXML
    private TextField tfNom;
    private TextField tfUserID;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label LBLWAE;
    @FXML
    private Label LBLNOM;
    @FXML
    private Button btnconfirmer;
    @FXML
    private TableColumn<Matiere, Integer> id;
    @FXML
    private TableColumn<Matiere, String> idNom;
    @FXML
    private Button idPDF;
    @FXML
    private TableView<Matiere> idTabMatiere;
    private Label LBLN;
    @FXML
    private ImageView LogoImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            (btnconfirmer).setVisible(false);
            ServiceMatiere serM = new ServiceMatiere();
            ObservableList<Matiere> l;
            l = serM.readAll();
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            idNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idTabMatiere.setItems(recherche(l));
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }

    }

    @FXML
    private void Ajouter(ActionEvent event) {

        ServiceMatiere serM = new ServiceMatiere();
        try {
            boolean testNom = FormValidation.textFieldTypeAlphabet(tfNom, LBLNOM, "Ce message ne doit contenir que des lettres");
            if (testNom) {
                String nom = tfNom.getText();
                Matiere m = new Matiere(nom);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajouter");
                alert.setHeaderText(null);
                alert.setContentText("Matiere ajoutée");
                alert.showAndWait();
                serM.ajouter1(m);
                ObservableList<Matiere> l;
                l = serM.readAll();
                idTabMatiere.setItems(recherche(l));
            }
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }

    }

    private SortedList<Matiere> recherche(ObservableList matiere) {
        FilteredList<Matiere> filteredData = new FilteredList<>(matiere, b -> true);

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
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Matiere> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTabMatiere.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        return sortedData;
    }

    @FXML
    private void ItemSelected(MouseEvent event) {
        Matiere m = new Matiere();
        idTabMatiere.getSelectionModel().getSelectedItem();
        tfNom.setText(m.getNom());
        //tfIDniveau.setText(Integer.toString(m.getNiveau_id()));
        // idimage.setText(m.getPath());

    }

    @FXML
    private void ModifierNiveau(ActionEvent event) {
        ServiceMatiere serM = new ServiceMatiere();
        Matiere m = idTabMatiere.getSelectionModel().getSelectedItem();
        tfNom.setText(m.getNom());
        // idimage.setText(String.valueOf(m.getPath()));
        //  tfIDniveau.setText(String.valueOf(m.getNiveau_id()));
        int id = m.getId();
        (btnconfirmer).setVisible(true);

        btnconfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    boolean testNom = FormValidation.textFieldTypeAlphabet(tfNom, LBLNOM, "Ce message ne doit contenir que des lettres");
                    //   boolean testNS = FormValidation.textFieldTypeNumber(tfNivScolaire, LBLNS, "Veuilez saisir que des chiffres");
                    if (testNom) {
                        String nom = tfNom.getText();
                        // int niveau_id = Integer.parseInt(tfIDniveau.getText());
                        //  String nom_image = tfimage.getText();

                        Matiere m = new Matiere(id, nom);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("modifier");
                        alert.setHeaderText(null);
                        alert.setContentText("Matiere modifiée");
                        alert.showAndWait();
                        serM.update(m);

                        tfNom.clear();
                        //tfIDniveau.clear();
                        ObservableList<Matiere> l;
                        l = serM.readAll();
                        idTabMatiere.setItems(recherche(l));
                        (btnconfirmer).setVisible(false);
                    }
                } catch (SQLException ex) {
                    System.out.println("erreur" + ex.getMessage());
                }

            }
        });
    }

    @FXML
    private void SupprimerMatiere(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous confirmer la suppression?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            try {

                ServiceMatiere serM = new ServiceMatiere();
                serM.delete(idTabMatiere.getSelectionModel().getSelectedItem());
                ObservableList<Matiere> l;
                l = serM.readAll();
                idTabMatiere.setItems(recherche(l));
            } catch (SQLException ex) {
                System.out.println("erreur" + ex.getMessage());
            }
        }

    }

    @FXML
    private void PDF(ActionEvent event) {
        ServiceMatiere serM = new ServiceMatiere();
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF");
            alert.setHeaderText(null);
            alert.setContentText("PDF avec succée");
            alert.showAndWait();
            serM.sendPDF();
            
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(AddNiveauController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeStage() {
        ((Stage) pnlOverview1.getScene().getWindow()).close();
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

}
