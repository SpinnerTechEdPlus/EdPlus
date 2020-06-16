package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Etudiant;
import entities.Examen;
import services.BlocService;
import services.EtageService;
import services.EtudiantService;
import services.ExamenService;
import services.MatiereService;
import services.NoteService;
import services.SalleService;
import services.SeanceService;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {

    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Bienvenue à ", "WelcomeWhite"),
                new Label("EdPlus", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("WHITE LOGO.png");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth() + 200, mask.getHeight() + 200);
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        //profilePicLabel.setMask(mask.createMask());

        TextField login = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        login.getAllStyles().setMargin(LEFT, 0);
        login.getHintLabel().setUIID("TextField");
        password.getHintLabel().setUIID("TextField");
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Button createNewAccount = new Button("© SPINNER TECHNOLOGY");
        createNewAccount.setUIID("CreateNewAccountButton");

        Button loginButton = new Button("Se connecter");
        loginButton.setUIID("SkipButton");
        loginButton.addActionListener(e -> {
            if (!(login.getText().equals("") || password.getText().equals(""))) {
                
                if (EtudiantService.getInstance().getStudentConnection(login.getText(), password.getText())) {
                    Toolbar.setGlobalToolbar(false);
                    new loadingForm(theme).show();
                    Etudiant.examens = ExamenService.getInstance().getAllExams(Etudiant.classeId);
                    Etudiant.matieres = MatiereService.getInstance().getAllMatieres(Etudiant.classeId);
                    Etudiant.notes = NoteService.getInstance().getAllNotes(Etudiant.id);
                    Etudiant.seances = SeanceService.getInstance().getAllSeances(Etudiant.classeId);
                    Etudiant.salles = SalleService.getInstance().getAllSalles();
                    Etudiant.blocs = BlocService.getInstance().getAllBlocs();
                    Etudiant.etages = EtageService.getInstance().getAllEtages();
                    
                    
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mask.getWidth(), mask.getHeight(), 0xffff0000), true);
      
                    String imgProfile= "http://localhost/Ed+/web/Back_assets/assets/images/faces/face"+Etudiant.id+".jpg";
                    Etudiant.Imagee = URLImage.createToStorage(placeholder, Etudiant.id+".jpg",imgProfile); 
                    Toolbar.setGlobalToolbar(true);
                    new AccueilForm(theme).show();
                } else {
                    createNewAccount.setText("Veuillez verifier vos informations!");
                    createNewAccount.setUIID("vezrify");
                }
            } else {
                createNewAccount.setText("Veuillez verifier vos informations!");
                createNewAccount.setUIID("vezrify");
            }
        });
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }

        Container by = BoxLayout.encloseY(
                profilePicLabel,
                welcome,
                spaceLabel,
                BorderLayout.center(login).
                add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);

        // for low res and landscape devices
        by.setScrollableY(false);
        by.setScrollVisible(false);
    }
}
