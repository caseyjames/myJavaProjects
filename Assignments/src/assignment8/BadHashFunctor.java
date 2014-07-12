package assignment8;

/**
 * A bad hashing algorithm for String objects
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class BadHashFunctor implements HashFunctor {
    /**
     * @param item string object for which to find the hash code
     *
     * @return hash code, which is only the length of the string.
     */
    public int hash(String item) {
        return item.length();
    }
}
