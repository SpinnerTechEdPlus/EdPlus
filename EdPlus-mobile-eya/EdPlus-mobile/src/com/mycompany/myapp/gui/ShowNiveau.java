/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Niveau;
import services.ServiceNiveau;

/**
 *
 * @author Eya
 */
public class ShowNiveau extends Form {

    public ShowNiveau(Niveau n) {
        setLayout(BoxLayout.y());
        Form current = this;
        SpanLabel sp = new SpanLabel();
        SpanLabel sp2 = new SpanLabel();
        sp.setText(n.getNom());
        Button btnValider = new Button("Update Niveau");
        btnValider.addActionListener(e -> new UpdateNiveauForm(n).show());

        Button btnDelete = new Button("Delete Niveau");

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ServiceNiveau.getInstance().DeleteNiveau(n);
               ListProfForm listProf = new ListProfForm(current, "aa");
              listProf.show();

            }
        });

        addAll(sp, sp2, btnValider, btnDelete);

          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().show());
    }

}
