package moe.neptunenoire.web.controller.error;

public class IAmError extends Exception {
	public IAmError() {
		super();
	}

	public IAmError(String str) {
		super(str);
	}

	public IAmError(String msg, Throwable e) {
		super(msg, e);
	}
}
