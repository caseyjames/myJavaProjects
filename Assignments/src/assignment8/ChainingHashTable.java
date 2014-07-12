package assignment8;

import java.util.LinkedList;

/**
 * A closed-addressed HashTable implementation which uses separate chaining to resolve collisions and doesn't allow for
 * duplicate items.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class ChainingHashTable extends HashTable {
    // underlying data structure for ChainingHashTable, uses seperate chaining to avoid collisions
    private LinkedList<String>[] storage;
    private double lambda_MAX;


    /**
     * Constructor for the ChainingHashTable class that uses separate chaining as the method to resolve collisions.
     *
     * @param capacity initial capacity of the linked list string array
     * @param functor  function object used to create a hashcode for the added strings to this table.
     */
    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor) {
        // if the specified capacity is not prime, make it prime for more unique and distributed hash codes.
        if (! isPrime(capacity))
            capacity = nextPrime(capacity);
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        hasher = functor;
        this.capacity = capacity;
        size = 0;
        collisions = 0;
        // default load factor threshold which determines when ChainingHashTable reHashes
        lambda_MAX = 3.0;
    }

    /**
     * Getter method to return the load factor threshold 'lambda_MAX'
     * @return the load factor threshold 'lambda_MAX'
     */
    public double getLambdaMAX() {
        return lambda_MAX;
    }

    /**
     * Setter method to change the load factor threshold 'lambda_MAX'
     */
    public void setLambdaMAX(double newVal) {
        lambda_MAX = newVal;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     *
     * @return true if this set changed as a result of this method call (that is, if the input item was actually
     * inserted); otherwise, returns false
     */
    public boolean add(String item) {
        // determine string hashcode using HashFunctor
        int hashCode = hasher.hash(item);
        // use the item's hashcode and array size to determine the index or list to add item (asserts positive)
        int index = Math.abs(hashCode % capacity);

        // first check if the index is null, if not increment collisions
        if (storage[index] != null) {
            // check that String item is not already contained in the LinkedList at this index, if so return false.
            if (storage[index].contains(item))
                return false;
            collisions++;
        }
        // initialize LinkedList to index if it is null
        if (storage[index] == null)
            storage[index] = new LinkedList<String>();

        // specified item is not contained in the LinkedList at this index, add specified item
        storage[index].add(item);
        // increase size, return true.
        size++;
        // after adding, check if this ChainingHashTable needs to be rehashed
        if (lambda_MAX <= getLamda())
            this.rehash();
        return true;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified item.
     *
     * @param item - the item sought in this set
     *
     * @return true if there is an item in this set that is equal to the input item; otherwise, returns false
     */
    public boolean contains(String item) {
        // determine string hashcode
        int hashCode = hasher.hash(item);
        // determine index using array length to know which LinkedList to check
        int index = Math.abs(hashCode % capacity);
        // check first that index is not null, if it is return false.
        if (storage[index] == null)
            return false;
        // check if the item is contained in the non-empty LinkedList
        if (storage[index].contains(item))
            return true;
        // item not found, return false.
        return false;
    }

    /**
     * Clears all elements in this ChainingHashTable
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        // set everything to zero & create new empty array for the variable 'storage'
        lambda_MAX = 3.0;
        size = 0;
        collisions = 0;
        storage = (LinkedList<String>[]) new LinkedList[capacity];
    }

    /**
     * Double the size and add each element to new array using new hash codes
     */
    @SuppressWarnings("unchecked")
    public void rehash() {
        // declare new LinkedList array to store reference to current LinkedList 'storage'
        LinkedList<String>[] previousArr = storage;
        // create new array for storage, of capacity = size so lambda becomes 1.0
        capacity = size;
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        // update member variables
        size = collisions = 0;
        // rehash all the previous elements to the new LinkedList array
        for (int i = 0; i < previousArr.length; i++) {
            if (! previousArr[i].isEmpty()) {
                for (String str : previousArr[i])
                    this.add(str);
            }
        }
    }

    // end of class
}
