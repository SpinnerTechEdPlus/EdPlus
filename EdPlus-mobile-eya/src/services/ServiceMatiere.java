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
import entities.Matiere;
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
public class ServiceMatiere {

    private ConnectionRequest req;
    //ArrayList<Niveau> NiveauList = new ArrayList<>();
    public static ServiceMatiere instance = null;

    public static ServiceMatiere getInstance() {
        if (instance == null) {
            instance = new ServiceMatiere();
        }
        return instance;
    }

    public ServiceMatiere() {
        req = new ConnectionRequest();
    }
    public ArrayList<Matiere> Matieres;

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
    public ArrayList<Matiere> getAll() {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/users/matiereMobile";
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceMatiere SU = new ServiceMatiere();
                Matieres = SU.parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(Matieres);
        return Matieres;
    }

    public ArrayList<Matiere> parseTasks(String jsonText) {
        try {
            Matieres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Matiere t = new Matiere();

                t.setId((int) Float.parseFloat(obj.get("id").toString()));
                t.setNom(obj.get("nom").toString());
               // t.setPath((String) obj.get("path"));
                Matieres.add(t);
            }

        } catch (IOException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
        return Matieres;
    }

    public void AddMatiere(Matiere t) {
        MultipartRequest con = new MultipartRequest();
        if (!"".equals(t.getNom().trim())
                && t.getNom().length() > 0) {

                con.setUrl(BASE_URL + "/users/addMatiere/" + t.getNom());
               // con.addData("userImage", t.getPath(), "text/plain");
                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                    }
                });
                NetworkManager.getInstance().addToQueueAndWait(con);
           

        }
    }

    public void UpdateMatiere(Matiere t, int id) {
        MultipartRequest con = new MultipartRequest();
        if (!"".equals(t.getNom().trim()) && t.getNom().length() > 0) {

            con.setUrl(BASE_URL + "/users/UpdateMatiereMobile/" + id + "/" + t.getNom() );
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceMatiere ser = new ServiceMatiere();
                    System.out.println("sign up response server " + new String(con.getResponseData()));
                    if (!(new String(con.getResponseData()).equals("[" + "\"problem\"" + "]")
                            && !(new String(con.getResponseData()).equals("[" + "\"exists\"" + "]"))
                            && con.getResponseData() != null))  ;

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);

        }

    }

    public void DeleteMatiere(Matiere m) {
        MultipartRequest con = new MultipartRequest();
        con.setUrl(BASE_URL + "/users/DeleteMatiereMobile/" + m.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("Matiere supprimée");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

}
