

package services;



import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Examen;
import utils.Statics;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExamenService {
    
    public ArrayList<Examen> exams;
    
    public static ExamenService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ExamenService() {
         req = new ConnectionRequest();
    }

    public static ExamenService getInstance() {
        if (instance == null) {
            instance = new ExamenService();
        }
        return instance;
    }
    
    public ArrayList<Examen> parseExams(String jsonText){
        try {
            exams=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){ 
                
            int id = (int) Float.parseFloat(obj.get("id").toString()); 
            
            LinkedHashMap salle =  ( LinkedHashMap )obj.get("salle");
            LinkedHashMap matiere = ( LinkedHashMap )obj.get("matiere");
            LinkedHashMap classe = ( LinkedHashMap )obj.get("classe");
            
            int salleId = Double.valueOf(salle.get("id").toString()).intValue();
            String salleName = salle.get("nom").toString();
            
            int matiereId = Double.valueOf(matiere.get("id").toString()).intValue();
            String matiereName = matiere.get("nom").toString();
            
            int classeId = Double.valueOf(classe.get("id").toString()).intValue();
            String classeName = classe.get("nom").toString();
            
            LinkedHashMap datefin = (LinkedHashMap) obj.get("horaire");
            
            Double date_f=(Double)datefin.get("timestamp");
                   
            Date Hr = new Date(((date_f.longValue())*1000)-3600000);
            
            SimpleDateFormat formater=new SimpleDateFormat("dd/MM/yyyy à HHmm");
            String Horairesec = formater.format(Hr);
            String horaire = insertString(Horairesec, "h", 14);
            
            
            String semestre = obj.get("semestre").toString();
                
                
            Examen e = new Examen(id, salleId, matiereId, classeId,horaire,semestre);
            e.setClasseName(classeName);
            e.setMatiereName(matiereName);
            e.setSalleName(salleName);
            exams.add(e);
            }
            
            
        } catch (IOException ex) {
            
        }
        return exams;
    }
    
    
    
    public ArrayList<Examen> getAllExams(int classeId){
        String url = Statics.BASE_URL+"/m/classe/"+classeId+"/examens";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                exams = parseExams(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return exams;
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
