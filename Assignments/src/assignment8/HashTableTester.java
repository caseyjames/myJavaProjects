package assignment8;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Tester class to test various methods in assignment 8
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class HashTableTester extends TestCase {

//  <<<<<<<<<<<<<<<<<<<<<<<<<<<<   TESTED CHAINING HASH TABLE METHODS    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void testChainingHashTable() {
        //String[] for testing
        ArrayList<String> allStrings = new ArrayList<String>();
        // declare new ChainingHashTable
        ChainingHashTable testTable = null;
        // assert that test == null first
        assertTrue(testTable == null);

        // test the constructor method
        testTable = new ChainingHashTable(7, new GoodHashFunctor());
        // test isEmpty
        assertTrue(testTable.isEmpty());
        /* test that the member variables were initialized correctly and also tests
           the size, getCapacity, getCollisions & getLambdaMAX methods */
        assertEquals(0, testTable.size());
        assertEquals(7, testTable.getCapacity());
        assertEquals(0, testTable.getCollisions());
        assertEquals(3.0, testTable.getLambdaMAX());
        // tests the setLambdaMAX function
        testTable.setLambdaMAX(4.0);
        assertEquals(4.0, testTable.getLambdaMAX());

        // tests the add method, asserts returns true that they were added
        assertTrue(testTable.add("first"));
        assertTrue(testTable.add("second"));
        assertTrue(testTable.add("third"));
        // test isEmpty returns false
        assertFalse(testTable.isEmpty());
        // tests that duplicates are not allowed, asserts returns false in this case
        assertFalse(testTable.add("first"));
        // assert that size is still 3
        assertEquals(3, testTable.size());
        // test getLambda function now that size of testTable is not zero
        assertEquals(3.0 / 7, testTable.getLamda());

        //test the contains method
        assertTrue(testTable.contains("first"));
        assertTrue(testTable.contains("second"));
        assertTrue(testTable.contains("third"));
        //test contains returns false
        assertFalse(testTable.contains("fourth"));

        // test that the clear method works
        testTable.clear();
        assertTrue(testTable.isEmpty());
        assertEquals(0, testTable.size());
        assertEquals(7, testTable.getCapacity());
        assertEquals(0, testTable.getCollisions());
        assertEquals(3.0, testTable.getLambdaMAX());

        // test the addAll method, and containsAll method
        for (int i = 0; i < 3; i++)
            allStrings.add("test" + i);
        assertTrue(testTable.addAll(allStrings));
        assertTrue(testTable.contains("test0"));
        assertTrue(testTable.contains("test1"));
        assertTrue(testTable.contains("test2"));
        assertTrue(testTable.containsAll(allStrings));
        // test addAll returns false with same arraylist
        assertFalse(testTable.addAll(allStrings));
        assertEquals(3, testTable.size());
        // add to allStrings, test containsAll returns false
        allStrings.add("test3");
        assertFalse(testTable.containsAll(allStrings));
        // addAll returns true again with allStrings having one new item
        assertTrue(testTable.addAll(allStrings));

        // test rehash works, cause lambda = size/capacity = 21/7 >= 3.0, test current values before rehash too
        for (int i = 4; i < 20; i++)
            allStrings.add("test" + i);
        assertTrue(testTable.addAll(allStrings));
        assertEquals(20, testTable.size());
        assertEquals(7, testTable.getCapacity());
        assertEquals(20.0 / 7, testTable.getLamda());
        // show that by now, strings have been indexed to be added to the same list (collisions)
        assertTrue(testTable.getCollisions() > 0);
        // now test rehash works
        allStrings.add("test21");
        assertTrue(testTable.addAll(allStrings));
        assertEquals(21, testTable.size());
        assertEquals(21, testTable.getCapacity());
        assertEquals(1.0, testTable.getLamda());
        // assert all items are contained in new larger and rehashed testTable
        assertTrue(testTable.containsAll(allStrings));

        // test that constructor method creates ChainingHashTable with capacity that is prime
        testTable = new ChainingHashTable(30, new GoodHashFunctor());
        assertEquals(31, testTable.getCapacity());
    }

