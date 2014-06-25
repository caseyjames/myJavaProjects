package assignment4;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Class for testing the RecursiveSortingUtility class
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 6/12/2014
 */
public class RecursiveSortingTester extends TestCase {

    /// all of the ArrayLists of Integer type used in each method for testing
    ArrayList<Integer> mergeSortList, quickSortList, pivotStrategyList, bestCaseArray, worstCaseArray,
            swapElementsList, expectedSwapArray;

    // listSize is defines generated list size as well as range of values
    // numTries is for testing some methods multiple times to average the results
    // threshold can be changed on the fly in setUp() and is set in the setMergeSortThreshold test method
    int listSize, numTries, threshold;
    // known arrays to assign to ArrayLists so expected values can be known
    int[] arr, arr2, arr3;
    // variables keeping track of number of tests in generateAverageCase test, used to find average number of inversions
    int testCount, inversionSum;


    /**
     * Sets up each of the ArrayLists above for testing, as well as sets numTries
     *
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();

        // initialize arrays
        int[] arr = {1, 4, 9, 5, 13, 11, 24, -2, 32, -7};
        int[] arr2 = {1, 6, 3, 5, 0, 7, 2, 4};
        int[] arr3 = {2, 5, 3, 0, 6, 7, 1, 4};

        // set integer parameter values
        threshold = 10;
        numTries = 100;
        listSize = 1000;

        // generate a random list for mergesort and quicksort to sort and then test
        mergeSortList = RecursiveSortingUtility.generateAverageCase(listSize);
        quickSortList = RecursiveSortingUtility.generateAverageCase(listSize);

        // instantiate ArrayList collections
        pivotStrategyList = new ArrayList<Integer>(arr.length);
        quickSortList = new ArrayList<Integer>(arr2.length);
        swapElementsList = new ArrayList<Integer>(arr2.length);
        expectedSwapArray = new ArrayList<Integer>(arr3.length);

        // assign array values to different ArrayLists to be tested
        for (int element : arr)
            pivotStrategyList.add(element);

        // assign array values to different ArrayLists to be tested
        for (int element : arr2) {
            quickSortList.add(element);
            swapElementsList.add(element);
        }

        //expected array after swapping swapElementsList
        for (int element : arr3)
            expectedSwapArray.add(element);
    }

    /**
     * Tests that the setMergeSortThreshold method works properly and allows setting of threshold on the fly
     *
     * @throws Exception
     */
    public void testSetMergeSortThreshold() throws Exception {
        // invoke method from class name
        RecursiveSortingUtility.setMergeSortThreshold(threshold);
        // test that the threshold is indeed set properly
        assertEquals(threshold, RecursiveSortingUtility.getMergesortThreshold());
    }

    /**
     * Invokes mergeSortDrive method to test sorting of a randomly sorted list called mergeSortList
     *
     * @throws Exception
     */
    public void testMergeSortDriver() throws Exception {
        // sort mergeSortList
        RecursiveSortingUtility.mergeSortDriver(mergeSortList);
        // test that all elements are now in ascending order
        for (int i = 0; i < listSize - 1; i++)
            assertTrue(mergeSortList.get(i).compareTo(mergeSortList.get(i + 1)) <= 0);
    }

    /**
     * Invokes quickSortDriver method to test sorting of a randomly sorted list called quickSortList
     *
     * @throws Exception
     */
    public void testQuickSortDriver() throws Exception {
        // sort quickSortList
        RecursiveSortingUtility.quickSortDriver(quickSortList);
        // test that all elements are now in ascending order
        for (int i = 0; i < quickSortList.size() - 1; i++)
            assertTrue(quickSortList.get(i).compareTo(quickSortList.get(i + 1)) <= 0);
    }

    /**
     * Tests if goodPivotStrategy returns middle index of pivotStrategyList, and also that the correct pivot value is
     * located at that middle pivot index. (uses array 'arr' above)
     *
     * @throws Exception
     */
    public void testGoodPivotStrategy() throws Exception {
        // middle index should be 4
        assertEquals(4, RecursiveSortingUtility.goodPivotStrategy(pivotStrategyList, 0, 9));
        // value at index 4 of arr is 13, test for this
        assertEquals(13, (int) pivotStrategyList.get(RecursiveSortingUtility.goodPivotStrategy(pivotStrategyList, 0, 9)));
    }

    /**
     * Test if the random pivot index returned by betterPivotStrategy is indeed in the index range of pivotStrategyList,
     * it does this by testing that the element in arr at the returned index is also present in pivotStrategyList
     *
     * @throws Exception
     */
    public void testBetterPivotStrategy() throws Exception {
        // store the returned random index
        int pivotIndex = RecursiveSortingUtility.betterPivotStrategy(pivotStrategyList, 0, pivotStrategyList.size() - 1);
        // assert valid pivotIndex, test that pivotStrategyList contains element at pivotIndex of 'arr' array
        assertTrue(pivotStrategyList.contains((Integer) arr[pivotIndex]));
    }

    /**
     * Test that returned pivot index points to an element in pivotStrategyList that is always greater than -7 and
     * always less than 32, the min and max values of pivotStrategyList. bestPivotStrategy returns 1 of 3 random
     * non-repeating indexes that has the median value element of the 3 elements at those indexes.
     *
     * @throws Exception
     */
    public void testBestPivotStrategy() throws Exception {
        // variable to store returned pivot index with each loop
        int pivotIndex;
        // tested many times to assure pivot index element is never -7 or 32
        for (int i = 0; i < numTries; i++) {
            pivotIndex = RecursiveSortingUtility.bestPivotStrategy(pivotStrategyList, 0, pivotStrategyList.size() - 1);
            assertTrue(pivotStrategyList.get(pivotIndex) > -7 && pivotStrategyList.get(pivotIndex) < 32);
        }
    }

