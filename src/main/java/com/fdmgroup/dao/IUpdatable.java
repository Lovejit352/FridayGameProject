package com.fdmgroup.dao;

import com.fdmgroup.model.IStorable;

public interface IUpdatable<T extends IStorable> {
	T update(T t);

}
