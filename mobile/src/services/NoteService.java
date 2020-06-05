

package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Examen;
import entities.Note;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class NoteService {
    
    
    
    public ArrayList<Note> notes;
    
    public static NoteService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private NoteService() {
         req = new ConnectionRequest();
    }

    public static NoteService getInstance() {
        if (instance == null) {
            instance = new NoteService();
        }
        return instance;
    }
    
    public ArrayList<Note> parseNotes(String jsonText){
        try {
            notes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){ 
                
            int id = (int) Float.parseFloat(obj.get("id").toString()); 
            
            LinkedHashMap etudiant =  ( LinkedHashMap )obj.get("etudiant");
            LinkedHashMap examen = ( LinkedHashMap )obj.get("examen");
            
            LinkedHashMap matiere = ( LinkedHashMap )examen.get("matiere");
            
            int etudiantId = Double.valueOf(etudiant.get("id").toString()).intValue();
            String etudiantName = etudiant.get("nom").toString(); int examenId = Double.valueOf(examen.get("id").toString()).intValue();
            String examenName = matiere.get("nom").toString() + " S" + examen.get("semestre").toString();
            float note  = Float.parseFloat(obj.get("note").toString()); 
                
                
            Note n = new Note(id, etudiantId, examenId, note);
            n.setExamenName(examenName);
            n.setEtudiantName(etudiantName);
            notes.add(n);
            }
            
            
        } catch (IOException ex) {
            
        }
        return notes;
    }
    
    
    
    public ArrayList<Note> getAllNotes(int studentId){
        String url = Statics.BASE_URL+"/m/etudiant/"+studentId+"/notes";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                notes = parseNotes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return notes;
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
