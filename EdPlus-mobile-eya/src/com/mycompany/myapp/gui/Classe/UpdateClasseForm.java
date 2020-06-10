/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Classe;

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
import com.mycompany.myapp.gui.SideMenuBaseForm;
import entities.Classe;
import services.ServiceClasse;

/**
 *
 * @author Eya
 */
public class UpdateClasseForm extends SideMenuBaseForm {

    public UpdateClasseForm(Resources res, Classe c, Form previous) {

        setTitle("modifier une classe");
        setLayout(BoxLayout.y());

        Form current = this;
        TextField tfnum = new TextField(String.valueOf(c.getNum()), "Le numéro de la classe");
        TextField tfnbrEtudiant = new TextField(String.valueOf(c.getNbrEtudiant()), "Le nombre d'étudiants");
        TextField tfNom = new TextField(c.getNom(), "Le nom du classe");
        int id = c.getId();
        Button btnValider = new Button("Modifier la classe");
        btnValider.setUIID("LoginButton");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfnbrEtudiant.getText().length() == 0) || (tfnum.getText().length() == 0)) {
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                 if (!Dialog.show("Modifier", "Voulez vous vraiment modifier cette classe?", "Non", "Oui")) {
                    Classe NewClasse = new Classe();
                    NewClasse.setId(c.getId());

                    NewClasse.setNum(Integer.parseInt(tfnum.getText()));
                    NewClasse.setNbrEtudiant(Integer.parseInt(tfnbrEtudiant.getText()));
                    NewClasse.setNom(tfNom.getText());

                    ServiceClasse.getInstance().UpdateClasse(NewClasse, id);

                    ListClasseForm listProf = new ListClasseForm(res);
                    listProf.show();
                 }
                }

            }
        });
        addAll(tfnum, tfnbrEtudiant, tfNom, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
