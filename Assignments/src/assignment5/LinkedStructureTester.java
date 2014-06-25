package assignment5;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A JUnit testing class to test the methods of MyLinkedList class, and the MyStack class at the end with the
 * testBulkTest() method. This JUnit test does not use the setUp or tearDown methods as each test, for the most part,
 * uses a slightly different linked list of data. Therefore, list assignments are done in the test methods.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 6/16/2014
 */
public class LinkedStructureTester extends TestCase {
    // list for testing that contains objects of type String
    MyLinkedList<String> testListString = new MyLinkedList<String>();
    // second list for testing that will have no elements added to it.
    MyLinkedList<String> testListEmpty = new MyLinkedList<String>();

    /**
     * Tests that the first element is <tt>null</tt>, then adds 2 elements checking after each that the element added is
     * in the first index. This test also tests the <tt>getFirst</tt> method at the same time.
     */
    public void testAddFirst() {
        // first test that the head and first element is null
        assertTrue(testListString.head == null);
        // add an string object to the testList
        testListString.addFirst("addFirstTest001");
        assertTrue(testListString.head != null);
        // assert that the first element in the list is now "addFirstTest001"
        assertTrue(testListString.getFirst().equals("addFirstTest001"));
        // do the same as above to test it a second time
        testListString.addFirst("addFirstTest002");
        assertTrue(testListString.getFirst().equals("addFirstTest002"));
    }

    /**
     * Tests that the first element is <tt>null</tt>, then adds the previous two elements as before with <tt>addFirst</tt>
     * the adds 2 elements checking after each that the element added are in the last index.
     * This test also tests the <tt>getLast</tt> method at the same time.
     */
    public void testAddLast() {
        assertTrue(testListString.head == null);
        testListString.addFirst("addFirstTest001");
        testListString.addFirst("addFirstTest002");
        // add an string object to the testList with addLast
        testListString.addLast("addLastTest001");
        // assert that the last element in the list is now "addFirstTest001"
        assertTrue(testListString.getLast().equals("addLastTest001"));
        // do the same as above to test it a second time
        testListString.addLast("addLastTest002");
        assertTrue(testListString.getLast().equals("addLastTest002"));
    }

    /**
     * This test first asserts with the new test that <tt>testListString</tt> is again null. It then adds 5 elements to
     * the list with already proven working methods. It then tests the <tt>add</tt> and <tt>get</tt> methods.
     *
     * @throws Exception
     */
    public void testAdd() throws IndexOutOfBoundsException {
        assertTrue(testListString.head == null);
        // add five string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");

        // use same list as the two tests above so that elements are already present
        // add an element to index 2
        testListString.add(2, "addTest001");
        // assert that the element at index 2 in the list is now "addTest001"
        assertTrue(testListString.get(2).equals("addTest001"));
        // do the same as above to test it a second time
        testListString.add(2, "addTest002");
        assertTrue(testListString.get(2).equals("addTest002"));
        // test that the first element added "addTest001" is now at index 3
        assertTrue(testListString.get(3).equals("addTest001"));

        // test throwing the IndexOutOfBoundsException exception
        try {
            testListEmpty.add(4, "bad index");
        }
        catch (Exception e) {
            assertEquals("IndexOutOfBoundsException", e.getMessage());
        }
    }

    /**
     * This method is already tested to work in <tt>testAddFirst</tt>, here only the thrown exception is tested
     * @throws Exception
     */
    public void testGetFirst() throws NoSuchElementException {
        try {
            testListEmpty.getFirst();
        }
        catch (Exception e) {
            assertEquals("NoSuchElementException", e.getMessage());
        }
    }

    /**
     * This method is already tested to work in <tt>testAddLast</tt>, here only the thrown exception is tested
     * @throws Exception
     */
    public void testGetLast() throws NoSuchElementException {
        try {
            testListEmpty.getLast();
        }
        catch (Exception e) {
            assertEquals("NoSuchElementException", e.getMessage());
        }
    }

