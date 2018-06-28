package moe.neptunenoire.web.controller.error;

public class IAmError extends Exception {
	public IAmError() {
		super();
	}

	public IAmError(String str) {
		super(str);
	}
}
