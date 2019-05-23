package com.fdmgroup.dao;

import com.fdmgroup.model.IStorable;

public interface IDeletable<T extends IStorable> {
	boolean delete(int id);
}
