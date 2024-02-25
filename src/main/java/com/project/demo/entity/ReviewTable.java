package com.project.demo.entity;

import javax.persistence.*;

@Entity
@Table
public class ReviewTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;
	
	@ManyToOne
	@JoinColumn(name = "uId", referencedColumnName = "uId")
	private User user;
	
	private String uName;
	
	private String uMail;
	
	@ManyToOne
	@JoinColumn(name = "movId", referencedColumnName = "movId")
	private Movies movie;
	
	private String movName;
	
	private Double ratingPoints;
	
	private String review;

	public ReviewTable(User user, String uName, String uMail, Movies movie, String movName, Double ratingPoints,
			String review) {
		super();
		this.user = user;
		this.uName = uName;
		this.uMail = uMail;
		this.movie = movie;
		this.movName = movName;
		this.ratingPoints = ratingPoints;
		this.review = review;
	}

	public ReviewTable(Long reviewId, User user, String uName, String uMail, Movies movie, String movName,
			Double ratingPoints, String review) {
		super();
		this.reviewId = reviewId;
		this.user = user;
		this.uName = uName;
		this.uMail = uMail;
		this.movie = movie;
		this.movName = movName;
		this.ratingPoints = ratingPoints;
		this.review = review;
	}
	
	public ReviewTable() {
		super();
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuMail() {
		return uMail;
	}

	public void setuMail(String uMail) {
		this.uMail = uMail;
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public String getMovName() {
		return movName;
	}

	public void setMovName(String movName) {
		this.movName = movName;
	}

	public Double getRatingPoints() {
		return ratingPoints;
	}

	public void setRatingPoints(Double ratingPoints) {
		this.ratingPoints = ratingPoints;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "ReviewTable [reviewId=" + reviewId + ", user=" + user + ", uName=" + uName + ", uMail=" + uMail
				+ ", movie=" + movie + ", movName=" + movName + ", ratingPoints=" + ratingPoints + ", review=" + review
				+ "]";
	}
	
}
