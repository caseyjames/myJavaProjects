package FinalProject;

import java.io.*;
import java.util.Comparator;

/**
 * Created by Casey on 7/28/2014.
 */
public class Compressor {
    private File srcFile;
    private File dstFile;

    public void compress(File _srcFile, File _dstFile) {
        // assign values to srcFile and dstFile
        srcFile = _srcFile;
        dstFile = _dstFile;
        // PrintWriter to open file to print compressed file to.
        PrintWriter outFile;
        try {
            outFile = new PrintWriter(dstFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // attempt to open the file with FileReader then use BufferedReader
        FileReader inputStream;
        try {
            inputStream = new FileReader(srcFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        BufferedReader reader = new BufferedReader(inputStream);

        // array to count char frequencies in file, charNumbers index is the char code
        int[] charNumbers = new int[256];
        int nextByte;

        // try block increments frequency for charNumbers index equal to char code
        try {
            while ((nextByte = reader.read()) != -1) {
                charNumbers[nextByte]++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // priority queue to make binary trie
        PriorityQueueHEAP<CharNode> pq = new PriorityQueueHEAP<CharNode>(new CharNodeComparator());
        CharNode currentChar;
        // array to hold CharNodes that represent data for each character in the input file.
        CharNode[] charArray = new CharNode[256];
        // loading the priority queue
        for (int i = 0; i < 256; i++) {
            if (charNumbers[i] != 0) {
                currentChar = new CharNode((char) i, charNumbers[i]);
                charArray[i] = currentChar;
                pq.add(currentChar);
            }
        }
        // add EOF char
        pq.add(new CharNode((char) -1, 1));

        // building the binary trie
        CharNode left;
        CharNode right;
        CharNode parent;
        while (pq.size() > 1) {
            left = pq.deleteMin();
            right = pq.deleteMin();
            parent = new CharNode(left, right, (left.getFreq() + right.getFreq()));
            left.setParent(parent);
            right.setParent(parent);
            pq.add(parent);
        }

        // determine each characters encoding & set the value in its corresponding CharNode
        String encoding;
        for (int i = 0; i < 256; i++) {
            encoding = "";
            if (charArray[i] != null) {
                currentChar = charArray[i];
                // this portion prints the file header
                outFile.print(currentChar.getChar());
                outFile.print(currentChar.getFreq());
                while (currentChar.getParent() != null) {
                    if (currentChar.getParent().getLeft().getChar() == currentChar.getChar())
                        encoding = "0" + encoding;
                    else
                        encoding = "1" + encoding;
                    currentChar = currentChar.getParent();
                }
                charArray[i].setEncoding(encoding);
            }
        }
        // print end of header characters
        outFile.print((byte) 0);
        outFile.print(0);

        try {
            inputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // attempt to re-open the file with FileReader then use BufferedReader
        try {
            inputStream = new FileReader(srcFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        reader = new BufferedReader(inputStream);

        // start reading file again to encode it from beginning
        String bitString = "";
        int addZeros;
        try {
            while ((nextByte = reader.read()) != -1) {
                bitString += charArray[nextByte].getEncoding();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add remaining bits to make file of complete bytes
        addZeros = 8 - bitString.length() % 8;
        while (addZeros > 0) {
            bitString = bitString + "0";
            addZeros--;
        }

        int bitIndex = 0;
        byte toPrint;
        // traverse each character in bitString and print bytes to file
        while (bitIndex < bitString.length()) {
            toPrint = 0;
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(bitIndex) == '1') {
                    toPrint = (byte) (toPrint >>> 1);
                    toPrint += (byte) 128;
                } else
                    toPrint = (byte) (toPrint >>> 1);
                bitIndex++;
            }
            outFile.print((char) toPrint);
        }

        // close file when all bytes are printed to file.
        outFile.close();


        if (!dstFile.isFile())
            System.out.println(dstFile + " compression was unsuccessful!");
        else
            System.out.println(dstFile + " compression was successful!");
    }

    private static class CharNode {
        private char data;
        private int freq;
        private CharNode left;
        private CharNode right;
        private CharNode parent;
        private String encoding;

        public CharNode(char _data, int _freq) {
            data = _data;
            freq = _freq;
        }

        public CharNode(CharNode _left, CharNode _right, int _freq) {
            freq = _freq;
            left = _left;
            right = _right;
        }

        public char getChar() {
            return data;
        }

        public int getFreq() {
            return freq;
        }

        public void setParent(CharNode _parent) {
            parent = _parent;
        }

        public CharNode getParent() {
            return parent;
        }

        public void setEncoding(String str) {
            encoding = str;
        }

        public String getEncoding() {
            return encoding;
        }

        public CharNode getLeft() {
            return left;
        }

        public CharNode getRight() {
            return right;
        }
    }

    private static class CharNodeComparator implements Comparator<CharNode> {
        public int compare(CharNode o1, CharNode o2) {
            if (o1.getFreq() > o2.getFreq())
                return 1;
            else if (o1.getFreq() < o2.getFreq())
                return -1;
            else if (o1.getChar() < o2.getChar())
                return 1;
            else if (o1.getChar() > o2.getChar())
                return -1;
            else
                return 0;
        }
    }
}
