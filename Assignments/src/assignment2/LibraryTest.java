package assignment2;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

/**
 * Testing class for Library.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 1.0
 *
 */
public class LibraryTest {

    public static void main(String[] args) {
        // test an empty library
        Library lib = new Library();

        if (lib.lookup(978037429279L) != null)
            System.err.println("TEST FAILED -- empty library: lookup(isbn)");
        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
        if (booksCheckedOut == null || booksCheckedOut.size() != 0)
            System.err.println("TEST FAILED -- empty library: lookup(holder)");
        if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008))
            System.err.println("TEST FAILED -- empty library: checkout");
        if (lib.checkin(978037429279L))
            System.err.println("TEST FAILED -- empty library: checkin(isbn)");
        if (lib.checkin("Jane Doe"))
            System.err.println("TEST FAILED -- empty library: checkin(holder)");

        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        if (lib.lookup(9780330351690L) != null)
            System.err.println("TEST FAILED -- small library: lookup(isbn)");
        if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008))
            System.err.println("TEST FAILED -- small library: checkout");
        booksCheckedOut = lib.lookup("Jane Doe");
        if (booksCheckedOut == null
                || booksCheckedOut.size() != 1
                || !booksCheckedOut.get(0).equals(
                new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
                || !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
                || !booksCheckedOut.get(0).getDueDate().equals(
                new GregorianCalendar(2008, 1, 1)))
            System.err.println("TEST FAILED -- small library: lookup(holder)");
        if (!lib.checkin(9780330351690L))
            System.err.println("TEST FAILED -- small library: checkin(isbn)");
        if (lib.checkin("Jane Doe"))
            System.err.println("TEST FAILED -- small library: checkin(holder)");


        // test a medium library, add the .txt book list all at once to 'lib2'
        String filename = "Mushroom_Publishing.txt";  //file must be placed in current project file
        Library lib2 = new Library();
        lib2.addAll(filename);
        for (int i = 0; i < lib2.bookCount(); i++) {
            long isbnPrint = (long) lib2.getInstanceOf(i).getIsbn();
            String authorPrint = lib2.getInstanceOf(i).getAuthor();
            String titlePrint = lib2.getInstanceOf(i).getTitle();
            System.out.print(isbnPrint + "\t");
            System.out.print(authorPrint + "\t");
            System.out.print(titlePrint + "\t");
            System.out.print("\n");
        }


        // test a large library
        Library testLibrary = new Library();
        int numberOfBooks = 10000;

        // keep lists tracking the ISBNs and holders of the books we add to the library
        ArrayList<Long> isbnList = new ArrayList<Long>(numberOfBooks);
        ArrayList<String> holderList = new ArrayList<String>(numberOfBooks);

        // generate random isbns and book fields, substantiate books with those fields, and add those books to the library
        for (int i = 0; i < numberOfBooks; i++) {
            long isbn = generateIsbn();
            String author = UUID.randomUUID().toString() + "\tAuthor #" + String.format("%04d", i);
            String title = UUID.randomUUID().toString() + "\tTitle #" + String.format("%04d", i);
            String holder = "Holder = " + UUID.randomUUID().toString() + "\t#" + String.format("%04d", i);
            testLibrary.add(isbn, author, title);
            testLibrary.checkout(isbn, holder, (int) (Math.random() * 12), (int) (Math.random() * 12), (int) (Math.random() * 12));

            // keep track of the fields which were used to substantiate the books
            //  (in order to validate the lookups later)
            isbnList.add(isbn);
            holderList.add(holder);
        }

        // lookup books from random positions in the library and validate their holders against the
        //  previously substantiated list
        int numberOfLookups = 10000;
        for (int i = 0; i < numberOfLookups; i++) {
            int randIndex = (int) (Math.random() * numberOfBooks);
            long isbnTest = isbnList.get(randIndex);

            // console output for debugging purposes
//            System.out.println("Lookup #" + String.format("%05d", j+1) + "\t" + testLibrary.lookup(isbnTest));

            // validate the holder against the substantiated list
            if (!testLibrary.lookup(isbnTest).equals(holderList.get(randIndex)))
                System.out.println("Holder lookup failed");
        }

        System.out.println("Testing done.");

    }


    /**
     * Returns a library of "dummy" books (random ISBN and placeholders for author
     * and title).
     * <p/>
     * Useful for collecting running times for operations on libraries of varying
     * size.
     *
     * @param size --
     *             size of the library to be generated
     */
    public static ArrayList<LibraryBook> generateLibrary(int size) {
        ArrayList<LibraryBook> result = new ArrayList<LibraryBook>();

        for (int i = 0; i < size; i++) {
            // generate random ISBN
            Random randomNumGen = new Random();
            String isbn = "";
            for (int j = 0; j < 13; j++)
                isbn += randomNumGen.nextInt(10);

            result.add(new LibraryBook(Long.parseLong(isbn), "An Author", "A Title"));
        }

        return result;
    }

    /**
     * Returns a randomly-generated ISBN (a long with 13 digits).
     * <p/>
     * Useful for collecting running times for operations on libraries of varying
     * size.
     */
    public static long generateIsbn() {
        Random randomNumGen = new Random();

        String isbn = "";
        for (int j = 0; j < 13; j++)
            isbn += randomNumGen.nextInt(10);

        return Long.parseLong(isbn);
    }

}
