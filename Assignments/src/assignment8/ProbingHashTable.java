package assignment8;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An open-addressed HashTable implementation which uses quadratic probing to resolve collisions and doesn't allow for
 * duplicate items
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class ProbingHashTable extends HashTable {
    private String[] table;

    /**
     * Constructs a HashTable with a given capacity and using a particular hash function.
     *
     * @param capacity the number of 'buckets' in the hash table
     * @param functor  the hash function the table uses to index objects
     */
    public ProbingHashTable(int capacity, HashFunctor functor) {
        // check that capacity is a prime number, if not, make it prime.
        if (! isPrime(capacity))
            capacity = nextPrime(capacity);
        // initialize the object fields appropriately
        this.collisions = 0;
        this.capacity = capacity;
        this.table = new String[capacity];
        hasher = functor;
    }

    /**
     * Ensures that this set contains the specified item. add is guaranteed to find an empty spot to add if capacity is
     * prime and lambda < 0.5. With these to constraints enforced, int 'i' will never reach (capacity-1)
     *
     * @param item - the item whose presence is ensured in this set
     *
     * @return true if this set changed as a result of this method call (that is, if the input item was actually
     * inserted); otherwise, returns false
     */
    public boolean add(String item) {
        // handle null case, nulls are not allowed to be added for a HashTable
        if (item == null)
            return false;

        // return integer using the HashFunctor with specified item.
        int hash = hasher.hash(item);
        // determine initial index to add to array using this HashTables capacity, also assert it's positive.
        int currentIndex = Math.abs(hash % capacity);
        // checking the initial currentIndex the first time outside for loop to avoid skipping the first index.
        if (table[currentIndex] == null) {
            table[currentIndex] = item;  // item is added if the index points to empty spot.
            size++;
            // resize the table if the load factor has become too large
            if (getLamda() > 0.5)
                rehash();
            return true;
        }
        // check if element at currentIndex equals 'item', if so, return false because duplicates are not allowed.
        if (table[currentIndex].equals(item))
            return false;
        collisions++; // if the item cannot be added at the location it's a collision

        // now perform the same previous steps as necessary in for loop, updating currentIndex as needed.
        for (int i = 0; i < capacity; i++) {
            // theorem 20.5 is used from textbook to update quadratic probe with less complexity than H+i^2
            currentIndex = currentIndex + 2 * i - 1;
            //account for wrap around
            if (currentIndex >= capacity)
                currentIndex = currentIndex - capacity;
            if (table[currentIndex] == null) {
                table[currentIndex] = item;
                size++;
                break;
            }
            if (table[currentIndex].equals(item))
                return false;
            collisions++;
        }
        if (getLamda() > 0.5)
            rehash();
        return true;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified item. If capacity is prime and lambda <
     * 0.5, currentIndex will always reach the equal item or null before 'i' in the for-loop ever reaches (capacity-1)
     *
     * @param item - the item sought in this set
     *
     * @return true if there is an item in this set that is equal to the input item; otherwise, returns false
     */
    public boolean contains(String item) {
        // handle null case
        if (item == null)
            return true; // return true so the caller doesn't try to change the table

        // use quadratic probing to check for the item
        int hash = hasher.hash(item);
        // determine initial index to check in array using this HashTables capacity, also assert it's positive.
        int currentIndex = Math.abs(hash % capacity);
        // avoid skipping first index by evaluating at currentIndex once before the for loop.
        if (table[currentIndex] == null)
            return false;
        if (table[currentIndex].equals(item))
            return true;


        // if neither condition above occurred, keep checking in for-loop while updating currentIndex
        for (int i = 0; i < capacity; i++) {
            // theorem 20.5 is used from textbook to update quadratic probe with less complexity than H+i^2
            currentIndex = currentIndex + 2 * i - 1;
            //account for wrap around
            if (currentIndex >= capacity)
                currentIndex = currentIndex - capacity;

            if (table[currentIndex] == null) // if the location is empty the item hasn't been added
                return false;
            else if (table[currentIndex].equals(item)) // if the item is found it's obviously in the table
                return true;
        }
        // last condition if function breaks from for-loop without return, this should never be reached.
        return false;
    }

    /**
     * Clear all elements in the ProbingHashTable
     */
    public void clear() {
        size = 0;
        collisions = 0;
        table = new String[capacity];
    }

    /**
     * Double the size and add each element again
     */
    public void rehash() {
        // copy all existing elements (which shouldn't be used) in the table to a new list
        ArrayList<String> tableCopy = new ArrayList<String>(Arrays.asList(table));

        // increase the capacity of the table, clear all elements, then rehash them back in
        capacity *= 2;
        clear();
        addAll(tableCopy);
    }
}
