package assignment3;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class ArrayBasedCollectionTester extends TestCase {
    ArrayBasedCollection<String> emptyCollection, testCollectionAdd, testCollectionAddAll,
            testCollectionClear, testCollectionContainsAll, testCollectionIterator,
            testCollectionRemove, testCollectionRemoveAll, testCollectionRetainAll, testCollectionSize,
            testCollectionToArray, testCollectionGrow, testCollectionToSortedList;

    // this collection is used for testing binarySearch
    ArrayBasedCollection<Integer> testList;

    public void setUp() throws Exception {
        super.setUp();

        // these constructors arrayTest the different possible values to be passed to the ArrayBasedCollection constructor
        // there is one for each of the different methods to be tested.
        emptyCollection = new ArrayBasedCollection<String>(0);
        testCollectionAdd = new ArrayBasedCollection<String>();
        testCollectionAddAll = new ArrayBasedCollection<String>(100);

        testCollectionClear = new ArrayBasedCollection<String>();
        testCollectionContainsAll = new ArrayBasedCollection<String>();
        testCollectionIterator = new ArrayBasedCollection<String>();
        testCollectionRemove = new ArrayBasedCollection<String>();
        testCollectionRemoveAll = new ArrayBasedCollection<String>();
        testCollectionRetainAll = new ArrayBasedCollection<String>();
        testCollectionSize = new ArrayBasedCollection<String>();
        testCollectionToArray = new ArrayBasedCollection<String>();
        testCollectionGrow = new ArrayBasedCollection<String>(0);
        testCollectionToSortedList = new ArrayBasedCollection<String>();
    }

    public void tearDown() throws Exception {
    }

    public void testAdd() throws Exception {
        // asserts that each call to add does in fact add the string to the collection
        testCollectionAdd.add("Hello");
        testCollectionAdd.add("World");
        assertEquals(true, testCollectionAdd.contains("Hello"));
        assertEquals(true, testCollectionAdd.contains("World"));
    }

    public void testAddAll() throws Exception {
        // create a list to be added to the testCollectionAddAll collection
        ArrayList<String> addList = new ArrayList<String>();
        addList.add("arrayTest string 1");
        addList.add("arrayTest string 2");
        addList.add("arrayTest string 3");
        addList.add("arrayTest string 5"); // out of order on purpose
        addList.add("arrayTest string 4");

        testCollectionAddAll.addAll(addList);
        //after adding the entire list at once, tests to assert that items were added
        assertEquals(true, testCollectionAddAll.contains("arrayTest string 1"));
        assertEquals(true, testCollectionAddAll.contains("arrayTest string 2"));
        assertEquals(true, testCollectionAddAll.contains("arrayTest string 3"));
        assertEquals(true, testCollectionAddAll.contains("arrayTest string 5"));
        assertEquals(true, testCollectionAddAll.contains("arrayTest string 4"));
    }

    public void testClear() throws Exception {
        // first create a list to be added
        TreeSet<String> addTree = new TreeSet<String>();
        addTree.add("banana");
        addTree.add("apple");
        addTree.add("cherry");
        addTree.add("coconut");
        testCollectionClear.addAll(addTree);
        // after adding the list, check that all items were indeed added.
        assertEquals(true, testCollectionClear.contains("banana"));
        assertEquals(true, testCollectionClear.contains("apple"));
        assertEquals(true, testCollectionClear.contains("cherry"));
        assertEquals(true, testCollectionClear.contains("coconut"));
        testCollectionClear.clear();
        // after calling clear(), check that items were indeed cleared
        assertEquals(false, testCollectionClear.contains("banana"));
        assertEquals(false, testCollectionClear.contains("apple"));
        assertEquals(false, testCollectionClear.contains("cherry"));
        assertEquals(false, testCollectionClear.contains("coconut"));
    }

    public void testContains() throws Exception {
        // contains in implemented in all other tests, so a standalone arrayTest isn't needed
    }

    public void testContainsAll() throws Exception {
        // create addSet to be passed as the list to containsAll
        HashSet<String> addSet = new HashSet<String>();
        addSet.add("banana");
        addSet.add("pie");
        addSet.add("mork");
        addSet.add("help I'm trapped");
        addSet.add("silly String");

        // only add the first four at first to test for false return
        testCollectionContainsAll.add("banana");
        testCollectionContainsAll.add("pie");
        testCollectionContainsAll.add("mork");
        testCollectionContainsAll.add("help I'm trapped");

        // show that containsAll return false when not all items are present
        assertEquals(false, testCollectionContainsAll.containsAll(addSet));

        // add the last item
        testCollectionContainsAll.add("silly String");

        // test that true is returned when all items are present
        assertEquals(true, testCollectionContainsAll.containsAll(addSet));
    }

    public void testIsEmpty() throws Exception {
        // sets collection created in setUp and also testCollectionClear from testClear()
        // that both are empty and returns true.
        assertEquals(true, emptyCollection.isEmpty());
        assertEquals(true, testCollectionClear.isEmpty());
        // test that testCollectionAdd is not empty
        testCollectionAdd.add("not empty");
        assertEquals(false, testCollectionAdd.isEmpty());
    }

    public void testIterator() throws Exception {
        // this arrayTest has no "assertEquals," but instead uses a foreach loop to show that the iterator implementation is valid
        testCollectionIterator.add("first");
        testCollectionIterator.add("second");
        testCollectionIterator.add("third");
        testCollectionIterator.add("fourth");

        //noinspection StatementWithEmptyBody, with throw exception without working iterator
        for (String element : testCollectionIterator) {
            // empty block
        }
    }

    public void testRemove() throws Exception {
        // adds three items to testCollectionRemove
        testCollectionRemove.add("first");
        testCollectionRemove.add("second");
        testCollectionRemove.add("third");
        // only removes the middle one, showing all must have been added but if third is present
        testCollectionRemove.remove("second");
        // shows that only second was removed
        assertEquals(true, testCollectionRemove.contains("first"));
        assertEquals(false, testCollectionRemove.contains("second"));
        assertEquals(true, testCollectionRemove.contains("third"));
    }

    public void testRemoveAll() throws Exception {
        // add five items to collection to test
        testCollectionRemoveAll.add("first");
        testCollectionRemoveAll.add("second");
        testCollectionRemoveAll.add("third");
        testCollectionRemoveAll.add("fourth");
        testCollectionRemoveAll.add("fifth");

        // create a collection with only two items to be removed from testCollectionRemoveAll
        TreeSet<String> removeAllSet = new TreeSet<String>();
        removeAllSet.add("second");
        removeAllSet.add("fourth");

        // remove all items in removeAllSet and show that they are indeed removed.
        testCollectionRemoveAll.removeAll(removeAllSet);
        assertEquals(true, testCollectionRemoveAll.contains("first"));
        assertEquals(false, testCollectionRemoveAll.contains("second"));
        assertEquals(true, testCollectionRemoveAll.contains("third"));
        assertEquals(false, testCollectionRemoveAll.contains("fourth"));
        assertEquals(true, testCollectionRemoveAll.contains("fifth"));
    }

    public void testRetainAll() throws Exception {
        // add five items to collection to test
        testCollectionRetainAll.add("first");
        testCollectionRetainAll.add("second");
        testCollectionRetainAll.add("third");
        testCollectionRetainAll.add("fourth");
        testCollectionRetainAll.add("fifth");

        // list to pass to retainAll
        ArrayList<String> retainAllList = new ArrayList<String>();
        retainAllList.add("second");
        retainAllList.add("fourth");

        // shows that only items that are in both collections are kept
        testCollectionRetainAll.retainAll(retainAllList);
        assertEquals(false, testCollectionRetainAll.contains("first"));
        assertEquals(true, testCollectionRetainAll.contains("second"));
        assertEquals(false, testCollectionRetainAll.contains("third"));
        assertEquals(true, testCollectionRetainAll.contains("fourth"));
        assertEquals(false, testCollectionRetainAll.contains("fifth"));
    }

    public void testSize() throws Exception {
        // tests both size() and remove() again. tests size after each call to add()
        assertEquals(0, testCollectionSize.size);
        testCollectionSize.add("first");
        assertEquals(1, testCollectionSize.size());
        testCollectionSize.add("second");
        assertEquals(2, testCollectionSize.size());
        testCollectionSize.add("third");
        assertEquals(3, testCollectionSize.size());
        testCollectionSize.add("fourth");
        assertEquals(4, testCollectionSize.size());
        testCollectionSize.add("fifth");
        assertEquals(5, testCollectionSize.size());
        // begins to remove items, testing size again.
        testCollectionSize.remove("fifth");
        assertEquals(4, testCollectionSize.size());
        testCollectionSize.remove("second");
        assertEquals(3, testCollectionSize.size());
        testCollectionSize.remove("third");
        assertEquals(2, testCollectionSize.size());
    }

    public void testToArray() throws Exception {

        // add four items to be return as ArrayList
        testCollectionToArray.add("first");
        testCollectionToArray.add("second");
        testCollectionToArray.add("eleventh");
        testCollectionToArray.add("fifth");

        // show that collection is of length 10 from constructor before the toArray method
        assertEquals(10, testCollectionToArray.length());

        // return ArrayList to testArray
        Object[] testArray = testCollectionToArray.toArray();

        // first test that testArray contains the correct items in correct order
        assertEquals("first", testArray[0]);
        assertEquals("second", testArray[1]);
        assertEquals("eleventh", testArray[2]);
        assertEquals("fifth", testArray[3]);
        // show that testArray should also be the length of 4
        assertEquals(4, testArray.length);
    }

    public void testGrow() throws Exception {
        // arrayTest adding to collection of size 0 up to size 8
        assertEquals(0, testCollectionGrow.length());
        testCollectionGrow.add("first");
        assertEquals(1, testCollectionGrow.length());
        testCollectionGrow.add("second");
        assertEquals(2, testCollectionGrow.length());
        testCollectionGrow.add("third");
        assertEquals(4, testCollectionGrow.length());
        testCollectionGrow.add("fourth");
        // adding next shouldn't call grow because there's 1 empty index
        assertEquals(4, testCollectionGrow.length());
        // this time grow() should be called again
        testCollectionGrow.add("fifth");
        assertEquals(8, testCollectionGrow.length());
    }

    public void testToSortedList() throws Exception {
        // create a collection of 6 string objects
        testCollectionToSortedList.add("banana");
        testCollectionToSortedList.add("dragon");
        testCollectionToSortedList.add("carrot");
        testCollectionToSortedList.add("apple");
        testCollectionToSortedList.add("gorilla");
        testCollectionToSortedList.add("zebra");

        // comparator to sort strings using .equals from the String class
        class stringComparator implements Comparator<String> {
            public int compare(String left, String right) {
                return left.compareTo(right);
            }
        }

        // return a sorted list to sortedList
        ArrayList<String> sortedList = testCollectionToSortedList.toSortedList(new stringComparator());

        // test that the list is indeed now lexicographically sorted
        assertEquals("apple", sortedList.get(0));
        assertEquals("banana", sortedList.get(1));
        assertEquals("carrot", sortedList.get(2));
        assertEquals("dragon", sortedList.get(3));
        assertEquals("gorilla", sortedList.get(4));
        assertEquals("zebra", sortedList.get(5));
    }

    public void testBinarySearch() throws Exception {
        // randomize 100 ints from 0 to 100 and add them to the ArrayBasedCollection and a
        // HashSet (to compare the
        //  final sorted list against)
        int intsAdded = 100, range = 100;
        HashSet<Integer> testSet = new HashSet<Integer>();
        // create new object for testList
        testList = new ArrayBasedCollection<Integer>();
        for (int i = 0; i < intsAdded; i++) {
            int intToAdd = (int) ((Math.random() * range) + 1);
            // add random ints to both ArrayBasedCollection to test, and HashSet
            // neither collection allows for duplicates
            testList.add(intToAdd);
            testSet.add(intToAdd);
        }

        // use a default Comparator to compare the ints
        Comparator<Integer> cmp = new IntegerComparator();

        // invoke the toSortedList method to sort the randomized ints
        ArrayList<Integer> sortedList = testList.toSortedList(cmp);

        // then search for 50 random ints in the sortedList and assert their existence, this means
        // there's a high chance that some random ints will be present in testSet
        int tries = 50;
        for (int i = 0; i < tries; i++) {
            int intToTry = (int) ((Math.random() * range) + 1);
            // expected is whether testSet has intToTry or not, either way actual should match it.
            assertEquals(testSet.contains(intToTry), SearchUtil.binarySearch(sortedList, intToTry, cmp));
        }
    }
}