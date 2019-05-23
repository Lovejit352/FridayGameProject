package com.fdmgroup.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.dao.ITeamDao;
import com.fdmgroup.dao.jpa.DbConnection;
import com.fdmgroup.model.Team;
import com.fdmgroup.model.User;

public class TeamDao implements ITeamDao{

	@Autowired
	private DbConnection connection;
	
	@Override
	public Team create(Team t) {
		EntityManager em = connection.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();

		return t;
	}

	@Override
	public Team readById(int id) {
		EntityManager em = connection.getEntityManager();
		Team team = em.find(Team.class, id);
		em.close();
		
		return team;
	}

	@Override
	public List<Team> readAll() {
		EntityManager em = connection.getEntityManager();
		TypedQuery<Team> query = em.createQuery("SELECT t FROM GameTeam t", Team.class);
		List<Team> teams = query.getResultList();
		em.close();
		return teams;
	}

	@Override
	public Team update(Team t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getPlayersInTeamByTeamId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getTeamByGameId(int id) {
		EntityManager em = connection.getEntityManager();
		TypedQuery<Team> query = em.createQuery("SELECT t FROM GameTeam t WHERE t.gameId =: gId", Team.class);
		query.setParameter("gId", id);
		Team team = query.getResultList().get(0);
		em.close();
		return team;
	}

	@Override
	public Team getTeamByName(String name) {
		EntityManager em = connection.getEntityManager();
		TypedQuery<Team> query = em.createQuery("SELECT t FROM GameTeam t WHERE t.name =: n", Team.class);
		query.setParameter("n", name);
		Team team = query.getResultList().get(0);
		em.close();
		return team;
	}

}

