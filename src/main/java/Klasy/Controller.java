package Klasy;



import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.sql.*;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

//awt powoduje błędy

public class Controller {



    @FXML

    public MenuItem Hana;
    public MenuItem WMS1;
    public MenuItem WMS2;
    public MenuItem WMS3;
    public MenuItem WM;
    public MenuItem Q1;
    public MenuItem Dc;
    public MenuItem d4;
    public MenuItem logi;
    public MenuItem catalog;
    public Label status;
    public TableView<OITM> tabelka;
    public TableColumn<Object, Object> t1;
    public TableColumn<Object, Object> t2;
    public TableColumn<Object, Object> t3;

    boolean Connected = false;

    Connection connection = null;
    public ObservableList<OITM> OITM_LIST;


    public void initialize()
    {
        t1.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        t2.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        t3.setCellValueFactory(new PropertyValueFactory<>("OnHand"));
    }


    public void WMS ()
    {
        try {

            Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\CompuTec\\CompuTec WMS Client\\CompuTec.Client.Desktop.exe\"", null, new File("C:\\Program Files (x86)\\CompuTec\\CompuTec WMS Client\\"));
        }

        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public void ConnectToDataBase()
    {

        try {

            connection = DriverManager.getConnection("jdbc:sap://172.16.0.54:30015/?currentschema=SBOELECTROPOLI","SYSTEM", "");
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


    public void CheckDatabaseConnection()
    {


        try {
            status.setText("");
            String urlString = "http://172.16.0.57:30002/api/Database/GetDbCredentials?companyName=SBOELECTROPOLI&serverAddress=172.16.0.54:30015";
            URL url = new URL(urlString);
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;

            while ((line = br.readLine()) != null) {
                status.setText(line + "\n");

            }
        }

        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + "Tryb bezpośredniego połączenia z bazą danych jest wyłączony.");

        }
    }



    public void TestQuery()
    {   //testowe query
        try {


            Statement st = connection.createStatement();
            String query = "SELECT \"ItemCode\", \"ItemName\", \"OnHand\" \n" +
                    "FROM OITM \n" +
                    "WHERE \"OnHand\" > 0 AND \"ItemCode\" LIKE 'WG-%'\n" +
                    " ORDER BY \"ItemName\"";

            ResultSet rs = st.executeQuery(query);
            OITM_LIST = FXCollections.observableArrayList();  //ważne


            while (rs.next())
            {
                OITM obiekt = new OITM();
                obiekt.ItemName.set(rs.getString("ItemName"));
                obiekt.ItemCode.set(rs.getString("ItemCode"));
                obiekt.OnHand.set(rs.getInt(3));

                OITM_LIST.add(obiekt);  //dodajemy obiekty do observable list
            }

            tabelka.setItems(OITM_LIST);  //parametrem TableView jest Observable list
        }

        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());

        }

        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Not connected");
        }
    }

    public void DisConnect()
    {

        if (connection==null)
        {
            JOptionPane.showMessageDialog(null, "You have already disconnected !");
        }

        else {
            try {
                connection = null;
                JOptionPane.showMessageDialog(null, "Disconnected");
                status.setText("Not connected");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error(192)");
            }
        }
    }



    public void Logi()
    {
        try {
            Desktop.getDesktop().open(new File("C:\\ProgramData\\CompuTec\\CompuTec WMS\\Server"));

        }

        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        catch (RuntimeException e1)
        {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    public void Tlumaczenia ()
    {
        try {
            Desktop.getDesktop().open(new File("C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia"));

        }

        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        catch (RuntimeException e1)
        {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    public void CustomConfig ()
    {
        //zwrócić uwagę na command


        try {

            Runtime.getRuntime().exec("\"C:\\Program Files\\CompuTec\\CompuTec WMS Server\\CustomConfiguration\\CustomCofiguration.exe\"", null, new File("C:\\Program Files\\CompuTec\\CompuTec WMS Server\\CustomConfiguration\\"));
        }

        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + "Trzeba jeszcze skonfigurować uprawnienia...");
        }
    }

    public void PowerShellRobocopy1()
    {
        String source = "\"C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia\\Nazwy pól\"";
        String dest = "\"C:\\Program Files\\CompuTec\\CompuTec WMS Server\\Translations\"";
        String file = "polish.translations";

        PowerShellResponse rs = PowerShell.executeSingleCommand("Robocopy"+ " " +source + " " + dest + " " +file);
        JOptionPane.showMessageDialog(null, rs.getCommandOutput());

    }

//    public void RestoreServer() {
//
//          //kopiowanie bez powershella
//        InputStream inStream = null;
//        OutputStream ouStream = null;
//
//        //ścieżka do serwerów
//        String ServerSource = "C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia\\Nazwy_pól\\polish.translations";
//        String ServerDest = "C:\\Program Files\\CompuTec\\CompuTec WMS Server\\Translations\\polish.translations";
//
//        try {
//            File afile = new File(ServerSource);
//            File bfile = new File(ServerDest);
//
//
//            inStream = new FileInputStream(afile);
//            ouStream = new FileOutputStream(bfile);
//
//            byte[] bufor = new byte[1024];
//            int długość;
//
//            while ((długość = inStream.read(bufor)) > 0) {
//                ouStream.write(bufor, 0, długość);
//            }
//
//            inStream.close();
//            ouStream.close();
//
//            JOptionPane.showMessageDialog(null, "Menu restored.");
//
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//    }

    public void PowerShellRobocopy2()
    {
        String source = "\"C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia\\Nazwy opcji\"";
        String dest = "\"C:\\Program Files\\CompuTec\\CompuTec WMS Server\\Translations\"";
        String file = "pl.translations";

        PowerShellResponse rs = PowerShell.executeSingleCommand("Robocopy"+ " " +source + " " + dest + " " +file);
        JOptionPane.showMessageDialog(null, rs.getCommandOutput());
    }

//    public void RestoreDesktopClient() {
//
//        //skaner na desktopie bez powershella
//
//        String ClientSource = "C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia\\Nazwy opcji\\pl.translations";
//        String ClientDest = "C:\\Program Files (x86)\\CompuTec\\CompuTec WMS Client\\pl.translations";
//
//        InputStream inStream = null;
//        OutputStream ouStream = null;
//
//        try {
//            File afile = new File(ClientSource);
//            File bfile = new File(ClientDest);
//
//
//            inStream = new FileInputStream(afile);
//            ouStream = new FileOutputStream(bfile);
//
//            byte[] bufor = new byte[1024];
//            int długość;
//
//            while ((długość = inStream.read(bufor)) > 0) {
//                ouStream.write(bufor, 0, długość);
//            }
//
//            inStream.close();
//            ouStream.close();
//
//
//            JOptionPane.showMessageDialog(null, "Menu restored");
//
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//
//
//        }

    }


