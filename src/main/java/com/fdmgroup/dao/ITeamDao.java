package com.fdmgroup.dao;

import java.util.List;

import com.fdmgroup.model.Team;
import com.fdmgroup.model.User;


public interface ITeamDao extends ICreatable<Team>, IReadable<Team>, IUpdatable<Team>{
		
	
	public List<User> getPlayersInTeamByTeamId();
	public Team getTeamByGameId(int id);
	public Team getTeamByName(String name);
		
}