    /**
     * Tests that generateBestCase returns an ArrayList with all values from from 1 to listSize in ascending order.
     *
     * @throws Exception
     */
    public void testGenerateBestCase() throws Exception {
        // list generation is tested many times 'numTries' assuring consistent results
        for (int i = 0; i < numTries; i++) {
            bestCaseArray = RecursiveSortingUtility.generateBestCase(listSize);
            // test that every element at j is less than the next element.
            for (int j = 0; j < bestCaseArray.size() - 1; j++) {
                assertTrue(bestCaseArray.get(j) <= bestCaseArray.get(j + 1));
            }
        }
    }

    /**
     * Test to count the number of inversion in the random list generated by generateAverageCase, and compare it to the
     * average number of inversions in a completely random list which averages to (N^2-N)/4 inversions.
     *
     * @throws Exception
     */
    public void testGenerateAverageCase() throws Exception {

        // initiate inversions counting variable to 0 and set the number of inputs or list size 'N'
        inversionSum = 0;
        listSize = 1000;
        // set number of times to redo the test (more tries gives a better average)
        testCount = 20;

        // for each loop, send a new generateAverageCase returned ArrayList to the inversionCounter method
        // and add the amount of inversion in that list for that loop to inversionSum
        for (int i = 0; i < testCount; i++)
            inversionSum += RecursiveSortingTester.inversionCounter(RecursiveSortingUtility.generateAverageCase(listSize));

        // determine average amount of inversions for N inputs and amount of times tested equal to testCount
        double averageInversions = (double) inversionSum / testCount;
        // using 'N' calculate the expected average amount of inversion for the input amount of 'N'
        double expectedInversions = (double) listSize * (listSize - 1) / 4;
        // calculate the difference between the expected inversions and the acutal inversions, as a percentage
        double percentError = Math.abs(expectedInversions - averageInversions) / expectedInversions * 100;

        // print debug data of these two variables to compare the randomness of the generateAverageCase method.
//        System.out.println("\nFor a list size of N = " + N + " and total times tested equal to " + testCount + " times: ");
//        System.out.println("\nActual inversions count average for generated ArrayLists was:  " + averageInversions);
//        System.out.println("The Expected inversion count average for N = " + N + " inputs is:  " + expectedInversions);
//        System.out.println("\nThe difference bettween expected amount of inversions and actual average amount is: ");
//        double diff = Math.abs(averageInversions - expectedInversions);
//        System.out.println("     DIFFERENCE = abs(" + averageInversions + " - " + expectedInversions + ") = " + diff);
//        System.out.format("%n     Or, only a %4.2f percent difference.\n", percentError);

        // assert that the number of inversions is approximately close to what is expected for an InsertionSort on random data
        assertTrue(percentError < 5);
    }

    /**
     * Tests that generateWorstCase returns an ArrayList with all values from from listSize to 1 in descending order.
     */
    public void testGenerateWorstCase() {
        // list generation is tested many times 'numTries' assuring consistent results
        for (int i = 0; i < numTries; i++) {
            worstCaseArray = RecursiveSortingUtility.generateWorstCase(listSize);
            // test that every element at j is greater than the next element.
            for (int j = 0; j < worstCaseArray.size() - 1; j++) {
                assertTrue(worstCaseArray.get(j) >= worstCaseArray.get(j + 1));
            }
        }
    }

    /**
     * Tests that swapElements method swaps elements in given list at given indices properly.
     *
     * @throws Exception
     */
    public void testSwapElements() throws Exception {
        // make multiple swaps of list, the final expected array is predetermined as expectedSwapArray above.
        RecursiveSortingUtility.swapElements(swapElementsList, 1, 3);
        RecursiveSortingUtility.swapElements(swapElementsList, 3, 4);
        RecursiveSortingUtility.swapElements(swapElementsList, 0, 6);

        // compare swapElementsList after completing swaps with ArrayList expectedSwapArray
        for (int i = 0; i < swapElementsList.size(); i++)
            assertEquals(0, swapElementsList.get(i).compareTo(expectedSwapArray.get(i)));

    }

    /**
     * This is an extra method created in this test class that counts the number of inversions in an ArrayList. It is
     * used to test the randomness of the generateAverageCase method. On average, a random permuted list will have
     * N*(N-1)/4 inversion. This method is used to aid in comparing the actual inversions of a list to the expected
     * average amount of inversions for the given number of inputs 'N'
     *
     * @param list list to be sorted and count the number of inversions
     * @param <T>  generic type of ArrayList that must extend Comparable
     *
     * @return integer amount of the inversions in the passed ArrayList before it is sorted.
     */
    public static <T extends Comparable<? super T>> int inversionCounter(ArrayList<T> list) {
        // variable to keep track of number of inversions
        int inversionSum = 0;
        // for each element at 'i', the for loops count how many elements are to the right of itself but smaller than
        // itself. After 'i' passes by every element, all inversion have been accounted for.
        for (int i = 0; i < list.size(); i++)
            for (int j = i + 1; j < list.size() - 1; j++)
                if (list.get(i).compareTo(list.get(j)) > 0)
                    inversionSum++;
        return inversionSum;
    }

    /**
     * Prints a message to show completion of JUnit tests
     */
    public static void testPrintSuccessMessage() {
        System.out.println("If no exceptions were thrown and this message printed then all JUnit tests were successfully" +
                " passed!");
    }
}