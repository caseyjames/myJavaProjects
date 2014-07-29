package FinalProject;

import java.io.*;
import java.util.Comparator;

/**
 * Created by Casey on 7/28/2014.
 */
public class Decompressor {
    private File srcFile;
    private File dstFile;

    public void decompress(File _srcFile, File _dstFile) {
        srcFile = _srcFile;
        dstFile = _dstFile;


        // attempt to open the file with FileInputStream then use DataInputStream
        FileInputStream inputStream;
        DataInputStream inFile ;
        try {
            inputStream = new FileInputStream(srcFile);
            inFile = new DataInputStream(inputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        byte nextByte;
        int freq;
        int byteCount = 0;
        PriorityQueueHEAP<CharNode> pq = new PriorityQueueHEAP<CharNode>(new CharNodeComparator());
        try {
            while (true) {
                nextByte = inFile.readByte();
                freq = inFile.readInt();
                byteCount += 5;
                if (nextByte == 0 && freq == 0)
                    break;
                pq.add(new CharNode((char) nextByte, freq));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
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
        // assign final node in pq as the root node
        CharNode root = pq.deleteMin();

        // use file and PrintWriter to open file to print decompressed file to.
        PrintWriter outFile;
        try {
            outFile = new PrintWriter(dstFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // start reading file again to encode it from beginning
        String intStr;
        String encoding = "";
        byte[] allBytes = new byte[((int)(srcFile.length()) - byteCount)];
        // first build string 'encoding' of all the rest of the bits in the file
        try {
            inFile.read(allBytes);
            for (int i = 0; i < allBytes.length; i++) {
                intStr = Integer.toBinaryString((int) allBytes[i]);
                while (intStr.length() < 8)
                    intStr = "0" + intStr;
                encoding += intStr;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        CharNode currentNode = new CharNode(root);
        // use encode string to begin decoding and printing decoded characters to outFile.
        for (int i = 0; i < encoding.length(); i++) {
            if (encoding.charAt(i) == '0') {
                if (currentNode.getLeft() != null)
                    currentNode = currentNode.getLeft();
                else {
                    if (currentNode.getChar() == (char) -1)
                        break;
                    outFile.print(currentNode.getChar());
                    currentNode = new CharNode(root);
                }
            } else {
                if (currentNode.getRight() != null)
                    currentNode = currentNode.getRight();
                else {
                    if (currentNode.getChar() == (char) -1)
                        break;
                    outFile.print(currentNode.getChar());
                    currentNode = new CharNode(root);
                }
            }
        }

        // close files when all bytes are printed to file.
        try {
            outFile.close();
            inFile.close();
            inputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


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

        public CharNode(CharNode node) {
            data = node.getChar();
            freq = node.getFreq();
            left = node.getLeft();
            right = node.getRight();
            parent = node.getParent();
            encoding = node.getEncoding();
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
