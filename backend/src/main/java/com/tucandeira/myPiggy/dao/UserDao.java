package com.tucandeira.myPiggy.dao;

import com.tucandeira.myPiggy.model.User;
import java.util.List;

public interface UserDao{
	void save(User user);
	User findById(int userId);
	List<User> findAll();
	void update(User user);
	void delete(int userId);
}
