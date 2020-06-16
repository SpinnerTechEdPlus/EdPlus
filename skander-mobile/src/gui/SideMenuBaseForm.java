 

package gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import entities.Etudiant;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        
       // Image profilePic = res.getImage("WHITE LOGO.png");
        Image mask = res.getImage("round-mask.png");
         
        Image profilePic = Etudiant.Imagee; 
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  "+Etudiant.nom+" - "+Etudiant.classeName, profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Accueil", FontImage.MATERIAL_HOME, e -> new AccueilForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Mes Séances", FontImage.MATERIAL_CALENDAR_TODAY,  e ->{ Toolbar.setGlobalToolbar(false) ;  new CalendarForm(res).show()    ;  Toolbar.setGlobalToolbar(true);});
        getToolbar().addMaterialCommandToSideMenu("  Mes Examens", FontImage.MATERIAL_ACCESS_TIME,  e -> { Toolbar.setGlobalToolbar(false);
                    new WalkthruForm(res).show();
                    Toolbar.setGlobalToolbar(true);});
        getToolbar().addMaterialCommandToSideMenu("  Mes Notes", FontImage.MATERIAL_BADGE,  e -> new StatsForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Les Blocs", FontImage.MATERIAL_VIEW_CAROUSEL,  e -> new BlocsForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Les Etages", FontImage.MATERIAL_REORDER,  e -> new EtageForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Les Salles", FontImage.MATERIAL_VIEW_MODULE, e -> { Toolbar.setGlobalToolbar(false);
                    new SalleForm(res).show();
                    Toolbar.setGlobalToolbar(true);});

        getToolbar().addMaterialCommandToSideMenu("  Réclamation", FontImage.MATERIAL_TEXTSMS,  e -> new ReclamSMSForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Se déconnecter", FontImage.MATERIAL_LOGOUT,  e -> new LoginForm(res).show());
        
    }
    
    protected abstract void showOtherForm(Resources res);
}
