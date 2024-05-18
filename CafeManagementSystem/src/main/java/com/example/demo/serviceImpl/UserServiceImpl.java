package com.example.demo.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.constetnts.CafeConstants;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		log.info("Inside SignUp {}", requestMap);
		try {
		if (validateSignUpMap(requestMap)) {
			System.out.println(requestMap.get("email")+"  JJJJJJJJJJJJJJJJJJJJJJJJ");
			User user = userDao.findByEmailId(requestMap.get("email"));
//			System.out.println(user.toString()+"  JJJJJJJJJJJJJJJJJJJJJJ");
			if (Objects.isNull(user)) {
				userDao.save(getUserFromMap(requestMap));
				return CafeUtils.getResponseEntity("Registerd Successfully", HttpStatus.OK);
			} else {
				return CafeUtils.getResponseEntity("Email Already exists", HttpStatus.BAD_REQUEST);
			}
		} else {
			return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
	}

	private boolean validateSignUpMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
				&& requestMap.containsKey("password")) {
			return true;
		} else
			return false;
	}

	private User getUserFromMap(Map<String, String> requestMap) {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		return user;

	}

}
