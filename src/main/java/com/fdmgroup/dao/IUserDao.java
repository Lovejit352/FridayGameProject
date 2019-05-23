package com.fdmgroup.dao;

import com.fdmgroup.model.User;

public interface IUserDao extends ICreatable<User>, IReadable<User>, IUpdatable<User>{

	User readByEmail(String email);
	User readById(int id);
}
