/*
   Marco Alarcon
   IT206-201
   4/15/2021
   Programming Assignment 8
   
   WebServer Subclass
   --------------------------------------------------------------------
   This subclass extends the Server superclass and therefore inherits the
   instance, static, and constant variables as well as the methods from
   Server. However, since the private instance variables are not directly
   accessible due to having private access modifiers, those variables are
   accessed via calls to the superclass accessor methods.
   
   Apart from the inherited variables, the WebServer subclass contains a
   static int numWS variable that keeps track of the number of WebServer
   objects created thus far. Additionally, two constants 
   (MIN_LANGS & MAX_LANGS) dictate the min and max nnumber of languages the
   user is able to enter for a specific WebServer. 
   
   The WebServer constructor accepts serverID, os, and capacity variables
   which are passed on to the super constructor. However, the remaining
   numLangs and langs array parameters are passed into the WebServer instance
   and are set using the setNumLangs and setLangs. Additionally, the setUsage
   is overridden and used to calculate the usage by multiplying the number
   of programming languages by 25. Finally, the numWS variable is incremented
   to denote successful creation of a WebServer object.
   
   The toString method is overridden by including the list of programming
   languages in the String representation.
*/

public class WebServer extends Server{
   //Instance variables
   private double usage;
   private String[] langs;
   private int numLangs;
   
   //Static variable
   public static int numWS = 0;
   
   //Constants
   public static final int MIN_LANGS = 2;
   public static final int MAX_LANGS = 4;
   
   //Constructor
   public WebServer(String serverID, String os, double capacity, int numLangs, String[] langs){
      super(serverID, os, capacity);
      setNumLangs(numLangs);
      setLangs(langs);
      setUsage();
      numWS++;
   }
   
   //Accessors
   public double getUsage(){return this.usage;}
   public int getNumLangs(){return this.numLangs;}
   public String[] getLangs(){
      String[] langsCopy = new String[this.numLangs];
      for(int i = 0; i < this.numLangs; i++){
         langsCopy[i] = this.langs[i];
      }
      return langsCopy;
   }
   
   public static int getNumWS(){return numWS;}
   
   //Mutators
   public void setUsage(){
      if(this.numLangs < MIN_LANGS || this.numLangs > MAX_LANGS){
         throw new IllegalArgumentException("Number of programming languages");
      }
      this.usage = this.numLangs * 25;
   }
   
   public void setNumLangs(int numLangs){
      if(numLangs < MIN_LANGS || numLangs > MAX_LANGS){
         throw new IllegalArgumentException("Number of programming languages must be between 2 - 4");
      }
      this.numLangs = numLangs;
   }
   
   public void setLangs(String[] langs){
      //verify parameter array of langs
      for(int i = 0; i < this.numLangs; i++){
         if(langs[i] == null || langs[i].equals("")){
            throw new IllegalArgumentException("Programming language cannot be null");
         }
      }
      this.langs = langs;
   }
   
   //Special Purpose
   public String toString(){
      String langList = "";
      for(String x : this.langs){
         langList+= "["+x+"] ";
      }
      return String.format(
         "Server ID: %s     OS: %s     HardDrive Capacity: %.2f     Server Usage: %.2f     Language List: %s", super.getServerID(), super.getOS(), super.getCapacity(), this.usage, langList
      );
   }
   
   public boolean equals(Object o){
      if(this == o){
         return true;
      }
      if(!(o instanceof Server)){
         return false;
      }
      WebServer w = (WebServer) o;
      return this.getServerID().equals(w.getServerID()) &&
         this.getOS().equals(w.getOS()) &&
         this.getCapacity() == w.getCapacity() &&
         this.getUsage() == w.getUsage() &&
         this.getLangs() == w.getLangs() &&
         this.getNumLangs() == w.getNumLangs();
   }
}