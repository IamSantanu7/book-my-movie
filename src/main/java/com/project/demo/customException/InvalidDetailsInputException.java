package com.project.demo.customException;

public class InvalidDetailsInputException extends Exception{

	public InvalidDetailsInputException(String message) {
		super(message);
	}

}
