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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
;
import utils.Statics;
import static utils.Statics.BASE_URL;
import utils.UserSession;

/**
 *
 * @author medam
 */


public class ServiceUser {

    ArrayList<User> usersList = new ArrayList<>();
    public static ServiceUser instance = null;

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public void hide() {
        /*public ArrayList<User> parseMultipleJSON(String json) {
        ArrayList<User> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
            for (Map<String, Object> obj : list) {
                User parsedData = new User();
                parsedData.setId((int)Float.parseFloat(obj.get("id").toString()));
                parsedData.setUsername(obj.get("username").toString());
                parsedData.setEmail(obj.get("email").toString());
                parsedData.setPassword(obj.get("password").toString());

                parsedData.setEnabled(obj.get("enabled").toString().equals("true") ? 0 : 1);

                
                

                listUsers.add(parsedData);
            }

        } catch (IOException ex) {
        }
        return listUsers;
    }

   public List<User> getAll() {    
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/users/ecole/mobileUsers");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                usersList = ser.parseMultipleJSON(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return usersList;
    }*/
    }

    public ArrayList<User> Users;

    public ArrayList<User> parseTasks(String jsonText) {
        try {
            Users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User t = new User();

                t.setId((int) Float.parseFloat(obj.get("id").toString()));
                t.setUsername(obj.get("username").toString());
                t.setEmail(obj.get("email").toString());
                t.setPassword(obj.get("password").toString());
               // t.setUser_id((int) Float.parseFloat(obj.get("User").toString()));
                t.setUser_id(UserSession.getUser().getId());
                if (obj.get("birthDate") != null) {
                    ;
                    String charDate = obj.get("birthDate").toString();
                    int day = Integer.parseInt(charDate.substring(8, 10));
                    int month = Integer.parseInt(charDate.substring(5, 7));
                    int year = Integer.parseInt(charDate.substring(0, 4));
                    SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        t.setBirthDate(std.parse(charDate));
                    } catch (ParseException ex) {

                    }

                }
                Users.add(t);
            }

        } catch (IOException ex) {

        }
        return Users;
    }

    public User parseSimpleJSON(String json) {
        User parsedData = new User();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
            System.out.println("ParseSimple list: " + list);
            for (Map<String, Object> obj : list) {
                parsedData.setEmail(obj.get("email").toString());
                parsedData.setPassword(obj.get("password").toString());
                parsedData.setUsername(obj.get("username").toString());
                parsedData.setId((int) Float.parseFloat(obj.get("id").toString()));
                parsedData.setEnabled(obj.get("enabled").toString().equals("true") ? 0 : 1);

                if (obj.get("roles").toString().equals("[ROLE_ECOLE")) {
                    parsedData.setRoles("ecole");
                } else if (obj.get("roles").toString().equals("[ROLE_PROFFESSEUR")) {
                    parsedData.setRoles("professeur");
                } else if (obj.get("roles").toString().equals("[ROLE_ETUDIANT")) {
                    parsedData.setRoles("etudiant");
                }

            }
        } catch (IOException ex) {
        }
        return parsedData;
    }

    public User SignUp(User t) {
        MultipartRequest con = new MultipartRequest();
        if (!"".equals(t.getEmail().trim()) && !"".equals(t.getPassword().trim())
                && t.getUsername().length() > 0 && t.getPassword().length() > 0) {

            con.setUrl(BASE_URL + "/users/signUp/" + t.getUsername() + "/" + t.getPassword() + "/" + t.getEmail());
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceUser ser = new ServiceUser();
                    System.out.println("sign up response server " + new String(con.getResponseData()));
                    if (!(new String(con.getResponseData()).equals("[" + "\"problem\"" + "]")
                            && !(new String(con.getResponseData()).equals("[" + "\"exists\"" + "]"))
                            && con.getResponseData() != null)) {
                        connectedUser = parseSimpleJSON(new String(con.getResponseData()));
                    }
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);

        } else {
            connectedUser = null;
        }
        return connectedUser;
    }

    public void AddUser(User t, boolean SendMail) {
        MultipartRequest con = new MultipartRequest();
        if (!"".equals(t.getEmail().trim()) && !"".equals(t.getPassword().trim())
                && t.getUsername().length() > 0 && t.getPassword().length() > 0) {

            con.setUrl(BASE_URL + "/users/addUser/" + t.getUsername() + "/" + t.getPassword() + "/" + t.getEmail() + "/" + t.getRoles() + "/" + t.getBirthDate() + "/" + SendMail+ "/" + t.getUser_id());
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    /*ServiceUser ser = new ServiceUser();
                        System.out.println("sign up response server "+new String(con.getResponseData()));
                        if(!(new String(con.getResponseData()).equals("["+"\"problem\""+"]")
                                && !(new String(con.getResponseData()).equals("["+"\"exists\""+"]"))
                                && con.getResponseData()!=null))              
                            connectedUser = parseSimpleJSON(new String(con.getResponseData()));*/

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);

        }
        // else connectedUser = null;

    }

    public void UpdateUser(User t, String OldUserName, boolean SendMail) {
        MultipartRequest con = new MultipartRequest();
        if (!"".equals(t.getEmail().trim()) && !"".equals(t.getPassword().trim())
                && t.getUsername().length() > 0 && t.getPassword().length() > 0) {

            con.setUrl(BASE_URL + "/users/updateUser/" + OldUserName + "/" + t.getUsername() + "/" + t.getPassword() + "/" + t.getEmail() + "/" + t.getBirthDate() + "/" + SendMail);
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceUser ser = new ServiceUser();
                    System.out.println("sign up response server " + new String(con.getResponseData()));
                    if (!(new String(con.getResponseData()).equals("[" + "\"problem\"" + "]")
                            && !(new String(con.getResponseData()).equals("[" + "\"exists\"" + "]"))
                            && con.getResponseData() != null))  ;
                    //connectedUser = parseSimpleJSON(new String(con.getResponseData()));*/

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);

        }
        // else connectedUser = null;

    }

    public void DeleteUser(User user) {
        MultipartRequest con = new MultipartRequest();
        con.setUrl(BASE_URL + "/users/DeleteUser/" + user.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                /* ServiceUser ser = new ServiceUser();
                        System.out.println("sign up response server "+new String(con.getResponseData()));
                        if(!(new String(con.getResponseData()).equals("["+"\"problem\""+"]")
                                && !(new String(con.getResponseData()).equals("["+"\"exists\""+"]"))
                                && con.getResponseData()!=null))  ;            
                            //connectedUser = parseSimpleJSON(new String(con.getResponseData()));*/

                System.out.println("user deleted");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    private ConnectionRequest req;

    public ArrayList<User> getAll(String role) {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/users/ecole/mobileUsers/" + role + "";
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser SU = new ServiceUser();
                Users = SU.parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //  System.out.println(Users);
        return Users;
    }
    
    
    public ArrayList<User> getAllById(String role,int userId) {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/users/ecole/mobileUsers/" + role +"/"+userId;
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser SU = new ServiceUser();
                Users = SU.parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //  System.out.println(Users);
        return Users;
    }
    
    
    
    public ArrayList<User> getAllByClasse(String role,int ClasseId) {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/users/ecole/mobileUsers/" + role +"/"+ClasseId;   //13
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser SU = new ServiceUser();
                Users = SU.parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //  System.out.println(Users);
        return Users;
    }
    

    User connectedUser = new User();

    public User verifyLoginCreds(String username, String password) {
        ConnectionRequest con = new ConnectionRequest();
        if (!"".equals(username.trim()) && !"".equals(password.trim())
                && username.length() > 0 && password.length() > 0) {
            con.setUrl(BASE_URL + "/users/ecole/verifyCreds/" + username + "/" + password);
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceUser SU = new ServiceUser();
                    // System.out.println("response login "+ new String(con.getResponseData()));
                    if (!(new String(con.getResponseData()).equals("[" + "\"invalid\"" + "]")) && con.getResponseData() != null) {
                        connectedUser = parseUserLoginCreds(new String(con.getResponseData()));
                    } else {
                        connectedUser = null;
                    }
                }

            });
            NetworkManager.getInstance().addToQueueAndWait(con);
        } else {
            connectedUser = null;
        }
        return connectedUser;
    }

    public User parseUserLoginCreds(String json) {
        User t = new User();
        if (!json.startsWith("error")) {
            try {
                JSONParser j = new JSONParser();
                Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));

                if (users.size() > 0) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) users.get("root");
                    for (Map<String, Object> obj : list) {
                        if (obj.get("invalid") != null) {
                            break;
                        } else {
                            t.setId((int) Float.parseFloat(obj.get("id").toString()));
                            t.setUsername(obj.get("username").toString());
                            t.setEmail(obj.get("email").toString());
                            t.setPassword(obj.get("password").toString());
                            t.setEnabled(obj.get("enabled").toString().equals("true") ? 1 : 0);

                            if (obj.get("roles").toString().equals("[ROLE_ECOLE, ROLE_USER]") || obj.get("roles").toString().equals("[ROLE_USER]") ) {
                                t.setRoles("ecole");
                            } else if (obj.get("roles").toString().equals("[ROLE_PROFESSEUR, ROLE_USER]")) {
                                t.setRoles("professeur");
                            } else if (obj.get("roles").toString().equals("[ROLE_ETUDIANT, ROLE_USER]")) {
                                t.setRoles("etudiant");
                            }
                            
                            if (obj.get("birthDate") != null) {
                    
                    String charDate = obj.get("birthDate").toString();
                    int day = Integer.parseInt(charDate.substring(8, 10));
                    int month = Integer.parseInt(charDate.substring(5, 7));
                    int year = Integer.parseInt(charDate.substring(0, 4));
                    SimpleDateFormat std = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        t.setBirthDate(std.parse(charDate));
                    } catch (ParseException ex) {

                    }

                }
                            System.out.println("this  " + obj.get("roles").toString());

                        }

                    }
                }
            } catch (IOException ex) {
                Dialog.show("Erreur de connexion", "Vérifiez votre mot de passe ou e-mail", "OK", "Sortir");
            }
        } else {
            t = null;
        }
        return t;
    }

    public String getAge(Date d) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        int age = 0;
        dob.setTime(d);

        int year1 = today.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);
        age = year1 - year2;
        int month1 = today.get(Calendar.MONTH);
        int month2 = dob.get(Calendar.MONTH);
        if (month2 > month1) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;

    }
    
    
    
    

}
