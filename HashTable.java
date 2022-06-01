/*
 * Name: Liam Manatt
 * PID: A16895135
 */

import javax.naming.BinaryRefAddr;
import java.util.Arrays;


/**
 * Hash table implementation
 * 
 * @author Liam Manatt
 * @since 5/25/2022
 */
public class HashTable implements IHashTable {
    private final static int BASE = 15;
    private final static int MIN = 5;
    private final static int RIGHT = 27;
    private final static int TWICE = 2;
    /* the bridge for lazy deletion */
    private static final String BRIDGE = new String("[BRIDGE]".toCharArray());

    /* instance variables */
    private int size; // number of elements stored
    private String[] table; // data table
    private int capacity;
    private double REHASH_FACTOR = .55;
    private int collisions;
    private int rehashes;
    private String log;

    /**
     * Constructor for hashtable with base capacity of 15
     *
     */
    public HashTable() {
       this(BASE);
       capacity = BASE;
    }
    /**
     * Constructor for hashtable
     * @param int capacity initial capacity
     * @throws  IAE if capacity <5
     */
    public HashTable(int capacity) {
        if(capacity < MIN){
            throw new IllegalArgumentException();
        }
        table = new String[capacity];
        size = 0;
        this.capacity = capacity;
        collisions = 0;
        rehashes = 1;
        log = "";

    }

    @Override
    /**
     * tries to insert item into hash
     * @param string to hash and insert
     * @returns bool true if not in hash, false else
     * @throws  NPE if value is null
     */
    public boolean insert(String value) {
        double cap = capacity;
        double sizeDouble = size;
        if(value == null){
            throw new NullPointerException();
        }
        for(int i = 0; i <capacity; i++) {
            if (table[(hashString(value) + i) % capacity] == null ) {
                if (sizeDouble / cap > REHASH_FACTOR) {
                    rehash();
                }
                table[(hashString(value) + i) % capacity] = value;
                size++;
                return true;
            }
            if (table[(hashString(value) + i) % capacity].equals(value)) {
                return false;
            }
            collisions ++;
        }
        return false;
    }
    /**
     * tries to find item in hash and delete it
     * @param string to find
     * @returns bool true if in hash, false else
     * @throws NPE if value is null
     */
    @Override
    public boolean delete(String value) {
        if(value == null) {
            throw new NullPointerException();
        }
        for(int i = 0; i <capacity; i++) {
            if (table[(hashString(value) + i) % capacity] == null) {
                return false;
            }
            if (table[(hashString(value) + i) % capacity].equals(value)){
                table[(hashString(value) + i) % capacity] = BRIDGE;
                size--;
                return true;
            }
        }
        return false;
    }
    /**
     * tries to find item in hash
     * @param string to find
     * @returns bool true if in hash, false else
     * @throws NPE if value is null
     */
    @Override
    public boolean lookup(String value) {
        if(value == null) {
            throw new NullPointerException();
        }
        for(int i = 0; i <capacity; i++) {
            if (table[(hashString(value) + i) % capacity] == null) {
                return false;
            }
            if(table[(hashString(value) + i) % capacity].equals(value)) {
                    return true;
            }
        }
        return false;
    }
    /**
     * gets n elems in the hash
     * @returns int n elems in the hash
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * gets capacity of the hash
     * @returns int capacity of the hash
     */
    @Override
    public int capacity() {
        return capacity;
    }
    /**
     * gets the collisions per rehash of the hash
     * @returns string collisions per rehash of the hash
     */
    public String getStatsLog() {
        return log;
    }
    /**
     * doubles the capacity of the hash and rehashes all elems
     */

    private void rehash() {
        double cap = capacity;
        double sizeDouble = size;
        log += "Before rehash #" + rehashes + ": load factor " +
                String.format("%.2f", sizeDouble/cap) + ", " + collisions
                + " collision(s).\n";
        String[] newTable = new String[capacity*TWICE];
        capacity = capacity * TWICE;
        String[] tempTable = table;
        table = newTable;
        size = 0;
        /**
         * inserts elems into new hash
         */
        for(int i = 0; i< tempTable.length; i++){
            if((tempTable[i]!= null) && !(tempTable[i].equals(BRIDGE))){
                insert(tempTable[i]);
            }
        }
        rehashes ++;
        collisions = 0;
    }
    /**
     * Simplified CRC hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashString(String value) {
        int hashValue = 0;
        for(int i=0; i<value.length(); i++){
            int leftShiftedValue = hashValue << MIN;
            int rightShiftedValue = hashValue >>> RIGHT;
            hashValue = (leftShiftedValue | rightShiftedValue) ^ value.charAt(i);
        }
        if (hashValue < 0){
            hashValue = hashValue * -1;
        }
        return hashValue % capacity;
    }

    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
