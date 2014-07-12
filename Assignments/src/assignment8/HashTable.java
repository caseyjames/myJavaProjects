package assignment8;

import java.util.Collection;

/**
 * An abstract class facilitating the implementation of a concrete hash table.
 *
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public abstract class HashTable implements Set<String> {
    /**
     * FILL IN MEMBER VARIABLES!
     *
     * Any member variables that you would maintain in both your QuadProbeHashTable and
     * your ChainingHashTable (such as, say, a size variable) probably belong here in
     * the parent class. Should the variable(s) be public, private, or protected?
     */
    protected int size, capacity, collisions;
    protected HashFunctor hasher;

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise, returns false
     */
    public final boolean addAll(Collection<? extends String> items) {
        int initialSize = size;
        for (String s : items)
            if (!this.contains(s))
                this.add(s);
        return (initialSize != size);
    }

    /**
     * Determines if for each item in the specified collection, there is an item
     * in this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an
     * item in this set that is equal to it; otherwise, returns false
     */
    public final boolean containsAll(Collection<? extends String> items) {
        for (String s : items)
            if (!this.contains(s))
                return false;
        return true;
    }

    /**
     * @return true if this set contains no items.
     */
    public final boolean isEmpty() {
        return (size == 0);
    }

    /**
     * @return the number of items in this set.
     */
    public final int size() {return size;}

    /* advised helper methods - not necessary, but recommended */

    /**
     * Function which returns the current capacity of this HashTable
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @return the number of collisions which happened in creating the current table
     */
    public int getCollisions() {
        return collisions;
    }

    /**
     * Function returns the current fraction of the HashTable that is filled if it is a probing HashTable,
     * or returns the average LinkedList length if it is a separate chaining HashTable.
     *
     * @return the load factor lambda of the current table
     */
    public double getLamda() {
        return ((double) size) / capacity;
    }

    /**
     * Returns the first prime integer greater than or equal to the passed integer
     */
    public static int nextPrime(int number) {
        // copy the number so we don't affect the parameter
        int n = number;

        // make n odd if it's not
        if (n % 2 == 0)
            n++;

        // increase n until a prime number is found
        while (! isPrime(n))
            n += 2;

        return n;
    }

    /**
     * Returns true iff the passed int is prime
     */
    public static boolean isPrime(int number) {
        // test each odd integer smaller than sqrt(number) to see if it's a factor
        for (int test = 3; test < Math.sqrt(number) + 1; test += 2)
            if (number % test == 0)
                return false;

        // if no factor was found the number is prime
        return true;
    }
}
