/*
   Marco Alarcon
   IT206-201
   4/15/2021
   Programming Assignment 8
   
   NetworkMenu Implementation Class
   --------------------------------------------------------------------
   This implementation class provides a user interface for the user
   to interact with the network. The user is given four options to add
   or access information on the network and its servers.
   
   addServer prompts the user for a specific type of server type and calls
   the constructor corresponding to the correct server type. If the incorrect
   choice is made, an exception is thrown. Each specific constructor prompts
   the user for specific information regarding the server type. Every server
   type requires a serverID, os, and capacity, however WebServers require
   the number of programming langauges as well as an array containing those
   programming languages, FileServers require a number of users, and
   DatabaseServers require a DB application as well as the number of users.
   If the server insantiation is successful, the object is then stored in
   the Server array named network and the user is then notified that the
   server was created successfully. 
   
   allServerInfo goes through each Server object in the network array and
   calls the toString method in order to create insert a String representation
   of each object into a String report along with the total number of servers
   created and total capacity in TB. 
   
   highestUsage prompts the user for a specific server type and then searches
   the network array for objects matching the server type search criteria.
   If the Server object has a higher usage than the basket variable, that usage
   is stored along with the index of that Server. Once the search is complete,
   the method returns the Server that has the highest usage in a report.
   
   avgUsage prompts the user for a specific server type, and then searches
   through the network array for those specific Server objects. Each Server
   that matches the search criteria has its usage added to a totalUsage
   variable until all Servers have been checked. Then the totalUsage variable
   is divided by the total number of Servers matching the search criteria. This
   is done by calling the corresponding accessor method that retrieves the
   tracker for that specific server type. The totalUsage variable is then
   displayed as the average usage along with the Server type the user chose.
   
   The main method provides a do-while loop that continuously prompts the
   user to choose an option in the menu. Entering 5 instead sets the repeat
   boolean variable to false in order to bbreak the while condition and allow
   the program to end. Choosing 1-4 allows the user to run any of the 4
   provided methods and loop back to the menu.
   
   At any time, if the user is prompted to enter a choice and an invalid
   choice is entered instead, an exception is thrown notifying the user that
   they entered an incorrect choice. All exceptions are handled by a
   try-catch block within the do-while menu loop.
*/

import javax.swing.JOptionPane;
public class NetworkMenu{
   //User String input
   public static String userString(String prompt){
      String input = JOptionPane.showInputDialog(prompt);
      return input;
   }
   
   //User int input
   public static int userInt(String prompt){
      int input = Integer.parseInt(JOptionPane.showInputDialog(prompt));
      return input;
   }
   
   //User double input
   public static double userDouble(String prompt){
      double input = Double.parseDouble(JOptionPane.showInputDialog(prompt));
      return input;
   }
   
   //Add Server
   public static void addServer(Server[] network){
      String serverType = userString("Enter choice for server type to create\n\n"
         +"[1] WebServer\n"
         +"[2] FileServer\n"
         +"[3] DatabaseServer\n"
         +"[0] Cancel");
         
      String serverID = "";
      String os = userString("Choose Server Operating System\n\n"
         +"[1] - Windows\n"
         +"[2] - Linux\n"
         +"[3] - OS X");
      switch(os){
         case "1":
            os = "WINDOWS";
            break;
         case "2":
            os = "LINUX";
            break;
         case "3":
            os = "OS X";
            break;
      }
      double capacity = userDouble("Enter Server hard drive capacity (TB)");
      
      switch(serverType){
         case "1":
            serverID = "WS" + String.format("%03d", WebServer.getNumWS()+1);
            
            int numLangs = userInt("Enter number of programming languages to create for WebServer (2-4)");
            String[] langs = new String[numLangs];
            for(int i = 0; i < numLangs; i++){
               langs[i] = userString("Enter programming language");
            }
            Server ws = new WebServer(serverID, os, capacity, numLangs, langs);
            network[Server.getNumServers()-1] = ws;
            break;
         case "2":
            serverID = "FS" + String.format("%03d", FileServer.getNumFS()+1);
            
            int fsNumUsers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of users for FileServer (0-4000)"));
            Server fs = new FileServer(serverID, os, capacity, fsNumUsers);
            network[Server.getNumServers()-1] = fs;
            break;
         case "3":
            serverID = "DS" + String.format("%03d", DatabaseServer.getNumDBS()+1);
            
            String app = JOptionPane.showInputDialog("Choose application for DatabaseServer\n\n"
               +"[1] - Microsoft SQL Server\n"
               +"[2] - Oracle");
            if(app.equals("1")){
               app = "MS SQL SERVER";
            }else if(app.equals("2")){
               app = "ORACLE";
            }
            int dbsNumUsers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of users for DatabaseServer (0-2000)"));
            Server dbs = new DatabaseServer(serverID, os, capacity, app, dbsNumUsers);
            network[Server.getNumServers()-1] = dbs;
            break;
         case "0":
            JOptionPane.showMessageDialog(null, "Operation canceled");
            break;
         default:
            throw new IllegalArgumentException("Must enter a valid input for menu");
      }
      JOptionPane.showMessageDialog(null, "Server created successfuly!");
   }
   
