package assignment6;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class BSTtimer {
    public static void main(String[] args) {
        new BSTtimer();
    }

    BSTtimer() {
//        BSTAscendingAdd();
        BSTRandomAdd();
//        TreeSetRandomAdd();
    }

    void BSTAscendingAdd() {
        int timesToLoop = 50;  // higher number causes more accurate average time
        int maxSize = 100000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavg");

        // testing loop
        for (int i = 1000; i <= maxSize; i += 1000) {   // each of these loops accounts for a different input size 'N'

            BinarySearchTree<Integer> randListBST;
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                randListBST = new BinarySearchTree<Integer>();
                for (int k = 0; k < i; k++) {
                    randListBST.add(k);
                }
            }

            midTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                randListBST = new BinarySearchTree<Integer>();
                for (int k = 0; k < i; k++) {
                }
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results
        }
    }

    void BSTRandomAdd() {
        int timesToLoop = 50;  // higher number causes more accurate average time
        int maxSize = 100000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavg");

        // testing loop
        for (int i = 1000; i <= maxSize; i += 1000) {   // each of these loops accounts for a different input size 'N'

            // declare necessary variables and lists for testing
            // allows i to equal 1000 then 5000 and then even 5000 increments after.

            ArrayList<Integer> intList = new ArrayList<Integer>(i);
            BinarySearchTree<Integer> randListBST = new BinarySearchTree<Integer>();
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // create list if Integers from 1 to 'i'
            for (int j = 1; j <= i; j++) {
                intList.add(j);
            }

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                intList = new ArrayList<Integer>(intList);
                randListBST = new BinarySearchTree<Integer>();
                for (int k = 0; k < i * 5; k++) {        //loops 10 times more to ensure good permuation
                    swapElements(intList, rand.nextInt(i), rand.nextInt(i));
                }
                for (int k = 0; k < i; k++) {
                    randListBST.add(intList.get(k));
                }
            }

            midTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                intList = new ArrayList<Integer>(intList);
                randListBST = new BinarySearchTree<Integer>();
                for (int k = 0; k < i * 5; k++) {
                    swapElements(intList, rand.nextInt(i), rand.nextInt(i));
                }
                for (int k = 0; k < i; k++) {
                    intList.get(k);
                }
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results
        }
    }

    void TreeSetRandomAdd() {
        int timesToLoop = 50;  // higher number causes more accurate average time
        int maxSize = 100000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavg");

        // testing loop
        for (int i = 1000; i <= maxSize; i += 1000) {   // each of these loops accounts for a different input size 'N'

            // declare necessary variables and lists for testing
            // allows i to equal 1000 then 5000 and then even 5000 increments after.

            ArrayList<Integer> intList = new ArrayList<Integer>(i);
            ArrayList<Integer> tempList = new ArrayList<Integer>(i);
            BinarySearchTree<Integer> randListBST = new BinarySearchTree<Integer>();
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // create list if Integers from 1 to 'i'
            for (int j = 1; j <= i; j++) {
                intList.add(j);
            }

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                tempList = new ArrayList<Integer>(intList);
                randListBST = new BinarySearchTree<Integer>();
                for (int k = 0; k < i * 5; k++) {        //loops 10 times more to ensure good permuation
                    swapElements(tempList, rand.nextInt(i), rand.nextInt(i));
                }
                for (int k = 0; k < i; k++) {
                    randListBST.add(tempList.get(k));
                }
            }

            midTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                tempList = new ArrayList<Integer>(intList);
                randListBST = new BinarySearchTree<Integer>();
                for (int k = 0; k < i * 5; k++) {
                    swapElements(tempList, rand.nextInt(i), rand.nextInt(i));
                }
                for (int k = 0; k < i; k++) {
                    tempList.get(k);
                }
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results
        }
    }

//    void bstInsertSorted() {
//        int timesToLoop = 100;  // higher number causes more accurate average time
//        int maxSize = 100000;   // determines right boundary of plot
//        Random rand = new Random(); // used to create random lists
//
//        //print info for the max input size and the number of times looping, as well as column headers for results
//        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavgTime");
//
//        // testing loop
//        for (int i = 0; i <= maxSize; i += 5000) {   // each of these loops accounts for a different input size 'N'
//
//            // declare necessary variables and lists for testing
//            // allows i to equal 1000 then 5000 and then even 5000 increments after.
//            if (i == 0) i = 1000;
//
//            ArrayList<Integer> intList = new ArrayList<Integer>(i);
//            ArrayList<Integer> tempList = new ArrayList<Integer>(i);
//            BinarySearchTree<Integer> randListBST = new BinarySearchTree<Integer>();
//            long startTime, midTime, endTime;
//            long seed = System.currentTimeMillis();
//            rand.setSeed(seed);
//
//            // create list if Integers from 1 to 'i'
//            for (int j = 1; j <= i; j++) {
//                intList.add(j);
//            }
//
//            // let a while loop run for a full second to get things spooled up.
//            startTime = System.nanoTime();
//            while (System.nanoTime() - startTime < 1e9) { //empty block
//            }
//
//            // startTime and testing start here.
//            startTime = System.nanoTime();
//            rand.setSeed(seed);
//            for (int j = 0; j < timesToLoop; j++) {
//                tempList = new ArrayList<Integer>(intList);
//                for (int k = 0; k < i * 10; k++) {        //loops 10 times more to ensure good permuation
//                    swapElements(tempList, rand.nextInt(i), rand.nextInt(i));
//                }
//                for (int k = 0; k < i; k++) {
//                    randListBST.add(tempList.get(k));
//                }
//            }
//
//            midTime = System.nanoTime();
//            rand.setSeed(seed);
//            for (int j = 0; j < timesToLoop; j++) {
//                tempList = new ArrayList<Integer>(intList);
//                for (int k = 0; k < i * 10; k++) {
//                    swapElements(tempList, rand.nextInt(i), rand.nextInt(i));
//                }
//                for (int k = 0; k < i; k++) {
//                    tempList.get(k);
//                }
//            }
//            endTime = System.nanoTime();
//
//            // subtract the over head and determine average time for 'i' calls to get.
//            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
//            double avgTime = totalTime / i;
//            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results
//
//            if (i == 1000) i = 0;
//        }
//    }

    public static <T extends Comparable<? super T>> void swapElements(ArrayList<T> list, int left, int right) {
        // stored copy of left indexed element to temp
        T temp = list.get(left);
        // reassign left indexed element to a copy of the the right indexed element
        list.set(left, list.get(right));
        // replace right indexed element with previous value of left index stored in temp
        list.set(right, temp);
    }
}

