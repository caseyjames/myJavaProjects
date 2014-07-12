package assignment8;

import java.math.BigInteger;

/**
 * A good hashing algorithm for String objects which creates a unique large integer value for each string, as a
 * BigInteger, no two being the same, for example "ab" becomes 9798. However, then the BigInteger is converted back to
 * an int, and the absolute value of this is returned from the hash method.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class GoodHashFunctor implements HashFunctor {
    public int hash(String item) {
        // string to hold all the integer values of each char in the specified string side by side
        String numVal = "";
        for (int i = 0; i < item.length(); i++) {
            // codePointAt returns the integer value of the char at index 'i', instead of the character like charAt
            numVal += item.codePointAt(i);
        }
        // convert the long string integer into a BigInteger
        BigInteger intVal = new BigInteger(numVal);
        // return the BigInteger converted to an integer and made positive
        return Math.abs(intVal.intValue());
    }
}
