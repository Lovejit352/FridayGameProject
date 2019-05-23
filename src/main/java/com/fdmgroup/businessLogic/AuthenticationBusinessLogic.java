package com.fdmgroup.businessLogic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.model.User;
import com.fdmgroup.repository.UserRepository;

public class AuthenticationBusinessLogic {
	
	@Autowired
//	private IUserDao userDao;
	private UserRepository userRepo;
	
	@Autowired
	private Logger logger;
	
	public User login(String emailAddress, String password){
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String errMsg = null;
		if (emailAddress == null || emailAddress.trim().length() < 1)
			errMsg = "Email address cannot be empty";
		else if (password == null || password.trim().length() < 1)
			errMsg = "Password cannot be empty";

		if (errMsg != null) {
			throw new IllegalArgumentException(errMsg);	
		}		
		
		User user = null; 
		user = userRepo.findByEmailAddress(emailAddress);
		
//		System.out.println("user "+user);
		if (user == null)
			errMsg = "User does not exist";
		else if (! passwordEncoder.matches(password, user.getPassword()))
			errMsg = "Incorrect Password";
		
		if (errMsg != null) {
			throw new IllegalArgumentException(errMsg);	
		}		
		logger.info("Sign in successful for user: " + emailAddress);
		
		//
		
		return user;
	}
//	public boolean logout() {
//		logger.info("Sign out successful for user: " );
//		return true;
//	}
}
