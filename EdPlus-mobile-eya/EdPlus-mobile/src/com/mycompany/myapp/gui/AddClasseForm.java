/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Classe;
import services.ServiceClasse;

/**
 *
 * @author bhk
 */
public class AddClasseForm extends SideMenuBaseForm {

    Form current;

    public AddClasseForm(Resources res, Form previous) {

        setTitle("Ajouter une Classe");
        setLayout(BoxLayout.y());
        current = this;
        TextField tfnum = new TextField("", "Le numéro de la classe");
        TextField tfnbrEtudiant = new TextField("", "Le nombre d'étudiants");
        TextField tfNom = new TextField("", "Le nom du classe");

        Button btnValider = new Button("Ajouter une classe");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfnbrEtudiant.getText().length() == 0) || (tfnum.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    Classe NewClasse = new Classe();
                    NewClasse.setNum(Integer.parseInt(tfnum.getText()));
                    NewClasse.setNbrEtudiant(Integer.parseInt(tfnbrEtudiant.getText()));
                    NewClasse.setNom(tfNom.getText());
                    ServiceClasse.getInstance().AddClasse(NewClasse);
                    ListClasseForm list = new ListClasseForm(res);
                    list.show();

                }

            }
        });

        addAll(tfnum, tfnbrEtudiant, tfNom, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
