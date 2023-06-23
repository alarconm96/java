/*
 * Marco Alarcon
 * IT 306-002
 * April 19, 2022
 * Assignment 6
 * */
package assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestHashTable {
    public static void main(String[] args) {    	
    	final int CAPACITY = 37;
    	
        //hash table to use basic ASCII sum hashing
    	HashTable HT = new HashTable(CAPACITY);
    	
    	//hash table to use polynomial ASCII hashing
        HashTable polyHT = new HashTable(CAPACITY);
    	
    	//read from HashTable.csv file
    	try {
			File file = new File("HashTable.csv");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				//split values using comma
				String[] line = sc.nextLine().split(",");
				
				//get rid of byte order mark (BOM) on UTF-8 .csv file
				if (line[0].length() > 1) {
					line[0] = line[0].substring(line[0].length()-1);
				}
				
				//query HT and polyHT using commands from first line[] element
				switch (line[0]) {
				case "I":
					//insert into HT
					try {
						String inserted = HT.tableInsert(line[1], String.format("Name: %s %s || Dept: %s || GPA: %s", line[2], line[3], line[4], line[5]));
						System.out.printf("Element %s inserted%n", inserted);
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					}
					
					//insert into polyHT
					try {
						String polyInserted = polyHT.tablePolyInsert(line[1], String.format("Name: %s %s || Dept: %s || GPA: %s", line[2], line[3], line[4], line[5]));
						System.out.printf("Element %s inserted (polynomial)%n", polyInserted);
						
					} catch (IllegalStateException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				case "S":
					//search in HT
					String searched = HT.tableSearch(line[1]);
					if (searched != null) {
						System.out.printf("Element %s found%n", searched);
					}else {
						System.out.printf("Element %s was NOT found%n", line[1]);
					}
					//search in polyHT
					String polySearched = polyHT.tablePolySearch(line[1]);
					if (polySearched != null) {
						System.out.printf("Element %s found (polynomial)%n", polySearched);
					}else {
						System.out.printf("Element %s was NOT found (polynomial)%n", line[1]);
					}
					break;
				case "R":
					//remove from HT
					try {
						String removed = HT.tableRemove(line[1]);
						System.out.printf("Element %s removed%n", removed);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					//remove from polyHT
					try {
						String polyRemoved = polyHT.tablePolyRemove(line[1]);
						System.out.printf("Element %s removed (polynomial)%n", polyRemoved);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					throw new IllegalArgumentException("Unexpected query: " + line[0]);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}   
    	
    	//print HT and polyHT contents
    	System.out.println("");
    	HT.tablePrint();
    	System.out.println("");
    	polyHT.tablePrint();
    	
    	//EXTRA CREDIT - compare HT and polyHT collisions
    	System.out.println("");
    	System.out.println("EXTRA CREDIT");
    	System.out.println("Comparing Base HashTable and Polynomial HashTable Statistics");
    	System.out.print("Base: ");
    	HT.printTableStats();
    	System.out.print("Polynomial: ");
    	polyHT.printTableStats();
    }

}
