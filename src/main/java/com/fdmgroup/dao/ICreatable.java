package com.fdmgroup.dao;

import com.fdmgroup.model.IStorable;

public interface ICreatable<T extends IStorable> {

	T create(T t);

}