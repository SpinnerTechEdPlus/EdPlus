

package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Bloc;
import entities.Examen;
import entities.Matiere;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import utils.Statics;

public class BlocService {

    public ArrayList<Bloc> blocs;
    
    public static BlocService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private BlocService() {
         req = new ConnectionRequest();
    }

    public static BlocService getInstance() {
        if (instance == null) {
            instance = new BlocService();
        }
        return instance;
    }
    
    public ArrayList<Bloc> parseBlocs(String jsonText){
        try {
            blocs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){ 
                
            int id = (int) Float.parseFloat(obj.get("id").toString());  
            
            String nom = obj.get("nom").toString();
             int nbetages = (int) Float.parseFloat(obj.get("nbetages").toString());  
  
                
            Bloc b = new Bloc(id, nom,nbetages);
            blocs.add(b);
            
            
            }
            
            
        } catch (IOException ex) {
            
        }
        return blocs;
    }
    
    
    
    public ArrayList<Bloc> getAllBlocs(){
        String url = "http://localhost/getBlocs.php";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                blocs = parseBlocs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return blocs;
    }
    
     public static String insertString( 
        String originalString, 
        String stringToBeInserted, 
        int index) 
    { 
  
        // Create a new string 
        String newString = new String(); 
  
        for (int i = 0; i < originalString.length(); i++) { 
  
            // Insert the original string character 
            // into the new string 
            newString += originalString.charAt(i); 
  
            if (i == index) { 
  
                // Insert the string to be inserted 
                // into the new string 
                newString += stringToBeInserted; 
            } 
        } 
  
        // return the modified String 
        return newString; 
    } 
}
