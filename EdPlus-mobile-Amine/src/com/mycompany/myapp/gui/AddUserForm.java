/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.gif.GifImage;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getResourceAsStream;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import entities.User;
import java.io.IOException;
import services.ServiceUser;
import utils.UserSession;


/**
 *
 * @author bhk
 */
public class AddUserForm extends Form{
    public AddUserForm(Resources res,Form previous,String role) throws IOException {
        
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setTitle("Add a user");
        //etLayout(BoxLayout.y());
        Form current=this;
        TextField tfUsername = new TextField("","Username");
        TextField tfEmail = new TextField("","Email");
        
        
        TextField tfPassword = new TextField("", "Password",20,TextField.PASSWORD) ;
        
        TextField tfconfirmPassword = new TextField("", "Confirmation", 20, TextField.PASSWORD);

        
       
        
        
       
            
        
        
        Picker dateNaissance = new Picker();
        dateNaissance.setFormatter(new SimpleDateFormat("yyyy-MM-dd"));
         CheckBox cb1 = new CheckBox("Send email");
        //String role="professeur";
        
        
        
        //Label loadingIcon = new Label(FontImage.createMaterial(FontImage.MATERIAL_3D_ROTATION, "Label", 5));

       // Image img = res.getImage("loading_gif.gif");
        //loadingIcon.setIcon(img);
        
       ScaleImageLabel loadingIcon= new ScaleImageLabel(GifImage.decode(getResourceAsStream("/loading.gif"), 1177720));
        
loadingIcon.getAllStyles().setBgTransparency(0);

Dialog loadingAnimation = new Dialog();
loadingAnimation.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
Style dlgStyle = loadingAnimation.getDialogStyle();
dlgStyle.setBorder(Border.createEmpty());
dlgStyle.setBgTransparency(0);
loadingAnimation.add(BorderLayout.CENTER, loadingIcon);
        
        
        
        
        
        Button btnValider = new Button("Add User");
        btnValider.setUIID("LoginButton");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
                if ((tfUsername.getText().length()==0)||(tfPassword.getText().length()==0) || tfEmail.getText().length()==0)
                {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                     return;
                }
                if(Integer.parseInt(ServiceUser.getInstance().getAge(dateNaissance.getDate()))<=10){
                         Dialog.show("Alert", "Please check birth date", new Command("OK"));
                         return;}
                
                
                 if(!tfPassword.getText().equals(tfconfirmPassword.getText())){
                Dialog.show("Alert", "Please check your password ", "OK", null);
                return;
            }
            if(tfPassword.getText().length()<5){
                Dialog.show("Alert", "Weak password", "OK", null);
                return;
            }

                
                
                    
                    loadingAnimation.showModeless();
                    
                    
                    
                    
                 User NewProf=new User();   
                 NewProf.setEmail(tfEmail.getText());
                 NewProf.setPassword(tfPassword.getText());
                 NewProf.setUsername(tfUsername.getText());
                 NewProf.setRoles(role);
                 NewProf.setBirthDate(dateNaissance.getDate());
                 NewProf.setUser_id(UserSession.getUser().getId());
                 ServiceUser.getInstance().AddUser(NewProf,cb1.isSelected());
                 ListProfForm listProf=new ListProfForm(res,role);
                 
                   
                 
                listProf.show();

                
                
    // Remove the UITimer and add your network tasks
    // Call loadingAnimation.dispose() when the tasks are ended
    new UITimer(() -> {
        loadingAnimation.dispose();
    }).schedule(5000, false, loadingAnimation);
                 

                }
                
                
            }
        );
        
       //S$ addAll(tfUsername,tfEmail,tfPassword,dateNaissance,cb1,btnValider);
        
        Container by = BoxLayout.encloseY(
                    tfUsername, tfEmail,tfPassword,tfconfirmPassword,dateNaissance,cb1
            

            );
            
            add(BorderLayout.NORTH, by);
        
        Container btns = BoxLayout.encloseY(
                

                btnValider
        );
        add(BorderLayout.SOUTH, btns);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
