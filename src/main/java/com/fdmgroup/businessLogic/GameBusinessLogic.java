package com.fdmgroup.businessLogic;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fdmgroup.model.ActiveStatus;
import com.fdmgroup.model.Game;
import com.fdmgroup.model.GameStatus;
import com.fdmgroup.model.Team;
import com.fdmgroup.model.User;
import com.fdmgroup.repository.GameRepository;
import com.fdmgroup.repository.UserRepository;

public class GameBusinessLogic {
	
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private UserRepository userRepo;
                
	@Autowired
	private Logger logger;
	
	public Game createGame(User sessionUser, String gameName, String gameDesc, int numOfTeams, int playersPerTeam) {

		Game game = null;
		String errMsg = null;
		
		if(gameName == null || gameName.trim().length() < 3)
			errMsg = "Invalid game name ";
		else if(gameDesc == null || gameDesc.trim().length() < 3)
                errMsg = "Invalid gameDesc";
		else if(numOfTeams <= 0 )
                errMsg = "Invalid numOfTeams";
		else if(playersPerTeam <= 0 )
                errMsg = "Invalid playersPerTeam";

		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}

		game= new Game(0, gameName, gameDesc, 0, 0, numOfTeams, playersPerTeam, GameStatus.CREATED, 
						ActiveStatus.ACTIVE, sessionUser,  new Date(), new Date(), null,null,null);

		Set<Team> teams = new HashSet<Team>();
		
		for(int i=0; i< numOfTeams; i++) {
			teams.add(new Team(0, game, null));
		}
		
		game.setTeams(teams);

		game = gameRepo.saveAndFlush(game);
		
		if(game !=null) 
			logger.info("Game created, id: "+game.getGameId());
		
