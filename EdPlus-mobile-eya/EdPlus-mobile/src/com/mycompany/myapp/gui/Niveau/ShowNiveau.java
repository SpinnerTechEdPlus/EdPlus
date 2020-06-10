/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Niveau;

import com.mycompany.myapp.gui.Niveau.UpdateNiveauForm;
import com.mycompany.myapp.gui.Niveau.ListNiveauForm;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
public class ShowNiveau extends SideMenuBaseForm {

    public ShowNiveau(Resources res, Form previous, Niveau n) {
        setLayout(BoxLayout.y());
        Form current = this;
        SpanLabel sp = new SpanLabel();
        SpanLabel sp2 = new SpanLabel();
        sp.setText("Nom : "+n.getNom());
        Button btnValider = new Button("Update Niveau");
        btnValider.addActionListener(e -> new UpdateNiveauForm(res, n, current).show());

        Button btnDelete = new Button("Delete Niveau");

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ServiceNiveau.getInstance().DeleteNiveau(n);
                ListNiveauForm listProf = new ListNiveauForm(res);
                listProf.show();

            }
        });

        addAll(sp, sp2, btnValider, btnDelete);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
