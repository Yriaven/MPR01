$EmailTo = "monit_sap@electropoli.pl"
$EmailFrom = "Alert_sap@electropoli.pl"
$Subject = "Status serwera WMS"
$Body = "Serwer WMS zatrzymany. Status zostanie sprawdzony ponownie za 2 minuty."
$SMTPServer = "SMTP.electropoli.pl"
$SMTPMessage = New-Object System.Net.Mail.MailMessage($EmailFrom,$EmailTo,$Subject,$Body)
$SMTPClient = New-Object Net.Mail.SmtpClient($SmtpServer, 587)
$SMTPClient.Credentials = New-Object System.Net.NetworkCredential("Alert_sap@electropoli.pl", "lsevAbABC5@");


while($true)
{

 If ((Get-Service "Computec WMS Server").status -eq "Stopped")
  {
        Try
         {
           $SMTPClient.Send($SMTPMessage)

         }
        Catch
         {
            $Body = "Error while executing a script"
            $SMTPClient.Send($SMTPMessage)
         }
       }

     Start-Sleep -Seconds 120
 }











