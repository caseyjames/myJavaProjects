package lab9;

import java.io.File;
import java.io.FileInputStream;

/**
 * Message Decoder class to decode the .txt file in lab 9
 *
 * @author Casey Nordgran
 * @version 7/16/2014
 */
public class MessageDecoder {
    // array of bytes to hold input from file
    byte[] fileData = new byte[376];

    // FileInputStream object to read bytes from the secret.txt file
    File inputFile;
    try{

    }
    FileInputStream inputBytes;
    try {
        inputBytes = new FileInputStream(new File("secret.txt"));
    }
    catch(Exception e){
        System.err.println(e.getMessage());
    }
}
