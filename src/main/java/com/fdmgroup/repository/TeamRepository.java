package com.fdmgroup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	@Query("SELECT t FROM Team t JOIN t.game game WHERE game.gameId = :gameId")
	public List<Team> findAllById(@Param("gameId") int gameId);
	
}
