/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Eya
 */
public class FormValidation {
     public FormValidation() {
    }
    
    
    
    public static boolean textFieldNotEmpty(TextField t){
        boolean  test=false;
        if(t.getText()!=null && !t.getText().isEmpty()){
            test=true;
        }
        return test;
    }
    
       public static boolean DatePickerNotEmpty(DatePicker t){
        boolean  test=false;
        if(t.getValue()!=null ){
            test=true;
        }
        return test;
    }
       
       
        public static boolean DatePickerNotEmpty(DatePicker t, Label l , String sValidationText){
        boolean  test=true;
        String c =null;
        if(! DatePickerNotEmpty(t)){
            test=false;
            c=sValidationText;
        }
        l.setText(c);
        return test;
    }
       
       public static boolean textFieldNotEmpty(TextField t, Label l , String sValidationText){
        boolean  test=true;
        String c =null;
        if(! textFieldNotEmpty(t)){
            test=false;
            c=sValidationText;
        }
        l.setText(c);
        return test;
    }
       
       public static boolean textFieldTypeNumber(TextField t){
           boolean test=false;
           if(t.getText().matches("[0-9]+")){
               test=true;
           }
           return test;
       }
           public static boolean textFieldTypeAlphabet(TextField t){
           boolean test=false;
           if(t.getText().matches("[a-zA-Z]+\\.?")){
               test=true;
           }
           return test;
       }
           
           public static boolean textFieldTypeEmail(TextField t){
           boolean test=false;
           if(t.getText().contains("@")&&t.getText().contains(".")){
               test=true;
           }
           return test;
       }
           
           public static boolean textFieldTypeEmail(TextField t, Label l , String sValidationText){
        boolean  test=true;
        String c =null;
        if(! textFieldTypeEmail(t)){
            test=false;
            c=sValidationText;
        }
        l.setText(c);
        return test;
    }
           
           public static boolean textFieldTypeNumber(TextField t, Label l , String sValidationText){
        boolean  test=true;
        String c =null;
        if(! textFieldTypeNumber(t)){
            test=false;
            c=sValidationText;
        }
        l.setText(c);
        return test;
    }
           
         public static boolean textFieldTypeAlphabet(TextField t, Label l , String sValidationText){
        boolean  test=true;
        String c =null;
        if(! textFieldTypeAlphabet(t)){
            test=false;
            c=sValidationText;
        }
        l.setText(c);
        return test;
    }
                  
                   public static boolean dateValid(DatePicker t){
        boolean  test=false;
        Date now = new Date(System.currentTimeMillis());
        LocalDate tt= t.getValue();
        Instant instant =Instant.from(tt.atStartOfDay(ZoneId.systemDefault()));
        Date date= Date.from(instant);
        if(now.compareTo(date)>0){
            test=true;
        }
        return test;
    }
                   
                  public static boolean dateValid(DatePicker t, Label l , String sValidationText){
        boolean  test=true;
        String c =null;
        if(! dateValid(t)){
            test=false;
            c=sValidationText;
        }
        l.setText(c);
        return test;
    }
                  public static boolean dateValidRDV(DatePicker t){
        boolean  test=false;
        Date now = new Date(System.currentTimeMillis());
        LocalDate tt= t.getValue();
        Instant instant =Instant.from(tt.atStartOfDay(ZoneId.systemDefault()));
        Date date= Date.from(instant);
        if(now.compareTo(date)<0){
            test=true;
        }
        return test;
    }
                   
                  public static boolean dateValidRDV(DatePicker t, Label l , String sValidationText){
        boolean  test=true;
        String c =null;
        if(! dateValidRDV(t)){
            test=false;
            c=sValidationText;
        }
        l.setText(c);
        return test;
    }
    
}
