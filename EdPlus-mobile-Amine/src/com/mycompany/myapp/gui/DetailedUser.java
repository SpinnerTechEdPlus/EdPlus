/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import services.ServiceUser;

/**
 *
 * @author medam
 */
public class DetailedUser extends Form {

    public DetailedUser(Resources res, Form previous, User user, String role) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

        setTitle(user.getUsername());
        // setLayout(BoxLayout.y());
        Form current = this;
        SpanLabel sp = new SpanLabel();
        SpanLabel sp2 = new SpanLabel();
        SpanLabel sp3 = new SpanLabel();

        sp.setText("Name : " + user.getUsername());
        sp.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        sp2.setText("Email : " + user.getEmail());
        sp2.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));

        if (user.getBirthDate() != null) {
            sp3.setText("age : " + ServiceUser.getInstance().getAge(user.getBirthDate()));
        }
        sp3.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
        // System.out.println("age "+user.getBirthDate());

        Button btnValider = new Button("Update User");
        btnValider.setUIID("LoginButton");
        btnValider.addActionListener(e -> {
            try {
                new UpdateUserForm(res, current, user, role).show();
            } catch (IOException ex) {
                System.out.println("idk ");
            }
        });

        Button btnDelete = new Button("Delete User");
        btnDelete.setUIID("LoginButton");

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (!Dialog.show("Delete", "Are you sure you want to delete this user ?", "No", "Yes")) {

                    ServiceUser.getInstance().DeleteUser(user);
                    ListProfForm listProf = new ListProfForm(res, role);
                    listProf.show();
                }

            }
        });

        if (user.getBirthDate() != null) {
            

            Container by = BoxLayout.encloseY(
                    sp, sp2, sp3
            

            );
            by.setScrollableY(true);
            by.setScrollVisible(false);
            add(BorderLayout.NORTH, by);
        } else {
            Container by = BoxLayout.encloseY(
                    sp, sp2
            

            );
            
            add(BorderLayout.NORTH, by);
        }

        Container btns = BoxLayout.encloseY(
                

                btnValider, btnDelete
        );
        add(BorderLayout.SOUTH, btns);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
