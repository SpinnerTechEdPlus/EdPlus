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
import entities.Classe;
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
public class ServiceClasse {

    private ConnectionRequest req;
    //ArrayList<Niveau> NiveauList = new ArrayList<>();
    public static ServiceClasse instance = null;

    public static ServiceClasse getInstance() {
        if (instance == null) {
            instance = new ServiceClasse();
        }
        return instance;
    }

    private ServiceClasse() {
        req = new ConnectionRequest();
    }
    public ArrayList<Classe> Classes;

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
    public ArrayList<Classe> getAll() {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/users/classeMobile";
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceClasse SU = new ServiceClasse();
                Classes = SU.parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(Classes);
        return Classes;
    }

    public ArrayList<Classe> parseTasks(String jsonText) {
        try {
            Classes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Classe t = new Classe();

                t.setId((int) Float.parseFloat(obj.get("id").toString()));
                t.setNum((int) Float.parseFloat(obj.get("num").toString()));
                t.setNbrEtudiant((int) Float.parseFloat(obj.get("nbrEtudiant").toString()));
                t.setNom(obj.get("nom").toString());
                
                Classes.add(t);
            }

        } catch (IOException ex) {

        }
        return Classes;
    }

    public void AddClasse(Classe t) {
        MultipartRequest con = new MultipartRequest();
        if (!"".equals(t.getNom().trim())
                && t.getNom().length() > 0) {

            con.setUrl(BASE_URL + "/users/addClasse/" + t.getNum() + "/" + t.getNbrEtudiant() + "/" + t.getNom());
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);

        }
    }

    public void UpdateClasse(Classe t, int id) {
        MultipartRequest con = new MultipartRequest();
        if (!"".equals(t.getNom().trim()) && t.getNom().length() > 0) {

            con.setUrl(BASE_URL + "/users/UpdateClasseMobile/" + id + "/" + t.getNum() + "/" + t.getNbrEtudiant() + "/" + t.getNom());
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceClasse ser = new ServiceClasse();
                    System.out.println("sign up response server " + new String(con.getResponseData()));
                    if (!(new String(con.getResponseData()).equals("[" + "\"problem\"" + "]")
                            && !(new String(con.getResponseData()).equals("[" + "\"exists\"" + "]"))
                            && con.getResponseData() != null))  ;

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);

        }

    }

    public void DeleteClasse(Classe c) {
        MultipartRequest con = new MultipartRequest();
        con.setUrl(BASE_URL + "/users/DeleteClasseMobile/" + c.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                /* ServiceUser ser = new ServiceUser();
                        System.out.println("sign up response server "+new String(con.getResponseData()));
                        if(!(new String(con.getResponseData()).equals("["+"\"problem\""+"]")
                                && !(new String(con.getResponseData()).equals("["+"\"exists\""+"]"))
                                && con.getResponseData()!=null))  ;            
                            //connectedUser = parseSimpleJSON(new String(con.getResponseData()));*/

                System.out.println("Classe supprimée");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

}
