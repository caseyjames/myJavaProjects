package assignment4;

import java.util.ArrayList;

/**
 * Created by Casey on 6/13/2014.
 */
public class Timer {
    long timesToLoop = 100;


    public static void main(String[] args) {
        new Timer();
    }

    Timer() {

//        int[] thresholdValues = {0, 5, 10, 20, 50, 100};
//
//        for (int i = 0; i < 6; i++) {
//
//            RecursiveSortingUtility.setMergeSortThreshold(thresholdValues[i]);
//            System.out.println("\n\nFor the threshold value of:  " + RecursiveSortingUtility.getMergesortThreshold());
//            System.out.println("\nInputSize\tTime");
//
//            for (int j = 1000; j <= 40000; j += 2000) {
//
//                ArrayList<Integer> sortList = RecursiveSortingUtility.generateAverageCase(j);
//                ArrayList<Integer> testArray;
//
//                long startTime, midpointTime, stopTime;
//
//                startTime = System.nanoTime();
//                while (System.nanoTime() - startTime < 1000000000) { // empty block
//                }
//
//                startTime = System.nanoTime();
//                for (long k = 0; k < timesToLoop; k++) {
//                    // create a random array of data and sort it
//                    testArray = new ArrayList<Integer>(sortList);
//                    RecursiveSortingUtility.mergeSortDriver(testArray);
//                }
//
//                midpointTime = System.nanoTime();
//
//                // Calculate the cost of looping
//
//                for (long h = 0; h < timesToLoop; h++) {
//                    testArray = new ArrayList<Integer>(sortList);
//                }
//
//                stopTime = System.nanoTime();
//
//                // Compute the time, subtract the cost of running the loop
//                // from the cost of running the loop and sorting the array.
//                // Average it over the number of runs.
//                double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
//                averageTime /= 1.0e9;
//
//                // print out tab-delimited results
//                System.out.println(j + "\t" + averageTime);
//            }
//        }


        for (int j = 1000; j <= 41000; j += 2000) {

            ArrayList<Integer> sortList = RecursiveSortingUtility.generateAverageCase(j);
            ArrayList<Integer> testArray;

            long startTime, midpointTime, stopTime;

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            startTime = System.nanoTime();
            for (long k = 0; k < timesToLoop; k++) {
                // create a random array of data and sort it
                testArray = new ArrayList<Integer>(sortList);
                RecursiveSortingUtility.quickSortDriver(testArray);
            }

            midpointTime = System.nanoTime();

            // Calculate the cost of looping

            for (long h = 0; h < timesToLoop; h++) {
                testArray = new ArrayList<Integer>(sortList);
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(j + "\t" + averageTime);
        }



    }
}
