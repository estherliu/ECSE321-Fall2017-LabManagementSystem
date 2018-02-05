# P16
     We have three main folders (desktop, android, web) in our Github Master Branch. Since we are using client-server mode, we have a server folder other than the three versions of this application.
# to run desktop version:
     1. open eclipse, import "Server" folder as a java project (if there is error, check if the jre is installed correctly by "configure build path"). 
     2. open src/ca/mcgill/ecse321/managementSystem/application/ManagementSystem.java.
     3. right click on this page, choose "Run as"->"Java application".
     4. should see "server started" in the console.
     5. open eclipse in another workspace, import "desktop" folder as a java project.
     6. open src/ca/mcgill/ecse321/managementSystem/application/ManagementSystem.java.
     7. right click on this page, choose "Run as"->"Java application".
     6. the application should start now.
     
# to run android version:
     1. run server on eclipse, the step is same as above in desktop version
     2. open android studio, open project "managementSystem" in "android" folder
     3. open "app/java/ca.mcgill.ecse321/tcpclient/TCPclient"
     4. change IP to your own IP (since android monitor is considered to have a different ip with current computer, we cannot use localhost now. Instead, we have to use tcp connection here. Here we assume the server's port is forwarded. If you are not able to do port forwarding, please contact us and change the ip address to 173.179.5.229. We will open the server on this ip).
     5. run this application on Android monitor.
     
# to run web version:
     1. double click on “XAAMP CONTROL PANEL” on desktop and Start “Apache”. Start "MySQL" as well, if the PHP scripts depend on a MySQL database to run.
     2. Place the "web" folder in the "htdocs" folder located under the "XAMMP" folder on the C: drive. The file path is "C:\xampp\htdocs" for the Web server.
     3. open the browser, type "http://localhost/web" and press enter. Enter "view" folder. The welcome page will show, if the server is connected correctly.