//  <<<<<<<<<<<<<<<<<<<<<<<<<<<<   TESTED PROBING HASH TABLE METHODS    >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void testProbingHashTable() {
        //String[] for testing
        ArrayList<String> allStrings = new ArrayList<String>();
        // declare new ProbingHashTable
        ProbingHashTable testTable = null;
        // assert that test == null first
        assertTrue(testTable == null);

        // test the constructor method
        testTable = new ProbingHashTable(11, new GoodHashFunctor());
        // test isEmpty
        assertTrue(testTable.isEmpty());
        /* test that the member variables were initialized correctly and also tests
           the size, getCapacity, getLamda & getCollisions methods*/
        assertEquals(0, testTable.size());
        assertEquals(11, testTable.getCapacity());
        assertEquals(0, testTable.getCollisions());
        assertEquals(0.0, testTable.getLamda());

        // tests the add method, asserts returns true that they were added
        assertTrue(testTable.add("first"));
        assertTrue(testTable.add("second"));
        assertTrue(testTable.add("third"));
        // test isEmpty returns false
        assertFalse(testTable.isEmpty());
        // tests that duplicates are not allowed, asserts returns false in this case
        assertFalse(testTable.add("first"));
        // assert that size is still 3
        assertEquals(3, testTable.size());
        // test getLambda function now that size of testTable is not zero
        assertEquals(3.0 / 11, testTable.getLamda());

        //test the contains method
        assertTrue(testTable.contains("first"));
        assertTrue(testTable.contains("second"));
        assertTrue(testTable.contains("third"));
        //test contains returns false
        assertFalse(testTable.contains("fourth"));

        // test that the clear method works
        testTable.clear();
        assertTrue(testTable.isEmpty());
        assertEquals(0, testTable.size());
        assertEquals(11, testTable.getCapacity());
        assertEquals(0, testTable.getCollisions());

        // test the addAll method, and containsAll method
        for (int i = 0; i < 3; i++)
            allStrings.add("test" + i);
        assertTrue(testTable.addAll(allStrings));
        assertTrue(testTable.contains("test0"));
        assertTrue(testTable.contains("test1"));
        assertTrue(testTable.contains("test2"));
        assertTrue(testTable.containsAll(allStrings));
        // test addAll returns false with same arraylist
        assertFalse(testTable.addAll(allStrings));
        assertEquals(3, testTable.size());
        // add to allStrings, test containsAll returns false
        allStrings.add("test3");
        assertFalse(testTable.containsAll(allStrings));
        // addAll returns true again with allStrings having one new item
        assertTrue(testTable.addAll(allStrings));

        // test rehash works, cause lambda = size/capacity = 21/7 >= 3.0, test current values before rehash too
        allStrings.add("test4");
        assertTrue(testTable.addAll(allStrings));
        assertEquals(5, testTable.size());
        assertEquals(11, testTable.getCapacity());
        assertEquals(5.0 / 11, testTable.getLamda());
        // now test rehash works
        allStrings.add("test5");
        assertTrue(testTable.addAll(allStrings));
        assertEquals(6, testTable.size());
        assertEquals(22, testTable.getCapacity());
        assertEquals(6.0 / 22, testTable.getLamda());
        // assert all items are contained in new larger and rehashed testTable
        assertTrue(testTable.containsAll(allStrings));

        // test that constructor method creates ChainingHashTable with capacity that is prime
        testTable = new ProbingHashTable(30, new GoodHashFunctor());
        assertEquals(31, testTable.getCapacity());
    }

