package com.fdmgroup.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TEAMS")
public class Team implements IStorable{

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "teamId_seq")
	@SequenceGenerator(sequenceName="teamId_seq", allocationSize=1, initialValue=100001, name="teamId_seq")
	private int teamId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name= "gameId" , nullable=false)
	private Game game;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "TeamMembers"
		,joinColumns = @JoinColumn(name="teamId")
		,inverseJoinColumns = @JoinColumn(name="userId")
	)
	private Set<User> teamMembers;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Set<User> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(Set<User> teamMembers) {
		this.teamMembers = teamMembers;
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", game=" + game.getGameName() + ", teamMembers=" + teamMembers.toString() + "]";
	}

	public Team(int teamId, Game game, Set<User> teamMembers) {
		super();
		this.teamId = teamId;
		this.game = game;
		this.teamMembers = teamMembers;
	}

	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
