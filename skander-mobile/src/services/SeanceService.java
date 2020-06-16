

package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Examen;
import entities.Seance;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class SeanceService {
    
    
    public ArrayList<Seance> seances;
    
    public static SeanceService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private SeanceService() {
         req = new ConnectionRequest();
    }

    public static SeanceService getInstance() {
        if (instance == null) {
            instance = new SeanceService();
        }
        return instance;
    }
    
    public ArrayList<Seance> parseSeances(String jsonText){
        try {
            seances=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){  
                
            int id = (int) Float.parseFloat(obj.get("id").toString()); 
         
            LinkedHashMap salle =  ( LinkedHashMap )obj.get("salle");
            int salleId = Double.valueOf(salle.get("id").toString()).intValue();
            String salleName  = salle.get("nom").toString();
           
            LinkedHashMap matiere = ( LinkedHashMap )obj.get("matiere");
            int matiereId = Double.valueOf(matiere.get("id").toString()).intValue();
            String matiereName = matiere.get("nom").toString();
           
            LinkedHashMap professeur = ( LinkedHashMap )obj.get("professeur");
            int profId = Double.valueOf(professeur.get("id").toString()).intValue();
            String profName = professeur.get("nom").toString();
            
            LinkedHashMap classe = ( LinkedHashMap )obj.get("classe");
            int classeId = Double.valueOf(classe.get("id").toString()).intValue();
            String classeName = classe.get("nom").toString();
            
            LinkedHashMap jour = (LinkedHashMap) obj.get("jour");
            Double date_jour=(Double)jour.get("timestamp");
            Date jourd = new Date(((date_jour.longValue())*1000)-3600000);
            SimpleDateFormat formater_jour=new SimpleDateFormat("dd/MM/yyyy");
            String jourString = formater_jour.format(jourd); 
            
            LinkedHashMap debut = (LinkedHashMap) obj.get("debut");
            Double date_debut=(Double)debut.get("timestamp");
            Date debutd = new Date(((date_debut.longValue())*1000)-3600000);
            SimpleDateFormat formater_debut=new SimpleDateFormat("HHmm");
            String debutNotString = formater_debut.format(debutd);
            String debutString = insertString(debutNotString, "h", 1);
            
            LinkedHashMap fin = (LinkedHashMap) obj.get("fin");
            Double date_fin=(Double)fin.get("timestamp");
            Date find = new Date(((date_fin.longValue())*1000)-3600000);
            SimpleDateFormat formater_fin=new SimpleDateFormat("HHmm");
            String finNotString = formater_fin.format(find);
            String finString = insertString(finNotString, "h", 1);
                
            Seance s = new Seance(id, salleId, matiereId, profId, classeId, jourString, debutString, finString);
            s.setClasseName(classeName);
            s.setMatiereName(matiereName);
            s.setProfesseurName(profName);
            s.setSalleName(salleName);
            
            seances.add(s);
            }
            
            
        } catch (IOException ex) {
            
        }
        return seances;
    }
    
    
    
    public ArrayList<Seance> getAllSeances(int classeId){
        String url = Statics.BASE_URL+"/m/classe/"+classeId+"/seances";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                seances = parseSeances(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return seances;
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
