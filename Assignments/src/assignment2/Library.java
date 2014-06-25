package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 1.0
 */
public class Library {

    // field containing the collection of libraryBook objects
    private ArrayList<LibraryBook> library;

    // sole constructor for the Library class
    public Library() {
        library = new ArrayList<LibraryBook>();
    }

    /**
     * Add the specified book to the library, assume no duplicates.
     *
     * @param isbn   --
     *               ISBN of the book to be added
     * @param author --
     *               author of the book to be added
     * @param title  --
     *               title of the book to be added
     */
    public void add(long isbn, String author, String title) {
        library.add(new LibraryBook(isbn, author, title));
    }

    /**
     * Add the list of library books to the library, assume no duplicates.
     *
     * @param list --
     *             list of library books to be added
     */
    public void addAll(ArrayList<LibraryBook> list) {
        library.addAll(list);
    }

    /**
     * Add books specified by the input file. One book per line with ISBN, author,
     * and title separated by tabs.
     * <p/>
     * If file does not exist or format is violated, do nothing.
     *
     * @param filename name of the text file to be read
     */
    public void addAll(String filename) {
        // temporary array to hold field values read from the text file.
        ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

        try {
            Scanner fileIn = new Scanner(new File(filename));
            int lineNum = 1;

            // pull the data from the file one line at a time
            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();

                // second scanner to read each line separately
                Scanner lineIn = new Scanner(line);
                lineIn.useDelimiter("\t");

                // next three if statements throw exceptions if the format is wrong
                if (!lineIn.hasNextLong())
                    throw new ParseException("ISBN", lineNum);
                long isbn = lineIn.nextLong();

                if (!lineIn.hasNext())
                    throw new ParseException("Author", lineNum);
                String author = lineIn.next();

                if (!lineIn.hasNext())
                    throw new ParseException("Title", lineNum);
                String title = lineIn.next();

                // fields from 1 line used to create new library book and add it to the temp list
                toBeAdded.add(new LibraryBook(isbn, author, title));

                // line number is followed to give line# of exception error if it occurrs
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + " Nothing added to the library.");
            return;
        } catch (ParseException e) {
            System.err.println(e.getLocalizedMessage()
                    + " formatted incorrectly at line " + e.getErrorOffset()
                    + ". Nothing added to the library.");
            return;
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\t The Files directory path is incorrect!");
            return;
        }

        // field values finally assigned to the library array list of library books
        library.addAll(toBeAdded);
    }

    /**
     * Returns the holder of the library book with the specified ISBN.
     * <p/>
     * If no book with the specified ISBN is in the library, returns null.
     *
     * @param isbn --
     *             ISBN of the book to be looked up
     */
    public String lookup(long isbn) {
        for (LibraryBook libraryBook : library)
            if (libraryBook.getIsbn() == isbn)
                return libraryBook.getHolder();
        return null;
    }

    /**
     * Returns the list of library books checked out to the specified holder.
     * <p/>
     * If the specified holder has no books checked out, returns an empty list.
     *
     * @param holder --
     *               holder whose checked out books are returned
     */
    public ArrayList<LibraryBook> lookup(String holder) {
        // initialize list of books with specified holder
        ArrayList<LibraryBook> returnList = new ArrayList<LibraryBook>();

        // parse library and add all books with specified holder to return list
        for (LibraryBook libraryBook : library) {
            if (libraryBook.getHolder() == null)
                continue;
            if (libraryBook.getHolder().equals(holder))
                returnList.add(libraryBook);
        }

        // return the substantiated list of books
        return returnList;
    }

    /**
     * Sets the holder and due date of the library book with the specified ISBN.
     * <p/>
     * If no book with the specified ISBN is in the library, returns false.
     * <p/>
     * If the book with the specified ISBN is already checked out, returns false.
     * <p/>
     * Otherwise, returns true.
     *
     * @param isbn   --
     *               ISBN of the library book to be checked out
     * @param holder --
     *               new holder of the library book
     * @param month  --
     *               month of the new due date of the library book
     * @param day    --
     *               day of the new due date of the library book
     * @param year   --
     *               year of the new due date of the library book
     */
    public boolean checkout(long isbn, String holder, int month, int day, int year) {
        for (LibraryBook libraryBook : library)
            if (libraryBook.getIsbn() == isbn) {

                // check if book is already checked out
                if (libraryBook.getHolder() != null)
                    return false;

                // since the book hasn't been checked out set holder and due date and return
                libraryBook.setHolder(holder);
                libraryBook.setDueDate(month, day, year);
                return true;
            }
        // if the book wasn't found return false
        return false;
    }

    /**
     * Unsets the holder and due date of the library book.
     * <p/>
     * If no book with the specified ISBN is in the library, returns false.
     * <p/>
     * If the book with the specified ISBN is already checked in, returns false.
     * <p/>
     * Otherwise, returns true.
     *
     * @param isbn --
     *             ISBN of the library book to be checked in
     */
    public boolean checkin(long isbn) {
        for (LibraryBook libraryBook : library) // parse entire library
            if (libraryBook.getIsbn() == isbn) { // either return false if book is checked in or check the book out
                //  and return true
                if (libraryBook.getHolder() == null)
                    return false;
                libraryBook.checkIn();
                return true;
            }
        return false; // if the book wasn't found return false
    }

    /**
     * Unsets the holder and due date for all library books checked out be the
     * specified holder.
     * <p/>
     * If no books with the specified holder are in the library, returns false;
     * <p/>
     * Otherwise, returns true.
     *
     * @param holder --
     *               holder of the library books to be checked in
     */
    public boolean checkin(String holder) {
        // get list of all books under specified holder
        ArrayList<LibraryBook> books = this.lookup(holder);

        // if no books under the specified holder return false
        if (books.size() == 0)
            return false;

        // otherwise check in each book and return true
        for (LibraryBook book : books)
            book.checkIn();
        return true;
    }

    /**
     * Test method which returns a copy of the list of books
     */
    protected ArrayList<LibraryBook> bookList() {
        return new ArrayList<LibraryBook>(library);
    }

    /**
     * Used for testing purposes in the LibraryTest class
     *
     * @return Number of books that are in the library.
     */
    protected int bookCount() {
        return library.size();
    }

    /**
     * Returns a LibraryBook object copy at the index of this LibraryBook ArrayList
     *
     * @param index Index of requested LibraryBook object to be returned.
     * @return requested library book at specific index
     */
    public LibraryBook getInstanceOf(int index) {
        return library.get(index);
    }
}
