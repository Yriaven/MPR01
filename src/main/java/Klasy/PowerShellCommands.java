package Klasy;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PowerShellCommands {

    public static void TranslateDesktopClient() {
        String source = "\"C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia\\Nazwy opcji\"";
        String dest = "\"C:\\Program Files (x86)\\CompuTec\\CompuTec WMS Client\"";
        String file = "pl.translations";



        PowerShellResponse rs = PowerShell.executeSingleCommand("Robocopy" + " " + source + " " + dest + " " + file);

        JOptionPane.showMessageDialog(null, rs.getCommandOutput());
    }

    public static void TranslateServer() {


        String source = "\"C:\\Users\\pbetkows_admin\\Desktop\\Tłumaczenia\\Nazwy pól\"";
        String dest = "\"C:\\Program Files\\CompuTec\\CompuTec WMS Server\\Translations\"";
        String file = "polish.translations";

        PowerShellResponse rs = PowerShell.executeSingleCommand("Robocopy" + " " + source + " " + dest + " " + file);
        JOptionPane.showMessageDialog(null, "Restart WMS server now" + rs.getCommandOutput());

    }

    public void RestoreWMSServer() {
        try {
            PowerShell powerShell = PowerShell.openSession();
            PowerShellResponse rs = null;
            String script = "/scripts/restartWMS.ps1";


            BufferedReader srcReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(script)));
            rs = powerShell.executeScript(srcReader);


            if (powerShell != null) {
                powerShell.close();

            }
        } catch (PowerShellNotAvailableException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public void RestoreLicenseServer()
    {
        try {
            PowerShell powerShell = PowerShell.openSession();
            PowerShellResponse rs = null;
            String script = "/scripts/restartWMS.ps1";


            BufferedReader srcReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(script)));
            rs = powerShell.executeScript(srcReader);


            if (powerShell != null) {
                powerShell.close();

            }
        } catch (PowerShellNotAvailableException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }
}








