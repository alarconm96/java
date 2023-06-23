/*
   Marco Alarcon
   IT206-201
   4/15/2021
   Programming Assignment 8
   
   Server Superclass
   -----------------------------------------------------------------
   This Server class is an abstract superclass that is meant to provide
   the basic serverID, os, capacity, and usage instance variables for its
   subclasses. The usage class, however, is given an abstract mutator method
   in the Server class since each subclass has a different method of
   calculating usage. Therefore, each subclass is expected to override the
   setUsage method. The Server class keeps track of the number of servers
   created using the static int numServers variable, which is incremented
   upon successful Server object instantiation.
   
   The Server class contains 3 constants:
   
      1. OPERATING_SYSTEMS - String array containing valid operating systems
      
      2. MAX_USAGE - Maximum usage allowed for servers
      
      3. ID_CHAR - Max number of characters in serverID
   
   The constructor calls mutator methods for serverID, os, and capacity in
   order to validate the parameters accepted by the constructor. If all data
   is correct, the instance variables are set and the numServers varaible is
   incremented.
   
   Special purpose methods toString and equals are included. The toString
   returns the serverID, os, capacity, and usage instance variables in a 
   well formatted String message. The equals method allows the Server
   object to compare itself to any Object passed in by the user.
*/

public abstract class Server{
   //Instance Variables
   private String serverID;
   private String os;
   private double capacity;
   private double usage;
   
   //Static variables
   public static int numServers;
   
   //Constants
   public static final String[] OPERATING_SYSTEMS = {
      "WINDOWS", "LINUX", "OS X"
   };
   public static final int MAX_USAGE = 100;
   public static final int ID_CHAR = 5;
   
   //Constructors
   public Server(String serverID, String os, double capacity){
      setServerID(serverID);
      setOS(os);
      setCapacity(capacity);
      numServers++;
   }
   
   //Accessors
   public String getServerID(){return this.serverID;}
   public String getOS(){return this.os;}
   public double getCapacity(){return this.capacity;}
   public double getUsage(){return this.usage;}
   
   public static int getNumServers(){return numServers;}
   
   //Mutators
   public void setServerID(String serverID){
      //verify serverID char length
      if(serverID.length() != ID_CHAR){
         throw new IllegalArgumentException("Server ID must be 5 characters");
      }
      //verify first 2 letters of serverID
      if(!Character.isLetter(serverID.charAt(0)) || !Character.isLetter(serverID.charAt(1))){
         throw new IllegalArgumentException("First 2 characters of Server ID must be letters");
      }
      //verify remaining 3 chars of serverID
      boolean valid = true;
      for(int i = 2; i < serverID.length(); i++){
         if(!Character.isDigit(serverID.charAt(i))){
            valid = false;
         }
      }
      if(!valid){
         throw new IllegalArgumentException("Remaining 4 characters of Server ID must be digits");
      }
      this.serverID = serverID;
   }
   
   public void setOS(String os){
      boolean valid = false;
      os = os.toUpperCase();
      for(String x : OPERATING_SYSTEMS){
         if(os.equals(x)){
            valid = true;
         }
      }
      if(!valid){
         throw new IllegalArgumentException("Server OS must be Windows, Linux, or OS X");
      }
      this.os = os;
   }
   
   public void setCapacity(double capacity){
      if(capacity < 0){
         throw new IllegalArgumentException("Hard drive capacity must be a positive number of terabytes (TB)");
      }
      this.capacity = capacity;
   }
   
   public abstract void setUsage();
   
   //Special Purpose
   public String toString(){
      return String.format("Server ID: %s     OS: %s     HD Capacity: %f TB     Server Usage: %f%", this.serverID, this.os, this.capacity, this.usage);
   }
   
   
   public boolean equals(Object o){
      //returns true if serverID matches
      if(this == o){
         return true;
      }
      if(o == null || getClass() != o.getClass()){
         return false;
      }
      Server server = (Server) o;
      return this.serverID.equals(server.serverID);
   }
}