package com.fdmgroup.businessLogic;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.model.Comment;
import com.fdmgroup.model.Game;
import com.fdmgroup.model.User;
import com.fdmgroup.repository.CommentRepository;

public class CommentBusinessLogic {
	@Autowired
	private CommentRepository commentRepo;
//	private ICommentDao commentDao;
	
	@Autowired
	private Logger logger;
	
	public Comment createComment(Game game, User sessionUser, Comment parentComment, String strComment) {
		Comment comment = null;
		
		String errMsg = null;
		if(sessionUser == null || sessionUser.getUserId()< 100001)
			errMsg = "Invalid session user";
		else if(strComment == null || strComment.trim().length() < 1)
			errMsg = "Comment cannot be empty";
		else if(game == null || game.getGameId() < 100001 )
			errMsg = "Invalid game id";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		comment = new Comment(0, game, sessionUser, parentComment, strComment, new Date());
		comment = commentRepo.saveAndFlush(comment);
		if(comment !=null)
			logger.info("Comment created, id:"+comment.getCommentId());
		return comment;
	}
	
	public Comment createSubComment(Game game, User sessionUser, Comment parentComment, String strComment) {
		Comment comment = null;
		
		String errMsg = null;
		if(sessionUser == null || sessionUser.getUserId()< 100001)
			errMsg = "Invalid session user";
		else if(strComment == null || strComment.trim().length() < 1)
			errMsg = "Comment cannot be empty";
		else if(game == null || game.getGameId() < 100001 )
			errMsg = "Invalid game id";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		comment = new Comment(0, game, sessionUser, parentComment, strComment, new Date());
		comment = commentRepo.saveAndFlush(comment);
		if(comment !=null)
			logger.info("Comment created, id:"+comment.getCommentId());
		return comment;
	}
	
	public Comment updateComment( User sessionUser,  Comment comment) {
		
		String strComment = comment.getComment();
		String errMsg = null;
		if(sessionUser == null || sessionUser.getUserId() < 100001)
			errMsg = "Invalid session user";
		else if(strComment == null || strComment.trim().length() < 1)
			errMsg = "Comment cannot be empty";
		else if( commentRepo.findById(comment.getCommentId()) == null)
			errMsg = "Does not exist";
		
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		comment = commentRepo.saveAndFlush(comment);
		if(comment !=null)
			logger.info("Comment updated, id:"+comment.getCommentId());
		
		return comment;
	}
	
	public boolean deleteComment(int commentId) {
		
		String errMsg = null;
		if(commentId < 100001)
			errMsg = "Invalid commentId";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		commentRepo.deleteById(commentId);
		commentRepo.flush();
		return true;
	}
	
	public Comment getCommentById(int commentId) {
		
		Comment comment = null;
		
		String errMsg = null;
		if(commentId < 100001)
			errMsg = "Invalid commentId";
		
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		comment = commentRepo.findById(commentId).get();
		
		return comment;
	}
	
	public List<Comment> getAllCommentsByGameId(int gameId) {
		List<Comment> commentList = null;
		
		String errMsg = null;
		if(gameId  < 100001) 
			errMsg = "Invalid gameId";
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		commentList = commentRepo.findCommentGameId(gameId);
		
		return commentList;
	}
	
	public List<Comment> getAllCommentsByParentCommentId(int commentId) {
		List<Comment> commentList = null;
		
		String errMsg = null;
		if(commentId  < 100001) 
			errMsg = "Invalid gameId";
		if(errMsg != null) {
			throw new IllegalArgumentException(errMsg);
		}
		
		commentList = commentRepo.findAllCommentsByParentCommentId(commentId);
		
		return commentList;
	}
	
	
	
}
