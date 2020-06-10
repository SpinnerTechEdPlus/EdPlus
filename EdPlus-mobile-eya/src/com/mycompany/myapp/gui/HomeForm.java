/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.gui.Matiere.AddMatiereForm;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.Niveau.AddNiveauForm;
import entities.Classe;
import entities.Niveau;
import java.io.IOException;
import java.util.ArrayList;
import services.ServiceClasse;
import services.ServiceNiveau;

/**
 *
 * @author bhk
 */
public class HomeForm extends SideMenuBaseForm  {
    
    

     Media m;
    public ArrayList<Niveau> niveaux;
    public ArrayList<Classe> classes;

    public HomeForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("whiteLogo.png");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        // profilePicLabel.setMask(mask.createMask());
        niveaux = ServiceNiveau.getInstance().getAll();
        classes = ServiceClasse.getInstance().getAll();

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container remainingTasks = BoxLayout.encloseY(
                new Label(String.valueOf(niveaux.size()), "CenterTitle"),
                new Label("niveaux", "CenterTitle")
        );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                new Label(String.valueOf(classes.size()), "CenterTitle"),
                new Label("classes", "CenterTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Welcome Back", "Title"),
                                new Label("We Are ED+", "Title")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn(2, remainingTasks, completedTasks)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_PLAY_ARROW);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        fab.addActionListener(l -> {

            try {
                m = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/appbackground.mp3")), "audio/mpeg");
                m.play();
                m.setVolume(5);
                
            } catch (IOException ex) {
                System.out.println("erreur" + ex.getMessage());
            }

        });

        add(new Label("About ED+", "TodayTitle"));

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

        addButtonBottom(arrowDown, "en cours de construction ", 0xd997f1, true);
        
        FloatingActionButton AddBtn = FloatingActionButton.createFAB(FontImage.MATERIAL_STOP);
        AddBtn.addActionListener(l -> { 
              if( m.isPlaying()) {
              m.pause();}
          });
        AddBtn.bindFabToContainer(getContentPane());
   
        setupSideMenu(res);
    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        // new StatsForm(res).show();
    }

}
