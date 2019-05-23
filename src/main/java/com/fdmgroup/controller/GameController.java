package com.fdmgroup.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.businessLogic.CommentBusinessLogic;
import com.fdmgroup.businessLogic.GameBusinessLogic;
import com.fdmgroup.businessLogic.TeamBusinessLogic;
import com.fdmgroup.businessLogic.UserBusinessLogic;
import com.fdmgroup.model.Comment;
import com.fdmgroup.model.Game;
import com.fdmgroup.model.GameStatus;
import com.fdmgroup.model.Team;
import com.fdmgroup.model.User;
import com.fdmgroup.model.UserType;
import com.fdmgroup.repository.GameRepository;

@Controller
@SessionAttributes(value = { "sessionUser" }, types = { User.class })
public class GameController {
                
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private GameBusinessLogic gameBusinessLogic;
	@Autowired
	private UserBusinessLogic userBusinessLogic;
	@Autowired
	private CommentBusinessLogic commentBusinessLogic;
	@Autowired
	private TeamBusinessLogic teamusinessLogic;
	@Autowired
	private Logger logger;

	@RequestMapping("/pastgames/{pageId}")
	public String viewPastGames(Model model, @PathVariable int pageId) {
		
		Pageable pageable = PageRequest.of(pageId, 5);
		Page<Game> gamePages = gameBusinessLogic.getAllCompletedGames(pageable);
		List<Game> gameList = gamePages.getContent(); 
		Game pastGame = null;
		List<Comment> pastGameComments = null ;
		if(gameList.size() > 0) {
			pastGame = gameList.get(0);
			pastGameComments = commentBusinessLogic.getAllCommentsByGameId(pastGame.getGameId());
        }
		model.addAttribute("pastGame", pastGame);
		model.addAttribute("games", gamePages.getContent());
		model.addAttribute("pages", gamePages.getTotalPages());
		model.addAttribute("page", pageId);
		model.addAttribute("comments", pastGameComments);
		return "pastGames1";
	}
	

	 @RequestMapping(value = "/pastgames/{pageId}/{gameId}", method = RequestMethod.GET)
     public String pastGameDesc(Model model, @PathVariable(value="gameId") int gameId,@PathVariable(value="pageId") int pageId) {
         
		Pageable pageable = PageRequest.of(pageId, 5);
		Page<Game> gamePages = gameBusinessLogic.getAllCompletedGames(pageable);
		List<Game> gameList = gamePages.getContent();
		Game pastGame = null;
		for (Game game : gameList) {
			if(game.getGameId() == gameId) {
				pastGame = game;
			}
		}
		 List<Comment> pastGameComments = commentBusinessLogic.getAllCommentsByGameId(pastGame.getGameId());
         model.addAttribute("pastGame", pastGame);
         model.addAttribute("games", gamePages.getContent());
         model.addAttribute("pages", gamePages.getTotalPages());
         model.addAttribute("comments", pastGameComments); 
         model.addAttribute("page", pageId);
         
         return "pastGames1";
     }
	 @RequestMapping(value = "/pastgames/{pageId}/{gameId}", method = RequestMethod.POST)
     public String processPastGameDesc(Model model
    		 ,HttpServletRequest request
    		 ,@PathVariable(value="gameId") int gameId
    		 ,@PathVariable(value="pageId") int pageId
    		 ,@RequestParam(value="actionType") String actionType
    		 ) {
         	
		 	User sessionUser = (User) request.getSession().getAttribute("sessionUser");
			
			if(actionType.equals("like")) {
				gameBusinessLogic.addLike(gameId, sessionUser.getUserId());
			} else if(actionType.equals("unlike")) {
				gameBusinessLogic.removeLike(gameId, sessionUser.getUserId());
					
			}

		 	if(actionType.equals("createComment")) {
				Game commentGame = gameBusinessLogic.getGameById(gameId);
				User commentUser = (User) request.getSession().getAttribute("sessionUser");
				Comment parentComment =null;
				String strComment = (String) request.getParameter("strComment");

				//TODO change the below logic for parent comment
//				if(parentCommentId !=null && parentCommentId.trim().length() >0 ) {
//					parentComment = commentLogic.getCommentById(Integer.parseInt(parentCommentId));
//				}
				
				commentBusinessLogic.createComment(commentGame, commentUser, parentComment, strComment);

		 	}
			
		 
		 	Pageable pageable = PageRequest.of(pageId, 5);
			Page<Game> gamePages = gameBusinessLogic.getAllCompletedGames(pageable);
			List<Game> gameList = gamePages.getContent();
			Game pastGame = null;
			for (Game game : gameList) {
				if(game.getGameId() == gameId) {
					pastGame = game;
				}
			}
			List<Comment> pastGameComments = commentBusinessLogic.getAllCommentsByGameId(pastGame.getGameId());
	         model.addAttribute("pastGame", pastGame);
	         model.addAttribute("games", gamePages.getContent());
	         model.addAttribute("pages", gamePages.getTotalPages());
	         model.addAttribute("comments", pastGameComments); 
	         model.addAttribute("page", pageId);
	         
	         return "pastGames1";
	 }

