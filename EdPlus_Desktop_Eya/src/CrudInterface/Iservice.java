/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CrudInterface;
import java.sql.SQLException;

import javafx.collections.ObservableList;
/**
 *
 * @author user
 */
public interface Iservice<T> {
    void ajouter1(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    ObservableList<T> readAll() throws SQLException;
}
