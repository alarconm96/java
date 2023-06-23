/*
 * Marco Alarcon
 * IT 306-002
 * April 19, 2022
 * Assignment 6
 * */
package assignment6;

public class HashTable extends ArrayList{
    private static int numElement;
    private Entry DEFUNCT = new Entry(null, null); // sentinel for deleted item
    private int collisions; // counts the total number of collisions during insertion

    public HashTable(int capacity){
      super(capacity); 
    }
    
    //Returns true if location is either empty or the defunct sentinel
    private boolean isAvailable(int j) {
    	checkIndex(j, capacity);
    	if (data[j] == null || data[j].equals(DEFUNCT)) {
			return true;
		}
    	return false;
    }
    
    // compute and return hash code given the key
    private int computeHashCode(String key){
    	int ascii = 0;
    	for (int i = 0; i < key.length(); i++) {
			ascii += (int) key.charAt(i);
		}
    	return ascii;
	}
    
    //EXTRA CREDIT - compute polynomial hashcode
    private int computePolyHashCode(String key) {
    	int ascii = 0;
    	for (int i = 0; i < key.length(); i++) {
			int n = (int) key.charAt(i);
			ascii+= n^(i+1);
		}
    	return ascii;
    }
    
    // compute and return the compressed hash index 
    private int compressHashCode(int h1){
    	int hash = h1 % capacity;
    	return hash;
    }
    
    // return the index for a key.  
    public int findSlot(String k){
    	int index = compressHashCode(computeHashCode(k));
    	checkIndex(index, capacity);
    	return index;
    }
    
    //EXTRA CREDIT - return index for key using polynomial hashing
    public int findPolySlot(String k){
    	int index = compressHashCode(computePolyHashCode(k));
    	checkIndex(index, capacity);
    	return index;
    }
    
    // return the value associated with key K
    public String tableSearch(String k){
    	int index = compressHashCode(computeHashCode(k));
    	checkIndex(index, capacity);
    	if (data[index] == null || data[index].equals(DEFUNCT)) {
			return null;
		}
    	return data[index].getKey();
    }
    
    //EXTRA CREDIT - return the value associated with key k using polynomial hashing
    public String tablePolySearch(String k){
    	int index = compressHashCode(computePolyHashCode(k));
    	checkIndex(index, capacity);
    	if (data[index] == null || data[index].equals(DEFUNCT)) {
			return null;
		}
    	return data[index].getKey();
    }
    
    // inserts the value associated with key K if table is not full
    // if collision, probe each element linearly until available element is found
    public String tableInsert(String k, String v) {
    	if (size == capacity) {
			throw new IllegalStateException("Table is at maximum capacity - remove an element before inserting a new one");
		}
    	int index = compressHashCode(computeHashCode(k));
    	checkIndex(index, capacity);
    	Entry e = new Entry(k, v);
    	if (isAvailable(index)) {
			set(index, e);
			numElement++;
		}else {
			while (!isAvailable(index)) {
				collisions++;
				index = ++index % capacity;
				checkIndex(index, capacity);
				if (isAvailable(index)) {
					set(index, e);
					numElement++;
					break;
				}
			}
		}
    	return k;
    }
    
    //EXTRA CREDIT - insert value associated with key k, assuming table is not full
    //if collision, probe each element linearly until available slot is found
    public String tablePolyInsert(String k, String v) {
    	if (size == capacity) {
			throw new IllegalStateException("Table is at maximum capacity - remove an element before inserting a new one");
		}
    	int index = compressHashCode(computePolyHashCode(k));
    	checkIndex(index, capacity);
    	Entry e = new Entry(k, v);
    	if (isAvailable(index)) {
			set(index, e);
			numElement++;
		}else {
			while (!isAvailable(index)) {
				collisions++;
				index = ++index % capacity;
				checkIndex(index, capacity);
				if (isAvailable(index)) {
					set(index, e);
					numElement++;
					break;
				}
			}
		}
    	return k;
    }
    
    //remove the value associated with key K  
    public String tableRemove(String k) {
    	if (getNumElements() == 0) {
			throw new IllegalStateException("Table is empty - no elements to remove");
		}
    	int index = compressHashCode(computeHashCode(k));
    	checkIndex(index, capacity);
    	if (tableSearch(k) == null) {
			throw new IllegalArgumentException("Element " + k + " does not exist");
		}
    	Entry replaced = set(index, DEFUNCT);
    	numElement--;
    	return replaced.getKey();
	}
    
    //EXTRA CREDIT - remove the value associated with key k using polynomial hashing
    public String tablePolyRemove(String k) {
    	if (getNumElements() == 0) {
			throw new IllegalStateException("Table is empty - no elements to remove");
		}
    	int index = compressHashCode(computePolyHashCode(k));
    	checkIndex(index, capacity);
    	if (tablePolySearch(k) == null) {
			throw new IllegalArgumentException("Element " + k + " does not exist");
		}
    	Entry replaced = set(index, DEFUNCT);
    	numElement--;
    	return replaced.getKey();
	}
    
    //prints table contents using overridden toString()
    public void tablePrint(){
    	this.toString();
    }
    
    //prints collisions that have occurred so far with current table
    public void printTableStats() {
    	numCollisions();
    }
    
    public int getNumElements() {
    	return HashTable.numElement;
    }
    
    public int getCollisions() {
    	return this.collisions;
    }
    
    public void numElements() {
    	System.out.printf("Number of elements in table: %d%n", getNumElements());
    }
    
    public void numCollisions() {
    	System.out.printf("Number of collisions: %d%n", getCollisions());
    }
}  
