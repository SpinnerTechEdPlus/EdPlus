/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Niveau;

import com.mycompany.myapp.gui.Niveau.AddNiveauForm;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
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
import com.mycompany.myapp.gui.NivStat.ChartNiv;
import com.mycompany.myapp.gui.SideMenuBaseForm;
import entities.Niveau;
import java.io.IOException;
import java.util.ArrayList;
import services.ServiceNiveau;

/**
 *
 * @author Eya
 */
public class ListNiveauForm extends SideMenuBaseForm {

    public ArrayList<Niveau> niveaux;
    TextField SearchField = new TextField("", "niveau");
    String searchString;

    public ListNiveauForm(Resources res) {
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
        niveaux = ServiceNiveau.getInstance().getAll();

        int fiveMM = Display.getInstance().convertToPixels(5);
        Toolbar.setGlobalToolbar(true);
        add(new InfiniteProgress());

        // Recherche 
        Display.getInstance().scheduleBackgroundTask(() -> {
            Display.getInstance().callSerially(() -> {
                removeAll();

                for (Niveau u : niveaux) {

                    MultiButton mb = new MultiButton(u.getNom());
                    mb.addActionListener(
                            e -> new ShowNiveau(res, current, u).show()
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
        // end Recherche 

        FloatingActionButton AddBtn = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        AddBtn.addActionListener(e -> {
            new AddNiveauForm(res, current).show();
        });
        AddBtn.bindFabToContainer(getContentPane());

        FloatingActionButton nivchart = FloatingActionButton.createFAB(FontImage.MATERIAL_SHOW_CHART);
        nivchart.addActionListener(e -> {
            try {
                new ChartNiv(res).show();
            } catch (IOException ex) {
                System.out.println("erreur" + ex.getMessage());
            }
        });
        nivchart.bindFabToContainer(getContentPane());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_MENU, e -> getToolbar().openSideMenu());
        setupSideMenu(res);
    }

}
