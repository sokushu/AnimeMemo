package moe.neptunenoire.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import moe.neptunenoire.web.controller.error.BangumiException;
import moe.neptunenoire.web.controller.error.HomeException;

@ControllerAdvice
public class ErrorCatch {

	@ExceptionHandler(value = BangumiException.class)
	public String bangumiError() {
		return "";
	}

	@ExceptionHandler(value = HomeException.class)
	public String homeError() {
		return "";
	}

}
