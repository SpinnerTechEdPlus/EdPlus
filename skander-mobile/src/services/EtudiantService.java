

package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Etudiant;
import entities.Examen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import utils.Statics;

public class EtudiantService {

    public ArrayList<Etudiant> etudiants;
    
    public static EtudiantService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private EtudiantService() {
         req = new ConnectionRequest();
    }

    public static EtudiantService getInstance() {
        if (instance == null) {
            instance = new EtudiantService();
        }
        return instance;
    }
    
    public boolean parseStudents(String jsonText){
        try {
            etudiants=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){ 
                
            Etudiant.id = (int) Float.parseFloat(obj.get("id").toString()); 
             
            LinkedHashMap classe = ( LinkedHashMap )obj.get("classe");
            
            Etudiant.classeId = Double.valueOf(classe.get("id").toString()).intValue();
            Etudiant.classeName = classe.get("nom").toString();
            Etudiant.email = obj.get("mail").toString();
            Etudiant.nom = obj.get("nom").toString();
            Etudiant.tel = (int) Float.parseFloat(obj.get("tel").toString()); 
            Etudiant.password = obj.get("password").toString();
            return true;
            }
            
            
        } catch (IOException ex) {
            return false;
        }
        return false; 
    }
    
    
    
    public boolean getStudentConnection(String email, String password ){
        String url = Statics.BASE_URL+"/m/etudiant/"+email+"/"+password;
        req.setUrl(url);
        req.setPost(false);
        Etudiant.connected = false ; 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Etudiant.connected = parseStudents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  Etudiant.connected;
    }
    

}
