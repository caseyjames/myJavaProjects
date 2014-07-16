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
        byte[] fileData = new byte[376]; // array of bytes to store input
        FileInputStream inputBytes;  // create FileInputStream object
        try {
            inputBytes = new FileInputStream(new File("secret.txt"));
        }catch(Exception e){
            System.err.println(e.getMessage());
            return;
        }
        // read enough bytes to fill fileData
        try {
            if (inputBytes.read(fileData) != 376) {
                System.out.println("The correct number of bytes were not retrieved from the file!");
                return;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        int nextChar = 0;   // int to hold 8 bits at a time
        String secretMessage = "";
        /* nested for-loops to obtain 3rd bit (index 2) of each byte, multiplied by 2^(j) to assure correct bit
            position and added it to nextChar. When 8 bits fill nextChar, it's converted to char, added to
            secretMessage, then reset to zero */
        int index = 0;
        for (int i = 0; i < 47; i++) {
            for (int j = 0; j < 8; j++)
                nextChar += ((fileData[index++] >> 2)%2)*((int)Math.pow(2.0, (double)j));
            secretMessage += (char) nextChar;
            nextChar = 0;
        }
        // print secret message to console
        System.out.println(secretMessage);
    }
}
