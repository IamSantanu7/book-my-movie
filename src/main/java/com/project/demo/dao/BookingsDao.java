package com.project.demo.dao;

import java.util.List;

import com.project.demo.entity.Bookings;

public interface BookingsDao {

	public void saveBooking(Bookings booking);
	
	public List<Bookings> getAllBookings();
	
	public Bookings getBookingById(long bookingId);
	
	public Bookings getBookingsByUser(String useremail);
	
	public void deleteBooking(Bookings booking);
	
	public void updateBooking(Bookings booking);
	
	public String isMovieAllocatedInTheater(String theaterName, String slot);
}
