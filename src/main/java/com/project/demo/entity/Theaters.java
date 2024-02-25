package com.project.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "theater_list")
public class Theaters {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long theaterId;
	
	@NotNull
	private String theaterName;
	
	@NotNull
	private String theaterLocation;
	
	@Column(name = "time_slot")
	private String slot;
	
	@NotNull
	private Double ticketPrice;
	
	@ManyToMany(mappedBy = "theaters")
	private List<Movies> movies;
	
	public Theaters() {
	}

	public Theaters(String theaterName, List<Movies> movies) {
		super();
		this.theaterName = theaterName;
		this.movies = movies;
	}

	public Theaters(Long theaterId, String theaterName, String theaterLocation, String slot, Double ticketPrice) {
		super();
		this.theaterId = theaterId;
		this.theaterName = theaterName;
		this.theaterLocation = theaterLocation;
		this.slot = slot;
		this.ticketPrice = ticketPrice;
	}

	public Theaters(Long theaterId, String theaterName, String theaterLocation, String slot, Double ticketPrice, List<Movies> movies) {
		super();
		this.theaterId = theaterId;
		this.theaterName = theaterName;
		this.theaterLocation = theaterLocation;
		this.slot = slot;
		this.ticketPrice = ticketPrice;
		this.movies = movies;
	}

	public Long getTheaterid() {
		return theaterId;
	}

	public void setTheaterid(Long theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheatername() {
		return theaterName;
	}

	public void setTheatername(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getTheaterlocation() {
		return theaterLocation;
	}

	public void setTheaterlocation(String theaterLocation) {
		this.theaterLocation = theaterLocation;
	}
	
	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public Double getTicketprice() {
		return ticketPrice;
	}

	public void setTicketprice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public List<Movies> getMovies() {
		return movies;
	}

	public void setMovies(List<Movies> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Theaters [Theater Id=" + theaterId + ", Theater Name=" + theaterName + ", Theater Location="
				+ theaterLocation + ", slot=" + slot + ", Ticket Price=" + ticketPrice + "]";
	}

}
