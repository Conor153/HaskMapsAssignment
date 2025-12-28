package main;

/**
 * This is main.HashTableDivideAndConquer
 * Skeleton Code @author ruth.lennon
 * Functionality of methods @author Conor.Callaghan L00173495
 *  Date 23/12/2025
 */

public class HashTableDivideAndConquer {
    private String[] table;
    private int capacity;
    private int size;

    // Constructor to initialize the hash table with a given capacity
    /**
     * Constructor to create a Hash Table
     * @param capacity which is used to set the size of the table array
     * size is set to 0
     * */
    public HashTableDivideAndConquer(int capacity) {
        this.table = new String[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * function to search the table
     * @param key which is hashed to determine the table position
     * source of the code https://algs4.cs.princeton.edu/34hash/
     * modified the variable names and added 31 as the prime number to
     * significantly reduce collision chances
     * */
    private int hash(String key) {
    //<<fill in this method>>
        // Original code taken from https://algs4.cs.princeton.edu/34hash/
        // Princeton university. modified to make the variable names more in line with the class
        // Also used 31 as it is a prime number recommended by Princeton for Java
        int hash = 0;
        for(int letter = 0; letter<key.length(); letter++)
        {
            hash = (31 * hash + key.charAt(letter)) % capacity;
        }
        return hash;
    }

    /**
     * function to search the table
     * @param key which is inserted into the table
     * source of the code https://www.geeksforgeeks.org/dsa/program-to-implement-hash-table-using-open-addressing/
     * modified the indexing logic and variable names
     * */
    public void insert(String key) {
        if(size > capacity*0.75)
        {
            resize();
        }
        int hashPosition = hash(key);
        while(table[hashPosition] != null)
        {
            hashPosition = (hashPosition+1) % capacity;
        }
        table[hashPosition] = key;
        size++;
    }

    /**
     * function to search the table
     * @param key which is the value to be searched
     * @return boolean true if key is in the table, false if key is not in the table
     * */
    public boolean search(String key) {
        int hashPosition = hash(key);
        int count = 0;
        while(table[hashPosition] != null)
        {
            if(table[hashPosition].equals(key))
                return true;
            hashPosition = (hashPosition+1) % capacity;
            count++;
            if(count == capacity)
                return false;
        }
        return false;
    }

    /**
     * function to delete from the table
     * @param key which is deleted from the table
     * */
    public void delete(String key) {
        int hashPosition = hash(key);

        while(table[hashPosition] != null)
        {
            if(table[hashPosition].equals(key))
            {
                table[hashPosition] = null;
                size--;
                rehash();
                System.out.println("Conor Callaghan L00173495 Deleted " + key); // true
                return;
            }
            hashPosition = (hashPosition+1) % capacity;
        }
    }

    /**
     * function to resize the table and rehash the keys
     * */
    private void resize() {
        capacity = capacity*2;
        String[] tempTable = table;
        this.table = new String[capacity];
        size = 0;
        divideAndConquer(tempTable, 0, tempTable.length - 1);
    }

    /**
     * function to rehash the keys in the table
     * */
    private void rehash() {
        String[] tempTable = table;
        this.table = new String[capacity];
        size = 0;
        divideAndConquer(tempTable, 0, tempTable.length - 1);
    }

    /**
     * function to recursively divide the table and rehash the elements
     * source of code https://www.geeksforgeeks.org/dsa/merge-sort/
     * This source code was used to divide the array into smaller chunks
     * modified to change variable names as well as incorporate rehashing keys in the table
     * @param tempTable the old table that is to be rehashed
     * @param firstIndex First index of the dived portion
     * @param lastIndex Last index of the divided portion
     * */
    private void divideAndConquer(String[] tempTable, int firstIndex, int lastIndex) {
        //If first index and last index are equal
        if(firstIndex == lastIndex)
        {
            //Insert the key into the table where it will be rehashed
            if(tempTable[firstIndex] != null)
                insert(tempTable[firstIndex]);
            return;
        }
        //Get the midpoint of the current divided
        //recursively recall
        int midpoint = (firstIndex + lastIndex)/2;
        divideAndConquer(tempTable, firstIndex, midpoint);
        divideAndConquer(tempTable, midpoint+1, lastIndex);
    }

    /**
     * function to print the table
     * */
    public void printTable() {
        System.out.println("Printing Hash Table L00173495 Conor Callaghan");
        int count = 0;
        for(String t: table)
        {
            System.out.println("#"+count+++" "+t);
        }
        System.out.println("HashTable Printed for L00173495 Conor Callaghan");
        System.out.println();
    }

    public static void main(String[] args) {
        HashTableDivideAndConquer unihashTable = new HashTableDivideAndConquer(20);
        //Insert 5 keys by default
        unihashTable.insert("ATU Letterkenny");
        unihashTable.insert("ATU Killybegs");
        unihashTable.insert("ATU Sligo");
        unihashTable.insert("ATU Galway Mayo");
        unihashTable.insert("ATU Killybegs");
        //Print the keys
        System.out.println("Print Table");
        unihashTable.printTable();
        //Insert the keys until load factor is met
        unihashTable.insert("UCD");
        unihashTable.insert("Trinity College");
        unihashTable.insert("Queens Belfast");
        unihashTable.insert("Queens Dundalk");
        unihashTable.insert("Maynooth College");

        unihashTable.insert("Ulster University");
        unihashTable.insert("University College Cork");
        unihashTable.insert("Munster University");
        unihashTable.insert("Drogheda IT");
        unihashTable.insert("South East Technological University");

        unihashTable.insert("Shannon College");
        System.out.println();
        System.out.println("Print Table before Resize");
        unihashTable.printTable();
        unihashTable.insert("Thurles College");
        System.out.println("Load factor is greater than 75% of the table capacity");
        System.out.println("Table capacity will be doubled and items rehashed");
        //Load factor exceeded so table should resize
        System.out.println();
        System.out.println("Print Table after resize");
        unihashTable.printTable();
        unihashTable.insert("Maynooth University");
        unihashTable.insert("Dun Laoghaire IT");
        unihashTable.insert("Strandmillis University College");
        System.out.println();
        System.out.println("Print Table after 20 items inserted");
        unihashTable.printTable();
        //Search for items in the table
        System.out.println("L00173495 Is 'ATU Sligo' in the table? " + unihashTable.search("ATU Sligo")); // true
        System.out.println("L00173495 Is 'ATU Dundalk' in the table? " + unihashTable.search("ATU Dundalk"));
        System.out.println();
        //Delete items from table and search again
        unihashTable.delete("ATU Galway Mayo");
        unihashTable.delete("Ulster University");
        System.out.println("Conor Callaghan L00173495 Is 'ATU Galway Mayo' in the table? " + unihashTable.search("ATU Galway Mayo")); // true
        System.out.println("Conor Callaghan L00173495 Is 'Ulster University' in the table? " + unihashTable.search("Ulster University"));
        System.out.println();
        System.out.println("Print Table after deletions");
        unihashTable.printTable();
    }
}