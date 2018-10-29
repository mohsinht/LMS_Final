/*
    
Object Oriented Analysis & Design

Assignment #1
Section: CS-A

1.  Name: Mohsin Hayat   |   Roll Number: L16-4333
2.  Name: Aanish Amir    |   Roll Number: L16-4144

*/


package lms;

import java.util.ArrayList;
import java.util.Date;

public class Clerk extends User {

    ArrayList<reservationDate> reservedBooks = new ArrayList<>();

    public Clerk(String username, String password, String Name, String Gender, int Age) {
        super(username, password, Name, Gender, Age);
    }

    @Override
    public void reserveBook(Book b, Date d) {
        reservedBooks.add(new reservationDate(b, d));
    }
    @Override
    public void changeResStatus(Book b, String Status){
        for (int k = 0; k < reservedBooks.size(); k++) {
            if(reservedBooks.get(k).getBook() == b){
                reservedBooks.get(k).setStatus(Status);
            }
        }
    }
    @Override
    public ArrayList<reservationDate> getResInfo() {
        return this.reservedBooks;
    }

    @Override
    public void reserveBook(Book b, Date d, String status) {
        reservedBooks.add(new reservationDate(b, d, status));
    }

    public boolean reserveBook(Book b) {
        reservedBooks.add(new reservationDate(b, new Date()));
        return b.reserveBook(this);
    }

    public ArrayList<Book> searchBook(String query) {
        query = query.toLowerCase();
        ArrayList<Book> result = new ArrayList<>();
        for (int k = 0; k < LMS.Books.size(); k++) {
            Book bn = LMS.Books.get(k);
            if (bn.getName().toLowerCase().contains(query) || bn.getAuthor().toLowerCase().contains(query) || bn.getISBN().toLowerCase().contains(query)) {
                result.add(LMS.Books.get(k));
            }
        }
        return result;
    }

    public String[] getInfo() {
        String[] profile = new String[4];
        profile[0] = username;
        profile[1] = Name;
        profile[2] = Gender;
        profile[3] = String.valueOf(Age);
        return profile;
    }

    public boolean addBorrower(String username, String password, String Name, String Gender, int Age) {
        LMS.Users.add(new Borrower(username, password, Name, Gender, Age));
        return true;
    }

    public void update(String username, String password, String Name, String Gender, int Age) {
        this.username = username;
        this.password = password;
        this.Name = Name;
        this.Gender = Gender;
        this.Age = Age;
    }

    public ArrayList<reservationDate> reservedBooks() {
        return this.reservedBooks;
    }
    @Override
    public boolean removeBookFromReservation(Book b){
        return this.reservedBooks.remove(b);
    }
}
