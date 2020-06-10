/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.regex.RE;
import entities.User;
import services.ServiceUser;
import utils.UserSession;

/**
 *
 * @author medam
 */
public class SignUpForm extends Form {

    public SignUpForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");
        Container welcome = FlowLayout.encloseCenter(
        );
        
        Image LogoPic = theme.getImage("Image1.png");
      
        Label LogoPicLabel = new Label(LogoPic, "ProfilePicSignUp");
        
        TextField login = new TextField("", "Nom d'utilisateur", 20, TextField.EMAILADDR);
        TextField email = new TextField("", "Adresse mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirmation", 20, TextField.PASSWORD);

        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        Label mailIcon = new Label("", "TextField");
        Label confirmPWIcon = new Label("", "TexField");

        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        email.getAllStyles().setMargin(LEFT, 0);
        confirmPassword.getAllStyles().setMargin(LEFT, 0);

        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        mailIcon.getAllStyles().setMargin(RIGHT, 0);
        confirmPWIcon.getAllStyles().setMargin(LEFT, 0);

        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(confirmPWIcon, FontImage.MATERIAL_SECURITY, 3);
        FontImage.setMaterialIcon(mailIcon, FontImage.MATERIAL_MAIL, 3);

        Button confirmSignUp = new Button("CONFIRMER");
        confirmSignUp.setUIID("LoginButton");

        RE emailMatcher = new RE("[a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}");
            
        confirmSignUp.addActionListener(e -> {
        
        if (login.getText().equals("") || password.getText().equals("") || email.getText().equals("")) {
            Dialog.show("Erreur de validation", "veuillez renseigner tous les champs", "OK", null);
            return;
        }

        if (!emailMatcher.match(email.getText())) {
            Dialog.show("Erreur de validation", "Vérifiez votre adresse mail", "OK", null);
            return;
        }
        if (!password.getText().equals(confirmPassword.getText())) {
            Dialog.show("Erreur de validation", "Vérifiez vos mot de passe ", "OK", null);
            return;
        }
        if (password.getText().length() < 5) {
            Dialog.show("Erreur de validation", "Mot de passe faible", "OK", null);
            return;
        }

        User NewProf = new User();
        NewProf.setEmail(email.getText());
        NewProf.setPassword(password.getText());
        NewProf.setUsername(login.getText());
        NewProf.setRoles("ecole");
        //NewProf.setBirthDate(dateNaissance.getDate());
        ServiceUser.getInstance().AddUser(NewProf, false);
        
         if(NewProf != null){
                UserSession.getInstance(NewProf);
              //  H p = new ProfileForm(theme,result);
              
              if(NewProf.getRoles()=="ecole"){
               ProfileForm home=new ProfileForm(theme);
               home.show();
              }
        
        }
         
        });

        Button exitSignUp = new Button("Annuler");
        exitSignUp.setUIID("LoginButton");
        exitSignUp.addActionListener((evt) -> {
            Toolbar.setGlobalToolbar(false);
            new LoginForm(theme).show();
        });

        Container by = BoxLayout.encloseY(
                welcome,
                LogoPicLabel,
                BorderLayout.center(login).
                add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(email).
                add(BorderLayout.WEST, mailIcon),
                BorderLayout.center(password).
                add(BorderLayout.WEST, passwordIcon),
                BorderLayout.center(confirmPassword).
                add(BorderLayout.WEST, confirmPWIcon),
                BorderLayout.center(confirmSignUp).
                add(BorderLayout.WEST, exitSignUp)
        );
        add(BorderLayout.CENTER, by);

        

    }
}
