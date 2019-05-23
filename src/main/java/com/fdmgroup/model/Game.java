package com.fdmgroup.model;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="GAMES")
public class Game implements IStorable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "gameId_seq")
	@SequenceGenerator(sequenceName="gameId_seq", allocationSize=1, initialValue=100001, name="gameId_seq")
	private int gameId;
	
	@Column(nullable=false)
	private String gameName;
	@Column(nullable=false)
	private String gameDesc;
	
	@Column
	private int votes;
	@Column
	private int rating;
	@Column(nullable=false)
	private int numOfTeams;
	@Column(nullable=false)
	private int playersPerTeam;
	
	@Enumerated(EnumType.ORDINAL)
	private GameStatus gameStatus;
	@Enumerated(EnumType.ORDINAL)
	private ActiveStatus isActive;
	
	@ManyToOne
	@JoinColumn(name= "createdBy")
	private User createdBy; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date playedOn;
	
	@OneToMany(
			 mappedBy = "game"
			,fetch=FetchType.EAGER
			,cascade=CascadeType.ALL
			,orphanRemoval=true)
	Set<Team> teams;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL) 
	@JoinTable(name = "helpers"
		,joinColumns = @JoinColumn(name="gameId")
		,inverseJoinColumns = @JoinColumn(name="userId")
	)
	private Set<User> helpers;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL) 
	@JoinTable(name = "winners"
		,joinColumns = @JoinColumn(name="gameId")
		,inverseJoinColumns = @JoinColumn(name="userId")
	)
	private Set<User> Winners;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL) 
	@JoinTable(name = "game_likes"
		,joinColumns = @JoinColumn(name="gameId")
		,inverseJoinColumns = @JoinColumn(name="userId")
	)
	private Set<User> Likes;
	
	@Override
	public int hashCode() {
		return 31*this.gameId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return (this.gameId == ((Game) obj).gameId);
	}
	
	public Set<User> getLikes() {
		return Likes;
	}
	public void setLikes(Set<User> likes) {
		Likes = likes;
	}
//	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL) 
//	@JoinTable(name = "game_votes"
//		,joinColumns = @JoinColumn(name="gameId")
//		,inverseJoinColumns = @JoinColumn(name="gameId")
//	)
//	private Set<User> voters;
	
//	public Set<User> getVoters() {
//		return voters;
//	}
//	public void setVoters(Set<User> voters) {
//		this.voters = voters;
//	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameDesc() {
		return gameDesc;
	}
	public void setGameDesc(String gameDesc) {
		this.gameDesc = gameDesc;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getNumOfTeams() {
		return numOfTeams;
	}
	public void setNumOfTeams(int numOfTeams) {
		this.numOfTeams = numOfTeams;
	}
	public int getPlayersPerTeam() {
		return playersPerTeam;
	}
	public void setPlayersPerTeam(int playersPerTeam) {
		this.playersPerTeam = playersPerTeam;
	}
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	public ActiveStatus getIsActive() {
		return isActive;
	}
	public void setIsActive(ActiveStatus isActive) {
		this.isActive = isActive;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getPlayedOn() {
		return playedOn;
	}
	public void setPlayedOn(Date playedOn) {
		this.playedOn = playedOn;
	}
	public Set<Team> getTeams() {
		return teams;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	public Set<User> getHelpers() {
		return helpers;
	}
	public void setHelpers(Set<User> helpers) {
		this.helpers = helpers;
	}
	public Set<User> getWinners() {
		return Winners;
	}
	public void setWinners(Set<User> winners) {
		Winners = winners;
	}
	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", gameName=" + gameName + ", gameDesc=" + gameDesc + ", votes=" + votes
				+ ", rating=" + rating + ", numOfTeams=" + numOfTeams + ", playersPerTeam=" + playersPerTeam
				+ ", gameStatus=" + gameStatus + ", isActive=" + isActive + ", createdBy=" + createdBy.getFullName()
				+ ", lastModifiedBy=" + ", createdOn=" + createdOn + ", playedOn="
				+ playedOn + "]";
	}
	public Game(int gameId, String gameName, String gameDesc, int votes, int rating, int numOfTeams, int playersPerTeam,
			GameStatus gameStatus, ActiveStatus isActive, User createdBy, Date createdOn,
			Date playedOn, Set<Team> teams, Set<User> helpers, Set<User> winners) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
		this.gameDesc = gameDesc;
		this.votes = votes;
		this.rating = rating;
		this.numOfTeams = numOfTeams;
		this.playersPerTeam = playersPerTeam;
		this.gameStatus = gameStatus;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.playedOn = playedOn;
		this.teams = teams;
		this.helpers = helpers;
		Winners = winners;
	}
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}