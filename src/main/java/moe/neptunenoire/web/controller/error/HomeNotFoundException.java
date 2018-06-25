package moe.neptunenoire.web.controller.error;

public class HomeNotFoundException extends Exception {

	public HomeNotFoundException() {
		super();
	}

	public HomeNotFoundException(String str) {
		super(str);
	}

}
