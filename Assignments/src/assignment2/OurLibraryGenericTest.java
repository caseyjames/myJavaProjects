package assignment2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Unit tests for the methods added to LibraryGeneric
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 1.0
 *
 */

public class OurLibraryGenericTest extends TestCase {

    // the only fields are a test library (of String type) and books to be added to the library
    LibraryGeneric<String> testLibraryString;
    LibraryBookGeneric<String> book1, book2, book3, book4, book5, book6;

    public void setUp() throws Exception {
        super.setUp();

        // create library
        testLibraryString = new LibraryGeneric<String>();

        // substantiate Book fields
        book1 = new LibraryBookGeneric<String>(1234567890123L, "first author", "first title");
        book2 = new LibraryBookGeneric<String>(1234567890354L, "second author", "second title");
        book3 = new LibraryBookGeneric<String>(1234567890687L, "third author", "third title");
        book4 = new LibraryBookGeneric<String>(1234567890500L, "fourth author", "fourth title");
        book5 = new LibraryBookGeneric<String>(1234567890501L, "fifth author", "fifth title");

        // sixth book to test getOrderedByAuthor when author is the same
        book6 = new LibraryBookGeneric<String>(1234567890506L, "fifth author", "sixth title");

        // add books to library
        testLibraryString.add(book1.getIsbn(), book1.getAuthor(), book1.getTitle());
        testLibraryString.add(book2.getIsbn(), book2.getAuthor(), book2.getTitle());
        testLibraryString.add(book3.getIsbn(), book3.getAuthor(), book3.getTitle());
        testLibraryString.add(book4.getIsbn(), book4.getAuthor(), book4.getTitle());
        testLibraryString.add(book5.getIsbn(), book5.getAuthor(), book5.getTitle());
        testLibraryString.add(book6.getIsbn(), book6.getAuthor(), book6.getTitle());
    }

    public void tearDown() throws Exception {
        testLibraryString = null;
    }

    public void testGetInventoryList() throws Exception {
        // use getInventoryList() to retrieve a sorted list
        ArrayList<LibraryBookGeneric<String>> stringInventoryList = testLibraryString.getInventoryList();

        // assertEquals for each book in the sorted array against the expected sorting of the books
        assertEquals(book1, stringInventoryList.get(0));
        assertEquals(book2, stringInventoryList.get(1));
        assertEquals(book4, stringInventoryList.get(2));
        assertEquals(book5, stringInventoryList.get(3));
        assertEquals(book6, stringInventoryList.get(4));
        assertEquals(book3, stringInventoryList.get(5));
    }

    public void testGetOrderedByAuthor() throws Exception {
        // use getOrderedByAuthor() to retrieve a sorted list
        ArrayList<LibraryBookGeneric<String>> stringInventoryList = testLibraryString.getOrderedByAuthor();

        // assertEquals for each book in the sorted array against the expected sorting of the books
        assertEquals(book5, stringInventoryList.get(0));
        assertEquals(book6, stringInventoryList.get(1));
        assertEquals(book1, stringInventoryList.get(2));
        assertEquals(book4, stringInventoryList.get(3));
        assertEquals(book2, stringInventoryList.get(4));
        assertEquals(book3, stringInventoryList.get(5));
    }

    public void testGetOverdueList() throws Exception {
        // checkout each book to set a due date

        /* should be overdue */
        testLibraryString.checkout(book1.getIsbn(), "first holder", 2014, 5, 26);
        testLibraryString.checkout(book2.getIsbn(), "second holder", 2014, 5, 26);
        testLibraryString.checkout(book3.getIsbn(), "third holder", 2014, 5, 28);
        testLibraryString.checkout(book4.getIsbn(), "fourth holder", 2014, 5, 24);

        /* should _not_ be overdue */
        testLibraryString.checkout(book5.getIsbn(), "fifth holder", 2014, 5, 29);
        testLibraryString.checkout(book6.getIsbn(), "sixth holder", 2014, 5, 30);

        // use getOverdueList() to retrieve a sorted list
        ArrayList<LibraryBookGeneric<String>> stringInventoryList = testLibraryString.getOverdueList(5, 29, 2014);

        // assertEquals for each book in the sorted array against the expected sorting of the books
        assertEquals(book4, stringInventoryList.get(0));
        assertEquals(book2, stringInventoryList.get(1));
        assertEquals(book1, stringInventoryList.get(2));
        assertEquals(book3, stringInventoryList.get(3));
    }
}