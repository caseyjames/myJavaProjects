package assignment3;

import java.util.Comparator;

/**
 * Wrapper class for an Integer comparator, implementing the compare
 * Uses the Integer class's comparable compareTo to do the comparison
 *
 * @author Paymon Saebi
 */
public class IntegerComparator implements Comparator<Integer> {
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
