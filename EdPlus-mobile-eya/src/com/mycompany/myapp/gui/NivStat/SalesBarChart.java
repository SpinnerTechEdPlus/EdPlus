/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.mycompany.myapp.gui.NivStat;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import entities.Niveau;
import services.ServiceClasse;
import services.ServiceNiveau;

/**
 * Sales demo bar chart.
 */
public class SalesBarChart extends AbstractDemoChart {

    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Sales horizontal bar chart";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The monthly sales for the last 2 years (horizontal bar chart)";
    }

    @Override
    public Component getChartModelEditor() {
        return null;
    }

    @Override
    public String getChartTitle() {
        return "";
    }

    @Override
    public Component execute() {
        String[] titles = new String[]{"", ""};
        List<double[]> values = new ArrayList<double[]>();
        ArrayList<Niveau> niveaux;
        niveaux = ServiceNiveau.getInstance().getAll();
        
        double[] nbclasses=new double[niveaux.size()];
        double a=0;
        ServiceClasse SC=new ServiceClasse();
       int ii=0;
        for (Niveau u : niveaux)
        { a=SC.getNombreClasse(u.getId());
            nbclasses[ii]=a;
            ii++;
             
        }
        
        for(int i=0;i<ii;i++){
            
            nbclasses[i]*=2;
            System.out.println("hedhi val mta3 ii"+nbclasses[i]); 
        }
      
        //System.out.println("test thiiis "+nbclasses);
            
        
        values.add(nbclasses);
        values.add(nbclasses);
        int[] colors = new int[]{ColorUtil.MAGENTA, ColorUtil.MAGENTA};
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        renderer.setOrientation(Orientation.HORIZONTAL);
        setChartSettings(renderer, "Le Nombre de classes par niveau", " Niveaux ", " Le Nombre * 2 ", 0.5,
                10, 0, 50, ColorUtil.GRAY, ColorUtil.LTGRAY);
        renderer.setXLabels(4);
        renderer.setYLabels(4);
       
        
        int compt=1;
        for (Niveau u : niveaux)
        { a=SC.getNombreClasse(u.getId());
           
            renderer.addXTextLabel( compt,u.getNom());
            compt++;
             
        }
        
      
        initRendererer(renderer);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }

        BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
                Type.DEFAULT);
        return newChart(chart);

    }

}