	@RequestMapping(value = "/dashboardGameMaker" , method = RequestMethod.GET)
	public String showDashboardGameMaker(
			HttpSession session
			,Model model
			) {
		
		User sessionUser = (User) session.getAttribute("sessionUser");
		if(sessionUser == null) {
			return "redirect:/";
		}
		List<Game> gameList = gameBusinessLogic.getGameMakerGames(sessionUser.getUserId());
		List<User> userList = userBusinessLogic.getAllUsers();
		model.addAttribute("gameList", gameList);
		model.addAttribute("userList", userList);
		return "dashboardGameMaker";
	}

	@RequestMapping(value = "/dashboardGameMaker" , method = RequestMethod.POST)
	public String processDashboardGameMaker(
			HttpSession session
			,Model model
			,HttpServletRequest request
			) {
		User sessionUser = (User) session.getAttribute("sessionUser");
		if(sessionUser.getUserType() == UserType.TRAINEE) {
			return "redirect:/";
		}
		
		String strGameId = request.getParameter("gameId");
		int gameId = -1;
		if(strGameId !=null && strGameId.trim().length() > 2) {
			gameId = Integer.parseInt(strGameId);
		}
		
		String actionType = request.getParameter("actionType");
		
		if( actionType!=null && actionType.equals("delete") ) {
			gameBusinessLogic.deleteGame(gameId);
			File file = new File(session.getServletContext().getRealPath("/") + "/resources/images/" + gameId);
			try {
				Files.delete(file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Picture Delete Error");
			}
		} else if( actionType!=null && actionType.equals("submit") ) {
			gameBusinessLogic.changeGameToSubmitted(gameId);
		} else  {
			
			String strNumOfTeams = request.getParameter("numOfTeams");
			int numOfTeams = -1;
			if(strNumOfTeams !=null && strNumOfTeams.trim().length() > 0 ) {
				numOfTeams = Integer.parseInt(strNumOfTeams);
			}
			String strPlayersPerTeam = request.getParameter("playersPerTeam");
			int playersPerTeam = -1;
			if(strPlayersPerTeam !=null && strPlayersPerTeam.trim().length() >0) {
				playersPerTeam = Integer.parseInt(strPlayersPerTeam);
			}
			String gameName = request.getParameter("gameName");
			String gameDesc = request.getParameter("gameDesc");
			String strRemoveHelper = request.getParameter("removeHelper");
			String strAddHelper = request.getParameter("addHelper");
			
			if(gameId > 1) {
				int addHelperId = -1;
				int removeHelperId = -1;
				
				if(strRemoveHelper.trim().length() >3 ) {
					removeHelperId  = Integer.parseInt(strRemoveHelper); 
				}
				if(strAddHelper.trim().length() >3 ) {
					addHelperId = Integer.parseInt(strAddHelper); 
				}
				gameBusinessLogic.updateGame(gameId, gameName, gameDesc, numOfTeams, playersPerTeam, addHelperId, removeHelperId);
				
			} else {
				//gameBusinessLogic.createGame(sessionUser, gameName, gameDesc, numOfTeams, playersPerTeam);					
			}
			
		}
		
		List<Game> gameList = gameBusinessLogic.getGameMakerGames(sessionUser.getUserId());
		List<User> userList = userBusinessLogic.getAllUsers();
		model.addAttribute("userList", userList);
		model.addAttribute("gameList", gameList);

		return "dashboardGameMaker";
	}
	
	@RequestMapping(value = "/createGame" ,  method = RequestMethod.POST) 
	public String createGame(HttpSession session, Model model, HttpServletRequest request, 
							 @RequestParam("gamePicture") MultipartFile gamePicture) {
		
		User sessionUser = (User) session.getAttribute("sessionUser");
		if(sessionUser.getUserType() == UserType.TRAINEE) {
			return "redirect:/";
		}
		
		String gameName = request.getParameter("gameName");
		String gameDesc = request.getParameter("gameDesc");
		
		String strNumOfTeams = request.getParameter("numOfTeams");
		int numOfTeams = -1;
		if(strNumOfTeams !=null && strNumOfTeams.trim().length() > 0 ) {
			numOfTeams = Integer.parseInt(strNumOfTeams);
		}
		String strPlayersPerTeam = request.getParameter("playersPerTeam");
		int playersPerTeam = -1;
		if(strPlayersPerTeam !=null && strPlayersPerTeam.trim().length() >0) {
			playersPerTeam = Integer.parseInt(strPlayersPerTeam);
		}
		
		Game game = gameBusinessLogic.createGame(sessionUser, gameName, gameDesc, numOfTeams, playersPerTeam);	
		
    	String path = session.getServletContext().getRealPath("/");  
    	
		File dir = new File(path + "/resources/images");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		System.out.println(dir.getAbsolutePath()+ File.separator + game.getGameId());
		
	    File serverFile = new File(dir.getAbsolutePath()+ File.separator + game.getGameId()); //you can change filename to gameId
	    
	    byte[] bytes;
		try {
			bytes = gamePicture.getBytes();
			
	        BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
		
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Picture Upload Error");
		}
                
		return "redirect:/dashboardGameMaker";
	}

	@RequestMapping(value = "/dash-trainee" , method = RequestMethod.GET)
	public String showDashboardTrainee(
			HttpSession session
			,Model model
			) {
		
		User sessionUser = (User) session.getAttribute("sessionUser");
		if(sessionUser == null) {
			return "redirect:/";
		}
		List<Game> pastGames = gameBusinessLogic.allPastGame();
		Game lastGame = null;
		if (pastGames != null) {
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
		return "dash-trainee";
	}
	
	
	@RequestMapping(value = "/dash-trainee",  method = RequestMethod.POST)
	public String processDashboardTrainee(Model model) {
		return "dash-trainee";
	}
	
	@RequestMapping("/closeGame") 
	public String closeGame(
			@RequestParam("gameId") String gameId
			,@RequestParam("teamId") String strTeamId
			
			) {
		
		Game game = gameBusinessLogic.getGameById(Integer.parseInt(gameId));
		int teamId = Integer.parseInt(strTeamId);
		Team winningTeam = null ;
		for (Team team : game.getTeams()) {
			if(team.getTeamId() == teamId) {
				winningTeam = team;
			}
		}
		
		game.setGameStatus(GameStatus.PLAYED);
		Set<User> winners = game.getWinners();
		if(winners == null) {
			winners = new HashSet<User>();
			game.setWinners(winners);
		}
		for (User user : winningTeam.getTeamMembers()) {
			winners.add(user);
		}
//		game.setWinners(winningTeam.getTeamMembers());
		gameRepo.saveAndFlush(game);
		
		return "redirect:/";
	}
	
//	@RequestMapping("/closeVoting") 
//	public String closeGame() {
//		
//		List<Game> games = gameBusinessLogic.getAllSubmittedGames();
//		
//		Game winningGame = games.get(0);
//		winningGame.setGameStatus(GameStatus.SELECTED);
//		gameRepo.saveAndFlush(winningGame);
//		
//		List<Game> losingGames = new ArrayList<>();
//		
//		for (int i = 1; i < games.size(); i++) {
//			if(games.get(i).getVotes() > winningGame.getVotes()) {
//				winningGame = games.get(i);
//			}
////			games.get(i).getVoters().clear();
//		}
//		
//		for (int i = 0; i < games.size(); i++) {
//			if(games.get(i).getGameId() != winningGame.getGameId()) {
//				losingGames.add(games.get(i));
//			}
//		}
//		
//		
//		for (Game game : losingGames) {
//			game.setGameStatus(GameStatus.CREATED);
//			gameRepo.saveAndFlush(game);
//		}
//		
//		return "redirect:/";
//	}
	@RequestMapping( value="/votingList", method=RequestMethod.GET) 
	public String showVotingList(Model model) {
		List<Game> gameList = gameBusinessLogic.getAllSubmittedGames();
		model.addAttribute("gameList", gameList);
		return "votingList";
	}
	@RequestMapping( value="/votingList", method=RequestMethod.POST) 
	public String processVotingList(
			HttpSession session
			,Model model
			,@RequestParam("gameId") int gameId
			,@RequestParam("action") String actionType
			) {
		User sessionUser = (User) session.getAttribute("sessionUser");
		if(sessionUser == null || sessionUser.getUserId() < 100) {
			return "redirect:/";
		}
		if(actionType.equals("vote")) {
			gameBusinessLogic.addVote(gameId, sessionUser.getUserId());
		} else if(actionType.equals("unvote")) {
			gameBusinessLogic.removeVote(gameId, sessionUser.getUserId());
		}
		//refresh the sessionUSer object
		session.setAttribute("sessionUser", null);
		session.setAttribute("sessionUser", userBusinessLogic.getUserById(sessionUser.getUserId()));
		model.addAttribute("sessionUser", userBusinessLogic.getUserById(sessionUser.getUserId()));
		
		List<Game> gameList = gameBusinessLogic.getAllSubmittedGames();
		model.addAttribute("gameList", gameList);
		return "votingList";
	}
	
//	@RequestMapping(value = "/likegame",  method = RequestMethod.POST)
//	public String likeGame(Model model, HttpSession session,@RequestParam("gameId") int gameId, @RequestParam("pageId") int pageId, 
//			@RequestParam("actionType") String actionType) 
//	{
//		User sessionUser = (User) session.getAttribute("sessionUser");
//		
//		if(actionType.equals("like")) {
//			gameBusinessLogic.addLike(gameId, sessionUser.getUserId());
//		} else if(actionType.equals("unlike")) {
//			gameBusinessLogic.removeLike(gameId, sessionUser.getUserId());
//				
//		}
//		
//		
//		Pageable pageable = PageRequest.of(pageId, 5);
//		Page<Game> gamePages = gameBusinessLogic.getAllCompletedGames(pageable);
//		List<Game> gameList = gamePages.getContent();
//		Game pastGame = null;
//		for (Game game : gameList) {
//			if(game.getGameId() == gameId) {
//				pastGame = game;
//			}
//		}
//		 List<Comment> pastGameComments = commentBusinessLogic.getAllCommentsByGameId(pastGame.getGameId());
//         model.addAttribute("pastGame", pastGame);
//         model.addAttribute("games", gamePages.getContent());
//         model.addAttribute("pages", gamePages.getTotalPages());
//         model.addAttribute("comments", pastGameComments); 
//         model.addAttribute("page", pageId);
//         
//         return "pastGames1";
//	}

	
	@RequestMapping( value="/closeVoting") 
	public String processCloseVoting(
			) {
		
		gameBusinessLogic.closeVoting();
		return "redirect:/";
	}
}
