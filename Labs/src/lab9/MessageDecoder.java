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

    public static void main(String[] args) {
        // array of bytes to hold input from file
        byte[] fileData = new byte[376];
        // FileInputStream object to read bytes from the secret.txt file
        FileInputStream inputBytes;
        try {
            inputBytes = new FileInputStream(new File("secret.txt"));
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return;
        }
        // read enough bytes to fill fileData
        int feedBack =0;
        try {
            feedBack = inputBytes.read(fileData);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (feedBack != 376)
            System.out.println("The correct number of bytes were not retrieved from the file!");
        // integer to hold 8 bits and convert to a char
        int nextChar = 0;
        // String to add characters and hold the final message
        String secret = "";
        // obtain key bits and perform algorithm to produce secret message
        int index = 0;
        for (int i = 0; i < 47; i++) {
            for (int j = 0; j < 8; j++)
                nextChar += ((fileData[index++] >> 2)%2)*((int)Math.pow(2.0, (double)j));
            secret += (char) nextChar;
            nextChar = 0;
        }
        System.out.println(secret);
    }
}
