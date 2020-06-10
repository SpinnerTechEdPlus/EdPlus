/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import CrudInterface.Iservice;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import pidev.DataBase;
import entities.Matiere;
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
public class ServiceMatiere implements Iservice<Matiere> {

    private Connection con;
    private Statement ste;

    public ServiceMatiere() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter1(Matiere m) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `edplus`.`matiere` ( `nom`) VALUES ( ?);");

        pre.setString(1, m.getNom());
       
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Matiere m) throws SQLException {

        ste = con.createStatement();
        String requeteDelete = "DELETE FROM `matiere` WHERE id = '" + m.getId() + "'";

        if (ste.executeUpdate(requeteDelete) == 1) {
            System.out.println("Cett matiere a été supprimé avec succès");
            return true;
        } else {
            System.out.println("Cette matiere n'existe pas");
            return false;
        }
    }

    @Override
    public boolean update(Matiere m) throws SQLException {

        ste = con.createStatement();
        String requeteDelete = "UPDATE `matiere` SET `nom`= '" + m.getNom() + "',`id`= '" + m.getId() + "' WHERE id = '" + m.getId() + "' ";

        if (ste.executeUpdate(requeteDelete) == 1) {
            System.out.println("matiere mis à jour !");
            return true;
        } else {
            System.out.println("Cette matiere n'existe pas");
            return false;
        }
    }

    @Override
    public ObservableList<Matiere> readAll() throws SQLException {
        ObservableList<Matiere> l = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from matiere");
        while (rs.next()) {
            Matiere m = new Matiere();
             m.setId(rs.getInt(1));
             m.setNom(rs.getString(2));
             m.setNiveau_id(rs.getInt(3));
             m.setPath(rs.getString(4));
             
            l.add(m);
        }
        return l;
    }
    
    
    public void sendPDF() throws DocumentException, FileNotFoundException {

        try {

            String file_name = "C:\\xampp\\htdocs\\lib\\documents\\Listematieres.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            PdfPTable table = new PdfPTable(3);
            PdfPCell c1 = new PdfPCell(new Phrase("Id"));
            table.addCell(c1);
            document.open();

            document.add(new Paragraph(" Votre Liste des matieres "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            ste = con.createStatement();

            String req = "select * from matiere ";

            ResultSet rs = ste.executeQuery(req); //retourne un résulat

            while (rs.next()) {

                Paragraph para = new Paragraph(
                        rs.getInt(1) + " "
                        + rs.getString(2));  
                document.add(para);
                document.add(new Paragraph(" "));

            }

            document.close();

        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }
    
    
   

}
