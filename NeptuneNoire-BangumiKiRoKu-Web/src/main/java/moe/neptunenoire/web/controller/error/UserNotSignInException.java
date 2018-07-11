package moe.neptunenoire.web.controller.error;

/**
 * 用户未登录异常
 * @author jo
 *
 */
public class UserNotSignInException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 137532092995041220L;
	
	public UserNotSignInException() {
		super();
	}
	
	public UserNotSignInException(String str) {
		super(str);
	}

}
