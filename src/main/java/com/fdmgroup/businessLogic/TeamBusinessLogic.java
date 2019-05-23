package com.fdmgroup.businessLogic;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.model.Game;
import com.fdmgroup.model.Team;
import com.fdmgroup.model.User;
import com.fdmgroup.repository.GameRepository;
import com.fdmgroup.repository.TeamRepository;

public class TeamBusinessLogic {
	@Autowired
	private TeamRepository teamRepo;
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private Logger logger;
	
	public List<Team> getAllTeamsByGameId(int gameId) {
		List<Team> teamList = null;
		
		String errMsg = null;
		if(gameId  <1) 
			errMsg = "Invalid gameId";
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
	
		teamList = teamRepo.findAllById(gameId);
		
		return teamList;
	}
	
public Team addTeamMember(int gameId, User member, int addToTeamId) {
		
		Game dbGame = null;
		Team addToTeam = null;
		
		String errMsg = null;
		if(gameId <1) 
			errMsg = "Invalid game object";
		else if(member == null )
			errMsg = "Invalid member object";
		else if(addToTeamId < 2)
			errMsg = "Invalid team id";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		dbGame = gameRepo.findById(gameId).get();
		
		
		addToTeam = teamRepo.findById(addToTeamId).get();
		
		
		if(addToTeam.getTeamMembers().size() < dbGame.getNumOfTeams()) {
			addToTeam.getTeamMembers().add(member);
		} else {
			throw new IllegalArgumentException("Team is already full");
		}
		
		addToTeam = teamRepo.saveAndFlush(addToTeam);
		if(addToTeam !=null) 
			logger.info("Join the team, team id: " + addToTeam.getTeamId());
		return addToTeam;
	}
	
	
}
