/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Matiere;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.SideMenuBaseForm;
import entities.Matiere;
import services.ServiceMatiere;

/**
 *
 * @author Eya
 */
public class ShowMatiere extends SideMenuBaseForm {

    public ShowMatiere(Resources res, Form previous, Matiere m) {
        setLayout(BoxLayout.y());
        Form current = this;
        //SpanLabel spnum = new SpanLabel();
       // SpanLabel spnbr = new SpanLabel();
        SpanLabel spnom = new SpanLabel();
        
        
        spnom.setText("Nom : "+m.getNom());
        
      //  spnum.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
      //  spnbr.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        spnom.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        Button btnValider = new Button("modifier la Matiere");
        btnValider.addActionListener(e -> new UpdateMatiereForm(res, m, current).show());

        Button btnDelete = new Button("supprimer la Matiere");

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ServiceMatiere.getInstance().DeleteMatiere(m);
                ListMatiereForm list = new ListMatiereForm(res);
                list.show();

            }
        });

        addAll(spnom, btnValider, btnDelete);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
