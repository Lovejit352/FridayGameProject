package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fdmgroup.businessLogic.GameBusinessLogic;
import com.fdmgroup.businessLogic.TeamBusinessLogic;
import com.fdmgroup.businessLogic.UserBusinessLogic;
import com.fdmgroup.model.Game;
import com.fdmgroup.model.Team;
import com.fdmgroup.model.User;
import com.fdmgroup.model.UserType;

@Controller
public class UserController {
	
	@Autowired
	private UserBusinessLogic userLogic;
	@Autowired
	private GameBusinessLogic gameBusinessLogic;
	@Autowired
	private TeamBusinessLogic teamusinessLogic;

	@RequestMapping("/usercreate")
	public String createUser(HttpServletRequest request, Model model) {

		String status = userLogic.validateUserCreateInput(request.getParameter("inputFullName"), 
				   										  request.getParameter("inputPassword"), 
				   										  request.getParameter("inputEmail"), 
				   										  "TRAINEE");//request.getParameter("userType"));
		if (status != null) {
			model.addAttribute("errorMessage", status);
			return "index";
		}
		
		if(userLogic.createUser( 
								  request.getParameter("inputFullName"), 
								  request.getParameter("inputPassword"), 
								  request.getParameter("inputEmail"), 
								  "TRAINEE"//request.getParameter("userType")
		) != null) {
			model.addAttribute("successMessage", "Registration Complete");
		}
		else {
			model.addAttribute("errorMessage", "Registration Failed");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/userupdate")
	public String updateUser(@SessionAttribute("sessionUser") User sessionUser, HttpServletRequest request, Model model) {
		
		String status = userLogic.validateUserUpdateInput(request.getParameter("fullname"), 
														  request.getParameter("userType"));
		if (status != null) {
			model.addAttribute("errorMessage", status);
			return "index";
		}
		
		if(userLogic.updateUser(sessionUser.getUserId(),
								request.getParameter("fullname"),
								sessionUser.getEmailAddress(),
								request.getParameter("userType")
		) != null) {
			model.addAttribute("successMessage", "Update Complete");
		}
		else {
			model.addAttribute("errorMessage", "Update Failed");
		}
		
		return "index";
	}
	
	@RequestMapping("/userchangename")
	public String changeName(@SessionAttribute("sessionUser") User sessionUser, HttpServletRequest request, Model model) {
		
		String status = userLogic.validateUserUpdateInput(request.getParameter("fullname"), 
														  sessionUser.getUserType().toString());
		if (status != null) {
			model.addAttribute("errorMessage", status);
			return "redirect:/";
		}
		
		if(userLogic.updateUser(sessionUser.getUserId(),
								request.getParameter("fullname"),
								sessionUser.getEmailAddress(),
								sessionUser.getUserType().toString()
		) != null) {
			model.addAttribute("successMessage", "Update Complete");
			sessionUser.setFullName(request.getParameter("fullname"));
		}
		else {
			model.addAttribute("errorMessage", "Update Failed");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/changepassword")
	public String changePassword(@SessionAttribute("sessionUser") User sessionUser, HttpServletRequest request, Model model) {
	
		if(!request.getParameter("newPassword").equals(request.getParameter("confirmNewPassword"))) {
			model.addAttribute("errorMessage", "Passwords do not match");
			return "redirect:/";
		}
		
		String status = userLogic.validateUserPasswordInput(sessionUser.getPassword(), 
															request.getParameter("currentPassword"), 
															request.getParameter("newPassword"));	
		if (status != null) {
			model.addAttribute("errorMessage", status);
			return "redirect:/";
		}
		
		if(userLogic.changePassword(sessionUser, request.getParameter("newPassword"))) {
			model.addAttribute("successMessage", "Password Changed");
		}
		else {
			model.addAttribute("errorMessage", "Password could not be changed");
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/getUserById")
	public String getUserById(HttpServletRequest request, Model model) {
		
		int id = Integer.parseInt(request.getParameter("userId"));
		
		User user = userLogic.getUserById(id);
		
		if(user == null) {
			model.addAttribute("errorMessage", "User Id is invalid");
			
			return "index";
		}
		
		model.addAttribute("result", user.getFullName());
		
		return "index";
	}
	
	@RequestMapping("/getAllUsers")
	public String getAllUsers(Model model) {
		
		model.addAttribute("result", userLogic.getAllUsers().get(0).getFullName());
		
		return "index";
	}
	
	@RequestMapping("/dash-admin")
	public String adminPage(Model model, @SessionAttribute("sessionUser") User sessionUser) {
		
		if(sessionUser == null || sessionUser.getUserType() != UserType.ADMIN) {
			return "redirect:/";
		}
		
		return  getUsersOnPage(model, sessionUser, 0);
	}
	
	@RequestMapping("/dash-admin-{page_id}")
	public String getUsersOnPage(Model model, @SessionAttribute("sessionUser") User sessionUser, @PathVariable int page_id) {
		
		if(sessionUser == null || sessionUser.getUserType() != UserType.ADMIN) {
			return "redirect:/";
		}
		List<Game> pastGames = gameBusinessLogic.allPastGame();
		Game lastGame = null;
		if (pastGames != null && pastGames.size() > 0) {
			lastGame = pastGames.get(0);
		}
		
		Game thisGame = gameBusinessLogic.getSelectedGame(); 
		List<Team> teamList = null;
		if(thisGame != null ) {
			teamList = teamusinessLogic.getAllTeamsByGameId(thisGame.getGameId());
			model.addAttribute("teams",teamList);	
		}
		
		model.addAttribute("thisGame", thisGame);
		model.addAttribute("lastGame", lastGame);
		
		
		Pageable pageable = PageRequest.of(page_id, 5);
 
		Page<User> users =  userLogic.getPageUsers(pageable);
		
		model.addAttribute("users", users.getContent());

		model.addAttribute("pages", users.getTotalPages());
		
		model.addAttribute("page_id", page_id);
		
		return "dash-admin";
	}
	
	@RequestMapping("/getUserByEmailAddress")
	public String getUserByEmailAddress(HttpServletRequest request, Model model) {
		
		model.addAttribute("result", userLogic.getUserByEmailAddress(request.getParameter("emailAddress")).getFullName());
		
		return "index";
	}
	
	@RequestMapping("/adminedituser-{page_id}")
	public String adminEditUser(Model model, @SessionAttribute("sessionUser") User sessionUser, HttpServletRequest request, @PathVariable int page_id) {

		String name = request.getParameter("fullname");
		String email = request.getParameter("email");
		String usertype = request.getParameter("usertype");
		int userId = Integer.parseInt(request.getParameter("edituserid"));
		
		userLogic.updateUser(userId, name, email, usertype.toUpperCase());
		
		return getUsersOnPage(model, sessionUser, page_id);
	}
	
	@RequestMapping("searchusers")
	public String searchUsers(Model model, @SessionAttribute("sessionUser") User sessionUser, @RequestParam String searchName, @RequestParam String searchEmail) {
		
		if(sessionUser == null || sessionUser.getUserType() != UserType.ADMIN) {
			return "redirect:/";
		}
		
		if(searchName.isEmpty() && searchEmail.isEmpty()) {
			return getUsersOnPage(model, sessionUser, 0);
		}
		else if(searchName.isEmpty() && !searchEmail.isEmpty()) {
			
			User user = userLogic.getUserByEmailAddress(searchEmail);
			
			if(user == null) {
				return getUsersOnPage(model, sessionUser, 0);
			}
			else {
				List<User> users = new ArrayList<>();
				users.add(user);
				model.addAttribute("users", users);
				model.addAttribute("pages", 0);
				model.addAttribute("page_id", 0);
				return "dash-admin";
			}
			
		}
		else if(!searchName.isEmpty() && searchEmail.isEmpty()) {
			model.addAttribute("users", userLogic.getUserByFullName(searchName));
			model.addAttribute("pages", 0);
			model.addAttribute("page_id", 0);
			return "dash-admin";
		}
		
		return getUsersOnPage(model, sessionUser, 0);
	}

}
