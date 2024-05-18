package com.example.demo.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constetnts.CafeConstants;
import com.example.demo.pojo.User;
import com.example.demo.rest.UserRest;
import com.example.demo.service.UserService;
import com.example.demo.utils.CafeUtils;

@RestController
public class UserRestImpl implements UserRest {
	
	@Autowired
	 UserService userService;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
		return userService.signUp(requestMap);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
