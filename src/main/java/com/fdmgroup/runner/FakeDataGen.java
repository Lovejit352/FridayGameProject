package com.fdmgroup.runner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.model.ActiveStatus;
import com.fdmgroup.model.Comment;
import com.fdmgroup.model.Game;
import com.fdmgroup.model.GameStatus;
import com.fdmgroup.model.Team;
import com.fdmgroup.model.User;
import com.fdmgroup.model.UserType;

public class FakeDataGen {
	private static final EntityManager em = Persistence.createEntityManagerFactory("jpaPU").createEntityManager();
	
	public static void main(String[] args) {
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		em.getTransaction().begin();
		
		User newUser = null;
		String[] userNames = null;
		List<User> traineeList = new ArrayList<User>();
		List<User> gameManagerList = new ArrayList<User>();
		
		System.out.println("Creating ADMIN");		
		newUser = new User(0, "Site Admin", passwordEncoder.encode("password1"),"admin@fdmgroup.com", 
				0, UserType.ADMIN, ActiveStatus.ACTIVE);
		em.persist(newUser);
		
		System.out.println("Creating TRAINEES");
		userNames = "Jack Peterson,Albert Mitchell,Phillip Kelly,Aaron White,Chris Gonzales,Robert Cooper".split(",");
		for(String fullname : userNames) {
			String[] names = fullname.split(" ");
			newUser = new User(0, fullname, passwordEncoder.encode("password1"), names[0].toLowerCase()+"."+names[1].toLowerCase()+"@fdmgroup.com", 
					0, UserType.TRAINEE, ActiveStatus.ACTIVE);
			
			em.persist(newUser);
			traineeList.add(newUser);
		}
		em.flush();
		System.out.println("TRAINEES created");
		
		System.out.println("Creating GAMEMANAGERS");
		userNames = "Harry Garcia,Jonathan Thomas,Gerald Perez,Todd Simmons,Thomas Rivera".split(",");
		for(String fullname : userNames) {
			String[] names = fullname.split(" ");
			newUser = new User(0, fullname, passwordEncoder.encode("password1"), names[0].toLowerCase()+"."+names[1].toLowerCase()+"@fdmgroup.com", 
					0, UserType.GAMEMANAGER, ActiveStatus.ACTIVE);
			
			em.persist(newUser);
			gameManagerList.add(newUser);
		}
		em.flush();
		System.out.println("GAMEMANAGERS created");
		
		
		
		System.out.println("Creating game");
		
		Set<Team> teamList = new HashSet<Team>();
		Set<User> helpers = new HashSet<User>();
		Game game = null;
		
		game = new Game(0, "gameName", "gameDesc soooooooooommmmmmmmmmthing llllllllloooooonnnnnggggggggg goes here"
				, 0, 0, 3, 2, GameStatus.PLAYED, ActiveStatus.ACTIVE, 
				gameManagerList.get(0), new Date(), new Date(), teamList, helpers, null);
		
		for(int i=0; i<6; i+=2) {
			Team team = new Team(0, game, new HashSet<User>());
			team.getTeamMembers().add(traineeList.get(i));
			team.getTeamMembers().add(traineeList.get(i+1));
			teamList.add(team);
		}
		
		for(User user : gameManagerList) {
			helpers.add(user);
		}
		
		em.persist(game);
		em.flush();
		System.out.println("game created");
		
		
		System.out.println("Creating comments");
		for (User trainee : traineeList) {
			Comment comment = null;
			comment = new Comment(0, game, trainee, comment, trainee.getFullName()+" has commented on this game", new Date());
			em.persist(comment);
			em.flush();
			comment = new Comment(0, game, trainee, comment, trainee.getFullName()+" has commented on comment", new Date());
			em.persist(comment);
			em.flush();
			
		}
		System.out.println("Comments created");
		System.out.println("All done");
		
		for(int i=0; i<10; i++) {
			game = new Game(0, "gameName"+i, "Game description goes here"+i
					, 0, 0, 3, 2, GameStatus.PLAYED, ActiveStatus.ACTIVE, 
					gameManagerList.get(0), new Date(), new Date(), teamList, helpers, null);
			em.persist(game);
			em.flush();
		}
		System.out.println("Created dummy games");
		
		
		em.getTransaction().commit();

		em.close();
	
	}
}
