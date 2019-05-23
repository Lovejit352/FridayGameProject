package com.fdmgroup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	//The name of the :parameter must match @Param("parameter")
	@Query("SELECT u FROM User u WHERE u.emailAddress = :emailAddress")
	public User findByEmailAddress(@Param("emailAddress") String emailAddress);
	
	@Query("SELECT u FROM User u WHERE u.fullName = :fullname")
	public List<User> findByFullName(@Param("fullname") String fullname);

}
