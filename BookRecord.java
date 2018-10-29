/*
    
Object Oriented Analysis & Design

Assignment #1
Section: CS-A

1.  Name: Mohsin Hayat   |   Roll Number: L16-4333
2.  Name: Aanish Amir    |   Roll Number: L16-4144

*/


package lms;

import java.util.Date;

public class BookRecord {

    User issuer;
    User issuedTo;
    Book book;
    Date issueDate;
    Date returnDate;
    int id;
    Date dueDate;
    int fine = 0;

    public BookRecord(User issuer, User issuedTo, Book book, Date issueDate, Date dueDate) {

        this.issuer = issuer;
        this.issuedTo = issuedTo;
        this.book = book;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public BookRecord(User issuer, User issuedTo, Book book, Date issueDate, Date returnDate, Date dueDate) {
        this.issuer = issuer;
        this.issuedTo = issuedTo;
        this.book = book;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
        this.id = id;
    }

    public User getIssuer() {
        return issuer;
    }

    public User getIssuedTo() {
        return issuedTo;
    }

    public Book getBook() {
        return book;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getFine() { //fifty rupees per day after due date
        int totalFine;
        if (returnDate == null) {
            Date curr = new Date();
            if (curr.before(dueDate)) {
                return 0;
            } else {
                long timeDiff1 = curr.getTime() - dueDate.getTime();
                totalFine = (int) (timeDiff1 / (1000 * 60 * 60 * 24) * 50);
                return totalFine;
            }
        }
        if (returnDate.before(dueDate)) {
            return 0;
        }
        long timeDiff2 = returnDate.getTime() - dueDate.getTime();
        totalFine = (int) (timeDiff2 / (1000 * 60 * 60 * 24) * 50);
        return totalFine;
    }

}
