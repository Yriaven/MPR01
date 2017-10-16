package Klasy;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileOpening {
    //opening folders and files...

    public static void OpenWMS()
    {
        try {

            Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\CompuTec\\CompuTec WMS Client\\CompuTec.Client.Desktop.exe\"", null, new File("C:\\Program Files (x86)\\CompuTec\\CompuTec WMS Client\\"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void Logi()
    {
        try {
            Desktop.getDesktop().open(new File("C:\\ProgramData\\CompuTec\\CompuTec WMS\\Server"));

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (RuntimeException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    public static void TranslationFiles()
    {
        try {
            Desktop.getDesktop().open(new File("C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia"));
        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
