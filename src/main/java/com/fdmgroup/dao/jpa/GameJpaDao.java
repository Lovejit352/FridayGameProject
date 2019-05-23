package com.fdmgroup.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import com.fdmgroup.dao.IGameDao;
import com.fdmgroup.model.Game;





public class GameJpaDao implements IGameDao {
	
	private DbConnection connection;
	
	@Override
	public Game create(Game t) {
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return t;
	}

	@Override
	public Game update(Game t) {
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return t;
	}

	@Override
	public boolean delete(int id) {
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		em.remove(id);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public Game readById(int id) {
		EntityManager em = connection.getEntityManager();
		Game game = em.find(Game.class, id);
		em.close();
		return game;
	}

	@Override
	public List<Game> readAll() {
		EntityManager em = connection.getEntityManager();
		List<Game> games = em.createNamedQuery("Game.All",Game.class).getResultList();
		em.close();
		return games;
	}



}
