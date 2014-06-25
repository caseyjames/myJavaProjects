package assignment5;

import java.util.NoSuchElementException;

/**
 * Represents a generic doubly linked list.
 *
 * @param <E> - the type of elements contained in the linked list
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 6/16/2014
 */
public class MyLinkedList<E> implements List<E> {
    //Instance variables
    int size;
    Node head;
    Node tail;

    /**
     * Constructor.  Creates a blank linked list.
     */
    public MyLinkedList() {
        size = 0;
    }

    /**
     * Inserts the specified element at the beginning of the list. O(1) for a doubly-linked list.
     *
     * @param element - The element to add at the beginning of the list.
     */
    public void addFirst(E element) {
        // if there are no elements of the list yet, add this as first
        if (size == 0) {
            head = new Node(element);
            tail = head;
            size++;
            return;
        }

        // otherwise add new node, interchange references, assign new node as head.
        head.prev = new Node(element);
        head.prev.next = head;
        head = head.prev;
        size++;
    }

    /**
     * Inserts the specified element at the end of the list. O(1) for a doubly-linked list.
     *
     * @param o - The element to add at the end of the list.
     */
    public void addLast(E o) {
        // if this is first the first add, call addFirst instead, and return so it doesn't continue.
        if (size == 0) {
            addFirst(o);
            return;
        }
        // otherwise add new node, interchange references, assign new node as tail.
        tail.next = new Node(o);
        tail.next.prev = tail;
        tail = tail.next;
        size++;
    }

    /**
     * Inserts the specified element at the specified position in the list. Throws IndexOutOfBoundsException if index is
     * out of range. O(N) for a doubly-linked list.
     *
     * @param index   position of list to add the new element.
     * @param element new item to be added at the position of <tt>index</tt>
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, E element) throws IndexOutOfBoundsException {
        /*check for index as first or last index first thing, in case index = size = 0, before exception is thrown.
        added returns after addFirst or addLast so statements do not continue*/
        if (index == 0) {
            this.addFirst(element);
            return;
        }
        if (index == size - 1) {
            this.addLast(element);
            return;
        }
        if (index < 0 || index >= size)  // check for out of bounds and throw exception.
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException"); // message created for testing

        //find which end the index is closest to, and start from that end.
        boolean startFront = true;
        if (size - index < index) {
            startFront = false;
            index = size - index - 1;
        }

        // variables referencing nodes that go on either side of desired added index
        Node prevNode, nextNode;
        // if index is closer to head, start at head
        if (startFront) {
            nextNode = head;
            // start at the head node and move through list until i reaches index
            for (int i = 0; i < index; i++)
                nextNode = nextNode.next;
            // after index node is found, set node of opposite side of the index
            prevNode = nextNode.prev;
        } else {
            prevNode = tail;  // if index is closer to the tail.
            // start at the tail of the list, move backward until index is reached
            for (int i = 0; i < index; i++)
                prevNode = prevNode.prev;
            nextNode = prevNode.next;
        }

