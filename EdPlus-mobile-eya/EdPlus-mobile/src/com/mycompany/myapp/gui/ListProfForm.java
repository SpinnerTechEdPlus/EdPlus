/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import entities.Niveau;
import java.util.ArrayList;
import services.ServiceNiveau;

/**
 *
 * @author bhk
 */
public class ListProfForm extends Form {

    public ArrayList<Niveau> niveaux;

    public ListProfForm(Form previous, String role) {
        setTitle("La liste des niveaux");
        setLayout(BoxLayout.y());

        niveaux = ServiceNiveau.getInstance().getAll();

        for (Niveau u : niveaux) {

            MultiButton mb = new MultiButton(u.getNom());
            mb.addActionListener(
                    e -> new ShowNiveau(u).show()
            );

            add(mb);

        };
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().show());
    }

}
