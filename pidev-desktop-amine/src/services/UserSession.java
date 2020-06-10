/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;

/**
 *
 * @author medam
 */
public class UserSession {
 private static UserSession _instance;
    private  static User user;

    private UserSession(User user) {
        System.out.println(user);
        this.user = user;
    }

    public static UserSession getInstance(User user) {
        if(_instance == null) {
            ServiceUser SU = new ServiceUser();
            _instance = new UserSession(user);
        }
        
        return _instance;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    

    
 
    
    public  static User getUser() {
        return user;
    }

    public static void disconnectFromApp() {
        getInstance(null);
        _instance = null;
        
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "user='" + user +
                "'}";
    }
}

