package com.ngo.exception;

/**
 * Custom Exception from Service Layer depending on DAO alterations.
 * 
 * @author Sai Pranav
 *
 */
public class NGOException extends RuntimeException{

	public NGOException(String message){
		super(message);
	}
	
}
