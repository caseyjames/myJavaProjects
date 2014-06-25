package assignment5;

import java.util.ArrayList;
import java.util.Random;

/**
 * Runs timing algorithms to find the execution time and complexity of various MyLinkedList operations.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class LinkedStructureTimer2 {
    long seed = 13489723891023874L;

    LinkedStructureTimer2() {
//        problem3ai();
//        problem3aii();
//        problem3bi();
//        problem3bii();
        problem3ci();
//        problem3cii();
    }

    public static void main(String[] args) {
        new LinkedStructureTimer2();
    }

    /**
     * Finds the average running time of the addFirst method for MyLinkedList O(1) expected
     */
    void problem3ai() {
        // substantiate a random (seeded) MyLinkedList to run tests upon
        MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
        int timesToLoop = 10000, maxSize = 20000;
        Random rand = new Random(seed);

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through list sizes and find the average time for addFirst on a list of each size, then print the results
        for (int i = 0; i <= maxSize; i += 500) {

                /* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime1, midpointTime2, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
                // empty block
            }

            // Now, run the test.
            startTime = System.nanoTime();

            for (long j = 0; j < timesToLoop; j++) {
                // create a MyLinkedList of the current size (the size is set by the outer for loop index 'i')
                for (int k = 0; k < i; k++)
                    testList.addFirst(rand.nextInt());

                // reset testList so it's an empty list for the next loop
                testList = new MyLinkedList<Integer>();
            }

            // midpointTime1 marks the end of the testing execution.
            midpointTime1 = System.nanoTime();

            // reset random variable to assure that the execution time for the setup overhead is _exactly_ the same.
            //  timing note: the statements between midpointTime1 and midpointTime2 do not affect the timing at all.
            rand = new Random(seed);

            // since resetting rand would necessarily take execution time we establish a new midpointTime in order to
            //  calculate the overhead alone (separate from the time taken to reset rand)
            midpointTime2 = System.nanoTime();

            // Calculate the cost of looping and resetting testList

            for (long j = 0; j < timesToLoop; j++) {
                testList = new MyLinkedList<Integer>();
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime1 - startTime) - (stopTime - midpointTime2)) / timesToLoop;
            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(i + "\t" + averageTime);
        }
    }

    /**
     * Finds the average running time of add(0, item) for an ArrayList
     */
    void problem3aii() {
        // substantiate a random (seeded) Arraylist to run tests upon
        ArrayList<Integer> testList = new ArrayList<Integer>();
        int timesToLoop = 10000, maxSize = 20000;
        Random rand = new Random(seed);

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through list sizes and find the average time for add(0, item) on a list of each size, then print the results
        for (int i = 0; i <= maxSize; i += 500) {

                /* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime1, midpointTime2, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
                // empty block
            }

            // Now, run the test.
            startTime = System.nanoTime();

            for (long j = 0; j < timesToLoop; j++) {
                // create an ArrayList of the current size (the size is set by the outer for loop index 'i')
                for (int k = 0; k < i; k++)
                    testList.add(0, rand.nextInt());

                // reset testList so it's an empty list for the next loop
                testList = new ArrayList<Integer>();
            }

            // midpointTime1 marks the end of the testing execution.
            midpointTime1 = System.nanoTime();

            // reset random variable to assure that the execution time for the setup overhead is _exactly_ the same.
            //  timing note: the statements between midpointTime1 and midpointTime2 do not affect the timing at all.
            rand = new Random(seed);

            // since resetting rand would necessarily take execution time we establish a new midpointTime in order to
            //  calculate the overhead alone (separate from the time taken to reset rand)
            midpointTime2 = System.nanoTime();

            // Calculate the cost of looping and resetting testList

            for (long j = 0; j < timesToLoop; j++) {
                testList = new ArrayList<Integer>();
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime1 - startTime) - (stopTime - midpointTime2)) / timesToLoop;
            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(i + "\t" + averageTime);
        }
    }

    /**
     * Finds the average running time of the get method for a MyLinkedList, O(N) expected
     */
    void problem3bi() {
        // substantiate a random (seeded) MyLinkedList to run tests upon and an ArrayList of added objects from which
        //  objects can be accessed for the get method
        MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
        Random rand = new Random(seed);

        int timesToLoop = 10000, maxSize = 51000;

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through list sizes and find the average time for addFirst on a list of each size, then print the results
        for (int i = 1000; i <= maxSize; i += 5000) {

                /* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime1, midpointTime2, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
                // empty block
            }

            // substantiate the list to .get from
            for (int k = 0; k < maxSize; k++)
                testList.addFirst(rand.nextInt());

            // Now, run the test.
            startTime = System.nanoTime();

            for (long j = 0; j < timesToLoop; j++) {
                // .get from a random index
                int randomIndex = rand.nextInt(testList.size());
                testList.get(randomIndex);
            }

            // midpointTime1 marks the end of the testing execution.
            midpointTime1 = System.nanoTime();

            // reset random variable to assure that the execution time for the setup overhead is _exactly_ the same.
            //  timing note: the statements between midpointTime1 and midpointTime2 do not affect the timing at all.
            rand = new Random(seed);

            // since resetting rand would necessarily take execution time we establish a new midpointTime in order to
            //  calculate the overhead alone (separate from the time taken to reset rand)
            midpointTime2 = System.nanoTime();

            // Calculate the total overhead (cost of looping, substantiating the list, creating a random index, and
            // resetting the list)
            for (long j = 0; j < timesToLoop; j++) {
                int randomIndex = rand.nextInt(testList.size());
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime1 - startTime) - (stopTime - midpointTime2)) / timesToLoop;
            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(i + "\t" + averageTime);
        }
    }

    /**
     * Finds the average running time of the get method for an ArrayList, O(1) expected
     */
    void problem3bii() {
        // substantiate a random (seeded) MyLinkedList to run tests upon and an ArrayList of added objects from which
        //  objects can be accessed for the get method
        ArrayList<Integer> testList = new ArrayList<Integer>();
        Random rand = new Random(seed);

        int timesToLoop = 10000, maxSize = 50000;

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through list sizes and find the average time for addFirst on a list of each size, then print the results
        for (int i = 2000; i <= maxSize; i += 3000) {

                /* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime1, midpointTime2, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
                // empty block
            }

            // substantiate the list to .get from
            for (int k = 0; k < maxSize; k++)
                testList.add(rand.nextInt());

            // Now, run the test.
            startTime = System.nanoTime();

            for (long j = 0; j < timesToLoop; j++) {
                // .get from a random index
                int randomIndex = rand.nextInt(testList.size());
                testList.get(randomIndex);
            }

            // midpointTime1 marks the end of the testing execution.
            midpointTime1 = System.nanoTime();

            // reset random variable to assure that the execution time for the setup overhead is _exactly_ the same.
            //  timing note: the statements between midpointTime1 and midpointTime2 do not affect the timing at all.
            rand = new Random(seed);

            // since resetting rand would necessarily take execution time we establish a new midpointTime in order to
            //  calculate the overhead alone (separate from the time taken to reset rand)
            midpointTime2 = System.nanoTime();

            // Calculate the total overhead (cost of looping, substantiating the list, creating a random index, and
            // resetting the list)
            for (long j = 0; j < timesToLoop; j++) {
                int randomIndex = rand.nextInt(testList.size());
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime1 - startTime) - (stopTime - midpointTime2)) / timesToLoop;
//            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(testList.size() + "\t" + averageTime);
        }
    }

    /**
     * Finds the average running time of the remove method for a MyLinkedList, O(N) expected
     */
    void problem3ci() {
        // substantiate a random (seeded) MyLinkedList to run tests upon and an ArrayList of added objects from which
        //  objects can be accessed for the get method
        MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
        Random rand = new Random(seed);

        int timesToLoop = 1000, maxSize = 100000;

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through list sizes and find the average time for addFirst on a list of each size, then print the results
        for (int i = 1000; i <= maxSize; i += 7000) {

                /* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime1, midpointTime2, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
                // empty block
            }

            // substantiate the list to remove from
            for (int k = 0; k < maxSize; k++)
                testList.addFirst(rand.nextInt());

            // Now, run the test.
            startTime = System.nanoTime();

            for (long j = 0; j < timesToLoop; j++) {
                // remove from a random index, then add to maintain size
                int randomIndex = rand.nextInt(testList.size());
                testList.remove(randomIndex);
                testList.addFirst(rand.nextInt());
            }

            // midpointTime1 marks the end of the testing execution.
            midpointTime1 = System.nanoTime();

            // reset random variable to assure that the execution time for the setup overhead is _exactly_ the same.
            //  timing note: the statements between midpointTime1 and midpointTime2 do not affect the timing at all.
            rand = new Random(seed);

            // since resetting rand would necessarily take execution time we establish a new midpointTime in order to
            //  calculate the overhead alone (separate from the time taken to reset rand)
            midpointTime2 = System.nanoTime();

            // Calculate the total overhead (cost of looping, creating a random index, and adding another element)
            for (long j = 0; j < timesToLoop; j++) {
                int randomIndex = rand.nextInt(testList.size());
                testList.addFirst(rand.nextInt());
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime1 - startTime) - (stopTime - midpointTime2)) / timesToLoop;
            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(i + "\t" + averageTime);
        }
    }

    /**
     * Finds the average running time of the remove method for an ArrayList, O(N) expected
     */
    void problem3cii() {
        // substantiate a random (seeded) MyLinkedList to run tests upon and an ArrayList of added objects from which
        //  objects can be accessed for the get method
        ArrayList<Integer> testList = new ArrayList<Integer>();
        Random rand = new Random(seed);

        int timesToLoop = 10000, maxSize = 50000;

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through list sizes and find the average time for addFirst on a list of each size, then print the results
        for (int i = 2000; i <= maxSize; i += 3000) {

                /* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime1, midpointTime2, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
                // empty block
            }

            // substantiate the list to .get from
            for (int k = 0; k < maxSize; k++)
                testList.add(rand.nextInt());

            // Now, run the test.
            startTime = System.nanoTime();

            for (long j = 0; j < timesToLoop; j++) {
                // .get from a random index
                int randomIndex = rand.nextInt(testList.size());
                testList.get(randomIndex);
            }

            // midpointTime1 marks the end of the testing execution.
            midpointTime1 = System.nanoTime();

            // reset random variable to assure that the execution time for the setup overhead is _exactly_ the same.
            //  timing note: the statements between midpointTime1 and midpointTime2 do not affect the timing at all.
            rand = new Random(seed);

            // since resetting rand would necessarily take execution time we establish a new midpointTime in order to
            //  calculate the overhead alone (separate from the time taken to reset rand)
            midpointTime2 = System.nanoTime();

            // Calculate the total overhead (cost of looping, substantiating the list, creating a random index, and
            // resetting the list)
            for (long j = 0; j < timesToLoop; j++) {
                int randomIndex = rand.nextInt(testList.size());
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime1 - startTime) - (stopTime - midpointTime2)) / timesToLoop;
//            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(testList.size() + "\t" + averageTime);
        }
    }
}
