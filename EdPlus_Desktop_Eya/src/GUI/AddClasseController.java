/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.FormValidation;
import Services.ServiceClasse;
import Services.ServiceMatiere;
import Services.ServiceNiveau;
import com.itextpdf.text.DocumentException;
import entities.Classe;
import entities.Matiere;
import entities.Niveau;
import java.io.FileNotFoundException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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
public class AddClasseController implements Initializable {

    @FXML
    private Pane pnlOverview1;
    @FXML
    private TextField filterField;
    private TextField tfIDniveau;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label LBLNiveau;
    @FXML
    private Button btnconfirmer;
    @FXML
    private Button idPDF;
    @FXML
    private TextField tfNum;
    @FXML
    private TextField tfNbrE;
    @FXML
    private TableView<Classe> idTabClasse;
    @FXML
    private TableColumn<Classe, Integer> idNum;
    @FXML
    private TableColumn<Classe, Integer> idNbrE;
    @FXML
    private Label LBLNum;
    @FXML
    private Label LBNbrE;
    @FXML
    private ImageView LogoImage;
    @FXML
    private TableColumn<Classe, String> idNom;
    @FXML
    private TextField tfNom;
    @FXML
    private Label LBLNom;
    @FXML
    private ChoiceBox<String> choiceBox;

    ServiceClasse serC = new ServiceClasse();
    ServiceNiveau serN = new ServiceNiveau();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            List<String> names = serN.readNiveaName();
            choiceBox.getItems().addAll(names);

            (btnconfirmer).setVisible(false);
            ObservableList<Classe> l;
            l = serC.readAll();
            //  id.setCellValueFactory(new PropertyValueFactory<>("id"));
            //idNivClasse.setCellValueFactory(new PropertyValueFactory<>("niveau_id"));
            idNum.setCellValueFactory(new PropertyValueFactory<>("num"));
            idNbrE.setCellValueFactory(new PropertyValueFactory<>("nbrEtudiant"));
            idNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

            idTabClasse.setItems(recherche(l));

        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }

    }

    private SortedList<Classe> recherche(ObservableList classe) {
        FilteredList<Classe> filteredData = new FilteredList<>(classe, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(c -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (c.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(c.getNbrEtudiant()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(c.getNum()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Classe> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(idTabClasse.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        return sortedData;
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        ServiceClasse serC = new ServiceClasse();

        try {

            String choice = choiceBox.getValue();

            //get classe id
            int niveauId = serN.getNiveauId(choice);

            boolean testnum = FormValidation.textFieldTypeNumber(tfNum, LBLNum, "Veuilez saisir que des chiffres");
            boolean testnbr = FormValidation.textFieldTypeNumber(tfNbrE, LBNbrE, "Veuilez saisir que des chiffres");
            // boolean testnombre = FormValidation.textFieldTypeNumber(tfIDniveau, LBLNiveau, "Veuilez saisir que des chiffres");
            boolean testEmpty = FormValidation.textFieldNotEmpty(tfNom, LBLNom, "Veuilez saisir un nom d'une classe");
            if (testnbr && /*testnombre && */ testnum & testEmpty) {

                //int niveau_id = Integer.parseInt(tfIDniveau.getText());
                int num = Integer.parseInt(tfNum.getText());
                int nbrEtudiant = Integer.parseInt(tfNbrE.getText());
                String nom = tfNom.getText();
                Classe c = new Classe(niveauId, num, nbrEtudiant, nom);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajouter");
                alert.setHeaderText(null);
                alert.setContentText("Classe ajoutée");
                alert.showAndWait();
                serC.ajouter1(c);
//                idTabClasse.getItems().clear();
                //   idTabClasse.setItems(serC.readAll());
                //   idTabClasse.setItems(recherche(l));
                ObservableList<Classe> l;
                l = serC.readAll();
                idTabClasse.setItems(recherche(l));
            }
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }

    @FXML
    private void ItemSelected(MouseEvent event) {
        Classe c = new Classe();
        idTabClasse.getSelectionModel().getSelectedItem();
//        tfIDniveau.setText(Integer.toString(c.getNiveau_id()));
        tfNum.setText(Integer.toString(c.getNum()));
        tfNbrE.setText(Integer.toString(c.getNbrEtudiant()));
    }

    @FXML
    private void ModifierNiveau(ActionEvent event) {
        ServiceClasse serC = new ServiceClasse();
        Classe c = idTabClasse.getSelectionModel().getSelectedItem();

        // tfIDniveau.setText(String.valueOf(c.getNiveau_id()));
        tfNum.setText(String.valueOf(c.getNum()));
        tfNbrE.setText(String.valueOf(c.getNbrEtudiant()));
        tfNom.setText(c.getNom());
        int id = c.getId();
        (btnconfirmer).setVisible(true);

        btnconfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    boolean testnum = FormValidation.textFieldTypeNumber(tfNum, LBLNum, "Veuilez saisir que des chiffres");
                    boolean testnbr = FormValidation.textFieldTypeNumber(tfNbrE, LBNbrE, "Veuilez saisir que des chiffres");
                    //    boolean testnombre = FormValidation.textFieldTypeNumber(tfIDniveau, LBLNiveau, "Veuilez saisir que des chiffres");
                    if (testnbr && testnum) {

                        //   int niveau_id = Integer.parseInt(tfIDniveau.getText());
                        int num = Integer.parseInt(tfNum.getText());
                        int nbrEtudiant = Integer.parseInt(tfNbrE.getText());
                        String nom = tfNom.getText();
                        
                        String choice = choiceBox.getValue();

                        //get classe id
                        int niveauId = serN.getNiveauId(choice);
                        Classe c = new Classe( id,niveauId, num, nbrEtudiant, nom);

                        serC.update(c);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("modifier");
                        alert.setHeaderText(null);
                        alert.setContentText("Classe modifiée");
                        alert.showAndWait();
                        
                        tfNum.clear();
                        tfNom.clear();
                        //  tfIDniveau.clear();
                        tfNbrE.clear();
                        ObservableList<Classe> l;
                        l = serC.readAll();
                        idTabClasse.setItems(recherche(l));
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
                ServiceClasse serC = new ServiceClasse();
                serC.delete(idTabClasse.getSelectionModel().getSelectedItem());
                ObservableList<Classe> l;
                l = serC.readAll();
                idTabClasse.setItems(recherche(l));
            } catch (SQLException ex) {
                System.out.println("erreur" + ex.getMessage());
            }
        }

    }

    @FXML
    private void PDF(ActionEvent event) {
        ServiceClasse serC = new ServiceClasse();
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF");
            alert.setHeaderText(null);
            alert.setContentText("PDF avec succée");
            alert.showAndWait();
            serC.sendPDF();
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println("erreur" + ex.getMessage());
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
