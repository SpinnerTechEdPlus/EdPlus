

package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Bloc;
import entities.Etage;
import entities.Examen;
import entities.Matiere;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import utils.Statics;

public class EtageService {

    public ArrayList<Etage> etages;
    
    public static EtageService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private EtageService() {
         req = new ConnectionRequest();
    }

    public static EtageService getInstance() {
        if (instance == null) {
            instance = new EtageService();
        }
        return instance;
    }
    
    public ArrayList<Etage> parseEtages(String jsonText){
        try {
            etages=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){ 
                
            int id = (int) Float.parseFloat(obj.get("id").toString());  
            int numero = (int) Float.parseFloat(obj.get("numero").toString());  
            int nbsalle = (int) Float.parseFloat(obj.get("nbsalle").toString());  
  
                
            Etage e = new Etage(id, numero, nbsalle);
            etages.add(e);
            
            
            }
            
            
        } catch (IOException ex) {
            
        }
        return etages;
    }
    
    
    
    public ArrayList<Etage> getAllEtages(){
        String url = "http://localhost/getEtages.php";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                etages = parseEtages(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return etages;
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
