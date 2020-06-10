/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Matiere;

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
import entities.Matiere;
import services.ServiceMatiere;

/**
 *
 * @author Eya
 */
public class UpdateMatiereForm extends SideMenuBaseForm {

    public UpdateMatiereForm(Resources res, Matiere m, Form previous) {

        setTitle("modifier une Matiere");
        setLayout(BoxLayout.y());

        Form current = this;
         TextField tfNom = new TextField("", "Le nom du Matiere");
        TextField tfimage = new TextField("", "Le numéro de la classe");
        int id = m.getId();
        
        Button btnValider = new Button("Modifier la Matiere");
        btnValider.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    Matiere NewMatiere = new Matiere();
                    
            
                    NewMatiere.setNom(tfNom.getText());
                    NewMatiere.setNom_image(tfimage.getText());
                    ServiceMatiere.getInstance().AddMatiere(NewMatiere);
                    ListMatiereForm list = new ListMatiereForm(res);
                    list.show();

                }

            }
        });
        addAll(tfNom, tfimage, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    
}