    /**
     * This method is already tested to work in <tt>testAdd</tt>, here only the thrown exception is tested
     * @throws Exception
     */
    public void testGet() throws IndexOutOfBoundsException {
        // test throwing the IndexOutOfBoundsException exception
        try {
            testListEmpty.get(4);
        }
        catch (Exception e) {
            assertEquals("IndexOutOfBoundsException", e.getMessage());
        }
    }

    /**
     * Tests that the first item is removed from a list with <tt>removeFirst</tt> and also that the method returns the
     * correct item that it removed.
     *
     * @throws Exception
     */
    public void testRemoveFirst() throws NoSuchElementException {
        // fist assert list is null, then add three items to the list
        assertTrue(testListString.head == null);
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");

        // assert the first item is "one" after all three adds
        assertTrue(testListString.getFirst().equals("one"));
        // remove the first item at the same time as testing it's return values equals "one"
        assertEquals("one", testListString.removeFirst());
        // then test that the new first item is equal to "two"
        assertTrue(testListString.getFirst().equals("two"));

        // test throwing the exception
        try {
            testListEmpty.removeFirst();
        }
        catch (Exception e) {
            assertEquals("NoSuchElementException", e.getMessage());
        }
    }

    /**
     * Tests that the last item is removed from a list with <tt>removeLast</tt> and also that the method returns the
     * correct item that it removed.
     *
     * @throws Exception
     */
    public void testRemoveLast() throws NoSuchElementException {
        // fist assert list is null, then add three items to the list
        assertTrue(testListString.head == null);
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");

        // assert the last item is "three"
        assertTrue(testListString.getLast().equals("three"));
        // remove the last item testing that it also returns the last item that removed
        assertEquals("three", testListString.removeLast());
        // then test that the new last item is equal to "two"
        assertTrue(testListString.getLast().equals("two"));

        // test throwing the exception
        try {
            testListEmpty.removeLast();
        }
        catch (Exception e) {
            assertEquals("NoSuchElementException", e.getMessage());
        }
    }

    /**
     * Tests the first Remove method with takes an index as the argument. First test the list is null again.
     * Adds five elements. Removes elements at index 1, test the new elements in their place.
     * @throws Exception
     */
    public void testRemove() throws IndexOutOfBoundsException {
        assertTrue(testListString.head == null);
        // add five string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");

        // assert that item at index 1 is "two" and size is 5
        assertTrue(testListString.get(1).equals("two") && testListString.size == 5);
        // remove item at index 1
        testListString.remove(1);
        // assert that item at index 1 is now "three" and size is 4
        assertTrue(testListString.get(1).equals("three") && testListString.size == 4);
        // assert that "one" is still there as the first element
        assertTrue(testListString.getFirst().equals("one"));

        // test throwing the IndexOutOfBoundsException exception
        try {
            testListEmpty.remove(4);
        }
        catch (Exception e) {
            assertEquals("IndexOutOfBoundsException", e.getMessage());
        }
    }

    /**
     * <tt>remove</tt> is overloaded and this tests the second remove function when it is passed an object to be removed
     * instead of an index pointing to an object. Test null, then adds five elements. Tests that "four" is present, then
     * removes "four" and tests that it is no longer there. This also test the <tt>contains</tt> method as it uses it to
     * assert that "four" is present or not
     */
    public void testRemove1() {
        assertTrue(testListString.head == null);
        // add five string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");

        // assert the list contains "four" and size is 5
        assertTrue(testListString.contains("four") && testListString.size == 5);
        // remove "four"
        testListString.remove("four");
        // assert the list no longer contains "four" and the size is now 4
        assertTrue(! testListString.contains("four") && testListString.size == 4);
    }

    /**
     * This is already tested above in <tt>testRemove1</tt>
     */
    public void testContains() {
    }

