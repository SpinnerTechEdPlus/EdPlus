/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Niveau;

import com.mycompany.myapp.gui.Niveau.ListNiveauForm;
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
import entities.Niveau;
import services.ServiceNiveau;

/**
 *
 * @author Eya
 */
public class UpdateNiveauForm extends SideMenuBaseForm {

    public UpdateNiveauForm(Resources res,Niveau n,Form previous) {

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

                    ListNiveauForm listProf = new ListNiveauForm(res);
                    listProf.show();

                }

            }
        });
        addAll(tfNom, tfniv, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
