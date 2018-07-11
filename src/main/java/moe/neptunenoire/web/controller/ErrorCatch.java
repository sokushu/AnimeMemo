package moe.neptunenoire.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import moe.neptunenoire.web.controller.error.HomeNotFoundException;

@ControllerAdvice
public class ErrorCatch {

	@ExceptionHandler(value = HomeNotFoundException.class)
	public String homeNotFoundError(HomeNotFoundException e, Model model) {
		model.addAttribute("test", "HomeNotFoundException");
		return "test";
	}


	public String error(Exception e) {
		return e.getMessage();
	}

}
