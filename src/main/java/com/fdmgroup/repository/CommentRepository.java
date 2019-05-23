package com.fdmgroup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.model.Comment;


public interface CommentRepository extends JpaRepository<Comment, Integer>{

	@Query("SELECT com FROM Comment com JOIN com.game game WHERE game.gameId = :gameId")
	public List<Comment> findCommentGameId(@Param("gameId") int gameId);
	
	@Modifying
    @Query("update Comment com set com.comment = :newComment where com.commentId = :commentId")
    void updateCommentById(  @Param("newComment") String newComment,  @Param("commentId") int commentId);
	
	@Query("SELECT com FROM Comment com JOIN com.parentComment parentComment WHERE parentComment.commentId = :commentId")
	public List<Comment> findAllCommentsByParentCommentId(@Param("commentId") int commnentId);

}
