package assignment2;

import java.util.GregorianCalendar;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * <p/>
 * Note that ISBNs are unique.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 1.0
 */
public class LibraryBook extends Book {

    // two extra fields added in order to checkout a book
    private String holder;
    private GregorianCalendar dueDate;

    /**
     * sole constructor for the LibraryBook class, uses the book constructor
     * to assign ISBN, author, and title, and also requires holder & dueDate be set
     * to null until they are checked out.
     */
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);
        holder = null;
        dueDate = null;
    }

    /**
     * @return the due date of the book
     */
    public GregorianCalendar getDueDate() {
        return this.dueDate;
    }

    /**
     * @return Name of the person with the book checked out
     */
    public String getHolder() {
        return this.holder;
    }

    /**
     * Sets the holder field to the name of the person checking out the book.
     *
     * @param newHolder Name of the person that is checking out the library book.
     */
    public void setHolder(String newHolder) {
        this.holder = newHolder;
    }

    /**
     * Sets the due date of this libraryBook at time of checkout
     *
     * @param month Month as an integer between 0 and 11
     * @param day   Day of the month
     * @param year  year of the due date
     */
    public void setDueDate(int month, int day, int year) {
        this.dueDate = new GregorianCalendar(year, month, day);
    }

    /**
     * Sets the holder & due date fields back to null when the book is checked in.
     */
    public void checkIn() {
        // remove 'holder' and 'dueDate'
        this.holder = null;
        this.dueDate = null;
    }
}
