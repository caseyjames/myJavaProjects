package assignment8;

/**
 * A fair hashing algorithm for String objects which sums the ASCII int equivalent of the characters in the String and
 * then multiplies that sum by the String length.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class FairHashFunctor implements HashFunctor {
    public int hash(String item) {
        int asciiSum = 0;
        for (int i = 0; i < item.length(); i++)
            asciiSum += item.charAt(i);
        return asciiSum * item.length();
    }
}
