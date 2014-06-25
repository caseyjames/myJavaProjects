package assignment5;

import java.util.NoSuchElementException;

/**
 * Represents a generic stack (Last in, first out). It is implemented by the underlying linked list <tt>MyLinkedList</tt>
 * and many of the methods simply call the equivalent linked list method.
 *
 * @param <E> - the type of elements contained in the stack
 *
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 *
 * @version 6/16/2014
 */
public class MyStack<E> {
    // the stack uses MyLinkedList to implement it.
    private MyLinkedList<E> linkedListStack;

    /**
     * Sole constructor of MyStack, creates a new instance of a linked list.
     */
    public MyStack() {
        linkedListStack = new MyLinkedList<E>();
    }

    /**
     * Removes all of the elements from the stack.
     */
    public void clear() {
        linkedListStack.clear();
    }

    /**
     * Returns true if the stack contains no elements.
     *
     * @return True if there are no elements in this stack, otherwise false.
     */
    public boolean isEmpty() {
        return linkedListStack.isEmpty();
    }

    /**
     * Returns the item at the top of the stack without removing it from the stack. Throws NoSuchElementException if the
     * stack is empty.
     *
     * @return The item item at the top of the stack (item most recently added) but does not remove it.
     */
    public E peek() throws NoSuchElementException {
        // check for empty stack, if no elements are present throw an exception.
        if (linkedListStack.size() == 0)
            throw new NoSuchElementException();

        // return the last item in the linked list because added items are added to the end of the list.
        return linkedListStack.getLast();
    }

    /**
     * Returns and removes the item at the top of the stack. Throws NoSuchElementException if the stack is empty.
     *
     * @return The item at the top of the stack that is removed.
     */
    public E pop() throws NoSuchElementException {
        // check for empty stack
        if (linkedListStack.size() == 0)
            throw new NoSuchElementException();

        // return the removed last item in the linked list because added items are added to the end of the list.
        return linkedListStack.removeLast();
    }

    /**
     * Pushes the input item onto the top of the stack.
     *
     * @param item Element to be added to the top of the stack.
     */
    public void push(E item) {
        // added items are added to the end of the list.
        linkedListStack.addLast(item);
    }

    /**
     * Returns the number of items in the stack.
     */
    public int size() {
        return linkedListStack.size();
    }

    /**
     * Returns an array containing all of the elements in this stack in proper sequence, from the first element (bottom),
     * to the last element (top). ( O(N) for a doubly-linked list.
     *
     *@return An array of type Object that contains all the elements in this stack in the same order as this list.
     */
    public Object[] toArray() {
        return linkedListStack.toArray();
    }
}