        // with known nodes on both sides, set new element between them and increment size.
        prevNode.next = new Node(element);
        prevNode.next.prev = prevNode;
        nextNode.prev = prevNode.next;
        nextNode.prev.next = nextNode;
        size++;
    }

    /**
     * Returns the first element in the list. Throws NoSuchElementException if the list is empty. O(1) for a
     * doubly-linked list.
     *
     * @return First element in the this list.
     * @throws NoSuchElementException
     */
    public E getFirst() throws NoSuchElementException {
        // check that there is at least one element in the list, if not, throw exception.
        if (size == 0)
            throw new NoSuchElementException("NoSuchElementException");//message for testing
        // return first element, or the data of head
        return head.data;
    }

    /**
     * Returns the last element in the list. Throws NoSuchElementException if the list is empty. O(1) for a
     * doubly-linked list.
     *
     * @return Last element in this list.
     * @throws NoSuchElementException
     */
    public E getLast() throws NoSuchElementException {
        // check that there is at least one element in the list, if not, throw exception.
        if (size == 0)
            throw new NoSuchElementException("NoSuchElementException");//message for testing
        // return data of last element, located at tail.
        return tail.data;
    }

    /**
     * Returns the element at the specified position in the list. Throws IndexOutOfBoundsException if index is out of
     * range. O(N) for a doubly-linked list.
     *
     * @param index Position of element in this list to be returned.
     * @return Element from this list located at the position of <tt>index</tt>
     * @throws IndexOutOfBoundsException
     */
    public E get(int index) throws IndexOutOfBoundsException {
        // first check for index being out of bounds
        if (size == 0 || index >= size || index < 0)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException"); //message added for testing

        // if index is the first or last index, use getFirst or getLast instead.
        if (index == 0)
            return this.getFirst();
        if (index == size - 1)
            return this.getLast();

        //find which end the index is closest to start from
        boolean startFront = true;
        if (size - index < index) {
            startFront = false;
            index = size - index - 1;
        }

        // node to start from and traverse with.
        Node toReturn;
        // if index is closer to head, start at head
        if (startFront) {
            toReturn = head;
            for (int i = 0; i < index; i++)
                toReturn = toReturn.next;
        } else { // otherwise traverse the list backwards from the tail
            toReturn = tail;
            for (int i = 0; i < index; i++)
                toReturn = toReturn.prev;
        }
        // with toReturn node at correct index, return its associated data
        return toReturn.data;
    }

    /**
     * Removes and returns the first element from the list. Throws NoSuchElementException if the list is empty.
     * O(1) for a doubly-linked list.
     *
     * @return The first element from this list that is then removed.
     * @throws NoSuchElementException
     */
    public E removeFirst() throws NoSuchElementException {
        // variable to hold the removed element to be returned
        E item;
        // check that there is at least 1 element in the list, if not throw exception
        if (size == 0)
            throw new NoSuchElementException("NoSuchElementException");//message for testing
        // with only one item in the list, set the head and tail to null
        if (size == 1) {
            item = head.data;
            head = null;
            tail = null;
            size--;
            return item;
        }
        // store the data at head first, set head to second item, set first item to null
        item = head.data;
        head = head.next;
        head.prev = null;
        size--;
        // return the stored data item
        return item;
    }

    /**
     * Removes and returns the last element from the list. Throws NoSuchElementException if the list is empty.
     * O(1) for a doubly-linked list.
     *
     * @return The last element from this list that is then removed.
     * @throws NoSuchElementException
     */
    public E removeLast() throws NoSuchElementException {
        // variable for item to be removed and returned
        E item;
        // check that there is at least 1 element in the list
        if (size == 0)
            throw new NoSuchElementException("NoSuchElementException");//message for testing
        // with only one item in the list, set the head and tail to null
        if (size == 1) {
            item = tail.data;
            head = null;
            tail = null;
            size--;
            return item;
        }
        // store the data at tail first, set tail to second to last item, then set last item to null
        item = tail.data;
        tail = tail.prev;
        tail.next = null;
        size--;
        // return the stored data item
        return item;
    }

    /**
     * Removes and returns the element at the specified position in the list. Throws IndexOutOfBoundsException if
     * index is out of range. O(N) for a doubly-linked list.
     *
     * @param index Position of element in this list to be removed.
     * @return Element in this list that is removed at the index position.
     * @throws IndexOutOfBoundsException
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        // variable for item to be removed and returned
        E item;
        // check first that index is not out of bounds, if so throw exception
        if (size == 0 || index >= size || index < 0)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");//message added for testing

        // if index is the first or last index, use removeFirst & removeLast instead.
        if (index == 0)
            return this.removeFirst();
        if (index == size - 1)
            return this.removeLast();

        //find which end the index is closest to start from
        boolean startFront = true;
        if (size - index < index) {
            startFront = false;
            index = size - index - 1;
        }

        // node to start from and traverse with.
        Node toRemove;
        // if index is closer to head, start at head
        if (startFront) {
            toRemove = head;
            for (int i = 0; i < index; i++)
                toRemove = toRemove.next;
        } else {
            toRemove = tail;
            for (int i = 0; i < index; i++)
                toRemove = toRemove.prev;
        }

        // store element being removed to item to be returned, remove element, and decrement size.
        item = toRemove.data;
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        toRemove.next = null;
        toRemove.prev = null;
        size--;
        // returned stored item that was removed.
        return item;
    }

    /**
     * Removes the first occurrence of the specified element from this list, and returns true if the element was
     * found and removed, false otherwise. O(N) for a doubly-linked list.
     *
     * @param element The element to be found and removed from this list if it is contained in this list.
     * @return True if the element was found and removed, otherwise false.
     */
    public boolean remove(E element) {
        // check first that this list has at least one element, if not return false
        if (size == 0)
            return false;

        // create starting node
        Node toRemove = head;
        // traverse from the head node until the element is reached or the end is reached
        while (!toRemove.data.equals(element) && toRemove != tail)
            toRemove = toRemove.next;

        // if the tail was reached but the tail element is not equal to the argument, then the element wasn't found.
        if (toRemove == tail && !toRemove.data.equals(element))
            return false;

        // at this point the element is found. Remove it, decrement size and return true.
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        size--;
        return true;
    }

    /**
     * Returns true if this list contains the specified element or false if this list does not contain the element.
     * O(N) for a doubly-linked list.
     *
     * @param element Item to be checked if it is contained within this list.
     * @return True if the specified element is contained in this list, otherwise false.
     */
    public boolean contains(E element) {
        // if there are no elements in the list, return false
        if (size == 0)
            return false;

        // create starting node
        Node toFind = head;
        // traverse from the head node until the element is reached or the end is reached
        while (!toFind.data.equals(element) && toFind != tail)
            toFind = toFind.next;

        // if the tail was reached but the tail element is not equal to the argument, then the element wasn't found.
        if (toFind == tail && !toFind.data.equals(element))
            return false;

        // at this point the element is found, so return true.
        return true;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list, or -1 if this list does not
     * contain the element. O(N) for a doubly-linked list.
     *
     * @param element Item to found in this list.
     * @return Index of the first occurrence of this element if it is found in this list, otherwise -1 to stating
     * the element was not found.
     */
    public int indexOf(E element) {
        // if there are no elements in this list, automatically return -1.
        if (size == 0)
            return -1;

        // variable to keep track of the node indexes
        int returnIndex = 0;
        // create starting node
        Node toFind = head;
        // traverse from the head node until the element is reached or the end is reached
        while (!toFind.data.equals(element) && toFind != tail) {
            toFind = toFind.next;
            returnIndex++;
        }

        // if the tail was reached but does not equal element, argument was not found so return -1
        if (toFind == tail && !toFind.data.equals(element))
            return -1;

        // at this point the element is found, so return the associated index.
        return returnIndex;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not
     * contain the element. O(N) for a doubly-linked list.
     *
     * @param element Item for which the last of occurrence of is to be found in this list.
     * @return Index of the last occurrence of this element if it is found in this list, otherwise -1 to stating
     * the element was not found.
     */
    public int lastIndexOf(E element) {
        // if there are no elements in this list, automatically return -1.
        if (size == 0)
            return -1;

        // variable to keep track of the node indexes, start from the tail and moving backward
        int returnIndex = size - 1;
        // create starting node
        Node toFind = tail;
        // traverse backward from the tail node until the element is reached or the beginning is reached
        while (!toFind.data.equals(element) && toFind != head) {
            toFind = toFind.prev;
            returnIndex--;
        }

        // if the head was reached but does not equal element, argument was not found so return -1
        if (toFind == head && !toFind.data.equals(element))
            return -1;

        // at this point the element is found, so return the associated index.
        return returnIndex;
    }

    /**
     * Returns the number of elements in this list. O(1) for a doubly-linked list.
     *
     * @return An integer stating the number of elements in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this collection contains no elements. O(1) for a doubly-linked list.
     *
     * @return True if there are no elements in this list, otherwise false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all of the elements from this list. O(1) for a doubly-linked list.
     */
    public void clear() {
        // set the head and tail of this list to null, and the size to zero.
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * O(N) for a doubly-linked list.
     *
     * @return An array of type Object that contains all the elements this list in the same order as this list.
     */
    public Object[] toArray() {
        // create new object array of the exact size of this list
        Object[] result = new Object[size];
        // copy each element of this list to the object array starting at index 0
        for (int i = 0; i < size; i++)
            result[i] = this.get(i);

        // return the object array
        return result;
    }

    /**
     * This class represents a node for a doubly-linked list. Each instance contains the data to be stored, and two
     * other nodes of this same class that represent references to the next & previous elements to this element.
     */
    private class Node {
        // item or element to be stored in the list
        E data;
        // two nodes of this same class to store the address of the next element and the previous element.
        Node next;
        Node prev;

        /**
         * Sole constructor of this node class.
         *
         * @param element Item to be added to this list. It is stored in a generic data variable.
         */
        public Node(E element) {
            data = element;
        }
    }
}
