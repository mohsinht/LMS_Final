
package lms;

public class rules {
    static String msg = "There are no errors";
    rules(){}
    
    public static boolean username(String username){
        if(username.length() > 20){
            msg = "The username must be less than 20 characters";
            return false;
        }
        if(username.equals("")){
            msg = "Username cannot be empty.";
            return false;
        }
        return true;
    }
}
