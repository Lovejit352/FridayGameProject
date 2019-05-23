package com.fdmgroup.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import com.fdmgroup.dao.ICommentDao;
import com.fdmgroup.model.Comment;

public class CommentDao implements ICommentDao{
 private DbConnection connection;
 
 
	public CommentDao() {
	super();
	// TODO Auto-generated constructor stub
	connection = DbConnection.getInstance();
}

	@Override
	public Comment create(Comment comment) {
		// TODO Auto-generated method stub
		Comment managedComment = null;
		if(comment != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			managedComment = em.merge(comment);
			em.persist(managedComment);
			em.getTransaction().commit();
			em.close();
		}
		return managedComment;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		Comment comment = em.find(Comment.class, id);
		if(comment != null){
			em.remove(comment);
			em.getTransaction().commit();
			result = true;
		}
		em.close();
		return result;
	}

	@Override
	public Comment readById(int id) {
		// TODO Auto-generated method stub
		Comment comment = null;
		EntityManager em = connection.getEntityManager();
		comment = em.find(Comment.class, id);
		em.close();
		return comment;
	}

	@Override
	public List<Comment> readAll() {
		// TODO Auto-generated method stub
		List<Comment> comments = null;
		EntityManager em = connection.getEntityManager();
		comments = em.createQuery("select comment from Comment comment", Comment.class).getResultList();
		em.close();
		return comments;
	}

	@Override
	public Comment update(Comment comment) {
		// TODO Auto-generated method stub
		Comment managedComment = null;
		if(comment != null){
			EntityManager em = connection.getEntityManager();
			em.getTransaction().begin();
			managedComment = em.find(Comment.class, comment.getCommentId());
			if(comment.getComment() != null){
				managedComment.setComment(comment.getComment());
			}
			if(comment.getGame() != null){
				managedComment.setGame(comment.getGame());
			}
			if(comment.getParentComment() != null){
				managedComment.setParentComment(comment.getParentComment());
			}
			if(comment.getUser() != null){
				managedComment.setUser(comment.getUser());
			}
			em.getTransaction().commit();
			em.close();
		}
		return managedComment;
	}

}
