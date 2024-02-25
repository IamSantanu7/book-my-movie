package com.project.demo.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.demo.entity.Theaters;

public interface TheatersDao {
	
	boolean addTheater(Theaters theater);
	boolean updateTheater(Theaters theater);
	boolean deleteTheater(String theatername);
	boolean getAllTheaterDetails();
	List<Theaters> getTheatreByName(String theatreName, String slot);
	Theaters getTheaterByName(String theaterName, String slot);
	
}
