/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Classe;
import services.ServiceClasse;

/**
 *
 * @author Eya
 */
public class ShowClasse extends SideMenuBaseForm {

    public ShowClasse(Resources res, Form previous, Classe c) {
        setLayout(BoxLayout.y());
        Form current = this;
        SpanLabel spnum = new SpanLabel();
        SpanLabel spnbr = new SpanLabel();
        SpanLabel spnom = new SpanLabel();
        
        
        spnum.setText("Numéro : "+String.valueOf(c.getNum()));
        spnbr.setText("Nombre d'étudiants : "+String.valueOf(c.getNbrEtudiant()));
        spnom.setText("Nom : "+c.getNom());
        
        spnum.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        spnbr.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        spnom.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        Button btnValider = new Button("modifier la classe");
        btnValider.addActionListener(e -> new UpdateClasseForm(res, c, current).show());

        Button btnDelete = new Button("supprimer la classe");

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ServiceClasse.getInstance().DeleteClasse(c);
                ListClasseForm list = new ListClasseForm(res);
                list.show();

            }
        });

        addAll(spnum, spnbr,spnom, btnValider, btnDelete);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
