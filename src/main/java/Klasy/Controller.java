package Klasy;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.sql.*;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

//awt powoduje błędy

public class Controller {




    public MenuItem Hana;
    public MenuItem WMS1;
    public MenuItem WMS2;
    public MenuItem WMS3;
    public MenuItem WMS4;
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
    public ProgressIndicator PB;
    private boolean check;
    public VBox vbox;
    PowerShellCommands pwObject;



    Connection connection = null;
    public ObservableList<OITM> OITM_LIST;


    public void initialize() {
        t1.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        t2.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        t3.setCellValueFactory(new PropertyValueFactory<>("OnHand"));
        PB.setProgress(0);
        vbox.setStyle(Styles.color1);



    }


    public void WMS() {
       FileOpening.openWMS();
    }


    public void connectToDataBase() {
        check = false;
        PB.setProgress(0);
        try {
            connection = DriverManager.getConnection("jdbc:sap://172.16.0.54:30015/?currentschema=SBOELECTROPOLI", "SYSTEM", "0");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        if (connection != null) {

            JOptionPane.showMessageDialog(null, "Connected");
            check = true;
            PB.setProgress(1);

            if (check == true) {
                status.setText("Connected to SBOELECTROPOLI");
                PB.setProgress(1);
            }
        }
    }


    public void checkDatabaseConnection() {
        PB.setProgress(0);

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
            PB.setProgress(1);


        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + "Tryb bezpośredniego połączenia z bazą danych jest wyłączony.");

        }
    }


    public void testQuery() {   //testowe query
        PB.setProgress(0);
        check = false;
        try {


            Statement st = connection.createStatement();
            String query = "SELECT \"ItemCode\", \"ItemName\", \"OnHand\" \n" +
                    "FROM OITM \n" +
                    "WHERE \"OnHand\" > 0 AND \"ItemCode\" LIKE 'WG-%'\n" +
                    " ORDER BY \"ItemName\"";

            ResultSet rs = st.executeQuery(query);
            OITM_LIST = FXCollections.observableArrayList();  //ważne


            while (rs.next()) {
                OITM obiekt = new OITM();
                obiekt.ItemName.set(rs.getString("ItemName"));
                obiekt.ItemCode.set(rs.getString("ItemCode"));
                obiekt.OnHand.set(rs.getInt(3));

                OITM_LIST.add(obiekt);  //dodajemy obiekty do observable list
            }

            tabelka.setItems(OITM_LIST);  //parametrem TableView jest Observable list

            PB.setProgress(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            PB.setProgress(0);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not connected");
            PB.setProgress(0);
        }
    }

    public void disConnect() {
        PB.setProgress(0);
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "You have already disconnected !");
        } else {
            try {
                connection = null;
                JOptionPane.showMessageDialog(null, "Disconnected");
                status.setText("Not connected");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error(192)");
            }
        }
    }


    public void logi() {
        FileOpening.logi();
    }

    public void tlumaczenia() {

        FileOpening.translationFiles();
    }



    public void translateServer()
    {
        PowerShellCommands.translateServer();
        PB.setProgress(1);
    }



    public void copyByPowershell()
    {
        PowerShellCommands.translateDesktopClient();
        PB.setProgress(1);
    }


    public void restartWMSServer()
    {
        pwObject = new PowerShellCommands();
        pwObject.restoreWMSServer();
        PB.setProgress(1);

    }


    public void restartLicenseServer()
    {
        pwObject = new PowerShellCommands();
        pwObject.restoreLicenseServer();
        PB.setProgress(1);
    }


}



