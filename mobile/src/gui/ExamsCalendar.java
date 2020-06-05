

package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.Examen;
import services.ExamenService;
import utils.Statics;

public class ExamsCalendar extends Form {
    
    private Resources theme=UIManager.initFirstTheme("/theme");
    
    public ExamsCalendar(){ 
        this.setTitle("Calendrier des examens");
      //  this.getAllStyles().setBgImage(theme.getImage("bg.jpg"));
        this.setLayout(BoxLayout.yCenter());
        
        getToolbar().addCommandToSideMenu("",null,(e)->{});
        getToolbar().addCommandToSideMenu("RECHERCHER",theme.getImage("bg.jpg").scaled(90, 90),(e)->{});
         
          getToolbar().addCommandToSideMenu("mes reservation ", theme.getImage("bg.jpg").scaled(90, 90),(e)->{});
          getToolbar().addCommandToSideMenu("HUNTKINDGOM ", theme.getImage("bg.jpg").scaled(90, 90),(e)->{});
          
          getToolbar().addCommandToOverflowMenu("tetae", theme.getImage("bg.jpg").scaled(90, 90),(e)->{});
          getToolbar().addCommandToOverflowMenu("se deconnecter", theme.getImage("bg.jpg").scaled(90, 90),(e)->{});
        for(Examen e : ExamenService.getInstance().getAllExams(1))
        {   ImageViewer imagef=new ImageViewer(theme.getImage("divider-lines-png-7.png").scaled(1250, 250));
            this.add(imagef);
            this.add(createForm(e));
        }
    }
    
    public Container createForm(Examen e)
    { 
        Container cY = new Container(BoxLayout.yCenter());
        Container cSemestreSalle = new Container(BoxLayout.xCenter());
        //Container cHoraire = new Container(BoxLayout.x());

        Label examName = new Label(e.getMatiereName());
        
        Label semestreName = new Label(e.getSemestre());
        Label salleName = new Label(e.getSalleName());
        
        Label horaire = new Label(e.getHoraire());
        
        cSemestreSalle.addAll(semestreName,salleName);
        
        Button b = new Button("Button");
        cY.addAll(examName,cSemestreSalle,horaire,b); 
        
        
        //cY.setLeadComponent(b);
        
        b.addActionListener(l -> {  Dialog.show("Delai depassé", "Delai depassé il reste seulement 1 jour", new Command("OK")); } );
        return cY;
    }
    
}
