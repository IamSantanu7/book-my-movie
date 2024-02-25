package com.project.demo.dao;

import java.util.List;

import com.project.demo.entity.Movies;
/*
 * Santanu
 */
public interface MovieTheaterMappingDao {
	
	public boolean addMovieToTheater(Long movieId, Long theaterId);
	
	List<String> getMoviesByTheaterId(Long theaterId);
	
	List<String> getMoviesByTheaterName(String theaterName);
	
	List<String> getTheatersByMovieId(Long movieId);
	
	List<String> getTheatersByMovieName(String movieName);

}
