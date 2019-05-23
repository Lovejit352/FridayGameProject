package com.fdmgroup.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Comments")
public class Comment implements IStorable{
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "commentId_seq")
	@SequenceGenerator(sequenceName="commentId_seq", allocationSize=1, initialValue=100001, name="commentId_seq")
	private int commentId;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "gameId")
	private Game game;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name= "parentCommentId")
	private Comment parentComment;
	
	@Column(name="coment")
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn; 

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", game=" + game + ", user=" + user + ", parentComment="
				+ parentComment + ", comment=" + comment + ", createdOn=" + createdOn + "]";
	}

	public Comment(int commentId, Game game, User user, Comment parentComment, String comment, Date createdOn) {
		super();
		this.commentId = commentId;
		this.game = game;
		this.user = user;
		this.parentComment = parentComment;
		this.comment = comment;
		this.createdOn = createdOn;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	

}

