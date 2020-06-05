/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edplusdesktop;

import edplusentities.Examen;
import edplusentities.Note;
import edplusentities.Seance;
import edplusservices.ExamenServices;
import edplusservices.NoteServices;
import edplusservices.SeanceServices;
import edplusutils.Mailer;
import edplusutils.MyConnection;
import edplusutils.TimeSpinner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalTimeStringConverter;

/**
 *
 * @author BAYCII
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane mainScreen;
    @FXML
    private TabPane mainTab;
    @FXML
    private Tab seancesTab;
    @FXML
    private AnchorPane seancesScreen;
    @FXML
    private Tab examenTab;
    @FXML
    private AnchorPane examenScreen;
    @FXML
    private Tab notesTab;
    @FXML
    private AnchorPane notesScreen;
    private final ExamenServices eS = new ExamenServices();
    private final NoteServices nS = new NoteServices();
    private final SeanceServices sS = new SeanceServices();
    @FXML
    private TableView<Examen> examensContainer;
    @FXML
    private TableColumn examenIdColumn;
    @FXML
    private TableColumn examenSalleColumn;
    @FXML
    private TableColumn examenMatiereColumn;
    @FXML
    private TableColumn examenClasseColumn;
    @FXML
    private TableColumn examenHoraireColumn;
    @FXML
    private TableColumn examenSemestreColumn;
    @FXML
    private TableView<Seance> seancesContainer;
    @FXML
    private TableColumn seanceClasseIdColumn;
    @FXML
    private TableColumn seanceProfesseurIdColumn;
    @FXML
    private TableColumn seanceMatiereIdColumn;
    @FXML
    private TableColumn seanceSalleIdColumn;
    @FXML
    private TableColumn seanceIdColumn;
    @FXML
    private TableColumn seanceSalleColumn;
    @FXML
    private TableColumn seanceMatiereColumn;
    @FXML
    private TableColumn seanceProfesseurColumn;
    @FXML
    private TableColumn seanceClasseColumn;
    @FXML
    private TableColumn seanceJourColumn;
    @FXML
    private TableColumn seanceDebutColumn;
    @FXML
    private TableColumn seanceFinColumn;
    @FXML
    private TableColumn examenClasseIdColumn;
    @FXML
    private TableColumn examenMatiereIdColumn;
    @FXML
    private TableColumn examenSalleIdColumn;
    @FXML
    private TableView<Note> notesContainer;
    @FXML
    private TableColumn noteEtudiantIdColumn;
    @FXML
    private TableColumn noteExamenIdColumn;
    @FXML
    private TableColumn noteIdColumn;
    @FXML
    private TableColumn noteEtudiantColumn;
    @FXML
    private TableColumn noteExamenColumn;
    @FXML
    private TableColumn noteNoteColumn;
    @FXML
    private Button seanceDeleteButton;
    @FXML
    private Button examenDeleteButton;
    @FXML
    private Button noteDeleteButton;
    @FXML
    private Button shutDown1;
    @FXML
    private Button shutDown2;
    @FXML
    private Button shutDown3;
    @FXML
    private ComboBox<String> seanceSalleComboBox;
    @FXML
    private ComboBox<String> seanceMatiereComboBox;
    @FXML
    private ComboBox<String> seanceProfesseurComboBox;
    @FXML
    private ComboBox<String> seanceClasseComboBox;
    @FXML
    private Button seanceUpdateButton;
    @FXML
    private TextField seanceIdTextField;
    @FXML
    private DatePicker seanceJourDatePicker;

    @FXML
    private TimeSpinner seanceDebutSpinner ;
    @FXML
    private TimeSpinner seanceFinSpinner ;

    @FXML
    private Button addNewSeanceDecision;
    @FXML
    private ComboBox<String> examenSalleComboBox;
    @FXML
    private ComboBox<String> examenMatiereComboBox;
    @FXML
    private ComboBox<String> examenClasseComboBox;
    @FXML
    private TimeSpinner examenTempsSpinner;
    @FXML
    private Button examenUpdateButton;
    @FXML
    private Button addNewExamenDecision;
    @FXML
    private TextField examenIdTextField;
    @FXML
    private Button noteUpdateButton;
    @FXML
    private Button addNewNoteDecision;
    @FXML
    private Spinner<Double> noteNoteSpinner;
    @FXML
    private TextField noteIdTextField;
    @FXML
    private ComboBox<String> noteExamenComboBox;
    @FXML
    private ComboBox<String> noteEtudiantComboBox;
    @FXML
    private DatePicker examenJourDatePicker;
    @FXML
    private ComboBox<String> examenSemestreComboBox;
    @FXML
    private TextField searchSeancesTextField;
    @FXML
    private TextField searchExamensTextField;
    @FXML
    private TextField searchNotesTextField;
    @FXML
    private Button gererSeanceButton;
    @FXML
    private Button gererExamsButton;
    @FXML
    private Button gererNotesButtons;
    @FXML
    private Button accueilButton;
    @FXML
    private Button releveNoteButton;
    @FXML
    private Button statsButton;
    @FXML
    private Tab releveTab;
    @FXML
    private Button getReleveButton;
    @FXML
    private ComboBox<String> releveEtudiantComboBox;
    @FXML
    private Label labeletudiant;
    @FXML
    private Tab statsTab;
    @FXML
    private BarChart barc;
    @FXML

    private Button updateBarChart;
    @FXML
    private Tab accueilTab;
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView mainImage;

    @FXML
    private void shutDown() {
        Platform.exit();
    }

    private void initializeExamensContainer() {
        examenIdColumn.setCellValueFactory(new PropertyValueFactory<Examen, Integer>("id"));
        examenSalleIdColumn.setCellValueFactory(new PropertyValueFactory<Examen, Integer>("salle"));
        examenSalleColumn.setCellValueFactory(new PropertyValueFactory<Examen, String>("salleName"));
        examenMatiereIdColumn.setCellValueFactory(new PropertyValueFactory<Examen, Integer>("matiere"));
        examenMatiereColumn.setCellValueFactory(new PropertyValueFactory<Examen, String>("matiereName"));
        examenClasseIdColumn.setCellValueFactory(new PropertyValueFactory<Examen, Integer>("classe"));
        examenClasseColumn.setCellValueFactory(new PropertyValueFactory<Examen, String>("classeName"));
        examenHoraireColumn.setCellValueFactory(new PropertyValueFactory<Examen, LocalDateTime>("horaire"));
        examenSemestreColumn.setCellValueFactory(new PropertyValueFactory<Examen, String>("semestre"));
    }

    private void initializeSeancesContainer() {
        seanceIdColumn.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("id"));
        seanceSalleColumn.setCellValueFactory(new PropertyValueFactory<Seance, String>("salleName"));
        seanceSalleIdColumn.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("salle"));
        seanceMatiereColumn.setCellValueFactory(new PropertyValueFactory<Seance, String>("matiereName"));
        seanceMatiereIdColumn.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("matiere"));
        seanceProfesseurColumn.setCellValueFactory(new PropertyValueFactory<Seance, String>("professeurName"));
        seanceProfesseurIdColumn.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("professeur"));
        seanceClasseColumn.setCellValueFactory(new PropertyValueFactory<Seance, String>("classeName"));
        seanceClasseIdColumn.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("classe"));
        seanceJourColumn.setCellValueFactory(new PropertyValueFactory<Seance, LocalDate>("jour"));
        seanceDebutColumn.setCellValueFactory(new PropertyValueFactory<Seance, LocalTime>("debut"));
        seanceFinColumn.setCellValueFactory(new PropertyValueFactory<Seance, LocalTime>("fin"));

    }

    private void initializeNotesContainer() {
        noteIdColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("id"));
        noteEtudiantColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("etudiantName"));
        noteEtudiantIdColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("etudiant"));
        noteExamenIdColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("examen"));
        noteExamenColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("examenName"));
        noteNoteColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("note"));

    }

    private void refreshNotesContainer() {
        ObservableList<Note> notes = FXCollections.observableArrayList(nS.list());
        notesContainer.getItems().clear();
        notesContainer.setItems(notes);
    }

    private void refreshExamensContainer() {
        ObservableList<Examen> exams = FXCollections.observableArrayList(eS.list());
        examensContainer.getItems().clear();
        examensContainer.setItems(exams);
    }

    private void refreshSeancesContainer() {
        ObservableList<Seance> seances = FXCollections.observableArrayList(sS.list());
        seancesContainer.getItems().clear();
        seancesContainer.setItems(seances);

    }

    private void refreshComboBoxes() {

        seanceSalleComboBox.getItems().clear();
        seanceMatiereComboBox.getItems().clear();
        seanceProfesseurComboBox.getItems().clear();
        seanceClasseComboBox.getItems().clear();

        examenSalleComboBox.getItems().clear();
        examenMatiereComboBox.getItems().clear();
        examenClasseComboBox.getItems().clear();

        noteEtudiantComboBox.getItems().clear();
        noteExamenComboBox.getItems().clear();

        releveEtudiantComboBox.getItems().clear();

        for (String salle : eS.getAllSalles()) {
            seanceSalleComboBox.getItems().add(salle);
            examenSalleComboBox.getItems().add(salle);
        }
        for (String matiere : eS.getAllMatieres()) {
            seanceMatiereComboBox.getItems().add(matiere);
            examenMatiereComboBox.getItems().add(matiere);
        }
        for (String professeur : eS.getAllProfesseurs()) {
            seanceProfesseurComboBox.getItems().add(professeur);
        }
        for (String classe : eS.getAllClasses()) {
            seanceClasseComboBox.getItems().add(classe);
            examenClasseComboBox.getItems().add(classe);
        }

        for (String etudiant : eS.getAllEtudiants()) {
            noteEtudiantComboBox.getItems().add(etudiant);
            releveEtudiantComboBox.getItems().add(etudiant);
        }

        for (String examen : eS.getAllExamens()) {
            noteExamenComboBox.getItems().add(examen);
        }

        examenSemestreComboBox.getItems().clear();
        examenSemestreComboBox.getItems().add("1");
        examenSemestreComboBox.getItems().add("2");
        examenSemestreComboBox.setValue("1");

        seanceSalleComboBox.setValue(seanceSalleComboBox.getItems().get(0));
        seanceMatiereComboBox.setValue(seanceMatiereComboBox.getItems().get(0));
        seanceProfesseurComboBox.setValue(seanceProfesseurComboBox.getItems().get(0));
        seanceClasseComboBox.setValue(seanceClasseComboBox.getItems().get(0));

        examenSalleComboBox.setValue(examenSalleComboBox.getItems().get(0));
        examenMatiereComboBox.setValue(examenMatiereComboBox.getItems().get(0));
        examenClasseComboBox.setValue(examenClasseComboBox.getItems().get(0));

        noteEtudiantComboBox.setValue(noteEtudiantComboBox.getItems().get(0));
        if (noteExamenComboBox.getItems().size() > 0) {
            noteExamenComboBox.setValue(noteExamenComboBox.getItems().get(0));
        }

        releveEtudiantComboBox.setValue(releveEtudiantComboBox.getItems().get(0));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* seanceDeleteButton.setDisable(true);
     noteDeleteButton.setDisable(true);
     examenDeleteButton.setDisable(true);
     refreshSalleInput();
         */
        noteNoteSpinner.getEditor().setText("10.00");
        initializeDatePickers();
        initializeTimeSpinners();

        seanceIdTextField.setText("0");
        noteIdTextField.setText("0");
        examenIdTextField.setText("0");

        initializeExamensContainer();
        initializeSeancesContainer();
        initializeNotesContainer();

        refreshComboBoxes();
        refreshExamensContainer();
        refreshNotesContainer();
        refreshSeancesContainer();

    }

    @FXML
    private void deleteSelectedSeance(MouseEvent event) {
        if (!seancesContainer.getSelectionModel().isEmpty()) {

            Seance s = seancesContainer.getSelectionModel().getSelectedItem();
            if (alertConfirmation("Voulez vous supprimer la seance de " + s.getProfesseurName())) {
                sS.remove(s.getId());
                refreshSeancesContainer();
            }
        }
    }

    @FXML
    private void deleteSelectedExamen(MouseEvent event) {
        if (!examensContainer.getSelectionModel().isEmpty()) {

            Examen e = examensContainer.getSelectionModel().getSelectedItem();

            if (alertConfirmation("Voulez vous supprimer l'examen de " + e.getMatiereName())) {
                eS.remove(e.getId());
                refreshExamensContainer();
                refreshComboBoxes();
            }
        }
    }

    @FXML
    private void deleteSelectedNote(MouseEvent event) {

        if (!notesContainer.getSelectionModel().isEmpty()) {

            Note n = notesContainer.getSelectionModel().getSelectedItem();

            if (alertConfirmation("Voulez vous supprimer la note de " + n.getEtudiantName())) {
                nS.remove(n.getId());
                refreshNotesContainer();

            }
        }
    }

    @FXML
    private void updateSeances(MouseEvent event) {
        int id = Integer.parseInt(seanceIdTextField.getText());
        int salle = eS.getComboBoxesConversion("salle", seanceSalleComboBox.getValue());
        int matiere = eS.getComboBoxesConversion("matiere", seanceMatiereComboBox.getValue());
        int professeur = eS.getComboBoxesConversion("professeur", seanceProfesseurComboBox.getValue());
        int classe = eS.getComboBoxesConversion("classe", seanceClasseComboBox.getValue());
        LocalDate jour = seanceJourDatePicker.getValue();
        LocalTime debut = seanceDebutSpinner.getValue();
        LocalTime fin = seanceFinSpinner.getValue();

        if (fin.compareTo(debut) <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Debut de seance et Fin de seance Erreur");
            alert.setResizable(true);
            alert.setWidth(1366);
            alert.setHeight(768);
            alert.setHeaderText("debut > fin");
            alert.setContentText("Veuillez verifier vos choix !");
            alert.showAndWait();
        } else {
            Seance s = new Seance(id, salle, matiere, professeur, classe, jour, debut, fin);
            if (verifySeanceBeforeInsertion(s)) {
                if (id == 0) {
                    sS.add(s);
                    alertSucces("Séance ajoutée");
                } else {

                    sS.edit(s);

                    alertSucces("Séance modifiée");
                }
                refreshSeancesContainer();
            }
        }
    }

    @FXML
    private void seanceSelectionDetected(MouseEvent event) {

        if (!seancesContainer.getSelectionModel().isEmpty()) {
            seanceUpdateButton.setText("Modifer");
            Seance s = seancesContainer.getSelectionModel().getSelectedItem();
            seanceSalleComboBox.setValue(eS.doComboBoxesConversion("salle", s.getSalle()));
            seanceMatiereComboBox.setValue(eS.doComboBoxesConversion("matiere", s.getMatiere()));
            seanceProfesseurComboBox.setValue(eS.doComboBoxesConversion("professeur", s.getProfesseur()));
            seanceClasseComboBox.setValue(eS.doComboBoxesConversion("classe", s.getClasse()));
            seanceJourDatePicker.setValue(s.getJour());
            seanceIdTextField.setText("" + s.getId());
            seanceDebutSpinner.getEditor().setText("" + s.getDebut());
            seanceFinSpinner.getEditor().setText("" + s.getFin()); 
            

        } else {
            seanceUpdateButton.setText("Ajouter");
            seanceSalleComboBox.setValue("");
            seanceMatiereComboBox.setValue("");
            seanceProfesseurComboBox.setValue("");
            seanceClasseComboBox.setValue("");
            seanceJourDatePicker.setValue(LocalDate.now());
            seanceIdTextField.setText("0");
            seanceDebutSpinner.getEditor().setText("" + LocalTime.of(8,0));
            seanceFinSpinner.getEditor().setText("" + LocalTime.of(10,45));
        }

        
        
        
    }

    @FXML
    private void addNewSeanceDecisionMaked(MouseEvent event) {

        refreshComboBoxes();
        seanceUpdateButton.setText("Ajouter");
        seanceJourDatePicker.setValue(LocalDate.now());
        seanceIdTextField.setText("0");

    }

    private void initializeTimeSpinners() {

        noteNoteSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 20.0, 10.0, 0.25));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        seanceFinSpinner.valueProperty().addListener((obs, oldTime, newTime)
                -> System.out.println(formatter.format(newTime)));

        seanceDebutSpinner.valueProperty().addListener((obs, oldTime, newTime)
                -> System.out.println(formatter.format(newTime)));

        examenTempsSpinner.valueProperty().addListener((obs, oldTime, newTime)
                -> System.out.println(formatter.format(newTime)));

    }

    private void initializeDatePickers() {

        seanceJourDatePicker.setValue(LocalDate.now());
        examenJourDatePicker.setValue(LocalDate.now());

    }

    @FXML
    private void updateExamens(MouseEvent event) {

        int id = Integer.parseInt(examenIdTextField.getText());
        int salle = eS.getComboBoxesConversion("salle", examenSalleComboBox.getValue());
        int matiere = eS.getComboBoxesConversion("matiere", examenMatiereComboBox.getValue());
        int classe = eS.getComboBoxesConversion("classe", examenClasseComboBox.getValue());
        LocalDate jour = examenJourDatePicker.getValue();
        System.out.println("Jour : " + jour);
        LocalTime temps = examenTempsSpinner.getValue();
        System.out.println("Temps : ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime horaire = LocalDateTime.parse(jour + " " + temps.toString().substring(0, 8), formatter);
        String semestre = examenSemestreComboBox.getValue();
        Examen e = new Examen(id, salle, matiere, classe, horaire, semestre);

        if (verifyExamenBeforeInsertion(e)) {
            if (id == 0) {

                eS.add(e);

                alertSucces("Examen ajouté");
            } else {

                eS.edit(e);

                alertSucces("Examen modifié");
            }
            refreshExamensContainer();
            refreshComboBoxes();
        }
    }

    @FXML
    private void addNewExamenDecisionMaked(MouseEvent event) {

        refreshComboBoxes();
        examenUpdateButton.setText("Ajouter");
        examenJourDatePicker.setValue(LocalDate.now());
        examenIdTextField.setText("0");

    }

    @FXML
    private void seanceSelectionDetectedKey(KeyEvent event) {

        if (!seancesContainer.getSelectionModel().isEmpty()) {
            seanceUpdateButton.setText("Modifer");
            Seance s = seancesContainer.getSelectionModel().getSelectedItem();
            seanceSalleComboBox.setValue(eS.doComboBoxesConversion("salle", s.getSalle()));
            seanceMatiereComboBox.setValue(eS.doComboBoxesConversion("matiere", s.getMatiere()));
            seanceProfesseurComboBox.setValue(eS.doComboBoxesConversion("professeur", s.getProfesseur()));
            seanceClasseComboBox.setValue(eS.doComboBoxesConversion("classe", s.getClasse()));
            seanceJourDatePicker.setValue(s.getJour());
            seanceIdTextField.setText("" + s.getId());
            seanceDebutSpinner.getEditor().setText("" + s.getDebut());
            seanceFinSpinner.getEditor().setText("" + s.getFin());

        } else {
            seanceUpdateButton.setText("Ajouter");
            seanceSalleComboBox.setValue("");
            seanceMatiereComboBox.setValue("");
            seanceProfesseurComboBox.setValue("");
            seanceClasseComboBox.setValue("");
            seanceJourDatePicker.setValue(LocalDate.now());
            seanceIdTextField.setText("0");
            seanceDebutSpinner.getEditor().setText("" + LocalTime.of(8, 00));
            seanceFinSpinner.getEditor().setText("" + LocalTime.of(10,45));
        }

    }

    @FXML
    private void examenSelectionDetectedKey(KeyEvent event) {
        if (!examensContainer.getSelectionModel().isEmpty()) {
            examenUpdateButton.setText("Modifer");
            Examen e = examensContainer.getSelectionModel().getSelectedItem();
            examenSalleComboBox.setValue(eS.doComboBoxesConversion("salle", e.getSalle()));
            examenMatiereComboBox.setValue(eS.doComboBoxesConversion("matiere", e.getMatiere()));
            examenClasseComboBox.setValue(eS.doComboBoxesConversion("classe", e.getClasse()));
            examenJourDatePicker.setValue(e.getHoraire().toLocalDate());
            examenTempsSpinner.getEditor().setText("" + e.getHoraire().toLocalTime());
            examenIdTextField.setText("" + e.getId());

        } else {
            examenUpdateButton.setText("Modifer");
            examenSalleComboBox.setValue("");
            examenMatiereComboBox.setValue("");
            examenClasseComboBox.setValue("");
            examenJourDatePicker.setValue(LocalDate.now());
            examenTempsSpinner.getEditor().setText("" + LocalTime.of(8,15,01));
            examenIdTextField.setText("0");
        }

    }

    @FXML
    private void examenSelectionDetected(MouseEvent event) {
        if (!examensContainer.getSelectionModel().isEmpty()) {
            examenUpdateButton.setText("Modifer");
            Examen e = examensContainer.getSelectionModel().getSelectedItem();
            examenSalleComboBox.setValue(eS.doComboBoxesConversion("salle", e.getSalle()));
            examenMatiereComboBox.setValue(eS.doComboBoxesConversion("matiere", e.getMatiere()));
            examenClasseComboBox.setValue(eS.doComboBoxesConversion("classe", e.getClasse()));
            examenJourDatePicker.setValue(e.getHoraire().toLocalDate());
            examenTempsSpinner.getEditor().setText("" + e.getHoraire().toLocalTime());
            examenIdTextField.setText("" + e.getId());

        } else {
            examenUpdateButton.setText("Modifer");
            examenSalleComboBox.setValue("");
            examenMatiereComboBox.setValue("");
            examenClasseComboBox.setValue("");
            examenJourDatePicker.setValue(LocalDate.now());
            examenTempsSpinner.getEditor().setText("" + LocalTime.now());
            examenIdTextField.setText("0");
        }

    }

    @FXML
    private void noteSelectionDetectedKey(KeyEvent event) {

        if (!notesContainer.getSelectionModel().isEmpty()) {
            noteUpdateButton.setText("Modifer");
            Note n = notesContainer.getSelectionModel().getSelectedItem();
            noteExamenComboBox.setValue(eS.doExamenComboBoxesConversion(n.getExamen()));
            noteEtudiantComboBox.setValue(eS.doComboBoxesConversion("etudiant", n.getEtudiant()));
            noteNoteSpinner.getEditor().setText("" + n.getNote());
            noteIdTextField.setText("" + n.getId());

        } else {
            noteUpdateButton.setText("Ajouter");
            noteExamenComboBox.setValue("");
            noteEtudiantComboBox.setValue("");
            noteNoteSpinner.getEditor().setText("10.00");
            noteIdTextField.setText("0");
        }
    }

    @FXML
    private void noteSelectionDetected(MouseEvent event) {

        if (!notesContainer.getSelectionModel().isEmpty()) {
            noteUpdateButton.setText("Modifer");
            Note n = notesContainer.getSelectionModel().getSelectedItem();
            noteExamenComboBox.setValue(eS.doExamenComboBoxesConversion(n.getExamen()));
            noteEtudiantComboBox.setValue(eS.doComboBoxesConversion("etudiant", n.getEtudiant()));
            noteNoteSpinner.getEditor().setText("" + n.getNote());
            noteIdTextField.setText("" + n.getId());

        } else {
            noteUpdateButton.setText("Ajouter");
            noteExamenComboBox.setValue("");
            noteEtudiantComboBox.setValue("");
            noteNoteSpinner.getEditor().setText("10.00");
            noteIdTextField.setText("0");
        }

    }

    @FXML
    private void updateNotes(MouseEvent event) {

        int id = Integer.parseInt(noteIdTextField.getText());
        int etudiant = eS.getComboBoxesConversion("etudiant", noteEtudiantComboBox.getValue());
        int examen = eS.getExamenComboBoxesConversion(noteExamenComboBox.getValue());
        double d = noteNoteSpinner.getValue();
        float note = (float) d;
        Mailer m = new Mailer();
        Note n = new Note(id, etudiant, examen, note);
        if (verifyNoteBeforeInsertion(n)) {
            if (id == 0) {
                nS.add(n);
                alertSucces("Note ajouté ");
                m.sendMail("bayciitn@gmail.com", "[ Nouvelle Note ] " + noteExamenComboBox.getValue() + " " + n.getNote(), "Votre note de : " + noteExamenComboBox.getValue() + " est :" + n.getNote());
            } else {

                m.sendMail("bayciitn@gmail.com", "[ Note Modifiée ] " + noteExamenComboBox.getValue() + " " + n.getNote(), "Votre note de : " + noteExamenComboBox.getValue() + " est :" + n.getNote());
                nS.edit(n);

                alertSucces("Note modifé");
            }
            refreshNotesContainer();
        }
    }

    @FXML
    private void addNewNoteDecisionMaked(MouseEvent event) {

        refreshComboBoxes();
        noteUpdateButton.setText("Ajouter");
        noteNoteSpinner.getEditor().setText("10.5");
        noteIdTextField.setText("0");
    }

    @FXML
    private void applySearchedSeances(ActionEvent event) {

        if (searchSeancesTextField.getText() == "") {
            refreshSeancesContainer();
        } else {

            seancesContainer.getItems().clear();

            ObservableList<Seance> seances = FXCollections.observableArrayList(sS.list());

            for (Seance s : seances) {

                String input = searchSeancesTextField.getText().toLowerCase();
                if (s.getMatiereName().toLowerCase().contains(input) || s.getProfesseurName().toLowerCase().contains(input) || s.getSalleName().toLowerCase().contains(input) || s.getClasseName().toLowerCase().contains(input)) {
                    seancesContainer.getItems().add(s);

                }
            }
        }
    }

    @FXML
    private void applySearchedExamens(ActionEvent event) {

        if (searchExamensTextField.getText() == "") {
            refreshExamensContainer();
        } else {

            examensContainer.getItems().clear();

            ObservableList<Examen> examens = FXCollections.observableArrayList(eS.list());

            for (Examen e : examens) {

                String input = searchExamensTextField.getText().toLowerCase();
                if (e.getMatiereName().toLowerCase().contains(input) || e.getSalleName().toLowerCase().contains(input) || e.getClasseName().toLowerCase().contains(input)) {
                    examensContainer.getItems().add(e);
                }
            }
        }

    }

    @FXML
    private void applySearchedNotes(ActionEvent event) {

        if (searchNotesTextField.getText() == "") {
            refreshNotesContainer();
        } else {

            notesContainer.getItems().clear();

            ObservableList<Note> notes = FXCollections.observableArrayList(nS.list());

            for (Note n : notes) {

                String input = searchNotesTextField.getText().toLowerCase();
                if (n.getExamenName().toLowerCase().contains(input) || n.getEtudiantName().toLowerCase().contains(input) || (n.getNote() + "").toLowerCase().contains(input)) {
                    notesContainer.getItems().add(n);
                }
            }
        }

    }

    @FXML
    private void goToSeances(MouseEvent event) {
        mainImage.setImage(new Image(getClass().getResourceAsStream("/edplusstyles/meetingblue.png")));
        mainLabel.setText("Gérer Les Séances");
        initializeDashboardButtons();
        gererSeanceButton.setStyle(gererSeanceButton.getStyle() + "-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");

        gererSeanceButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererSeanceButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        mainTab.getSelectionModel().select(seancesTab);

    }

    @FXML
    private void goToExams(MouseEvent event) {

        mainImage.setImage(new Image(getClass().getResourceAsStream("/edplusstyles/seanceiconblue.png")));
        mainLabel.setText("Gérer Les Examens");
        initializeDashboardButtons();
        gererExamsButton.setStyle(gererNotesButtons.getStyle() + "-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
        gererExamsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererExamsButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        mainTab.getSelectionModel().select(examenTab);
    }

    @FXML
    private void goToNotes(MouseEvent event) {
        mainImage.setImage(new Image(getClass().getResourceAsStream("/edplusstyles/notesiconblue.png")));
        mainLabel.setText("Gérer Les Notes");
        initializeDashboardButtons();
        gererNotesButtons.setStyle(gererNotesButtons.getStyle() + "-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");

        gererNotesButtons.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererNotesButtons.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        mainTab.getSelectionModel().select(notesTab);
    }

    private void initializeDashboardButtons() {

        accueilButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
        gererExamsButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
        gererNotesButtons.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
        gererSeanceButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
        statsButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
        releveNoteButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");

        accueilButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                accueilButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });

        gererExamsButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererExamsButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        gererNotesButtons.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererNotesButtons.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        gererSeanceButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererSeanceButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        statsButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                statsButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        releveNoteButton.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                releveNoteButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });

        accueilButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                accueilButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
            }
        });

        gererExamsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererExamsButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
            }
        });
        gererNotesButtons.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererNotesButtons.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
            }
        });
        gererSeanceButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gererSeanceButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
            }
        });
        statsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                statsButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
            }
        });
        releveNoteButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                releveNoteButton.setStyle("-fx-background-color : #0a8adf; -fx-text-fill : white; ");
            }
        });

    }

    private void saveTextToFile(String content, File file ) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void saveReleve(MouseEvent event) {
        String head = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "	<title>Releve de " + releveEtudiantComboBox.getSelectionModel().getSelectedItem() + " </title>\n"
                + "	<meta charset=\"UTF-8\">\n"
                + "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "<!--===============================================================================================-->	\n"
                + "	<link rel=\"icon\" type=\"image/png\" href=\"C:/Users/BAYCII/Downloads/releveSRC/images/icons/favicon.ico\"/>\n"
                + "<!--===============================================================================================-->\n"
                + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"C:/Users/BAYCII/Downloads/releveSRC/vendor/bootstrap/css/bootstrap.min.css\">\n"
                + "<!--===============================================================================================-->\n"
                + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"C:/Users/BAYCII/Downloads/releveSRC/fonts/font-awesome-4.7.0/css/font-awesome.min.css\">\n"
                + "<!--===============================================================================================-->\n"
                + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"C:/Users/BAYCII/Downloads/releveSRC/vendor/animate/animate.css\">\n"
                + "<!--===============================================================================================-->\n"
                + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"C:/Users/BAYCII/Downloads/releveSRC/vendor/select2/select2.min.css\">\n"
                + "<!--===============================================================================================-->\n"
                + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"C:/Users/BAYCII/Downloads/releveSRC/vendor/perfect-scrollbar/perfect-scrollbar.css\">\n"
                + "<!--===============================================================================================-->\n"
                + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"C:/Users/BAYCII/Downloads/releveSRC/css/util.css\">\n"
                + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"C:/Users/BAYCII/Downloads/releveSRC/css/main.css\">\n"
                + "<!--===============================================================================================-->\n"
                + "</head>\n"
                + "<body>\n"
                + "	<div class=\"limiter\">\n"
                + "		 \n"
                + "					 \n"
                + "						 \n"
                + "					<center><div class=\"table\" style=\"background-color : #0a8adf;\" >\n"
                + "					 \n"
                + "						<div class=\"row header\"  style=\"background-color : #0a8adf;\" > \n"
                + "							<div class=\"cell\"  >\n"
                + "								<img style=\"width :7%;\" src = \"D:/Mobile3AEsprit/EdPlusDesktop/src/edplusstyles/logoimg.png\" > \n"
                + " <br>Relevé de " + releveEtudiantComboBox.getSelectionModel().getSelectedItem() + " \n"
                + "							</div>\n"
                + "						</div>\n"
                + "						\n"
                + "\n"
                + "					</div></center>\n"
                + "			 \n"
                + "	\n"
                + "		<div class=\"container-table100\">\n"
                + "			<div class=\"wrap-table100\">\n"
                + "					<div class=\"table\">\n"
                + "\n"
                + "						<div class=\"row header\" style=\"background-color : #0a8adf;\">\n"
                + "							<div class=\"cell\" style=\"background-color : #0a8adf;\">\n"
                + "								Examen\n"
                + "							</div>\n"
                + "							<div class=\"cell\">\n"
                + "								Note\n"
                + "							</div>\n"
                + "						</div>";

        String releve = "";

        String bottom = "					</div>\n"
                + "			</div>\n"
                + "		</div>\n"
                + "	</div>\n"
                + "\n"
                + "\n"
                + "	\n"
                + "\n"
                + "<!--===============================================================================================-->	\n"
                + "	<script src=\"vendor/jquery/jquery-3.2.1.min.js\"></script>\n"
                + "<!--===============================================================================================-->\n"
                + "	<script src=\"vendor/bootstrap/js/popper.js\"></script>\n"
                + "	<script src=\"vendor/bootstrap/js/bootstrap.min.js\"></script>\n"
                + "<!--===============================================================================================-->\n"
                + "	<script src=\"vendor/select2/select2.min.js\"></script>\n"
                + "<!--===============================================================================================-->\n"
                + "	<script src=\"js/main.js\"></script>\n"
                + "\n"
                + "</body>\n"
                + "</html>";

        ObservableList<Note> notes = FXCollections.observableArrayList(nS.list());
        float tot = 0;
        float div = 0; 
        for (Note n : notes) {

            String input = releveEtudiantComboBox.getSelectionModel().getSelectedItem();
            if ((n.getEtudiant() + " - " + n.getEtudiantName()).equals(input)) {
                tot += n.getNote();
                div++;
                releve += "\n" + "<div class=\"row\">\n"
                        + "							<div class=\"cell\" data-title=\"Examen\">\n"
                        + "								" + n.getExamenName() + "\n"
                        + "							</div>\n"
                        + "							<div class=\"cell\" data-title=\"Note\">\n"
                        + "								" + n.getNote() + "\n"
                        + "							</div>\n"
                        + "						</div>\n"
                        + "						";
            }
        }
        if(div>0) {  
         releve += "\n" + "<div class=\"row\">\n"
                        + "							<div class=\"cell \" style=\"background-color : #0a8adf;  color : white ;\" data-title=\"Examen\">\n"
                        + "								 Moyenne Actuelle\n"
                        + "							</div>\n"
                        + "							<div class=\"cell\"  style=\"background-color : #0a8adf;  color : white ;\" data-title=\"Note\">\n"
                        + "								" + (float) (tot/div) + "\n"
                        + "							</div>\n"
                        + "						</div>\n"
                        + "						"; 
        }
        else { 
             releve += "\n" + "<div class=\"row\">\n"
                        + "							<div class=\"cell\" style=\"background-color : #0a8adf;  color : white ;\"s data-title=\"Examen\">\n"
                        + "								 Moyenne Actuelle\n"
                        + "							</div>\n"
                        + "							<div class=\"cell\"  style=\"background-color : #0a8adf; color : white ;\"  data-title=\"Note\">\n"
                        + "								Auccune note!\n"
                        + "							</div>\n"
                        + "						</div>\n"
                        + "						"; 
        }

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("html files (*.html)", "*.html");

        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("[RELEVE] " + releveEtudiantComboBox.getSelectionModel().getSelectedItem() + " " + LocalDate.now());
        //Show save file dialog
        File file = fileChooser.showSaveDialog((Stage) mainTab.getScene().getWindow());

        if (file != null) {
            saveTextToFile(head + releve + bottom, file);
        }
    }

    @FXML
    private void goToAccueil(MouseEvent event) {

        mainImage.setImage(new Image(getClass().getResourceAsStream("/edplusstyles/homeiconblue.png")));
        mainLabel.setText("Accueil");
        initializeDashboardButtons();
        accueilButton.setStyle(accueilButton.getStyle() + "-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
        accueilButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                accueilButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        mainTab.getSelectionModel().select(accueilTab);
    }

    @FXML
    private void goToReleve(MouseEvent event) {
        mainImage.setImage(new Image(getClass().getResourceAsStream("/edplusstyles/releveblue.png")));
        mainLabel.setText("Relevé de notes");

        initializeDashboardButtons();
        releveNoteButton.setStyle(releveNoteButton.getStyle() + "-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");

        releveNoteButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                releveNoteButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        mainTab.getSelectionModel().select(releveTab);
    }

    @FXML
    private void goToStats(MouseEvent event) {
        mainImage.setImage(new Image(getClass().getResourceAsStream("/edplusstyles/statsblue.png")));
        mainLabel.setText("Statistiques");
        initializeDashboardButtons();
        statsButton.setStyle(statsButton.getStyle() + "-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
        statsButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                statsButton.setStyle("-fx-background-color : #58b1eb; -fx-text-fill : whitesmoke; ");
            }
        });
        mainTab.getSelectionModel().select(statsTab);

    }

    @FXML
    private void updateBarChart(MouseEvent event) {

        updatebars();
        updatebars();

    }

    private void updatebars() {

        barc.setTitle("Nombre d'examen par matiere");
        barc.getXAxis().setLabel("Matiere");
        barc.getYAxis().setLabel("Nombre d'examen");
        XYChart.Series<String, Number> series1 = new XYChart.Series();

        series1.getData().clear();
        series1.setName("nombre d'examen par matiere");
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            String requete = "SELECT matiere.nom as nom, count(*) as nb FROM examen INNER join matiere on matiere.id = examen.matiere GROUP BY matiere.id ";
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                series1.getData().add(new XYChart.Data(rs.getString("nom"), rs.getInt("nb")));
            }

            barc.getData().clear();

            barc.getData().add(series1);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void alertSucces(String Content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Opération effectué avec succes");
        alert.setHeaderText(null);
        alert.setContentText(Content + "!");

        alert.showAndWait();
    }

    boolean alertConfirmation(String Content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(Content);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    void alertError(String Content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(Content);
        
        
        alert.showAndWait();
    }

    boolean verifySeanceBeforeInsertion(Seance ss) {

        ObservableList<Seance> seances = FXCollections.observableArrayList(sS.list());
        for (Seance s : seances) {

            if (ss.equals(s)) {
                if (ss.getId() != s.getId()) {
                    alertError("Vous ne pouvez pas programme cette séance! \n Verifiez la disponibilité des salle ou des prof etc ...");

                    return false;
                }
            }
        }
        return true;
    }

    boolean verifyNoteBeforeInsertion(Note nn) {

        ObservableList<Note> notes = FXCollections.observableArrayList(nS.list());
        for (Note n : notes) {

            if (nn.equals(n)) {

                if (n.getId() != nn.getId()) {
                    alertError("Vous ne pouvez pas attribuer la note deux fois!");
                    return false;
                }
            }
        }
        return true;
    }

    boolean verifyExamenBeforeInsertion(Examen ee) {

        ObservableList<Examen> examens = FXCollections.observableArrayList(eS.list());
        for (Examen e : examens) {

            if (ee.equals(e)) {

                if (ee.getId() != e.getId()) {
                    alertError("Vous ne pouvez pas declarer le même examen!");
                    return false;
                }
            }
        }
        return true;
    }

}