   //Display All Server Information
   public static void allServerInfo(Server[] network){
      String report = "Current Network Information (All Servers)\n\n";
      double totalCapacity = 0;
      for(int i = 0; i < Server.getNumServers(); i++){
         report+= String.format("%s%n", network[i].toString());
         totalCapacity+= network[i].getCapacity();
      }
      report+= String.format("%nTotal Servers: %d%nTotal Capacity: %.2fTB", Server.getNumServers(), totalCapacity);
      JOptionPane.showMessageDialog(null, report);
   }
   
   //Display Server with Highest Usage
   public static void highestUsage(Server[] network){
      String serverType = JOptionPane.showInputDialog("Choose Server Type\n\n"
         +"[1] - WebServer\n"
         +"[2] - FileServer\n"
         +"[3] - DatabaseServer\n");
      double highestUsage = 0;
      String serverID = "";
      
      switch(serverType.toUpperCase()){
         case "1":
            serverType = "WebServer";
            break;
         case "2":
            serverType = "FileServer";
            break;
         case "3":
            serverType = "DatabaseServer";
            break;
         default:
            throw new IllegalArgumentException("Must enter a valid server type");
      }
      
      for(int i = 0; i < Server.getNumServers(); i++){
         if(network[i].getClass().getName().equals(serverType)){
            if(network[i].getUsage() > highestUsage){
               highestUsage = network[i].getUsage();
               serverID = network[i].getServerID();
            }
         }
      }
      JOptionPane.showMessageDialog(null, String.format("Server: %s     Usage: %.2f", serverID, highestUsage));
   }
   
   //Display Average Usage by Server Type
   public static void avgUsage(Server[] network){
      String serverType = JOptionPane.showInputDialog("Enter server type chice\n\n"
         +"[1] - WebServer\n"
         +"[2] - FileServer\n"
         +"[3] - DatabaseServer");
      int totalServers = 0;
      double totalUsage = 0;
      
      switch(serverType.toUpperCase()){
         case "1":
            serverType = "WebServer";
            totalServers = WebServer.getNumWS();
            break;
         case "2":
            serverType = "FileServer";
            totalServers = FileServer.getNumFS();
            break;
         case "3":
            serverType = "DatabaseServer";
            totalServers = DatabaseServer.getNumDBS();
            break;
         default:
            throw new IllegalArgumentException("Must enter a valid server type");
      }
      
      for(int i = 0; i < Server.getNumServers(); i++){
         if(network[i].getClass().getName().equals(serverType)){
            totalUsage+= network[i].getUsage();
         }
      }
      totalUsage /= totalServers;
      JOptionPane.showMessageDialog(null, String.format("Server Type: %s     Average Usage: %f", serverType, totalUsage));
   }
   
   //Main method (Menu)
   public static void main(String[] args){
      final int MAX_SERVERS = 250;
      Server[] network = new Server[MAX_SERVERS];
      boolean repeat = true;
      
      do{
         try{
            String choice = JOptionPane.showInputDialog("Enter choice\n\n"
               +"[1] - Add Server\n"
               +"[2] - Display All Server Info\n"
               +"[3] - Display Highest Usage Server by Server Type\n"
               +"[4] - Display Average Usage by Server Type\n"
               +"[5] - Quit Program\n");
               
            switch(choice){
               case "1":
                  addServer(network);
                  break;
               case "2":
                  allServerInfo(network);
                  break;
               case "3":
                  highestUsage(network);
                  break;
               case "4":
                  avgUsage(network);
                  break;
               case "5":
                  repeat = false;
                  break;
               default:
                  JOptionPane.showMessageDialog(null, "Must enter valid choice for menu");
            }
         }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
         }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      }while(repeat);
   }
}