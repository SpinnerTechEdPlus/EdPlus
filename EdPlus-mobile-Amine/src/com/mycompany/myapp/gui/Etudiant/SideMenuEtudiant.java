/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Etudiant;

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.ListProfForm;
import com.mycompany.myapp.gui.LoginForm;
import utils.UserSession;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuEtudiant extends Form {
    
    String roleProf="professeur";
        String roleEtu="etudiant";

    public SideMenuEtudiant(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuEtudiant(String title) {
        super(title);
    }

    public SideMenuEtudiant() {
    }

    public SideMenuEtudiant(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu(Resources res) {
        Image IconImage = res.getImage("IconEDplusWhite.png").scaled(500, 500);

        Label profilePicLabel = new Label("", IconImage, "SideMenuTitle");
        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Home", FontImage.MATERIAL_DASHBOARD, e -> new DetailedEtudiant(res,this,UserSession.getUser(),UserSession.getUser().getRoles()));
        getToolbar().addMaterialCommandToSideMenu("  Check note", FontImage.MATERIAL_PEOPLE, e ->{});
        /*getToolbar().addMaterialCommandToSideMenu("  Niveaux", FontImage.MATERIAL_SIGNAL_CELLULAR_ALT, e -> new ListProfForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Classes", FontImage.MATERIAL_CLASS, e -> new ListProfForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Matières", FontImage.MATERIAL_SUBJECT, e -> new ListProfForm(res).show());*/

        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e ->{
            UserSession.disconnectFromApp();
            new LoginForm(res).show();});
    }

    protected void showOtherForm(Resources res) {
        // new StatsForm(res).show();
    }

}
