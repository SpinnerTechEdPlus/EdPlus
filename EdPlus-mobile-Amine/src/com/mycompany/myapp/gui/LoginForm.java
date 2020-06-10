/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.gif.GifImage;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getResourceAsStream;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.mycompany.myapp.gui.Etudiant.DetailedEtudiant;
import com.mycompany.myapp.gui.Professeur.DetailedProfesseur;
import com.mycompany.myapp.gui.ProfileForm;
import entities.User;
import java.io.IOException;


import java.util.List;

import services.ServiceUser;
import utils.UserSession;

/**
 *
 * @author Yassine Ben Hamida
 * Game of threads 2019 - MIT License 
 */
public class LoginForm extends Form {
     ServiceUser SU=new ServiceUser();
    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
        ); 
        getTitleArea().setUIID("Container");
      
        
        Image LogoPic = theme.getImage("Image1.png");
      
        Label LogoPicLabel = new Label(LogoPic, "ProfilePicSignUp");
        
        TextField login = new TextField("esprit", "Nom d'utilisateur", 20, TextField.EMAILADDR) ;
        TextField password = new TextField("esprit", "Mot de passe", 20, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        Button loginButton = new Button("Se connecter");
        loginButton.setUIID("LoginButton");
      
        
        try{
        loginButton.addActionListener(e -> {
            User result = SU.verifyLoginCreds(login.getText(), password.getText());
            if(result != null){
                UserSession.getInstance(result);
              //  H p = new ProfileForm(theme,result);
              
              if(result.getRoles()=="ecole"){
               //HomeForm home=new HomeForm(theme);
               ProfileForm home=new ProfileForm(theme);
               home.show();
               
              
              }
              else  if(result.getRoles()=="etudiant")
              {
                  DetailedEtudiant detail=new DetailedEtudiant(theme,this,UserSession.getUser(),UserSession.getUser().getRoles());
                  detail.show();
              }
                
                else  if(result.getRoles()=="professeur")
              {
                  DetailedProfesseur detail=new DetailedProfesseur(theme,this,UserSession.getUser(),UserSession.getUser().getRoles());
                  detail.show();
              }
                
                
               // ProfileForm home=new ProfileForm(theme);
               //home.show();
                Toolbar.setGlobalToolbar(true);
            //System.out.println(result);
            }
            else Dialog.show("Erreur de connexion", "Vérifiez votre mot de passe ou e-mail", "OK", null);
        });
        }catch(Exception e){
            Dialog.show("Erreur de connexion", "Vérifiez votre mot de passe ou e-mail", "OK", null);
        }
        
        
        Button createNewAccount = new Button("CREER UN COMPTE");
        createNewAccount.setUIID("LoginButton");
        createNewAccount.addActionListener((evt) -> {
                new SignUpForm(theme).show();             
        });
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                LogoPicLabel,
                
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
                
                
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
    
    
}
