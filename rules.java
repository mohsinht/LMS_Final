
package lms;

public class rules {
    static String msg = "There are no errors";
    rules(){}
    
    public static boolean username(String username){
        if(username.length() > 20){
            msg = "The username must be less than 20 characters";
            return false;
        }
        if(!username.matches("[a-zA-Z0-9.\\-_]{3,}")){
            msg = "The username is not in its correct format.";
        }
        if(username.equals("")){
            msg = "Username cannot be empty.";
            return false;
        }
        return true;
    }
    public static boolean age(String age){
        if(!age.matches("^[0-9]*$"))
        {
            msg="The age must contain a number";
            return false;
        }
        if(Integer.valueOf(age) > 90 || Integer.valueOf(age) < 10){
            msg="The age is not correct. It must be between 10 and 90";
            return false;
        }
        return true;
    }
}
