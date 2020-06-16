package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import entities.Etudiant;
import entities.Examen;
import entities.Salle;
import services.ExamenService;

/**
 * A swipe tutorial for the application
 *
 * @author Shai Almog
 */
public class SalleForm extends Form {

    public SalleForm(Resources res) {
        super(new LayeredLayout());
        getTitleArea().removeAll();
        getTitleArea().setUIID("Container");

        setTransitionOutAnimator(CommonTransitions.createUncover(CommonTransitions.SLIDE_HORIZONTAL, true, 400));

        Tabs walkthruTabs = new Tabs();
        walkthruTabs.setUIID("Container");
        walkthruTabs.getContentPane().setUIID("Container");
        walkthruTabs.getTabsContainer().setUIID("Container");
        walkthruTabs.hideTabs();

        Image notes = res.getImage("salle2.png");
        if (Etudiant.salles.size() > 0) {
            
        int currentTheme = 1;
        for (Salle s : Etudiant.salles) {
            Label notesPlaceholder = new Label("", "ProfilePic");
            Label notesLabel = new Label(notes, "ProfilePic");
            Component.setSameHeight(notesLabel, notesPlaceholder);
            Component.setSameWidth(notesLabel, notesPlaceholder);
            Label bottomSpace = new Label();

            Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                    notesPlaceholder,
                     new Label("SALLE "+s.getId() , "WalkthruWhite"), 
                      new Label("N°"+s.getNumero(), "WalkthruWhite"), 
                    bottomSpace 
            ));
            tab1.setUIID("WalkthruTab" + currentTheme);
            currentTheme++;
            if (currentTheme == 7) {
                currentTheme = 1;
            }
            notesPlaceholder.getParent().replace(notesPlaceholder, notesLabel, CommonTransitions.createFade(1500));

            walkthruTabs.addTab("", tab1);
        }
        } else {

            {
                Label notesPlaceholder = new Label("", "ProfilePic");
                Label notesLabel = new Label(notes, "ProfilePic");
                Component.setSameHeight(notesLabel, notesPlaceholder);
                Component.setSameWidth(notesLabel, notesPlaceholder);
                Label bottomSpace = new Label();

                Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                        notesPlaceholder,
                        new Label("Auccune Salle!", "WalkthruWhite"), 
                        bottomSpace
                ));
                tab1.setUIID("WalkthruTab1");  
                notesPlaceholder.getParent().replace(notesPlaceholder, notesLabel, CommonTransitions.createFade(1500));

                walkthruTabs.addTab("", tab1);
            }
        }

        add(walkthruTabs);

        ButtonGroup bg = new ButtonGroup();
        Image unselectedWalkthru = res.getImage("unselected-walkthru.png");
        Image selectedWalkthru = res.getImage("selected-walkthru.png");
        RadioButton[] rbs = new RadioButton[walkthruTabs.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(CENTER);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        walkthruTabs.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Button skip = new Button("Revenir à l'Accueil");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new AccueilForm(res).show());

        Container southLayout = BoxLayout.encloseY(
                radioContainer,
                skip
        );
        add(BorderLayout.south(
                southLayout
        ));

        // visual effects in the first show 
    }
}
