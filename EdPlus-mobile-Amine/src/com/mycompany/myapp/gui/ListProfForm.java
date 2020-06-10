/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entities.Classe;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import services.ServiceClasse;

import services.ServiceUser;
import utils.UserSession;

/**
 *
 * @author bhk
 */
public class ListProfForm extends SideMenuBaseForm{
    
    
    
   public ArrayList<User> UsersProf;
   TextField SearchField = new TextField("","Username");
   String searchString;

    public ListProfForm(Resources res,String role) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       
        
        

       
        
                

      //  String role="professeur";
       // UsersProf=ServiceUser.getInstance().getAll(role);
              UsersProf=ServiceUser.getInstance().getAllById(role,UserSession.getUser().getId());

        
        
           
       /* for(User u:UsersProf){
            
            
        
            MultiButton mb=new MultiButton(u.getUsername());
           mb.addActionListener(
             e-> new DetailedUser(res,current,u,role).show()
           );
                   
            add(mb);
           
            
        *
        };*/
        
        
        
        
        
        //depends on role
        
        //sp.setText(ServiceUser.getInstance().getAll(role).toString());
        
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       
       
     //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm(res).show());
    
        
        Form current=this;
        
        
        
        
      

int fiveMM = Display.getInstance().convertToPixels(5);

Toolbar.setGlobalToolbar(true);
//Form hi = new Form("Search", BoxLayout.y());
add(new InfiniteProgress());
Display.getInstance().scheduleBackgroundTask(()-> {
    // this will take a while...
        //UsersProf=ServiceUser.getInstance().getAllByClasse(role,13);    
        
        
        //UsersProf=ServiceUser.getInstance().getAll(role);
        UsersProf=ServiceUser.getInstance().getAllById(role,UserSession.getUser().getId());
       
        Display.getInstance().callSerially(() -> {
        removeAll();
       
      for(User u:UsersProf){
            
            
        
            MultiButton mb=new MultiButton(u.getUsername());
           mb.addActionListener(
             e-> new DetailedUser(res,current,u,role).show()
           );
                   
            add(mb);
            
           
            
        
        };
        
        /*if(role=="etudiant")
            
        {   ArrayList<Classe> classes = ServiceClasse.getInstance().getAll();
            Picker p = new Picker();
            for (Classe u : classes)
            {
            p.setStrings(u.getNom());
            }
            p.setSelectedString("all");
                       // p.setSelectedString(characters[0]);
            p.addActionListener(e ->System.out.println("aa"));
            add(p);
        }*/
        
     
     
     
        revalidate();
    });
});

tb.addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            
            cmp.setHidden(false);
            cmp.setVisible(true);
            
            

        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);



    FloatingActionButton AddFab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        AddFab.addActionListener(e-> {
            try {
                new AddUserForm(res,current,role).show();
            } catch (IOException ex) {
                System.out.println("idk ");            }
        });
        AddFab.bindFabToContainer(getContentPane());
        
        
         
         /*FloatingActionButton menuFab = FloatingActionButton.createFAB(FontImage.MATERIAL_CONTACT_SUPPORT);
         menuFab.setUIID("Title");
        AddFab.addActionListener(e -> getToolbar().openSideMenu());
        menuFab.bindFabToContainer(getContentPane());*/
        
         
         
         /*Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton)
        
        );
        
        getToolbar().setTitleComponent(titleCmp);*/
        
        
        
        /*FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_SCHOOL);
        fab.addActionListener(e-> new HomeForm(res).show());
        
        
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
       // fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));*/
        
        
        
        
        
        
        
        //AddFab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
       // fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
       // tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        
setupSideMenu(res);


/*Button AddBtn = new Button("Add a new "+role);
        AddBtn.setUIID("LoginButton");
        
        AddBtn.addActionListener(e-> {
            try {
                new AddUserForm(res,current,role).show();
            } catch (IOException ex) {
                System.out.println("idk ");            }
        });
        add(AddBtn);*/

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_MENU, e -> getToolbar().openSideMenu());

    
    
    }}
