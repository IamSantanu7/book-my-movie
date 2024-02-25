package com.project.demo.dao;

import com.project.demo.entity.User;

public interface UserDao {

	void saveUser(User user);

	User loginUser(String email, String password);

}
