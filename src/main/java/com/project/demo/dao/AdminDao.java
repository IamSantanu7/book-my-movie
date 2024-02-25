package com.project.demo.dao;

import com.project.demo.entity.Admin;

public interface AdminDao {

	void saveAdmin(Admin admin);

	Admin loginAdmin(String email, String password);

}
