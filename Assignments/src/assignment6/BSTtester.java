package assignment6;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

public class BSTtester extends TestCase {
    // null string reference to test case of NullPointerException, both item and array
    String nullString;
    ArrayList<String> nullStrOfStrings = new ArrayList<String>();
    // non-null BinarySearchTree reference to run other various test on
    BinarySearchTree<String> BSTtestList = new BinarySearchTree<String>();
    // BinarySearchTree object used to test addAll, removeAll, containsAll etc.
    ArrayList<String> arrayListTest = new ArrayList<String>(3);


    /**
     * Sets up various objects and arrays, as well as BinarySearchTree objects to test
     *
     * @throws Exception
     */
    protected void setUp() throws Exception {
        super.setUp();

        // set values for strOfStrings
        arrayListTest.add("1st");
        arrayListTest.add("2nd");
        arrayListTest.add("3rd");
    }

    /**
     * tests the add, isEmpty & contains methods along with a test for a NullPointerException
     *
     * @throws NullPointerException
     */
    public void testAdd() throws NullPointerException {
        // first, assert that there are no elements in the BinarySearchTree
        assertTrue(BSTtestList.isEmpty());
        // assert that 'true' is returned when adding "first" because the BinarySearchTree was changed
        assertTrue(BSTtestList.add("first"));
        // test that it is now not empty
        assertFalse(BSTtestList.isEmpty());
        // test the BSTtestList now contains "first"
        assertTrue(BSTtestList.contains("first"));
        // assert false that BSTtestList contains "third"
        assertFalse(BSTtestList.contains("third"));
        // now asserts that 'false' is returned because "first" is present & BinarySearchTree was not changed
        assertFalse(BSTtestList.add("first"));

        //re-do above test a second time
        assertTrue(BSTtestList.add("second"));
        assertTrue(BSTtestList.contains("second"));
        assertFalse(BSTtestList.add("second"));

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.add(nullString);
        }
        catch (Exception e) {
            assertEquals("Tried adding null", e.getMessage());
        }
    }

    /**
     * tests addAll, isEmpty, contains, containsAll, & size methods. Also test for case of NullPointerException
     *
     * @throws NullPointerException
     */
    public void testAddAll() throws NullPointerException {
        // first, assert that there are no elements in the BinarySearchTree
        assertTrue(BSTtestList.isEmpty());
        // use addAll with arrayListTest list, assert returns true as the BinarySearchTree is changed
        assertTrue(BSTtestList.addAll(arrayListTest));
        // assert that the BSTtestList contains the added items
        assertTrue(BSTtestList.contains("1st"));
        assertTrue(BSTtestList.contains("2nd"));
        assertTrue(BSTtestList.contains("3rd"));
        // assert false that BSTtestList contains "4th"
        assertFalse(BSTtestList.contains("4th"));
        // test contains all
        assertTrue(BSTtestList.containsAll(arrayListTest));
        // add to arrayListTest, to test that contains all then return false
        arrayListTest.add("4th");
        // assert that BSTtestList no longer contains all of arrayListTest
        assertFalse(BSTtestList.containsAll(arrayListTest));
        // assert addAll with arrayListTest returns true, because "4th"  now is added
        assertTrue(BSTtestList.addAll(arrayListTest));
        // assert addAll now returns false with arrayListTest because nothing is added
        assertFalse(BSTtestList.addAll(arrayListTest));
        // assert that proper elements are present
        assertTrue(BSTtestList.contains("1st"));
        assertTrue(BSTtestList.contains("2nd"));
        assertTrue(BSTtestList.contains("3rd"));
        assertTrue(BSTtestList.contains("4th"));
        assertTrue(BSTtestList.containsAll(arrayListTest));

        // assert the size (number of elements) should only be 4
        assertEquals(4, BSTtestList.size());

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.addAll(nullStrOfStrings);
        }
        catch (Exception e) {
            assertEquals("Tried to add a null Collection with addAll", e.getMessage());
        }
    }

    /**
     * tests the clear method, other methods within are already tested above to work.
     */
    public void testClear() {
        // first, assert that there are no elements in the BinarySearchTree
        assertTrue(BSTtestList.isEmpty());
        // use addAll with arrayListTest list, assert returns true as the BinarySearchTree is changed
        assertTrue(BSTtestList.addAll(arrayListTest));
        // assert that the BSTtestList contains the added items
        assertTrue(BSTtestList.contains("1st"));
        assertTrue(BSTtestList.contains("2nd"));
        assertTrue(BSTtestList.contains("3rd"));

        // test that it is now not empty
        assertFalse(BSTtestList.isEmpty());

        // clear BSTtestList
        BSTtestList.clear();
        // assert that BSTtestList is now empty
        assertTrue(BSTtestList.isEmpty());
        // assert non of the previous items added are now present
        assertFalse(BSTtestList.contains("1st"));
        assertFalse(BSTtestList.contains("2nd"));
        assertFalse(BSTtestList.contains("3rd"));
    }

    /**
     * Test the case of NullPointerException for the contains method. Test if BSTtestList is empty returns false.
     *
     * @throws NullPointerException
     */
    public void testContains() throws NullPointerException {
        // assert returns false when contains is used on an empty BinarySearchTree
        assertFalse(BSTtestList.contains("1st"));
        // add to BSTtestList so its not empty, assert return 'true' that it was indeed changed.
        assertTrue(BSTtestList.add("1st"));
        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.contains(nullString);
        }
        catch (Exception e) {
            assertEquals("Tried contains with null item", e.getMessage());
        }
    }

    /**
     * Test the case of NullPointerException for the containsAll method. containsAll method is re-tested as it is in the
     * addAll method test
     *
     * @throws NullPointerException
     */
    public void testContainsAll() throws NullPointerException {
        // first assert returns false if this BinarySearchTree is empty when calling contains all.
        assertFalse(BSTtestList.containsAll(arrayListTest));
        // add to BSTtestList so its not empty, assert return 'true' that it was indeed changed.
        assertTrue(BSTtestList.addAll(arrayListTest));
        // assert that all items added from arrayListTest are contained in BSTtestList with containsAll
        assertTrue(BSTtestList.containsAll(arrayListTest));
        arrayListTest.add("4th");
        assertTrue(BSTtestList.addAll(arrayListTest));
        assertTrue(BSTtestList.contains("1st"));
        assertTrue(BSTtestList.contains("2nd"));
        assertTrue(BSTtestList.contains("3rd"));
        assertTrue(BSTtestList.contains("4th"));
        assertTrue(BSTtestList.containsAll(arrayListTest));

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.containsAll(nullStrOfStrings);
        }
        catch (Exception e) {
            assertEquals("Tried contains with null item", e.getMessage());
        }
    }

    /* isEmpty is already tested multiple times to work in the above tests. */
    public void testIsEmpty() {
    }

    /**
     * First test case of NullPointerException, than adds multiple items to list and tests remove method by the removing
     * them and asserting the changes that should be made.
     *
     * @throws NullPointerException
     */
    public void testRemove() throws NullPointerException {

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.add(nullString);
        }
        catch (Exception e) {
            assertEquals("Tried adding null", e.getMessage());
        }

        // add items, assert that the BST contains them, then remove the items and assert that the BST doesn't contain them
        BSTtestList.clear();
        ArrayList<String> testList = new ArrayList<String>();
        /* this tree tests every remove scenario: removing a leaf, removing a node with one child, removing a node with
           two children, removing root in each of those cases, and removing every element in a tree */
        testList.add("first");
        testList.add("second");
        testList.add("third");
        testList.add("fourth");
        testList.add("fifth");
        testList.add("sixth");
        testList.add("seventh");
        BSTtestList.addAll(testList);
        for (String element : testList)
            assertEquals(true, BSTtestList.contains(element));
        for (String element : testList) {
            BSTtestList.remove(element);
            assertEquals(false, BSTtestList.contains(element));
        }
    }

    /**
     * Similar to test for the remove method only this time passes the whole array of items to be removed and asserts
     * the correct changes were made.
     *
     * @throws NullPointerException
     */
    public void testRemoveAll() throws NullPointerException {

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.add(nullString);
        }
        catch (Exception e) {
            assertEquals("Tried adding null", e.getMessage());
        }

        // add items, assert that the BST contains them, then remove the items and assert that the BST doesn't contain them
        BSTtestList.clear();
        ArrayList<String> testList = new ArrayList<String>();
        /* this tree tests every remove scenario: removing a leaf, removing a node with one child, removing a node with
           two children, removing root in each of those cases, and removing every element in a tree */
        testList.add("first");
        testList.add("second");
        testList.add("third");
        testList.add("fourth");
        testList.add("fifth");
        testList.add("sixth");
        testList.add("seventh");
        BSTtestList.addAll(testList);

        for (String element : testList)
            assertEquals(true, BSTtestList.containsAll(testList));
        BSTtestList.removeAll(testList);
        for (String element : testList)
            assertEquals(false, BSTtestList.contains(element));
    }

    /**
     * continually adds multiple items, then removes multiple items, and at each add or remove call, the size is checked
     * to assert that it is correct and updating as it should.
     */
    public void testSize() {
        // add elements asserting that size updates with each add, the do the same with remove
        BSTtestList.clear();
        assertEquals(0, BSTtestList.size());
        BSTtestList.add("first");
        assertEquals(1, BSTtestList.size());
        BSTtestList.add("second");
        assertEquals(2, BSTtestList.size());
        BSTtestList.add("third");
        assertEquals(3, BSTtestList.size());
        BSTtestList.add("fourth");
        assertEquals(4, BSTtestList.size());
        BSTtestList.add("fifth");
        assertEquals(5, BSTtestList.size());
        BSTtestList.add("sixth");
        assertEquals(6, BSTtestList.size());
        BSTtestList.add("seventh");
        assertEquals(7, BSTtestList.size());

        BSTtestList.remove("third");
        assertEquals(6, BSTtestList.size());
        BSTtestList.remove("first");
        assertEquals(5, BSTtestList.size());
        BSTtestList.remove("second");
        assertEquals(4, BSTtestList.size());
        BSTtestList.remove("seventh");
        assertEquals(3, BSTtestList.size());
        BSTtestList.remove("fourth");
        assertEquals(2, BSTtestList.size());
        BSTtestList.remove("sixth");
        assertEquals(1, BSTtestList.size());
        BSTtestList.remove("fifth");
        assertEquals(0, BSTtestList.size());
    }

    /**
     * Tests toArrayList and inOrderDFT
     */
    public void testToArrayList() {
        BSTtestList.clear();
        // first assert that BSTtestList is indeed empty
        assertTrue(BSTtestList.isEmpty());
        // test case that passing empty BST returns an empty list instead of throwing an exception
        ArrayList testEmpty = BSTtestList.toArrayList();
        // first assert testEmpty is not null
        assertFalse(testEmpty == null);
        // now assert that testEmpty ArrayList is a reference to an 'empty' arraylist
        assertTrue(testEmpty.isEmpty());
        // add items to testList
        ArrayList<String> testList = new ArrayList<String>();
        testList.add("first");
        testList.add("second");
        testList.add("third");
        testList.add("fourth");
        testList.add("fifth");
        testList.add("sixth");
        testList.add("seventh");
        // add all items now to BSTtestList to be converted back to ArrayList as sorted list and returned
        BSTtestList.addAll(testList);
        // list holding correct order of sorting to test against.
        ArrayList<String> correctList = new ArrayList<String>();
        correctList.add("fifth");
        correctList.add("first");
        correctList.add("fourth");
        correctList.add("second");
        correctList.add("seventh");
        correctList.add("sixth");
        correctList.add("third");
        // assert returned arraylist is equal to the known correct sorted list
        assertTrue(correctList.equals(BSTtestList.toArrayList()));
    }

    public void testFirst() throws NoSuchElementException {
        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.clear();
            BSTtestList.first();
        }
        catch (Exception e) {
            assertEquals("Tried first with an empty BST", e.getMessage());
        }
        // add elements asserting that first updates with each add, the do the same with remove
        String[] addList = new String[]{"20", "10", "15", "25", "22", "06", "08", "07", "05"};
        String[] assertAdd = new String[]{"20", "10", "10", "10", "10", "06", "06", "06", "05"};
        for (int i = 0; i < addList.length; i++) {
            BSTtestList.add(addList[i]);
            assertEquals(assertAdd[i], BSTtestList.first());
        }
        String[] removeList = new String[]{"15", "06", "22", "05", "20", "07", "08", "10"};
        String[] assertRemove = new String[]{"05", "05", "05", "07", "07", "08", "10", "25"};
        for (int i = 0; i < removeList.length; i++) {
            BSTtestList.remove(removeList[i]);
            assertEquals(assertRemove[i], BSTtestList.first());
        }
    }

    public void testLast() throws NoSuchElementException {
        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.clear();
            BSTtestList.last();
        }
        catch (Exception e) {
            assertEquals("Tried last with an empty BST", e.getMessage());
        }
        // add elements asserting that first updates with each add, the do the same with remove
        String[] addArray = new String[]{"20", "10", "40", "30", "50", "65", "45", "35", "70"};
        String[] assertAdd = new String[]{"20", "20", "40", "40", "50", "65", "65", "65", "70"};
        for (int i = 0; i < addArray.length; i++) {
            BSTtestList.add(addArray[i]);
            assertEquals(assertAdd[i], BSTtestList.last());
        }
        String[] removeArray = new String[]{"65", "50", "35", "70", "40", "45", "20", "30"};
        String[] assertRemove = new String[]{"70", "70", "70", "45", "45", "30", "30", "10"};
        for (int i = 0; i < removeArray.length; i++) {
            BSTtestList.remove(removeArray[i]);
            assertEquals(assertRemove[i], BSTtestList.last());
        }
    }

    /**
     * empty test method because inOrderDFT is already invoked and tested in the testToArrayList method
     */
    public void testInOrderDFT() {
    }

    /* for each traversal the same tree is substantiated and checked against the expected output for that traversal */
    public void testPreOrderDFT() {
        BSTtestList.clear();
        String[] addArray = new String[]{"20", "10", "40", "30", "50", "65", "45", "35", "70"};
        for (String addString : addArray)
            BSTtestList.add(addString);

        String[] correctArray = new String[]{"20", "10", "40", "30", "35", "50", "45", "65", "70"};
        ArrayList<String> correctList = new ArrayList<String>();
        for (String correctString : correctArray)
            correctList.add(correctString);

        assertEquals(correctList, BSTtestList.preOrderDFT());
    }

    public void testPostOrderDFT() {
        BSTtestList.clear();
        String[] addArray = new String[]{"20", "10", "40", "30", "50", "65", "45", "35", "70"};
        for (String addString : addArray)
            BSTtestList.add(addString);

        String[] correctArray = new String[]{"10", "35", "30", "45", "70", "65", "50", "40", "20"};
        ArrayList<String> correctList = new ArrayList<String>();
        for (String correctString : correctArray)
            correctList.add(correctString);

        assertEquals(correctList, BSTtestList.postOrderDFT());
    }

    public void testLevelOrderBFT() {
        BSTtestList.clear();
        String[] addArray = new String[]{"20", "10", "40", "30", "50", "65", "45", "35", "70"};
        for (String addString : addArray)
            BSTtestList.add(addString);

        String[] correctArray = new String[]{"20", "10", "40", "30", "50", "35", "45", "65", "70"};
        ArrayList<String> correctList = new ArrayList<String>();
        for (String correctString : correctArray)
            correctList.add(correctString);

        assertEquals(correctList, BSTtestList.levelOrderBFT());
    }

    /**
     * test the writeDot method which creates a graph representation of this BST in a .dot file to be used with the
     * graphviz software tool.
     */
    public void testWriteDot() {
        // initialize new ArrayList to add and store the letters in the order shown.
        ArrayList<String> letters = new ArrayList<String>();
        letters.add("i");
        letters.add("d");
        letters.add("j");
        letters.add("b");
        letters.add("f");
        letters.add("a");
        letters.add("c");
        letters.add("e");
        letters.add("h");
        letters.add("g");
        // create new BinarySearchTree 'dotList' and all the letters, occurs in the same order as above
        BinarySearchTree<String> dotList = new BinarySearchTree<String>(letters);
        // assert that all the strings in 'letter' are also now in dotList
        assertTrue(dotList.containsAll(letters));
        // creates a dot file graph representing dotList, proper function will put the new file in this workspace dir.
        dotList.writeDot("writeDotTest_letters.dot");

        //--------------------------------------------------------------------
        /*tested a second time with a slightly larger list of Integers
            first create list from 1 to desired size*/
        ArrayList<Integer> lrgList = new ArrayList<Integer>(15);
        for (int i = 1; i <= 15; i++) {
            lrgList.add(i);
        }

        // new random object to create random numbers, uncomment chosen seed method to use and comment out other
//        Random rand = new Random(12349879102366254L);
        Random rand = new Random(System.currentTimeMillis());

        // perform swaps of two elements at two random index chosen, loops 500 times to ensure good permutation
        for (int i = 0; i < 500; i++) {
            int left = rand.nextInt(15);
            int right = rand.nextInt(15);
            // stored copy of left indexed element to temp
            Integer temp = lrgList.get(left);
            // reassign left indexed element to a copy of the the right indexed element
            lrgList.set(left, lrgList.get(right));
            // replace right indexed element with previous value of left index stored in temp
            lrgList.set(right, temp);
        }

        // create new BinarySearchTree 'dotList2' with the random permuted list if Integers
        BinarySearchTree<Integer> dotList2 = new BinarySearchTree<Integer>(lrgList);
        // assert that all the Integers in lrgList are now in dotList2 as well
        assertTrue(dotList2.containsAll(lrgList));
        // creates a dot file graph representing dotList2, proper function will put the new file in this workspace dir.
        dotList2.writeDot("writeDotTest_integers.dot");
    }
}
