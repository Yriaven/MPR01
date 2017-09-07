package sample;



import javafx.fxml.FXML;

import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.omg.SendingContext.RunTime;

public class Controller {



    @FXML

    public MenuItem Hana;
    public MenuItem WMS1;
    public MenuItem WMS2;
    public MenuItem WMS3;
    public Label status;

    boolean Connected = false;




    public void RestoreServer() {


        InputStream inStream = null;
        OutputStream ouStream = null;

        try {
            File afile = new File("C:\\Users\\ppawlus_admin\\Desktop\\Tłumaczenia\\Nazwy pól\\polish.translations");
            File bfile = new File("C:\\Program Files\\CompuTec\\CompuTec WMS Server\\Translations\\polish.translations");


            inStream = new FileInputStream(afile);
            ouStream = new FileOutputStream(bfile);

            byte[] bufor = new byte[1024];
            int długość;

            while ((długość = inStream.read(bufor)) > 0) {
                ouStream.write(bufor, 0, długość);
            }

            inStream.close();
            ouStream.close();

            JOptionPane.showMessageDialog(null, "Przywrócono pomyślnie");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void RestoreDesktopClient() {


        InputStream inStream = null;
        OutputStream ouStream = null;

        try {
            File afile = new File("C:\\Users\\ppawlus_admin\\Desktop\\Tłumaczenia\\Nazwy opcji\\pl.translations");
            File bfile = new File("C:\\Program Files (x86)\\CompuTec\\CompuTec WMS Client\\pl.translations");


            inStream = new FileInputStream(afile);
            ouStream = new FileOutputStream(bfile);

            byte[] bufor = new byte[1024];
            int długość;

            while ((długość = inStream.read(bufor)) > 0) {
                ouStream.write(bufor, 0, długość);
            }

            inStream.close();
            ouStream.close();


            JOptionPane.showMessageDialog(null, "Przywrócono pomyślnie");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

}

    public void ConnectToDataBase()
    {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:sap://172.16.0.54:30015/?currentschema=SBOELECTROPOLI","SYSTEM", "Ep*4321#");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        if (connection != null)
        {
            JOptionPane.showMessageDialog(null, "Connected");
            Connected = true;

            if (Connected ==true)
            {
                status.setText("Connected to SBOELECTROPOLI");
            }
        }
    }


    public void CustomConfig ()
    {
        try {
            Runtime.getRuntime().exec("c:\\program files\\test\\test.exe", null, new File("c:\\program files\\test\\"));
        }

        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


}