		return game;
		               
	}

	public Game updateGame(int gameId, String gameName, String gameDesc, int numOfTeams, int playersPerTeam 
			, int addHelperId, int removeHelperId) {
                
		Game game = null;        
		Game databaseGame = gameRepo.findById(gameId).get();

//		User sessionUser
//		databaseGame.setCreatedBy(sessionUser);
//		databaseGame.setCreatedOn(new Date());
		databaseGame.setGameDesc(gameDesc);
		databaseGame.setGameName(gameName);
		databaseGame.setGameStatus(GameStatus.CREATED);
		databaseGame.setIsActive(ActiveStatus.ACTIVE);
		databaseGame.setCreatedOn(new Date());
		databaseGame.setNumOfTeams(numOfTeams);
		databaseGame.setPlayersPerTeam(playersPerTeam);
		databaseGame.setRating(0);
		databaseGame.setVotes(0);
		
		if(addHelperId > 0) {
			User helper = userRepo.findById(addHelperId).get();
			Set<User> helpers = databaseGame.getHelpers();
			if(helpers == null) {
				helpers = new HashSet<User>();
			}
			helpers.add(helper);
		}

		if(removeHelperId > 0) {
			User helper = userRepo.findById(removeHelperId).get();
			Set<User> helpers = databaseGame.getHelpers();
			if(helpers != null) {
				helpers.remove(helper);
			}
		}
		
		
		game = gameRepo.save(databaseGame);
		if(game !=null) 
			logger.info("Game updated, id: "+game.getGameId());

		return game;
		
	}

	public boolean deleteGame(int gameId) {
		
		Game game = null;
		String errMsg = null;
		
		if(gameId  <1)
			errMsg = "Invalid gameId";
		
		game = gameRepo.findById(gameId).get();
		
		if(game.getGameStatus() != GameStatus.CREATED)
			errMsg = "Cannot delete this game";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		gameRepo.deleteById(gameId);
		gameRepo.flush();
		
		return true;
	}

	public Game getGameById(int gameId) {
		
		Game game = null;
		String errMsg = null;
		
	    if(gameId  <1)
	    	errMsg = "Invalid gameId";
	    
	    if(errMsg != null) {
	    	throw new IllegalArgumentException(errMsg);
		}
	    
		game = gameRepo.findById(gameId).get();
		
		System.out.println("-----------" + game);
		
		return game;
		
	}

	public Game getGameFetch(int gameId) {
		
		Game game = null;
		String errMsg = null;
		
		if(gameId  <1)
			errMsg = "Invalid gameId";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		try {
			game = gameRepo.findById(gameId).get();
		}
		catch(Exception e) {
			game = null;	
			return game;
		}
		return game;
	}

	public Game addHelper(int gameId, int userId) {
		
		Game game = null;
		User helper = null;
		String errMsg = null;
		
		if(gameId  <1)
			errMsg = "Invalid gameId";
		else if(userId < 1)
			errMsg = "Invalid userId";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		game = gameRepo.findById(gameId).get();
		helper = userRepo.findById(userId).get();
		
		Set<User> helpers = game.getHelpers();
		
		if(helpers == null) {
			helpers = new HashSet<User>();
		}
		
		helpers.add(helper);
		
//		game = gameRepo.saveAndFlush(game);
		
		return game;
	                
	}
	public Game removeHelper(int gameId, int userId) {
		
		Game game = null;
		User helper = null;
		String errMsg = null;
		
		if(gameId  <1)
			errMsg = "Invalid gameId";
		else if(userId < 1)
			errMsg = "Invalid userId";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		game = gameRepo.findById(gameId).get();
		helper = userRepo.findById(userId).get();
		
		Set<User> helpers = game.getHelpers();
		
		if(helpers == null) {
			helpers = new HashSet<User>();
		}
		
		helpers.remove(helper);
		
//		game = gameRepo.saveAndFlush(game);
		
		return game;
	                
	}
            
	public List<Game> getAllSubmittedGames(){
		return gameRepo.findAllSubmittedGame();
	}
	
	public Page<Game> getAllCompletedGames(Pageable pageable){
	
		return gameRepo.findAllCompletedGames(pageable);
	}	
              
	 public List<Game>allPastGame(){
     	return gameRepo.findAllPastGame();
     }
	 
	 public Game getSelectedGame() {
		 return gameRepo.findSelectedGame();
	 }
	
	public List<Game> getGameMakerGames(int userId){
		return gameRepo.findGameManagerGamesByUserId(userId);
	}

	public Game changeGameToSubmitted(int gameId) {
		Game game = null;
		String errMsg = null;
		
		if(gameId  <1)
			errMsg = "Invalid gameId";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		game = gameRepo.findById(gameId).get();
		game.setGameStatus(GameStatus.SUBMITTED);
		gameRepo.saveAndFlush(game);
		
		return game;

		
	}
	
	public void addVote(int gameId, int userId) {
		
		Game game = gameRepo.findById(gameId).get();
		User user = userRepo.findById(userId).get();
		
		if(user.getGamesvoted().size() > 0) {
			user.getGamesvoted().clear();
			user.getGamesvoted().add(game);
		}
		else if (user.getGamesvoted().size() == 0) {
			user.getGamesvoted().add(game);
			game.setVotes(game.getVotes()+1);
		}
		
		gameRepo.saveAndFlush(game);
		userRepo.saveAndFlush(user);
		
	}

	public void removeVote(int gameId, int userId) {
		Game game = gameRepo.findById(gameId).get();
		User user = userRepo.findById(userId).get();
		user.setGamesvoted(new HashSet<Game>());
		game.setVotes(game.getVotes()-1);
		gameRepo.saveAndFlush(game);
		userRepo.saveAndFlush(user);
		
	}
	public void addLike(int gameId, int userId){
		Game game = null;
		User liker = null;
		String errMsg = null;
		
		if(gameId  <1)
			errMsg = "Invalid gameId";
		else if(userId < 1)
			errMsg = "Invalid userId";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		game = gameRepo.findById(gameId).get();
		liker = userRepo.findById(userId).get();
		
		Set<User> likers = game.getLikes();
		
		if(likers == null ) {
			likers = new HashSet<User>();
		}
		if(!likers.contains(liker) && !game.getHelpers().contains(liker) && !game.getWinners().contains(liker)) {
			likers.add(liker);
			game.setRating(game.getRating() + 1);
		}
		gameRepo.saveAndFlush(game);
	} 
                
	public void closeVoting() {
		Game maxVotedGame = gameRepo.maxVotedGame();
		maxVotedGame.setGameStatus(GameStatus.SELECTED);
		gameRepo.saveAndFlush(maxVotedGame);
		gameRepo.clearVotingTable();
	}

	public void removeLike(int gameId, int userId) {
		Game game = null;
		User liker = null;
		String errMsg = null;
		
		if(gameId  <1)
			errMsg = "Invalid gameId";
		else if(userId < 1)
			errMsg = "Invalid userId";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		game = gameRepo.findById(gameId).get();
		liker = userRepo.findById(userId).get();
		
		Set<User> likers = game.getLikes();
		
		if(likers == null) {
			likers = new HashSet<User>();
		}
		if(likers.contains(liker)) {
			likers.remove(liker);
			game.setRating(game.getRating() - 1);
		}

		gameRepo.saveAndFlush(game);		
	}
	                
}