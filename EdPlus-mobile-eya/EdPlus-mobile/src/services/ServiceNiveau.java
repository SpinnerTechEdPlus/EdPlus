/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Niveau;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;
import static utils.Statics.BASE_URL;

/**
 *
 * @author Eya
 */
public class ServiceNiveau {

    private ConnectionRequest req;
    //ArrayList<Niveau> NiveauList = new ArrayList<>();
    public static ServiceNiveau instance = null;

    public static ServiceNiveau getInstance() {
        if (instance == null) {
            instance = new ServiceNiveau();
        }
        return instance;
    }

    private ServiceNiveau() {
        req = new ConnectionRequest();
    }
    public ArrayList<Niveau> Niveaux;

    
    
   /* public Niveau parseSimpleJSON(String json) {
        Niveau parsedData = new Niveau();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));           
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
            System.out.println("ParseSimple list: " + list);
            for (Map<String, Object> obj : list) {      
                parsedData.setNom(obj.get("nom").toString());
                parsedData.setId((int)Float.parseFloat(obj.get("id").toString()));
                 parsedData.setNivScolaire((int)Float.parseFloat(obj.get("nivScolaire").toString()));
                
                
               
                    
            }
        } catch (IOException ex) {
        }
        return parsedData;
    }*/
    
    
    public ArrayList<Niveau> getAll(){
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/users/niveauMobile";
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceNiveau SU=new ServiceNiveau();
                Niveaux = SU.parseTasks(new String(req.getResponseData()));
               req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(Niveaux);
        return Niveaux;
    }
    
    
     public ArrayList<Niveau> parseTasks(String jsonText){
        try {
            Niveaux=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Niveau t = new Niveau();
                
                 t.setId((int)Float.parseFloat(obj.get("id").toString()));
                t.setNom(obj.get("nom").toString());
                t.setNivScolaire((int)Float.parseFloat(obj.get("nivScolaire").toString()));
                Niveaux.add(t);  }
                
            
            
        } catch (IOException ex) {
            
        }
        return Niveaux;
    }
    
    
     public void AddNiveau(Niveau t) {
        MultipartRequest con = new MultipartRequest();
       if(!"".equals(t.getNom().trim()) && 
                t.getNom().length()>0){
           
                con.setUrl(BASE_URL+"/users/addNiveau/"+t.getNom()+"/"+t.getNivScolaire());
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                      
                    }
                });
                NetworkManager.getInstance().addToQueueAndWait(con);
           
       }}
     
     
     public void UpdateNiveau(Niveau t,int id) {
        MultipartRequest con = new MultipartRequest();
       if(!"".equals(t.getNom().trim()) && t.getNom().length()>0){
            
                con.setUrl(BASE_URL+"/users/UpdateNiveauMobile/"+id+"/"+t.getNom()+"/"+t.getNivScolaire());
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        ServiceNiveau ser = new ServiceNiveau();
                        System.out.println("sign up response server "+new String(con.getResponseData()));
                        if(!(new String(con.getResponseData()).equals("["+"\"problem\""+"]")
                                && !(new String(con.getResponseData()).equals("["+"\"exists\""+"]"))
                                && con.getResponseData()!=null))  ;            
                        
                    }
                });
                NetworkManager.getInstance().addToQueueAndWait(con);
           
       }
       
    }
     
     
     
      public void DeleteNiveau(Niveau n)
       {        MultipartRequest con = new MultipartRequest();
                       con.setUrl(BASE_URL+"/users/DeleteNiveauMobile/"+n.getId());
con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                       /* ServiceUser ser = new ServiceUser();
                        System.out.println("sign up response server "+new String(con.getResponseData()));
                        if(!(new String(con.getResponseData()).equals("["+"\"problem\""+"]")
                                && !(new String(con.getResponseData()).equals("["+"\"exists\""+"]"))
                                && con.getResponseData()!=null))  ;            
                            //connectedUser = parseSimpleJSON(new String(con.getResponseData()));*/
                         
                        System.out.println("niveau deleted");
                    }
                });
                NetworkManager.getInstance().addToQueueAndWait(con);

           
       }
    
    
    
    
}
