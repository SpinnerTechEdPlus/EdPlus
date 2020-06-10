/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Niveau;

import com.mycompany.myapp.gui.Niveau.UpdateNiveauForm;
import com.mycompany.myapp.gui.Niveau.ListNiveauForm;
import com.codename1.components.SpanLabel;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.Classe.AddClasseForm;
import com.mycompany.myapp.gui.SideMenuBaseForm;
import entities.Niveau;
import java.io.IOException;
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
        sp.setText("Nom : " + n.getNom());
        sp.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        Button btnValider = new Button("modifier ce  Niveau");
        btnValider.setUIID("LoginButton");
        btnValider.addActionListener(e -> { 
            Media m;
             try {
                m = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/bip.mp3")), "audio/mpeg");
                m.play();
            } catch (IOException ex) {
                System.out.println("erreur" + ex.getMessage());
            }
            new UpdateNiveauForm(res, n, current).show();
        });

        Button btnDelete = new Button("supprimer ce Niveau");
        btnDelete.setUIID("LoginButton");
        Button btnAddClasse = new Button("ajouter Classe");
        btnAddClasse.setUIID("LoginButton");
        btnAddClasse.addActionListener(e -> {
            Media m;
            try {
                m = MediaManager.createMedia((Display.getInstance().getResourceAsStream(getClass(), "/bip.mp3")), "audio/mpeg");
                m.play();
            } catch (IOException ex) {
                System.out.println("erreur" + ex.getMessage());
            }
            new AddClasseForm(res, current, n).show();
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!Dialog.show("suppression ", "Voulez vous vraiment supprimer ce niveau ?", "Non", "Oui")) {

                    ServiceNiveau.getInstance().DeleteNiveau(n);
                    ListNiveauForm listProf = new ListNiveauForm(res);
                    listProf.show();
                }
            }
        });

        addAll(sp, sp2, btnValider, btnDelete, btnAddClasse);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
