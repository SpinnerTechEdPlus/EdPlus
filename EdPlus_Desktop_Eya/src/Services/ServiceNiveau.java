/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Niveau;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import pidev.DataBase;
import CrudInterface.Iservice;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Eya
 */
public class ServiceNiveau implements Iservice<Niveau> {

    private Connection con;
    private Statement ste;

    public ServiceNiveau() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter1(Niveau n) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `edplus`.`niveau` ( `nom`, `nivScolaire`, `user_id`) VALUES ( ?, ?, ?);");

        pre.setString(1, n.getNom());
        pre.setInt(2, n.getNivScolaire());
        pre.setInt(3, n.getUser_id());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Niveau n) throws SQLException {

        ste = con.createStatement();
        String requeteDelete = "DELETE FROM `niveau` WHERE id = '" + n.getId() + "'";

        if (ste.executeUpdate(requeteDelete) == 1) {
            System.out.println("Ce niveau a été supprimé avec succès");
            return true;
        } else {
            System.out.println("Ce niveau n'existe pas");
            return false;
        }
    }

    @Override
    public boolean update(Niveau n) throws SQLException {

        ste = con.createStatement();
        String requeteDelete = "UPDATE `niveau` SET `nom`= '" + n.getNom() + "',`nivScolaire`= '" + n.getNivScolaire() + "',`user_id`= '" + n.getUser_id() + "',`id`= '" + n.getId() + "' WHERE id = '" + n.getId() + "' ";

        if (ste.executeUpdate(requeteDelete) == 1) {
            System.out.println("niveau mis à jour !");
            return true;
        } else {
            System.out.println("Ce niveau n'existe pas");
            return false;
        }
        //   String requeteDelete = ("update niveau set nom=?,nivScolaire=?,user_id=? WHERE id=?");

    }

    @Override
    public ObservableList<Niveau> readAll() throws SQLException {
        ObservableList<Niveau> l = FXCollections.observableArrayList();

        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from niveau");
        while (rs.next()) {
            Niveau n = new Niveau();
            n.setId(rs.getInt(1));
            n.setNom(rs.getString(2));
            n.setNivScolaire(rs.getInt(3));
            n.setUser_id(rs.getInt(4));

            l.add(n);
        }
        return l;
    }

    public void sendPDF() throws DocumentException, FileNotFoundException {

        try {

            String file_name = "C:\\xampp\\htdocs\\lib\\documents\\Listeniveaux.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            PdfPTable table = new PdfPTable(3);
            PdfPCell c1 = new PdfPCell(new Phrase("Id"));
            table.addCell(c1);
            document.open();

            document.add(new Paragraph(" Votre Liste des niveaux "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            ste = con.createStatement();

            String req = "select * from niveau ";

            ResultSet rs = ste.executeQuery(req); //retourne un résulat

            while (rs.next()) {

                Paragraph para = new Paragraph(
                        rs.getInt(1) + " "
                        + rs.getString(2) + " "
                        + rs.getInt(3) + " "
                        + rs.getInt(4));
                document.add(para);
                document.add(new Paragraph(" "));

            }

            document.close();

        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }

    public ObservableList<String> readNiveaName() throws SQLException {
        ObservableList<String> arr = FXCollections.observableArrayList();
        String req = "select nom from niveau ";
        PreparedStatement preparedStatement;

        preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
        //   preparedStatement.setString(1, s);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {

            String Un = resultSet.getNString("nom");
            arr.add(Un);

        }

        return arr;

    }

    public int getMatiereId(String s) throws SQLException {
        int id = 0;
        String req = "select id from matiere where nom=? ";
        PreparedStatement preparedStatement;

        preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
        preparedStatement.setString(1, s);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("id");

        }
        return id;

    }

    public int getNiveauId(String s) throws SQLException {
        int id = 0;
        String req = "select id from niveau where nom=? ";
        PreparedStatement preparedStatement;

        preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
        preparedStatement.setString(1, s);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("id");

        }
        return id;

    }

   
}
