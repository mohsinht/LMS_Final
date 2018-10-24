/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
