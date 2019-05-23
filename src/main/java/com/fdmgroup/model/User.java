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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USERS")
public class User implements IStorable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "userId_seq")
	@SequenceGenerator(sequenceName="userId_seq", allocationSize=1, initialValue=100001, name="userId_seq")
	private int userId;
	
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false , unique = true)
	private String emailAddress;
	@Column(nullable = false)
	private int points;
	
	@Enumerated(EnumType.ORDINAL)
	private UserType userType;
	@Enumerated(EnumType.ORDINAL)
	private ActiveStatus isActive;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL) 
	@JoinTable(name = "game_votes"
		,joinColumns = @JoinColumn(name="userId")
		,inverseJoinColumns = @JoinColumn(name="gameId")
	)
	private Set<Game> gamesvoted;
	
	public Set<Game> getGamesvoted() {
		return gamesvoted;
	}

	public void setGamesvoted(Set<Game> gamesvoted) {
		this.gamesvoted = gamesvoted;
	}

	@Override
	public int hashCode() {
		return 31*this.userId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return (this.userId == ((User) obj).userId);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public ActiveStatus getIsActive() {
		return isActive;
	}

	public void setIsActive(ActiveStatus isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", emailAddress="
				+ emailAddress + ", points=" + points + ", userType=" + userType + ", isActive=" + isActive + "]";
	}

	public User(int userId, String fullName, String password, String emailAddress, int points, UserType userType,
			ActiveStatus isActive) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.points = points;
		this.userType = userType;
		this.isActive = isActive;
	}

	public User() {
		super();
	}
	
	
	
}
