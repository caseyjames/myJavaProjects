package assignment9;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items.
 * The queue is implemented as a binary search tree.
 *
 * @author Paymon Saebi
 * @author Casey Nordgran
 * @author Cody Cortello
 * @version 7/16/2014
 */
public class PriorityQueueBST<AnyType> {
    private BinarySearchTree<AnyType> BST;

    /**
     * Constructs an empty priority queue. Orders elements according
     * to their natural ordering (i.e., AnyType is expected to be Comparable)
     * AnyType is not forced to be Comparable.
     */
    public PriorityQueueBST() {
        BST = new BinarySearchTree<AnyType>();
    }

    /**
     * Construct an empty priority queue with a specified comparator.
     * Orders elements according to the input Comparator
     * (i.e., AnyType need not be Comparable).
     */
    public PriorityQueueBST(Comparator<? super AnyType> cmp) {
        // constructs new BinarySearchTree with the specified Comparator funtion object
        BST = new BinarySearchTree<AnyType>(cmp);
    }

    /**
     * @return the number of items in this priority queue.
     */
    public int size() {
        return BST.size();
    }

    /**
     * Makes this priority queue empty.
     */
    public void clear() {
        BST.clear();
    }

    /**
     * Finds and returns the minimum item in this priority queue but does not delete it.
     * (Runs in logarithmic time.)
     *
     * @return the minimum item in this priority queue.
     * @throws java.util.NoSuchElementException if this priority queue is empty.
     */
    public AnyType findMin() throws NoSuchElementException {
        // first check that the BST is not empty, if it is throw exception
        if (BST.isEmpty())
            throw new NoSuchElementException("This priority queue is empty!");
        // return left most node in the BST as this is the minimum value
        return BST.first();
    }

    /**
     * Removes and returns the minimum item in this priority queue.
     * (Runs in logarithmic time.)
     *
     * @throws java.util.NoSuchElementException if this priority queue is empty.
     */
    public AnyType deleteMin() throws NoSuchElementException {
        // first check that the BST is not empty, if it is throw exception
        if (BST.isEmpty())
            throw new NoSuchElementException("This priority queue is empty!");
        // use findMin to retrieve the minimum value, than pass it to BST's remove(Type) method
        AnyType minValue = BST.first();
        BST.remove(minValue);
        // return the found minimum value that was removed
        return minValue;
    }

    /**
     * Adds an item to this priority queue.
     * (Runs in logarithmic time.)
     *
     * @param x -- the item to be inserted
     */
    public void add(AnyType x) {
        BST.add(x);
    }

    /**
     * Should simply call your BST toArrayList
     *
     * @return Object array containing the PQs elements
     */
    public Object[] toArray() {
        return BST.toArrayList().toArray();
    }
}



