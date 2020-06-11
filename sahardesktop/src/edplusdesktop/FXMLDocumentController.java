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
import edplusutils.TimeSpinner;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private TimeSpinner seanceDebutSpinner;
    @FXML
    private TimeSpinner seanceFinSpinner;

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
        noteExamenComboBox.setValue(noteExamenComboBox.getItems().get(0));

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
            sS.remove(s.getId());
            refreshSeancesContainer();
        }
    }

    @FXML
    private void deleteSelectedExamen(MouseEvent event) {
        if (!examensContainer.getSelectionModel().isEmpty()) {

            Examen e = examensContainer.getSelectionModel().getSelectedItem();
            eS.remove(e.getId());
            refreshExamensContainer();
            refreshComboBoxes();
        }
    }

    @FXML
    private void deleteSelectedNote(MouseEvent event) {

        if (!notesContainer.getSelectionModel().isEmpty()) {

            Note n = notesContainer.getSelectionModel().getSelectedItem();
            nS.remove(n.getId());
            refreshNotesContainer();
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
            alert.setHeaderText("debut < fin");
            alert.setContentText("Veuillez verifier vos choix !");
            alert.showAndWait();
        } else {

            Seance s;
            if (id == 0) {
                s = new Seance(salle, matiere, professeur, classe, jour, debut, fin);
                sS.add(s);
            } else {
                s = new Seance(id, salle, matiere, professeur, classe, jour, debut, fin);
                sS.edit(s);
            }
            refreshSeancesContainer();
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
            seanceDebutSpinner.getEditor().setText("" + LocalTime.now());
            seanceFinSpinner.getEditor().setText("" + LocalTime.now());
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
        Examen e;
        if (id == 0) {
            e = new Examen(salle, matiere, classe, horaire, semestre);
            eS.add(e);
        } else {
            e = new Examen(id, salle, matiere, classe, horaire, semestre);
            eS.edit(e);
        }
        refreshExamensContainer();
        refreshComboBoxes();

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
            seanceDebutSpinner.getEditor().setText("" + LocalTime.now());
            seanceFinSpinner.getEditor().setText("" + LocalTime.now());
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
            examenTempsSpinner.getEditor().setText("" + LocalTime.now());
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
        Note n;
        if (id == 0) {
            n = new Note(etudiant, examen, note);
            nS.add(n);
            m.sendMail("bayciitn@gmail.com", "[ Nouvelle Note ] " + noteExamenComboBox.getValue() + " " + n.getNote(), "Votre note de : " + noteExamenComboBox.getValue() + " est :" + n.getNote());
        } else {
            n = new Note(id, etudiant, examen, note);

            m.sendMail("bayciitn@gmail.com", "[ Note Modifiée ] " + noteExamenComboBox.getValue() + " " + n.getNote(), "Votre note de : " + noteExamenComboBox.getValue() + " est :" + n.getNote());

            nS.edit(n);
        }
        refreshNotesContainer();

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
        
        
        
        if(searchExamensTextField.getText() == "")
        {
            refreshExamensContainer();
        }
        else { 
            
            examensContainer.getItems().clear();
            
          ObservableList<Examen> examens = FXCollections.observableArrayList(eS.list());
         
          for ( Examen e : examens) {
              
              String input = searchExamensTextField.getText().toLowerCase();
              if ( e.getMatiereName().toLowerCase().contains(input) || e.getSalleName().toLowerCase().contains(input) || e.getClasseName().toLowerCase().contains(input))
              {
              examensContainer.getItems().add(e);
              }
        }
        }
        
    }

    @FXML
    private void applySearchedNotes(ActionEvent event) {
         
        if(searchNotesTextField.getText() == "")
        {
            refreshNotesContainer();
        }
        else { 
            
            notesContainer.getItems().clear();
            
          ObservableList<Note> notes = FXCollections.observableArrayList(nS.list());
         
          for ( Note n : notes) {
              
              String input = searchNotesTextField.getText().toLowerCase();
              if ( n.getExamenName().toLowerCase().contains(input) || n.getEtudiantName().toLowerCase().contains(input) || (n.getNote()+"").toLowerCase().contains(input))
              {
              notesContainer.getItems().add(n);
              }
        }
        }
        
        
        
    }

}
