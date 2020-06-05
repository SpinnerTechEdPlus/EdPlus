

package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Examen;
import entities.Matiere;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import utils.Statics;

public class MatiereService {

    public ArrayList<Matiere> matieres;
    
    public static MatiereService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private MatiereService() {
         req = new ConnectionRequest();
    }

    public static MatiereService getInstance() {
        if (instance == null) {
            instance = new MatiereService();
        }
        return instance;
    }
    
    public ArrayList<Matiere> parseMatieres(String jsonText){
        try {
            matieres=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){ 
                
            int id = (int) Float.parseFloat(obj.get("id").toString());  
            
            String nom = obj.get("nom").toString();
                
                
            Matiere m = new Matiere(id, nom);
            matieres.add(m);
            
            
            }
            
            
        } catch (IOException ex) {
            
        }
        return matieres;
    }
    
    
    
    public ArrayList<Matiere> getAllMatieres(int classeId){
        String url = Statics.BASE_URL+"/m/classe/"+classeId+"/matieres";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                matieres = parseMatieres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return matieres;
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
