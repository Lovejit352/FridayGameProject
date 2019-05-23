package com.fdmgroup.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fdmgroup.businessLogic.CommentBusinessLogic;
import com.fdmgroup.businessLogic.GameBusinessLogic;
import com.fdmgroup.businessLogic.UserBusinessLogic;
import com.fdmgroup.model.Comment;
import com.fdmgroup.model.Game;
import com.fdmgroup.model.User;


@Controller
public class CommentController {
	

	@Autowired
	private CommentBusinessLogic commentLogic;
	@Autowired
	private GameBusinessLogic gameLogic;
	@Autowired
	private UserBusinessLogic userLogic;

//	@RequestMapping(value = "/createComment")
//	public String createComment() {
//		
//		return "index";
//	}
//	
	@RequestMapping(value = "/createComment", method=RequestMethod.POST)
	public String processComment(HttpServletRequest request) {
//		String userId = request.getParameter("userId");
//		String gameId = request.getParameter("gameId");
//		String parentCommentId = request.getParameter("parentCommentId");
//		String  strComment = request.getParameter("strComment");
//		
//		
//		Game commentGame = gameLogic.getGameById(Integer.parseInt(gameId));
//		User commentUser = userLogic.getUserById(Integer.parseInt(userId));
//		Comment parentComment =null;
//		if(parentCommentId !=null && parentCommentId.trim().length() >0 ) {
//			parentComment = commentLogic.getCommentById(Integer.parseInt(parentCommentId));
//		}
//		commentLogic.createComment(commentGame, commentUser, parentComment, strComment);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/updateComment", method=RequestMethod.POST)
	public String updateComment(@SessionAttribute("sessionUser") User sessionUser, HttpServletRequest request) {
		String commentId = request.getParameter("commentId");
		String newComment = request.getParameter("strComment");
		if(commentId.trim().isEmpty()){
			request.setAttribute("errorMessage", "commentId cannot be null!");
			return "index";
		}
		if(newComment.trim().isEmpty()){
			request.setAttribute("errorMessage", "strComment cannot be null!");
			return "index";
		}
		Comment oldComment = commentLogic.getCommentById(Integer.parseInt(commentId));
		
		oldComment.setComment(newComment);
		
		commentLogic.updateComment(sessionUser, oldComment);
		return "index";
	}
	
	@RequestMapping(value = "/getCommentById", method=RequestMethod.POST)
	public String getCommentById(HttpServletRequest request) {
		String commentId = request.getParameter("commentId");
		Comment comment = commentLogic.getCommentById(Integer.parseInt(commentId));
		request.setAttribute("comment", comment);
		return "index";
	}
	
	@RequestMapping(value = "/deleteCommentById", method=RequestMethod.POST)
	public String deleteCommentById(HttpServletRequest request) {
		String commentId = request.getParameter("commentId");
		List<Comment> childComments = commentLogic.getAllCommentsByParentCommentId(Integer.parseInt(commentId));
		for (Comment comment : childComments) {
			commentLogic.deleteComment(comment.getCommentId());
		}
		Comment comment = commentLogic.getCommentById(Integer.parseInt(commentId));
		commentLogic.deleteComment(comment.getCommentId());
		return "index";
	}
	
	@RequestMapping(value = "/getAllCommentsByGameId", method=RequestMethod.POST)
	public String getAllCommentsByGameId(HttpServletRequest request) {
		String gameId = request.getParameter("gameId");
		List<Comment> comments = commentLogic.getAllCommentsByGameId(Integer.parseInt(gameId));
		request.setAttribute("comments", comments);
		return "index";
	}
	
}
