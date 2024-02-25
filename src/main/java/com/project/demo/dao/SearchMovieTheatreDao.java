package com.project.demo.dao;

import java.util.List;

import com.project.demo.entity.Movies;
import com.project.demo.entity.Theaters;

public interface SearchMovieTheatreDao {
	List<Movies> searchMovie(String moviename);
	List<Theaters> searchTheatreByMovie(String moviename);
	List<Theaters> searchTheatre(String theatername);
	List<Movies> searchMovieByTheatre(String theatrename);
}