//  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<    TESTING PRIME NUMBER HELPER METHODS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void testPrimeNumberMethods() {
        // first assertTrue multiple times when passing prime numbers to the isPrime method
        assertTrue(HashTable.isPrime(53));
        assertTrue(HashTable.isPrime(337));
        assertTrue(HashTable.isPrime(23));
        assertTrue(HashTable.isPrime(1567));
        assertTrue(HashTable.isPrime(821));
        // assert false for multiple non prime numbers
        assertFalse(HashTable.isPrime(63));
        assertFalse(HashTable.isPrime(884));
        assertFalse(HashTable.isPrime(2047));
        assertFalse(HashTable.isPrime(471));
        assertFalse(HashTable.isPrime(27));

        // test the same non prime numbers above with nextPrime to return the next closest prime
        assertEquals(67, HashTable.nextPrime(63));
        assertEquals(887, HashTable.nextPrime(884));
        assertEquals(2053, HashTable.nextPrime(2047));
        assertEquals(479, HashTable.nextPrime(471));
        assertEquals(29, HashTable.nextPrime(27));
        // test nextPrime again with rounded expected user input numbers
        assertEquals(11, HashTable.nextPrime(8));
        assertEquals(23, HashTable.nextPrime(20));
        assertEquals(101, HashTable.nextPrime(100));
        assertEquals(503, HashTable.nextPrime(500));
        assertEquals(2111, HashTable.nextPrime(2100));
    }

//  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<    TESTING HASH FUNCTOR OBJECT METHODS   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void testHashFunctors() {
        // first test bad functor method, create new functor
        HashFunctor bad = new BadHashFunctor();
        // BadHashFunctor returns only the length of the string, assert this is true multiple times
        assertEquals(5, bad.hash("three"));
        assertEquals(10, bad.hash("university"));
        assertEquals(7, bad.hash("CS 2420"));
        assertEquals(15, bad.hash("Data Structures"));
        assertEquals(2, bad.hash("hi"));

        // now test the fair functor method
        HashFunctor fair = new FairHashFunctor();
        // FairHashFunctor sums all the char values in the string then multiplies the sum by the string length
        assertEquals(2680, fair.hash("three"));
        assertEquals(11220, fair.hash("university"));
        assertEquals(2674, fair.hash("CS 2420"));
        assertEquals(22530, fair.hash("Data Structures"));
        assertEquals(418, fair.hash("hi"));

        // now test the good functor method
        HashFunctor good = new GoodHashFunctor();
        /* this hash function places the integer values of each character in the string side by side
           to create a large BigInteger that uniquely defines that string, the BigInteger is then
           converted to an int and the absolute value of this is returned from the hash method */
        assertEquals(1736811667, good.hash("three"));
        assertEquals(777408473, good.hash("university"));
        assertEquals(1462947976, good.hash("CS 2420"));
        assertEquals(323755387, good.hash("Data Structures"));
        assertEquals(104105, good.hash("hi"));
        // also assert that the hash function should create the same hashcode for the same string every time.
        assertTrue(good.hash("three") == good.hash("three"));
        assertTrue(good.hash("university") == good.hash("university"));
        assertTrue(good.hash("CS 2420") == good.hash("CS 2420"));
        assertTrue(good.hash("Data Structures") == good.hash("Data Structures"));
        assertTrue(good.hash("hi") == good.hash("hi"));
    }

//  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<    TESTING SPELL CHECKER CLASSES   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public void testSpellChecker() {
        // create string array of desired arguments to pass to main.
        String[] args = new String[3];
        args[0] = "dictionary.txt";
        args[1] = "hello_world.txt";
        args[2] = "-p";
        // call main and pass the arguments, .txt files must be in the same workspace directory. Prints to screen
        SpellChecker.main(args);

        // test again with the second argument as the good_luck.txt document to spell check
        args[1] = "good_luck.txt";
        SpellChecker.main(args);

        // end of all statements in testing class, if next statement prints than all the tests passed
        System.out.println("! ALL TESTS HAVE PASSED !");
    }

//  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
