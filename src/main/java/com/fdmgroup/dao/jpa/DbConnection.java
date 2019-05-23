package com.fdmgroup.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConnection {

	private static final String PERSISTENCE_UNIT = "FridayGamePU";
	private static DbConnection connection = null;
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	private DbConnection(){
		init();
	}
	
	private void init() {
		if (emf == null || !emf.isOpen()) {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		}
	}

	public static DbConnection getInstance() {
		if (connection == null) {
			connection = new DbConnection();
		}
		
		return connection;
	}
	
	public void makeNewEntityManager() {
		em = emf.createEntityManager();
	}
	
	public EntityManager getEntityManager() {
		init();
		return em;
	}
	
	public void begin() {
		makeNewEntityManager();
		em.getTransaction().begin();
	}
	
	public void commit() {
		em.getTransaction().commit();
		em.close();
	}
	
	public void close() {
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
	
}
