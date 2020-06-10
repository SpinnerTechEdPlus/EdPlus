/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        String roleProf="professeur";
        String roleEtu="etudiant";
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddProf = new Button("Ajouter un niveau");
        Button btnAddEtu = new Button("Ajouter un etudiant");

        Button btnListProf = new Button("la liste des niveaux");
        Button btnListEtu = new Button("La liste des etudiants");

        
        btnAddProf.addActionListener(e-> new AddUserForm(current,roleProf).show());
        btnAddEtu.addActionListener(e-> new AddUserForm(current,roleEtu).show());

        btnListProf.addActionListener(e-> new ListProfForm(current,roleProf).show());
        btnListEtu.addActionListener(e-> new ListProfForm(current,roleEtu).show());

        addAll(btnAddProf,btnListProf,btnAddEtu,btnListEtu);
        
        
    }
    
    
}
