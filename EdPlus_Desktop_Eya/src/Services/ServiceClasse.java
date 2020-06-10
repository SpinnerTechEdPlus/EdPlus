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
import entities.Classe;
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
public class ServiceClasse implements Iservice<Classe> {

    private Connection con;
    private Statement ste;

    public ServiceClasse() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter1(Classe c) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `edplus`.`classe` ( `niveau_id`, `num`, `nbrEtudiant`, `nom`) VALUES ( ?, ?, ?,?);");

        pre.setInt(1, c.getNiveau_id());
        pre.setInt(2, c.getNum());
        pre.setInt(3, c.getNbrEtudiant());
        pre.setString(4, c.getNom());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Classe c) throws SQLException {

        ste = con.createStatement();
        String requeteDelete = "DELETE FROM `classe` WHERE id = '" + c.getId() + "'";

        if (ste.executeUpdate(requeteDelete) == 1) {
            System.out.println("Cette classe a été supprimée avec succès");
            return true;
        } else {
            System.out.println("Cette classe n'existe pas");
            return false;
        }
    }

    @Override
    public boolean update(Classe c) throws SQLException {

        ste = con.createStatement();
        String requeteDelete = "UPDATE `classe` SET `niveau_id`= '" + c.getNiveau_id() + "',`num`= '" + c.getNum() + "',`nbrEtudiant`= '" + c.getNbrEtudiant() + "',`nom`= '" + c.getNom() + "',`id`= '" + c.getId() + "' WHERE id = '" + c.getId() + "' ";

        if (ste.executeUpdate(requeteDelete) == 1) {
            System.out.println("Classe mise à jour !");
            return true;
        } else {
            System.out.println("Cette Classe n'existe pas");
            return false;
        }
    }

    @Override
    public ObservableList<Classe> readAll() throws SQLException {
        ObservableList<Classe> l = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from classe");
        while (rs.next()) {
            Classe c = new Classe();
            c.setId(rs.getInt(1));
            c.setNum(rs.getInt(2));
            c.setNbrEtudiant(rs.getInt(3));
            c.setNiveau_id(rs.getInt(4));
            c.setNom(rs.getString(5));

            l.add(c);
        }
        return l;
    }

    public void sendPDF() throws DocumentException, FileNotFoundException {

        try {

            String file_name = "C:\\xampp\\htdocs\\lib\\documents\\ListeClasses.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            PdfPTable table = new PdfPTable(3);
            PdfPCell c1 = new PdfPCell(new Phrase("Id"));
            table.addCell(c1);
            document.open();

            document.add(new Paragraph(" Votre Liste des Classes "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            ste = con.createStatement();

            String req = "select * from classe ";

            ResultSet rs = ste.executeQuery(req); //retourne un résulat

            while (rs.next()) {

                Paragraph para = new Paragraph(
                        rs.getInt(1) + " "
                        + rs.getInt(2) + " "
                        + rs.getInt(3) + " "
                        + rs.getInt(4) + " "
                        + rs.getString(5)
                );
                document.add(para);
                document.add(new Paragraph(" "));

            }

            document.close();

        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }

}
