package xyz.bangumi.core.catcherr;

/**
 * Error
 */
public class Error extends Exception{
    
    public Error(String string){
        System.err.println(string);
    }
}