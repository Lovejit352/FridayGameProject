package com.fdmgroup.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.model.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{
	
	@Query("SELECT u FROM Game u WHERE u.votes = (SELECT max(g2.votes) FROM Game g2)")
    public Game maxVotedGame();
	
	@Query("SELECT g FROM Game g WHERE g.gameStatus = 1")
	public List<Game> findAllSubmittedGame();
	
	@Query("SELECT g FROM Game g  WHERE g.gameStatus = 3 order by g.playedOn desc")
	public Page<Game> findAllCompletedGames(Pageable pageable);
	
	@Query("SELECT g FROM Game g WHERE g.gameStatus = 3 order by g.playedOn desc")
	public List<Game> findAllPastGame();

	@Query("SELECT g FROM Game g WHERE g.gameStatus = 2")
	public Game findSelectedGame();

	@Query("SELECT g FROM Game g JOIN g.createdBy u WHERE u.userId = :userId AND g.gameStatus < 2")
	public List<Game> findGameManagerGamesByUserId(@Param("userId") int userId);
    
	@Procedure(procedureName="clear_game_votes")
	public void clearVotingTable();

}
