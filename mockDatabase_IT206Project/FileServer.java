/*
   Marco Alarcon
   IT206-201
   4/15/2021
   Programming Assignment 8
   
   FileServer Subclass
   --------------------------------------------------------------------
   This subclass extends the Server superclass and therefore inherits the
   instance, static, and constant variables as well as the methods from
   Server. However, since the private instance variables are not directly
   accessible due to having private access modifiers, those variables are
   accessed via calls to the superclass accessor methods.
   
   Apart from the inherited variables, the FileServer subclass contains a
   static int numFS variable that keeps track of the number of FileServer
   objects created thus far. Additionally, the constant MAX_USERS keeps track
   of the max number of users allowed for one FileServer.
   
   The FileServer constructor accepts serverID, os, and capacity variables
   which are passed on to the super constructor. However, the remaining
   numUsers parameter is passed into the FileServer instance
   and is set using the setNumUsers method. Additionally, the setUsage
   is overridden and used to calculate the usage by multiplying the number
   of users by 0.025. Finally, the numFS variable is incremented
   to denote successful creation of a FileServer object.
   
   The toString method is overridden by including the number of users in the
   String representation.
*/

public class FileServer extends Server{
   //Instance variables
   private double usage;
   private int numUsers;
   
   //Static variables
   public static int numFS = 0;
   
   //Constants
   public static final int MAX_USERS = 4000;
   
   //Constructors
   public FileServer(String serverID, String os, double capacity, int numUsers){
      super(serverID, os, capacity);
      setNumUsers(numUsers);
      setUsage();
      numFS++;
   }
   
   //Accessors
   public double getUsage(){return this.usage;}
   public int getNumUsers(){return this.numUsers;}
   
   public static int getNumFS(){return numFS;}
   
   //Mutators
   public void setUsage(){
      if(this.numUsers < 0 || this.numUsers > MAX_USERS){
         throw new IllegalArgumentException("Number of users must be between 0-4000");
      }
      this.usage = this.numUsers * 0.025;
   }
   
   public void setNumUsers(int numUsers){
      if(numUsers < 0 || numUsers > MAX_USERS){
         throw new IllegalArgumentException("Number of users must be between 0-4000");
      }
      this.numUsers = numUsers;
   }
   
   //Special Purpose
   public String toString(){
      return String.format(
         "Server ID: %s     OS: %s     HardDrive Capacity: %.2f     Server Usage: %.2f     Number of Users: %d", super.getServerID(), super.getOS(), super.getCapacity(), this.usage, this.numUsers
      );
   }
   
   public boolean equals(Object o){
      if(this == o){
         return true;
      }
      if(!(o instanceof Server)){
         return false;
      }
      FileServer f = (FileServer) o;
      return this.getServerID().equals(f.getServerID()) &&
         this.getOS().equals(f.getOS()) &&
         this.getCapacity() == f.getCapacity() &&
         this.getUsage() == f.getUsage() &&
         this.getNumUsers() == f.getNumUsers();
   }
}