package com.lscchat.exception;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String id) {
		super("Could not found user with id "+id);
	}
}
