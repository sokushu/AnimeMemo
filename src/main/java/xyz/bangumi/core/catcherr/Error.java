package xyz.bangumi.core.catcherr;

/**
 * Error
 */
public class Error extends Exception{
    
    private static final long serialVersionUID = 1L;

	public Error(String string){
        System.err.println(string);
    }
}