/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.NivStat;

import com.codename1.media.Media;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.SideMenuBaseForm;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class ChartNiv extends SideMenuBaseForm {

    Form current;
    Media m;

    public ChartNiv(Resources res) throws IOException {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        SalesBarChart charts = new SalesBarChart();
        Component a = charts.execute();

     

        

        addAll(a);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_MENU, e -> getToolbar().openSideMenu());
        setupSideMenu(res);

        // ----------------
        // -------------------------------- 
    }

    @Override
    protected void showOtherForm(Resources res) {
        // new StatsForm(res).show();
    }

}
