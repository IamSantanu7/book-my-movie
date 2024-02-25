package com.project.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    
    @ManyToOne
	@JoinColumn(name = "uId", referencedColumnName = "uId")
	private User user;
    
    private String userEmail;

    @ManyToOne
	@JoinColumn(name = "movId", referencedColumnName = "movId")
	private Movies movie;
    
    private String movieName;

    @ManyToOne
	@JoinColumn(name = "theaterId", referencedColumnName = "theaterId")
	private Theaters theater;
    
    private String theaterName;
    
    private String theaterTimeSlot;
	
    private int noOfPerson;
    
    @Column(name = "booking_amount")
    private double totalAmount;
    
    private String paymentStatus; // Pending, Approved, Rejected

	public Bookings() {
		
	}

	public Bookings(String userName, User user, String userEmail, Movies movie, String movieName, Theaters theater,
			String theaterName, String theaterTimeSlot, int noOfPerson, double totalAmount, String paymentStatus) {
		super();
		this.userName = userName;
		this.user = user;
		this.userEmail = userEmail;
		this.movie = movie;
		this.movieName = movieName;
		this.theater = theater;
		this.theaterName = theaterName;
		this.theaterTimeSlot = theaterTimeSlot;
		this.noOfPerson = noOfPerson;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
	}

	public Bookings(Long id, String userName, User user, String userEmail, Movies movie, String movieName,
			Theaters theater, String theaterName, String theaterTimeSlot, int noOfPerson, double totalAmount,
			String paymentStatus) {
		super();
		this.id = id;
		this.userName = userName;
		this.user = user;
		this.userEmail = userEmail;
		this.movie = movie;
		this.movieName = movieName;
		this.theater = theater;
		this.theaterName = theaterName;
		this.theaterTimeSlot = theaterTimeSlot;
		this.noOfPerson = noOfPerson;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Theaters getTheater() {
		return theater;
	}

	public void setTheater(Theaters theater) {
		this.theater = theater;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getTheaterTimeSlot() {
		return theaterTimeSlot;
	}

	public void setTheaterTimeSlot(String theaterTimeSlot) {
		this.theaterTimeSlot = theaterTimeSlot;
	}

	public int getNoOfPerson() {
		return noOfPerson;
	}

	public void setNoOfPerson(int noOfPerson) {
		this.noOfPerson = noOfPerson;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Bookings [id=" + id + ", userName=" + userName + ", user=" + user + ", userEmail=" + userEmail
				+ ", movie=" + movie + ", movieName=" + movieName + ", theater=" + theater + ", theaterName="
				+ theaterName + ", theaterTimeSlot=" + theaterTimeSlot + ", noOfPerson=" + noOfPerson + ", totalAmount="
				+ totalAmount + ", paymentStatus=" + paymentStatus + "]";
	}
    
	
	
}






	

















/*
public class Bookings {

	private Long bookingid;
	private User user;
	private Movies movie;
	private Theaters hall;
	private Integer no_of_tickets;
	private Double amount;
	private Boolean paymentStatus;
	
	public Bookings(Long bookingid, User user, Movies movie, Theaters hall, Integer no_of_tickets, Double amount,
			Boolean paymentStatus) {
		super();
		this.bookingid = bookingid;
		this.user = user;
		this.movie = movie;
		this.hall = hall;
		this.no_of_tickets = no_of_tickets;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
	}

	public Long getBookingid() {
		return bookingid;
	}

	public void setBookingid(Long bookingid) {
		this.bookingid = bookingid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public Theaters getHall() {
		return hall;
	}

	public void setHall(Theaters hall) {
		this.hall = hall;
	}

	public Integer getNo_of_tickets() {
		return no_of_tickets;
	}

	public void setNo_of_tickets(Integer no_of_tickets) {
		this.no_of_tickets = no_of_tickets;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "Bookings [bookingid=" + bookingid + ", user=" + user + ", movie=" + movie + ", hall=" + hall
				+ ", no_of_tickets=" + no_of_tickets + ", amount=" + amount + ", paymentStatus=" + paymentStatus + "]";
	}
	
}
*/