package com.lscchat.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UserNotFoundAdvice {

	public Map<String, String> exceptionHanler(UserNotFoundException exception){
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error message",exception.getMessage());
		return errorMap;
	}
}
