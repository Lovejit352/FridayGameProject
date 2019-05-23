package com.fdmgroup.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.businessLogic.TeamBusinessLogic;
import com.fdmgroup.model.Team;
import com.fdmgroup.model.User;

@Controller
@SessionAttributes(value = {"sessionUser"}, types = {User.class})
public class TeamController {


	@Autowired
	private TeamBusinessLogic teamB;
	
	
	
	
	@RequestMapping(value = "/getAllTeamsByGameId", method = RequestMethod.POST)
	public String  getAllTeams(Model model, @RequestParam("gameId") int gameId) {
		List<Team> teamList = null;

		teamList = teamB.getAllTeamsByGameId(gameId);
		model.addAttribute("result",teamList);	
		
		return "index";
	}
	
	@RequestMapping(value = "/addTeamMember", method = RequestMethod.POST)
	public String  addUser(Model model,HttpSession session,@RequestParam("gameId") int gameId, 
			@RequestParam("teamId") int addToTeamId) {

		User sessionUser = (User) session.getAttribute("sessionUser");
		
		teamB.addTeamMember(gameId, sessionUser, addToTeamId);
//		model.addAttribute("result",addToTeam);
		
		return "redirect:/dash-trainee";
	
	}
	
	
}