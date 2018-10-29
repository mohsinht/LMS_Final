/*
    
Object Oriented Analysis & Design

Assignment #1
Section: CS-A

1.  Name: Mohsin Hayat   |   Roll Number: L16-4333
2.  Name: Aanish Amir    |   Roll Number: L16-4144

*/


package lms;

public class rules {

    static String msg = "There are no errors";

    rules() {
    }

    public static boolean username(String username) {
        if (username.length() > 20) {
            msg = "The username must be less than 20 characters";
            return false;
        }
        if (!username.matches("[a-zA-Z0-9.\\-_]{3,}")) {
            msg = "The username is not in its correct format.";
        }
        if (username.equals("")) {
            msg = "Username cannot be empty.";
            return false;
        }
        return true;
    }

    public static boolean age(String age) {
        if (!age.matches("^[0-9]*$")) {
            msg = "The age must contain a number";
            return false;
        }
        if (Integer.valueOf(age) > 90 || Integer.valueOf(age) < 10) {
            msg = "The age is not correct. It must be between 10 and 90";
            return false;
        }
        return true;
    }

    public static boolean ISBN(String ISBN) {
        if (!ISBN.matches("^[0-9]*$")) {
            msg = "ISBN must contain a number";
            return false;
        }
        if (ISBN.length() != 13) {
            msg = "The ISBN is not correct. It must be of length 13";
            return false;
        }
        return true;
    }

    public static boolean name(String name) {
        if (!name.matches("^[a-zA-Z .]+$")) {
            msg = "Name must contain only alphabets";
            return false;
        }
        if (name.length() > 20) {
            msg = "The name cannot be greater than 20 in length";
            return false;
        }
        return true;
    }

    public static boolean rollnumber(String rn){
        if(!rn.matches("((L|K|F|P)+[0-9].[-]+[0-9])\\w{3}")){
            msg = "Roll number must be in right format. (i.e L16-4333 or P18-8956)";
            return false;
        }
        if(rn.equals("")){
            msg = "Roll number must not be empty.";
            return false;
        }
        return true;
    }
    public static boolean password(String password) {
        if (password.length() > 20) {
            msg = "The password must be less than 20 characters";
            return false;
        }
        if (!password.matches("[A-Z]")) {
            msg = "The password must contain a upper case character.";
            return false;
        }
        if (!password.matches("[a-z]")) {
            msg = "The password contain a lower case character.";
            return false;
        }
        if (!password.matches("[@$\\-_*&^%!]")) {
            msg = "The password contain a unique character.";
            return false;
        }
        if (password.equals("")) {
            msg = "password cannot be empty.";
            return false;
        }

        return true;
    }
}
