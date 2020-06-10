/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDInterface;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author medam
 */
public interface Iservice<T> {
    
    void ajouterProf(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean updateProf(T t) throws SQLException;
    ObservableList<T> readAll(String s,int userId) throws SQLException;
    
}
