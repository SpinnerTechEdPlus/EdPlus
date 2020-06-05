/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entities.Etudiant;
import entities.Seance;
import formMobile.myapp.MyApplication;
import static formMobile.myapp.MyApplication.theme;
import java.util.ArrayList;
import java.util.Date;
import services.SeanceService;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class CalendarForm extends Form {

    public CalendarForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public CalendarForm(com.codename1.ui.util.Resources resourceObjectInstance) {
         super(new BorderLayout());
        MyApplication.theme = UIManager.initFirstTheme("/themePh");
        resourceObjectInstance =    MyApplication.theme;
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        initGuiBuilderComponents(resourceObjectInstance);
        setLayout(BoxLayout.y());
        setScrollableY(true);
        getContentPane().setScrollVisible(false);
        getToolbar().setUIID("Container");
        Button b = new Button(" ");
        b.setUIID("Container");
        getToolbar().setTitleComponent(b);
        getTitleArea().setUIID("Container"); 
        gui_Calendar_1.setTwoDigitMode(true);
        ArrayList<Container> mys = new ArrayList<Container>();
        Button backA = new Button("Accueil","Button");
        gui_Calendar_1.addActionListener(e -> { 
        
          for ( Container c : mys) {
                removeComponent(c);
            }
            mys.clear();
            removeComponent(backA);
            Date d = gui_Calendar_1.getDate();
            SimpleDateFormat formater_d=new SimpleDateFormat("dd/MM/yyyy"); 
            String dString = formater_d.format(d);
            for (Seance s : Etudiant.seances)
            {   
                if(s.getJour().equals(dString)) { 
                   Container newC = createEntry(MyApplication.theme, true, s.getDebut(), s.getFin(), s.getSalleName() ,s.getMatiereName());
                   mys.add(newC);
                   add(newC);
                }
            }
            add(backA);
        
        });
        
        
        
        Picker p = new Picker();
        b.addActionListener(e -> {
            p.pressed(); 
            p.released();
             for ( Container c : mys) {
                removeComponent(c);
            }
            mys.clear();
            removeComponent(backA);
            Date d = gui_Calendar_1.getDate();
            SimpleDateFormat formater_d=new SimpleDateFormat("dd/MM/yyyy"); 
            String dString = formater_d.format(d);
            for (Seance s : Etudiant.seances)
            {   
                if(s.getJour().equals(dString)) { 
                   Container newC = createEntry(MyApplication.theme, true, s.getDebut(), s.getFin(), s.getSalleName() ,s.getMatiereName());
                   mys.add(newC);
                   add(newC);
                }
            }
            add(backA);
        });
        p.addActionListener(e -> {
            gui_Calendar_1.setCurrentDate(p.getDate());
            gui_Calendar_1.setSelectedDate(p.getDate());
            gui_Calendar_1.setDate(p.getDate());
            for ( Container c : mys) {
                removeComponent(c);
            }
            mys.clear();
            removeComponent(backA);
            Date d = gui_Calendar_1.getDate();
            SimpleDateFormat formater_d=new SimpleDateFormat("dd/MM/yyyy"); 
            String dString = formater_d.format(d);
            for (Seance s : Etudiant.seances)
            {   
                if(s.getJour().equals(dString)) { 
                   Container newC = createEntry(MyApplication.theme, true, s.getDebut(), s.getFin(), s.getSalleName() ,s.getMatiereName());
                   mys.add(newC);
                   add(newC);
                }
            }
            add(backA);
        });
         
         for ( Container c : mys) {
                removeComponent(c);
            }
            mys.clear();
            removeComponent(backA);
            Date d = gui_Calendar_1.getDate();
            SimpleDateFormat formater_d=new SimpleDateFormat("dd/MM/yyyy"); 
            String dString = formater_d.format(d);
            for (Seance s : Etudiant.seances)
            {   
                if(s.getJour().equals(dString)) { 
                   Container newC = createEntry(MyApplication.theme, true, s.getDebut(), s.getFin(), s.getSalleName() ,s.getMatiereName());
                   mys.add(newC);
                   add(newC);
                }
            }
        add(backA);
        backA.addActionListener( l -> { 
        
            MyApplication.theme = UIManager.initFirstTheme("/theme");
            new AccueilForm(MyApplication.theme).show();
        
        } );
        
        p.setFormatter(new SimpleDateFormat("MMMM"));
        p.setDate(new Date());
        p.setUIID("CalendarDateTitle");
        Container cnt = BoxLayout.encloseY(
                p,
                new Label(resourceObjectInstance.getImage("calendar-separator.png"), "CenterLabel")
        );
        
        BorderLayout bl = (BorderLayout)gui_Calendar_1.getLayout();
        Component combos = bl.getNorth();
        gui_Calendar_1.replace(combos, cnt, null); 
    }

    private Container createEntry(Resources res, boolean selected, String startTime, String endTime, String location, String title) {
        Component time = new Label(startTime, "CalendarHourUnselected");
        if(selected) {
            time.setUIID("CalendarHourSelected");
        }
        
        Container circleBox = BoxLayout.encloseY(new Label(res.getImage("label_round-selected.png")),
                new Label("-", "OrangeLine"),
                new Label("-", "OrangeLine")
        );
        
        Container cnt = new Container(BoxLayout.x());
        
        Container mainContent = BoxLayout.encloseY(
                BoxLayout.encloseX(
                        new Label(title, "SmallLabel"),  
                cnt
        ));
        
        Label redLabel = new Label("", "RedLabelRight");
        Container loc = BoxLayout.encloseY(
                redLabel,
                new Label("Fin : "+endTime, "TinyBoldLabel"),
                new Label("Salle : "+location, "TinyBoldLabel")
        );
        
        mainContent= BorderLayout.center(mainContent).
                add(BorderLayout.WEST, circleBox);
         
        return BorderLayout.center(mainContent).
                add(BorderLayout.WEST, FlowLayout.encloseCenter(time)).
                add(BorderLayout.EAST, loc);
    }
    
//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Calendar gui_Calendar_1 = new com.codename1.ui.Calendar();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.GridLayout(2, 1));
        setTitle("");
        setName("CalendarForm");
        addComponent(gui_Calendar_1);
        gui_Calendar_1.setName("Calendar_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
 
    protected boolean isCurrentCalendar() {
        return true;
    }

    @Override
    protected void initGlobalToolbar() {
        setToolbar(new Toolbar(true));
    }
 
    protected void showOtherForm(Resources res) {
     }


    
}
