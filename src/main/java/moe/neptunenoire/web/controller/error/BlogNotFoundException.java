package moe.neptunenoire.web.controller.error;

public class BlogNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2906687553092115728L;
	
	public BlogNotFoundException() {
		super();
	}
	
	public BlogNotFoundException(String str) {
		super(str);
	}

}
