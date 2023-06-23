/*
   Marco Alarcon
   IT206-201
   4/15/2021
   Programming Assignment 8
   
   DatabaseServer Subclass
   --------------------------------------------------------------------
   This subclass extends the Server superclass and therefore inherits the
   instance, static, and constant variables as well as the methods from
   Server. However, since the private instance variables are not directly
   accessible due to having private access modifiers, those variables are
   accessed via calls to the superclass accessor methods.
   
   Apart from the inherited variables, the DatabaseServer subclass contains
   a static int numDBS variable that keeps track of the number of
   DatabaseServer objects created thus far. Three constants are included
   in order to maintain DatabaseServer integrity. VALID_APPS is a String
   array that includes the following valid database applications: 
   MS SQL SERVER, ORACLE. MAX_USERS denotes the maximum number of users
   allowed to be entered for a DAtabaseServer. Finally, the USAGE_RATE is
   the number to be multiplied into the number of users to calculate the
   usage for DatabaseServers.
   
   The DatabaseServer constructor accepts serverID, os, and capacity variables
   which are passed on to the super constructor. However, the remaining
   app and numUsers parameters are passed into the DatabaseServer instance
   and are set using the setApp and setNumUsers methods. Additionally, the setUsage
   is overridden and used to calculate the usage by multiplying the number
   of users by 0.05. Finally, the numDBS variable is incremented
   to denote successful creation of a DatabaseServer object.
   
   The toString method is overridden by including the database application
   and number of users in the String representation.
*/

public class DatabaseServer extends Server{
   //Instance variables
   private double usage;
   private String app;
   private int numUsers;
   
   //Static variables
   public static int numDBS = 0;
   
   //Constants
   public static final String[] VALID_APPS = {"MS SQL SERVER", "ORACLE"};
   public static final int MAX_USERS = 2000;
   public static final double USAGE_RATE = 0.05;
   
   //Constructor
   public DatabaseServer(String serverID, String os, double capacity, String app, int numUsers){
      super(serverID, os, capacity);
      setApp(app);
      setNumUsers(numUsers);
      setUsage();
      numDBS++;
   }
   
   //Accessors
   public double getUsage(){return this.usage;}
   public String getApp(){return this.app;}
   public int getNumUsers(){return this.numUsers;}
   
   public static int getNumDBS(){return numDBS;}
   
   //Mutators
   public void setUsage(){
      if(this.getNumUsers() < 0 || this.getNumUsers() > MAX_USERS){
         throw new IllegalArgumentException("Number of users must be between 0-2000");
      }
      this.usage = this.getNumUsers() * USAGE_RATE;
   }
   
   public void setApp(String app){
      boolean valid = false;
      app = app.toUpperCase();
      for(String x : VALID_APPS){
         if(app.equals(x)){
            valid = true;
         }
      }
      if(!valid){
         throw new IllegalArgumentException("DB Server application must be either Microsoft SQL Server or Oracle");
      }
      this.app = app;
   }
   
   public void setNumUsers(int numUsers){
      if(numUsers < 0 || numUsers > MAX_USERS){
         throw new IllegalArgumentException("Number of users must be between 0-2000");
      }
      this.numUsers = numUsers;
   }
   
   //Special Purpose
   public String toString(){
      return String.format(
         "Server ID: %s     OS: %s     HardDrive Capacity: %.2f     Server Usage: %.2f     Number of Users: %d     DB Server Application: %s", super.getServerID(), super.getOS(), super.getCapacity(), this.usage, this.numUsers, this.app
      );
   }
   
   public boolean equals(Object o){
      if(this == o){
         return true;
      }
      if(!(o instanceof Server)){
         return false;
      }
      DatabaseServer d = (DatabaseServer) o;
      return this.getServerID().equals(d.getServerID()) &&
         this.getOS().equals(d.getOS()) &&
         this.getCapacity() == d.getCapacity() &&
         this.getUsage() == d.getUsage() &&
         this.getNumUsers() == d.getNumUsers() &&
         this.getApp().equals(d.getApp());
   }
}