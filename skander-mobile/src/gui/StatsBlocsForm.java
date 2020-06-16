  
package gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Bloc;
import entities.Etudiant;
import entities.Matiere; 
import java.util.ArrayList;
import services.MatiereService;
import services.NoteService;
 
public class StatsBlocsForm extends SideMenuBaseForm {
    
        int tot = 0 ;
    public StatsBlocsForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
         Image profilePic = res.getImage("WHITE LOGO.png");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight() );
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
      //  profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container remainingTasks = BoxLayout.encloseY(
                        new Label(Etudiant.blocs.size()+"", "CenterTitle"),
                        new Label("Blocs", "CenterSubTitle")
                );
        
         for( Bloc e : Etudiant.blocs) {  tot = tot + e.getNbetages(); } 
        remainingTasks.setUIID("RemainingTasks"); 
        Container completedTasks = BoxLayout.encloseY(
                        new Label(""+tot, "CenterTitle"),
                        new Label("Etages", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(Etudiant.nom, "Title"),
                                    new Label(Etudiant.classeName, "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_LOGOUT);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        fab.addActionListener(l -> new LoginForm(res).show());
        
         
        

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.GREEN,  ColorUtil.YELLOW,  ColorUtil.MAGENTA,  ColorUtil.CYAN };
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(160);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
     
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("BLOCS ET NOMBRE D'ETAGES", Etudiant.blocs), renderer);

    // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
     add(c); 
 Button skip = new Button("Accueil");
        skip.setUIID("SkipButton");
        skip.addActionListener(e -> new AccueilForm(res).show());
        add(skip); 
         
         
        
         
        setupSideMenu(res);
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
    
    
    
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(60);
    renderer.setLegendTextSize(60);
    renderer.setLabelsColor(0x778c6);
  
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, ArrayList<Bloc> blocs) {
    CategorySeries series = new CategorySeries(title);
    
    for (Bloc b : Etudiant.blocs) {
        series.add("B "+b.getNom(), b.getNbetages());
    }

    return series;
}
 
    // Generate the values
  
    
}
