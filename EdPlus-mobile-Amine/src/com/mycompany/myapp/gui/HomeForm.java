/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import utils.UserSession;


/**
 *
 * @author bhk
 */
public class HomeForm extends SideMenuBaseForm{
Form current;
    public HomeForm(Resources res) {
         super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       
        
        

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton));
        
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_SCHOOL);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
       // fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        
        String roleProf="professeur";
        String roleEtu="etudiant";
        current=this;
        //setTitle("Home");
        //setLayout(BoxLayout.y());
        
        
        System.out.println("hedha coo "+UserSession.getUser() ); 
        
        //add(new Label("Choose an option"));
        Button btnAddProf = new Button("Add Prof");
        Button btnAddEtu = new Button("Add Etu");

        Button btnListProf = new Button("List Prof");
        Button btnListEtu = new Button("List Etu");

        
        btnAddProf.addActionListener(e-> {
             try {
                 new AddUserForm(res,current,roleProf).show();
             } catch (IOException ex) {
                System.out.println("idk ");
             }
         });
        btnAddEtu.addActionListener(e-> {
             try {
                 new AddUserForm(res,current,roleEtu).show();
             } catch (IOException ex) {
                 System.out.println("idk ");
             }
         });

        btnListProf.addActionListener(e-> new ListProfForm(res,roleProf).show());
        btnListEtu.addActionListener(e-> new ListProfForm(res,roleEtu).show());

        addAll(btnAddProf,btnListProf,btnAddEtu,btnListEtu);
        
        setupSideMenu(res);
        
        
        
        
    }
    
    @Override
    protected void showOtherForm(Resources res) {
        // new StatsForm(res).show();
    }

    
    
    
    
}
