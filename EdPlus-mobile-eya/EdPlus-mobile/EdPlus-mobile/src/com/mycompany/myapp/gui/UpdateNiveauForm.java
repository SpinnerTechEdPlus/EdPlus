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
import entities.Niveau;
import services.ServiceNiveau;

/**
 *
 * @author Eya
 */
public class UpdateNiveauForm extends Form {

    public UpdateNiveauForm(Niveau n) {

        setTitle("update a user");
        setLayout(BoxLayout.y());

        Form current = this;
        TextField tfNom = new TextField(n.getNom(), "nom");
        TextField tfniv = new TextField("", "niv");
        int id = n.getId();
        Button btnValider = new Button("update Niveau");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfniv.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {

                    Niveau NewNiv = new Niveau();
                    NewNiv.setId(n.getId());

                    NewNiv.setNom(tfNom.getText());
                    NewNiv.setNivScolaire(Integer.parseInt(tfniv.getText()));

                    ServiceNiveau.getInstance().UpdateNiveau(NewNiv, id);

                    ListProfForm listProf = new ListProfForm(current, "idk");
                    listProf.show();

                }

            }
        });
        addAll(tfNom, tfniv, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().show());

    }

}
