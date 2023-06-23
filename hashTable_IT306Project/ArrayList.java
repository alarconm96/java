/*
 * Marco Alarcon
 * IT 306-002
 * April 19, 2022
 * Assignment 6
 * */
package assignment6;

public class ArrayList{
    protected Entry[] data;
    protected int size;
    protected int capacity;

    public ArrayList() {
    	this.size = 0;
    	this.capacity = 0;
    }

    //initialize data array of type E[] and set the capacity
    public ArrayList(int capacity) {
       super();
       this.data = new Entry[capacity];
       this.capacity = capacity;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    //check if index valid, then return element at index i
    public Entry get(int i) throws IndexOutOfBoundsException {
    	//System.out.println("get element");
    	checkIndex(i, this.capacity);
        return this.data[i];
    }

    //check if index valid, then replace old element with new element, finally return old element
    public Entry set(int i, Entry e) throws IndexOutOfBoundsException {
    	checkIndex(i, this.capacity);
    	Entry replaced = this.data[i];
    	this.data[i] = e;
    	return replaced;
    }

    
   
    //check if index is within array bounds
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
    	//System.out.println("check index: i = " + i + " n = " + n);
        if (i < 0 || i >= n) throw new IndexOutOfBoundsException("Illegal index: " + i);
    }
    
    //overridden toString to print table contents
    public String toString() {
    	System.out.println("Printing HashTable Contents");
    	String s = "";
    	for (int i = 0; i < this.data.length; i++) {
			System.out.printf("[%d] %s%n", i+1, get(i));
		}
    	return s;
    }
    
    //nested Entry class
    protected class Entry {
        
        private String k;  // key
        private String v;  // value

        public Entry(String key, String value) {
          this.k = key;
          this.v = value;
        }

        // methods of the Entry interface
        public String getKey() {
        	if (this != null && this.k == null) {
				return "DEFUNCT";
			}else if (this.k == null) {
				return "null";
			}
        	return k;
    	}
        public String getValue() {
        	if (this != null && this.v == null) {
				return "DEFUNCT";
			}else if (this.v == null) {
				return "null";
			}
        	return this.v;
    	}

        // utilities not exposed as part of the Entry interface
        protected void setKey(String key) { k = key; }
        protected void setValue(String value) { v = value; }
      
        //overridden toString prints key and value of Entry
        public String toString() {
        	String entry = String.format("Key: %s || %s", this.getKey(), this.getValue());
        	return entry;
        }
      
    }

}
