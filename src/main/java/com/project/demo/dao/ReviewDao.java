package com.project.demo.dao;

import java.util.List;

import com.project.demo.entity.Movies;
import com.project.demo.entity.ReviewTable;

public interface ReviewDao {
	List<Movies> checkValidMovie(String moviename);
	
	void saveRating(ReviewTable reviewTable);
	
	List<ReviewTable> viewMyRating(String email);
	
	List<ReviewTable> viewAllRating();
	
	List<ReviewTable> viewRating(String moviename);
	
	Double viewAllRatingMovie(String moviename);
	
	void deleteRating(String moviename);
	
	void updateReview(String moviename, String review);
}
