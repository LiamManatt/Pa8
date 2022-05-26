/*
 * NAME: Liam Manatt
 * PID: A16895135
 */

/**
 * Bloom Filter Implementation
 *
 * @author Liam Manatt
 * @since 5/25/2022
 */
public class BloomFilterJunior {

    /* Constants */
    private static final int MIN_INIT_CAPACITY = 50;
    private static final int BASE256_LEFT_SHIFT = 8;
    private static final int HORNERS_BASE = 27;
    private final static int MIN = 5;
    private final static int RIGHT = 27;

    /* Instance variables */
    private boolean[] table;
    private int capacity;
    /**
     * Constructor for bfj
     *
     * @param int intial capacity
     * @throws  IAE if capacity <50
     */
    public BloomFilterJunior(int capacity) {
        if(capacity < MIN_INIT_CAPACITY){
            throw new IllegalArgumentException();
        }
        table = new boolean[capacity];
        this.capacity = capacity;
    }
    /**
     * inserts trues at hashed indexes
     *
     * @param string value to hash
     */
    public void insert(String value) {
        table[hashBase256(value)] = true;
        table[hashCRC(value)] = true;
        table[hashHorners(value)] = true;
    }
    /**
     * checks if true at all 3 indices
     *
     * @param string value to hash
     * @returns bool true if all 3 are true, false else
     */
    public boolean lookup(String value) {
        if(table[hashBase256(value)] && table[hashCRC(value)]
                && table[hashHorners(value)]){
            return true;
        }
        return false;
    }

    /**
     * Base-256 hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashBase256(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = ((hash << BASE256_LEFT_SHIFT) + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }

    /**
     * Simplified CRC hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashCRC(String value) {
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
     * Horner's hash function.
     *
     * @param value string to hash
     * @return hash value
     */
    private int hashHorners(String value) {
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = (hash * HORNERS_BASE + c) % table.length;
        }
        return Math.abs(hash % table.length);
    }
}
