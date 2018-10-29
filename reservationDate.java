/*
    
Object Oriented Analysis & Design

Assignment #1
Section: CS-A

1.  Name: Mohsin Hayat   |   Roll Number: L16-4333
2.  Name: Aanish Amir    |   Roll Number: L16-4144

*/

package lms;

import java.util.Date;

/**
 *
 * @author Mohsin Hayat
 */
public class reservationDate {
    Book book;
    Date date;
    String status;
    
    public reservationDate(Book book, Date date) {
        this.book = book;
        this.date = date;
        this.status = "pending";
    }

    public reservationDate(Book book, Date date, String s) {
        this.book = book;
        this.date = date;
        this.status = s;
    }
    public Book getBook() {
        return book;
    }

    public Date getDate() {
        return date;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
