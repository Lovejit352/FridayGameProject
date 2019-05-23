package com.fdmgroup.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.User;

public class UserJpaDao implements IUserDao {

	@Override
	public User create(User t) {
		try {			
			DbConnection.getInstance().begin();
	      
			DbConnection.getInstance().getEntityManager().persist(t);
	
	        DbConnection.getInstance().commit();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return t;
	}

	@Override
	public List<User> readAll() {
		try {
			DbConnection.getInstance().begin();
			
			String query = "Select u from player u";
	        
	        return DbConnection.getInstance().getEntityManager().createQuery(query, User.class).getResultList();
        }
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User update(User t) {
		try {	
			DbConnection.getInstance().begin();
			
			DbConnection.getInstance().getEntityManager().merge(t);
			
			DbConnection.getInstance().commit();
	        
	        return t;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User readByEmail(String email) {
		try {
			DbConnection.getInstance().begin();
			
			String query = "Select u from player u where u.email =:pemail";
	        Query q = DbConnection.getInstance().getEntityManager().createQuery(query, User.class);
	        q.setParameter("pemail", email);
	        
	        return (User) q.getResultList().get(0);
        }
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User readById(int id) {
		try {
			DbConnection.getInstance().begin();
			
	        return DbConnection.getInstance().getEntityManager().find(User.class, id);
        }
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

		

}
