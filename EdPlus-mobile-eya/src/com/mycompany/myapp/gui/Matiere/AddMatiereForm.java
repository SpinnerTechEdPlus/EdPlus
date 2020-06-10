/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Matiere;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.SOUTH;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.GroupConstraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.gui.SideMenuBaseForm;
import entities.Matiere;
import java.io.IOException;
import services.ServiceMatiere;

/**
 *
 * @author Eya
 */
public class AddMatiereForm extends SideMenuBaseForm {

    MultiButton photo = new MultiButton("");
    String path = null;
    Form current;

    public AddMatiereForm(Resources res, Form previous) {

        setTitle("Ajouter une Matiere");
        setLayout(BoxLayout.y());
        current = this;

       
        TextField tfNom = new TextField("", "Le nom du Matiere");

        Button btnValider = new Button("Ajouter une Matiere");
        FontImage.setMaterialIcon(btnValider, FontImage.MATERIAL_DONE);
        btnValider.setUIID("LoginButton");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0)) {
                    Dialog.show("Alert", "Veillez ajouter une matière", new Command("OK"));
                } else {
                    Matiere NewMatiere = new Matiere();

                    NewMatiere.setNom(tfNom.getText());
                    //NewMatiere.setPath(path);
                    ServiceMatiere.getInstance().AddMatiere(NewMatiere);
                    
                    ListMatiereForm list = new ListMatiereForm(res);
                    list.show();

                }

            }
        });

        addAll(tfNom, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        Validator val = new Validator();
        val.setShowErrorMessageForFocusedComponent(true);
        val.addConstraint(tfNom,
                new GroupConstraint(
                        new LengthConstraint(2, "Minimum 2 caracteres"),
                        new RegexConstraint("^([a-zA-Z ÉéèÈêÊôÔ']*)$", "Veuillez saisir que des caracteres"))
        );

    }

}