    /**
     * creates list of five elements, tests that passing "four" to <tt>indexOf</tt> returns the correct index of 3, the
     * then tests for index of "six" to show that it returns -1 meaning it is not in the list
     */
    public void testIndexOf() {
        assertTrue(testListString.head == null);
        // add eight string elements
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");
        testListString.addLast("seven");
        testListString.addLast("four");
        testListString.addLast("twelve");

        // assert that "four" is at index 3
        assertEquals(3, testListString.indexOf("four"));
        // asserts that searching for index of "six" returns -1 meaning its not in the list
        assertEquals(- 1, testListString.indexOf("six"));
    }

    public void testLastIndexOf() {
        assertTrue(testListString.head == null);
        // add eight string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");
        testListString.addLast("seven");
        testListString.addLast("four");
        testListString.addLast("twelve");

        // assert that the last occurrence of "four" is at index 6
        assertEquals(6, testListString.lastIndexOf("four"));
        // asserts that searching for index of the last occurrence of "eleven" returns -1 meaning its not in the list
        assertEquals(- 1, testListString.lastIndexOf("eleven"));
    }

    /**
     * test the size of the list after various adds
     */
    public void testSize() {
        assertTrue(testListString.head == null);
        assertEquals(0, testListString.size());
        // add eight string elements
        testListString.addFirst("one");
        testListString.addLast("two");
        assertEquals(2, testListString.size());
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");
        assertEquals(5, testListString.size());
        testListString.addLast("seven");
        testListString.addLast("four");
        testListString.addLast("twelve");
        assertEquals(8, testListString.size());
    }

    /**
     * tests multiple times that the list is empty or not after adding and removing elements
     */
    public void testIsEmpty() {
//        System.out.println(testListString.size());
        assertTrue(testListString.isEmpty());
        // add eight string elements
        testListString.addFirst("one");
        // then tests that its not empty
        assertFalse(testListString.isEmpty());
        // remove element
        assertEquals("one", testListString.removeFirst());
        // test that its empty again
        assertTrue(testListString.isEmpty());
    }

    /**
     * adds multiple elements testing the size, then clears them and asserts size == 0 and isEmpty is true
     */
    public void testClear() {
        assertTrue(testListString.head == null);
        assertEquals(0, testListString.size());
        // add eight string elements, asserting the growing size
        testListString.addFirst("one");
        testListString.addLast("two");
        assertEquals(2, testListString.size());
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");
        assertEquals(5, testListString.size());

        // now clear and show size == 0, and both head and tail are null
        testListString.clear();
        assertTrue(testListString.head == null);
        assertTrue(testListString.tail == null);
        assertEquals(0, testListString.size());
    }

    /**
     * creates a linkedlist of five elements, shows the returned Object array from toArray is all the same elements in
     * the same order
     */
    public void testToArray() {
        assertTrue(testListString.head == null);
        // add five string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");

        Object[] testArray = testListString.toArray();
        assertEquals("one", testArray[0]);
        assertEquals("two", testArray[1]);
        assertEquals("three", testArray[2]);
        assertEquals("four", testArray[3]);
        assertEquals("five", testArray[4]);
        assertEquals(5, testArray.length);

    }

    // this bulk test always as 1 extra element on stack compared to the array
    public void testBulkTest() throws Exception {
        int initialIntegers = 100000, moves = 100000;
        Random rand = new Random(26491324791L);
        MyStack<Integer> stack = new MyStack<Integer>();
        ArrayList<Integer> addedInts = new ArrayList<Integer>();
        for (int i = 0; i < initialIntegers; i++) {
            Integer intToAdd = rand.nextInt();
            stack.push(intToAdd);
            addedInts.add(intToAdd);
        }

        for (int i = 0; i < moves; i++) {
            int next = rand.nextInt();
            if (next % 2 == 0) {
                Integer pushInt = rand.nextInt();
                stack.push(pushInt);
                addedInts.add(pushInt);
            } else if (next % 2 == 1) {
                stack.pop();
                addedInts.remove(addedInts.size() - 1);
            }
        }
//        System.out.println("Integers added = \n" + addedInts.size());
//        System.out.println("\nCurrent stack size:\n" + stack.size());
        assertEquals(addedInts.size(), stack.size());
    }
}