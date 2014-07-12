package assignment8;

import java.util.Random;

/**
 * class to perform the timing analysis of assignment 8
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class HashTableTimer {
    static int MAX_TABLE_SIZE = 100000;
    static int TIMES_TO_LOOP = 100;
    static int INTERVAL = 5000;


    public static void main(String[] args) {
        hashFunctionTime();
    }

    public static void hashFunctionTime() {
        long startTime, midTime, endTime;
        int dataIndex = 0;
        // stores values than prints at the end.
        int[] sizeData = new int[MAX_TABLE_SIZE / INTERVAL + 1];
        double[] timeData = new double[MAX_TABLE_SIZE / INTERVAL + 1];

        //uncomment the hashfunction you want to test
        /*HashFunctor hasher = new BadHashFunctor();*/
        /*HashFunctor hasher = new FairHashFunctor();*/
        HashFunctor hasher = new GoodHashFunctor();

        // table sizes
        for (int i = 0; i <= MAX_TABLE_SIZE; i += INTERVAL) {
            if (i == 0) i = 1000;

            int combined = i * TIMES_TO_LOOP;
//            String[] randomStrings = stringArray(i * TIMES_TO_LOOP);
            String[] randomStrings = stringArray(combined);
            String temp;
            int index;

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            startTime = System.nanoTime();
            index = 0;
//            for (int j = 0; j < TIMES_TO_LOOP; j++) {
            for (int k = 0; k < combined; k++) {
                temp = randomStrings[index++];
                // this next statement is what is dependent on the hashtable size.
                int hashIndex = hasher.hash(temp) % i;
            }
//            }

            midTime = System.nanoTime();
            index = 0;
//            for (int j = 0; j < TIMES_TO_LOOP; j++) {
            for (int k = 0; k < combined; k++) {
                temp = randomStrings[index++];
            }
//            }

            endTime = System.nanoTime();
            sizeData[dataIndex] = i;
            timeData[dataIndex++] = (double) (2 * midTime - startTime - endTime) / combined;
//            timeData[dataIndex++] = (double) (2 * midTime - startTime - endTime) / i;

            if (i == 1000) i = 0;
        }
        // after testing finishes, print stored data
        System.out.println("size\ttime");
        for (int i = 0; i <= MAX_TABLE_SIZE / INTERVAL; i++) {
            System.out.println(sizeData[i] + "\t" + timeData[i]);
        }
    }

    public static String[] stringArray(int capacity) {
        // method to create a string array of random strings made up of lowercase letters.

        String[] array = new String[capacity];
        Random rand = new Random(System.currentTimeMillis());

        // first loop to iterate indexes in array
        for (int i = 0; i < capacity; i++) {
            // sets random string length at index 'i' of the string array, length from 4 to 20 characters
            int stringLength = rand.nextInt(17) + 4;
            // for loop adds random characters to string at index 'i' until j = stringLength-1
            array[i] = "";
            for (int j = 0; j < stringLength; j++) {
                array[i] += Character.toString((char) (rand.nextInt(26) + 97));
            }
//            if (i%1000==0)
//                System.out.println(array[i]);

        }
        return array;
    }

//     TODO: conduct an experiment assess the quality and efficiency of each of your three hash functions.
//    * A recommendation for this experiment is to create two plots: one that shows the
//    growth of the number of collisions incurred by each hash function for a variety of
//    hash table sizes, and one that shows the actual running time required by each hash
//    function for a variety of hash table sizes. You may use either type of table for
//    this experiment.

//     TODO: conduct an experiment assess the quality and efficiency of each of your two hash tables.
//    * A recommendation for this experiment is to create two plots: one that shows the number of
//     collisions incurred by each hash table using the hash function in GoodHashFunctor, and one
//     that shows the actual running time required by each hash table using the hash function in
//     GoodHashFunctor.
}
