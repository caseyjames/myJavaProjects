package assignment3;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 6/5/2014
 */
public class SearchUtil {
    /**
     * A recursive search method which guarantees O(log N) execution
     *
     * @param <T>  The type of elements contained in the list
     * @param list An ArrayList to search through.
     *             This ArrayList is assumed to be sorted according
     *             to the supplied comparator. This method has
     *             undefined behavior if the list is not sorted.
     * @param item The item to search for
     * @param cmp  Comparator for comparing Ts or a super class of T
     * @return true if "item" exists in "list", false otherwise
     */
    public static <T> boolean binarySearch(ArrayList<T> list, T item, Comparator<? super T> cmp) {

        //variables for lower, and upper bound indices, as well is a middle index
        int low = 0;
        int high = list.size() - 1;
        int mid;

        //this loop changes upper or lower bound index after comparing item to item at mid
        while (low <= high) {
            mid = (low + high) / 2;
            if (cmp.compare(item, list.get(mid)) > 0)
                low = mid + 1;
            else if (cmp.compare(item, list.get(mid)) < 0)
                high = mid - 1;
            else
                //if item is equal to item at mid then item exists so return true
                return true;
        }
        //if no item in list was found to be equal with item than return false.
        return false;
    }

}
