package com.fdmgroup.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.businessLogic.GameBusinessLogic;
import com.fdmgroup.model.Game;
import com.fdmgroup.model.User;
import com.fdmgroup.model.UserType;

@Controller
public class AuthenticationController {
	
	@Autowired
	private AuthenticationBusinessLogic authLogic;
	
	@Autowired
    private GameBusinessLogic gameLogic;
	
	@Autowired
	private Logger logger;
	
	@RequestMapping(value = {"/"})
	public String showLogin(Model model, HttpSession session) {
		User sessionUser = (User) session.getAttribute("sessionUser");
		if(sessionUser!=null && sessionUser.getUserType() == UserType.GAMEMANAGER) {
			return "redirect:/dashboardGameMaker";
		} else if(sessionUser!=null && sessionUser.getUserType() == UserType.TRAINEE) {
			return "redirect:/dash-trainee";
		} else if(sessionUser!=null && sessionUser.getUserType() == UserType.ADMIN) {
			return "redirect:/dash-admin";
		}
		List<Game> submittedGames = gameLogic.getAllSubmittedGames();
		Pageable pageable = PageRequest.of(0, 3);
		List<Game> pastGames =  gameLogic.getAllCompletedGames(pageable).getContent();
		logger.info("submittedGames " + submittedGames.size());
		logger.info("pastGames " + pastGames.size());
 		
		model.addAttribute("submittedGames", submittedGames);
		model.addAttribute("pastGames", pastGames);
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(
			Model model 
			,@RequestParam("emailAddress") String emailAddress
			,@RequestParam("password") String password
			,HttpServletRequest request
			,HttpSession session) {
		logger.info("Trying to log user: " + emailAddress);
		User user = null;
		try{
			user = authLogic.login(emailAddress, password);
//			logger.info("got user: " + user);
			session.setAttribute("sessionUser", user);
//			session.setAttribute("userLoggedIn", "True");
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, SessionStatus sessionStatus) {
		session.setAttribute("sessionUser", null);
		session.invalidate();
		sessionStatus.setComplete();
		return "redirect:/";
	}
		
}























