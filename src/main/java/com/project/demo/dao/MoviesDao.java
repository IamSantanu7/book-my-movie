package com.project.demo.dao;

import org.hibernate.Session;

import com.project.demo.entity.Movies;
/*
 * Santanu
 */
public interface MoviesDao {

	boolean addMovie(Movies movie);
	boolean updateMovie(Movies movie);
	boolean deleteMovie(String movname);
	boolean getAMovieDetails(String movname);
	boolean getAllMovieDetails();
	
}
