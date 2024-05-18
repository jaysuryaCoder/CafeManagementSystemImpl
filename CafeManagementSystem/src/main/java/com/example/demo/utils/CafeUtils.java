package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {
	private CafeUtils() {

	}
	
	public static ResponseEntity<String> getResponseEntity(String responseMsg,HttpStatus httpStatus){
		return new ResponseEntity<String>("{\"message\":\""+responseMsg+"\"}",httpStatus);
	}
}
