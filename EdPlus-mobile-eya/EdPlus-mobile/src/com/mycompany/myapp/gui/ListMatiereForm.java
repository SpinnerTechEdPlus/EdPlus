/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Classe;
import entities.Matiere;
import java.util.ArrayList;
import services.ServiceMatiere;

/**
 *
 * @author Eya
 */
public class ListMatiereForm extends SideMenuBaseForm  {
    public ArrayList<Matiere> matieres;

    public ListMatiereForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton)
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_SCHOOL);
        fab.addActionListener(e -> new HomeForm(res).show());

        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        // fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

       
        Form current=this;
        matieres = ServiceMatiere.getInstance().getAll();

        for (Matiere u : matieres) {

            MultiButton mb = new MultiButton(u.getNom());
            mb.addActionListener( e -> new ShowMatiere(res,current,u).show());

            add(mb);

        };
        
         Button AddBtn = new Button("ajouter une Matiere");
        
        AddBtn.addActionListener(e-> new AddMatiereForm(res, current).show());;
        add(AddBtn);
        
        //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().show());

        setupSideMenu(res);
    }

    
}
