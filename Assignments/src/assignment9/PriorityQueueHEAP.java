package assignment9;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items.
 * The queue is implemented as a min heap.
 * The min heap is implemented implicitly as an array.
 *
 * @author Paymon Saebi
 * @author Casey Nordgran
 * @author
 */
public class PriorityQueueHEAP<AnyType> {
    private AnyType[] array;
    private int currentSize;
    private Comparator<? super AnyType> cmp;

    /**
     * Constructs an empty priority queue. Orders elements according
     * to their natural ordering (i.e., AnyType is expected to be Comparable)
     * AnyType is not forced to be Comparable.
     */
    @SuppressWarnings("unchecked")
    public PriorityQueueHEAP() {
        array = (AnyType[]) new Object[10];
        currentSize = 0;
        cmp = null;
    }

    /**
     * Construct an empty priority queue with a specified comparator.
     * Orders elements according to the input Comparator
     * (i.e., AnyType need not be Comparable).
     */
    @SuppressWarnings("unchecked")
    public PriorityQueueHEAP(Comparator<? super AnyType> _cmp) {
        array = (AnyType[]) new Object[10];
        currentSize = 0;
        cmp = _cmp;
    }

    /**
     * @return the number of items in this priority queue.
     */
    public int size() {
        return currentSize;
    }

    /**
     * Makes this priority queue empty.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        array = (AnyType[]) new Object[array.length];
        currentSize = 0;
    }

    /**
     * @return the minimum item in this priority queue.
     * @throws java.util.NoSuchElementException if this priority queue is empty.
     *                                          <p/>
     *                                          (Runs in constant time.)
     */
    public AnyType findMin() throws NoSuchElementException {
        //TODO
        return null;
    }

    /**
     * Removes and returns the minimum item in this priority queue.
     * (Runs in logarithmic time.)
     *
     * @return The minimum item in the priority queue
     * @throws java.util.NoSuchElementException if this priority queue is empty.
     */
    public AnyType deleteMin() throws NoSuchElementException {
        //TODO
        return null;
    }

    /**
     * Adds an item to this priority queue.
     * If the underlying array becomes full, it gets doubled
     * (Runs in constant time, worst case logarithmic)
     *
     * @param x - the item to be inserted
     */
    public void add(AnyType x) {
        // first check if array needs to grow before adding.
        if (currentSize == array.length - 1)
            doublePQ();
        // index passed to percolateUp is the next open leaf, at index = currentSize
        array[currentSize] = x;
        percolateUp(currentSize);
    }

    /**
     * Simply use the underlying array and loop through it
     *
     * @return object array containing the pq elements
     */
    public Object[] toArray() {
        //TODO
        return null;
    }

    /**
     * Internal method for comparing lhs and rhs using Comparator if provided by the
     * user at construction time, or Comparable, if no Comparator was provided.
     */
    @SuppressWarnings("unchecked")
    private int compare(AnyType lhs, AnyType rhs) {
        if (cmp == null)
            return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
        // We won't test your code on non-Comparable types if we didn't supply a Comparator

        return cmp.compare(lhs, rhs);
    }

    /**
     * Generates a DOT file for visualizing the binary heap.
     */
    public void generateDotFile(String filename) {
        try {
            PrintWriter out = new PrintWriter(filename);
            out.println("digraph Heap {\n\tnode [shape=record]\n");

            for (int i = 0; i < currentSize; i++) {
                out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
                if (((i * 2) + 1) < currentSize)
                    out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1) + ":f1");
                if (((i * 2) + 2) < currentSize)
                    out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2) + ":f1");
            }

            out.println("}");
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * percolates the item up at the specified index
     *
     * @param index array index of specified element to percolate up
     */
    private void percolateUp(int index) {
        // store item at specified index to be inserted at correct location instead of swaping
        AnyType item = array[index];
        // continually move next parent down to current index if item is less than parent.
        while (compare(item, array[Math.abs((index - 1) / 2)]) < 0 && (index - 1) / 2 >= 0)
            array[index] = array[(index++ - 1) / 2];
        // after percolate up reaches correct spot, item is inserted at current index.
        array[index] = item;
    }

    /**
     * Recursive method to percolate down the specified item at the index, can be used with deleteMin
     * and buildHeap if needed.
     */
    private void percolateDown(int index) {
        // if this index has no children then return
        if (index*2+1 >= currentSize)
            return;
        // in the case of 1 child
        if (index * 2 + 1 < currentSize && index * 2 + 2 >= currentSize) {
            if (compare(array[index],array[index*2+1])>0) {
                swap(index, index*2+1);
                return;
            }
            return;
        }
        // case of 2 children
        int smallerChild;
        // determine smaller of the two children
        if (compare(array[index*2+1],array[index*2+2])<=0)
            smallerChild = index*2+1;
        else
            smallerChild = index*2+2;
        // compare smaller child with item at current index
        if (compare(array[index],array[smallerChild])>0){
            swap(index,smallerChild);
            percolateDown(smallerChild);
        }
        // if item is already in the correct position, just return
        return;
    }

    /**
     * Doubles the underlying array of this priority que during add if capacity is full.
     */
    @SuppressWarnings("unchecked")
    private void doublePQ() {
        // store current array elements before assigning 'array' to a new array of twice the size.
        AnyType[] oldArray = array;
        array = (AnyType[]) new Object[array.length * 2];
        for (int i = 0; i < currentSize; i++)
            array[i] = oldArray[i];
    }

    /**
     * Helper method for swapping the two elements indicated at the specified index in this array.
     * @param ind1 index of first element to be swapped.
     * @param ind2 index of second element to be swapped.
     */
    private void swap(int ind1, int ind2) {
        AnyType temp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = temp;
    }
}
