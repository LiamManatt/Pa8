/*
 * NAME: Liam Manatt
 * PID: A16895135
 */

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Document Frequency Implementation
 *
 * @author Liam Manatt
 * @since 5/25/2022
 */

public class DocumentFrequency {
    private Scanner scanner;
    private HashTable hashTable;
    /**
     * Constructor for DocumentFrequency with file path
     * @throws IOException when filepath DNE
     *
     */
    public DocumentFrequency(String path) throws IOException {
        File file = new File(path);
        try {
            scanner = new Scanner(file);
        }
        catch(Exception e){
            throw new IOException();
        }
    }
    /**
     * counts num of lines in file
     * @returns int amount of lines in file
     *
     */
    public int numDocuments() {
        int counter = 0;
        while (scanner.hasNextLine()) {
            counter ++;
            scanner.nextLine();
        }
        return counter;
    }
    /**
     * counts num of lines in file with the word
     * @returns int amount of lines in file with the word
     *
     */
    public int query(String word) {
        int counter = 0;
        while (scanner.hasNextLine()) {
            hashTable = new HashTable();
            String doc[] = scanner.nextLine().split(" ");
            for(int i =0; i<doc.length; i++){
                hashTable.insert(doc[i].toLowerCase());
            }
            if(hashTable.lookup(word.toLowerCase())){
                counter++;
            }
        }
        return counter;
    }
}
