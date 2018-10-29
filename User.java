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

abstract class User {
    String username;
    String password;
    String Name;
    String Gender;
    int Age;

    public User(String username, String password, String Name, String Gender, int Age) {
        this.username = username;
        this.password = password;
        this.Name = Name;
        this.Gender = Gender;
        this.Age = Age;
    }
    abstract void changeResStatus(Book b, String Status);
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }
    abstract void reserveBook(Book b, Date d);
    abstract void reserveBook(Book b, Date d, String status);
    abstract ArrayList<reservationDate> getResInfo();
    abstract boolean removeBookFromReservation(Book bk);
}
