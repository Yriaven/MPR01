package Klasy;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Unused {
//stare skrypty

    public void RestoreDesktopClient() {

        //skaner na desktopie bez powershella

        String ClientSource = "C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia\\Nazwy opcji\\pl.translations";
        String ClientDest = "C:\\Program Files (x86)\\CompuTec\\CompuTec WMS Client\\pl.translations";

        InputStream inStream = null;
        OutputStream ouStream = null;

        try {
            File afile = new File(ClientSource);
            File bfile = new File(ClientDest);


            inStream = new FileInputStream(afile);
            ouStream = new FileOutputStream(bfile);

            byte[] bufor = new byte[1024];
            int długość;

            while ((długość = inStream.read(bufor)) > 0) {
                ouStream.write(bufor, 0, długość);
            }

            inStream.close();
            ouStream.close();


            JOptionPane.showMessageDialog(null, "Menu restored");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());


        }


    }


    public void CustomConfig() {
        //zwrócić uwagę na command


        try {

            Runtime.getRuntime().exec("\"C:\\Program Files\\CompuTec\\CompuTec WMS Server\\CustomConfiguration\\CustomCofiguration.exe\"", null, new File("C:\\Program Files\\CompuTec\\CompuTec WMS Server\\CustomConfiguration\\"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage() + "\n" + "Trzeba jeszcze skonfigurować uprawnienia...");
        }
    }

    public static void RestoreServer() {

        //kopiowanie bez powershella
        InputStream inStream = null;
        OutputStream ouStream = null;

        //ścieżka do serwerów
        String ServerSource = "C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia\\Nazwy pól\\polish.translations";
        String ServerDest = "C:\\Program Files\\CompuTec\\CompuTec WMS Server\\Translations\\polish.translations";

        try {
            File afile = new File(ServerSource);
            File bfile = new File(ServerDest);


            inStream = new FileInputStream(afile);
            ouStream = new FileOutputStream(bfile);

            byte[] bufor = new byte[1024];
            int długość;

            while ((długość = inStream.read(bufor)) > 0) {
                ouStream.write(bufor, 0, długość);
            }

            inStream.close();
            ouStream.close();

            JOptionPane.showMessageDialog(null, "Menu restored.");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static String checkConnection (String ip)
    {
        boolean status = false;
        String connected = "request timed out";

        InetAddress IPAdress = null;
        try {

            IPAdress = InetAddress.getByName(ip);
            status = IPAdress.isReachable(1000);

            if (status == true)
            {
                return connected = "online";
            }

        }

        catch (UnknownHostException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return connected;

    }






}
