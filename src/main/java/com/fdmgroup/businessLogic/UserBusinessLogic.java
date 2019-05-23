package com.fdmgroup.businessLogic;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.model.ActiveStatus;
import com.fdmgroup.model.User;
import com.fdmgroup.model.UserType;
import com.fdmgroup.repository.UserRepository;

public class UserBusinessLogic {
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private UserRepository userRepository;
	
	public String validateUserCreateInput(String fullname, String password, String emailAddress, String userType) {
		
		if(fullname == null || fullname.trim().length() < 1) {
			return "Full name should be at least 1 character";
		}
		
		if(!fullname.matches("[A-Za-z -]+$")) {
			return "Invalid Name";
		}
		
		if(password == null || password.trim().length() < 6) {
			return "Password should be at least 6 characters";
		}
		
		if(emailAddress.trim().length() > 1 && !emailAddress.matches("[A-Za-z0-9]+\\.[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z]+$")) {
			return "Invalid email address";
		}
		
		if(userType == null || userType.trim().length() < 2) {
			return "User type is invalid";
		}
		
		if(getUserByEmailAddress(emailAddress) != null) {
			return "Email address is not unique";
		}
		
		try { 
			Enum.valueOf(UserType.class, userType);
		}catch(IllegalArgumentException e ) {
			return "User type is invalid";
		}

		return null;
	}
	
	public User createUser(String fullname, String password, String emailAddress, String userType) {
		
		User user = new User();
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		try {
		
			user.setFullName(fullname);
			user.setPassword(passwordEncoder.encode(password));
			user.setEmailAddress(emailAddress);
			user.setPoints(0);
			user.setUserType(UserType.valueOf(userType));
			user.setIsActive(ActiveStatus.ACTIVE);
		
			user = userRepository.saveAndFlush(user);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return user;
	}
	
	public String validateUserUpdateInput(String fullname, String userType) {
		
		if(fullname.trim().length() < 1) {
			return "Full name should be at least 1 character";
		}
		
		if(!fullname.matches("[A-Za-z -]+$")) {
			return "Invalid Name";
		}
		
		if(userType.trim().length() < 2) {
			return "User type is invalid";
		}
		
		try { 
			Enum.valueOf(UserType.class, userType);
		}catch(IllegalArgumentException e ) {
			return "User type is invalid";
		}

		return null;
	}
	
	public User updateUser(int userId, String fullname, String email, String userType) {
		
		User databaseUser = userRepository.findById(userId).get();
		
		if(databaseUser == null) {
			return null;
		}
		
		databaseUser.setFullName(fullname);	
		databaseUser.setEmailAddress(email);	
		databaseUser.setUserType(UserType.valueOf(userType));
		
		return userRepository.save(databaseUser);
	}
	
	public String validateUserPasswordInput(String currentPassword, String oldPassword, String newPassword) {
		
		if(newPassword == null || newPassword.trim().length() < 6) {
			return "Current Password should be at least 6 characters";
		}		
		
		if(oldPassword == null || oldPassword.trim().length() < 6 ) {
			return "New password should be at least 6 characters";
		}
		
		if(newPassword.equals(oldPassword)) {
			return "New password cannot be the same as current password";
		}
		
		if(!oldPassword.equals(currentPassword)) {
			return "Incorrect current password";
		}
		
		return null;
		
	}
	
	public boolean changePassword(User sessionUser, String password) {

		try {
			sessionUser.setPassword(password);
			
			User databaseUser = userRepository.findById(sessionUser.getUserId()).get();
			
			databaseUser.setPassword(password);
			
			User returnedUser = userRepository.save(databaseUser);
			
			if(returnedUser !=null && returnedUser.getPassword().equals(password) ) {
				logger.info("Password changed for user : " + returnedUser.getUserId());
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean deleteUser(User user) {
		
		try{
			userRepository.delete(user);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
		
	}
	
	public User getUserById(int userId){
		try {
			return userRepository.findById(userId).get();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getAllUsers() {
		return  userRepository.findAll();
	}	
	
	public Page<User> getPageUsers(Pageable pageable) {
		return  userRepository.findAll(pageable);
	}	

	public User getUserByEmailAddress(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress);
	}
	
	public List<User> getUserByFullName(String fullname) {
		return userRepository.findByFullName(fullname);
	}
	
}
