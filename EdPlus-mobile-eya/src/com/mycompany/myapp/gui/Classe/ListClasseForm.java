/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Classe;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.SideMenuBaseForm;
import entities.Classe;
import java.io.IOException;
import java.util.ArrayList;
import services.ServiceClasse;

/**
 *
 * @author Eya
 */
public class ListClasseForm extends SideMenuBaseForm {

    public ArrayList<Classe> classes;
    TextField SearchField = new TextField("", "classe");
    String searchString;

    public ListClasseForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

 Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn()
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_SCHOOL);
        fab.addActionListener(e -> {
         
                new HomeForm(res).show();
           
        });
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        Form current = this;
        classes = ServiceClasse.getInstance().getAll();
        int fiveMM = Display.getInstance().convertToPixels(5);
        Toolbar.setGlobalToolbar(true);
        add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            Display.getInstance().callSerially(() -> {
                removeAll();

                for (Classe u : classes) {

                    MultiButton mb = new MultiButton(u.getNom());
                    mb.addActionListener(
                            e -> new ShowClasse(res, current, u).show()
                    );

                    add(mb);

                };

                revalidate();
            });
        });

        tb.addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {

                    cmp.setHidden(false);
                    cmp.setVisible(true);

                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();

                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                getContentPane().animateLayout(150);
            }
        }, 4);

        /*       FloatingActionButton AddFab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
         Button AddBtn = new Button("ajouter une classe");
        AddFab.addActionListener(e -> new AddClasseForm(res, current).show());;
        add(AddBtn);   */
        //  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().show());
     //   FloatingActionButton AddFab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        /*AddFab.addActionListener(e -> {
            new AddClasseForm(res, current).show();
        });*/
       // AddFab.bindFabToContainer(getContentPane());

    //    setupSideMenu(res);
     //   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_MENU, e -> getToolbar().openSideMenu());
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_MENU, e -> getToolbar().openSideMenu());
        setupSideMenu(res);
    }

}
