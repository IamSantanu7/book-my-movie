package com.project.demo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.demo.dao.BookingsDao;
import com.project.demo.entity.Bookings;
import com.project.demo.entity.Movies;
import com.project.demo.entity.User;
import com.project.demo.entity.Theaters;
import com.project.demo.utility.HibernateUtil;

public class BookingsDaoImpl implements BookingsDao {
	
	private final SessionFactory sessionFactory;

    public BookingsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    @Override
    public void saveBooking(Bookings booking) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = getUserByEmail(booking.getUserEmail(), session);
			Movies movie = getMovieByName(booking.getMovieName(), session);
			Theaters theater = getTheaterByName(booking.getTheaterName(), booking.getTheaterTimeSlot(), session);
			booking.setUser(user);
			booking.setMovie(movie);
			booking.setTheater(theater);
            session.save(booking);
            transaction.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    
    private Theaters getTheaterByName(String theaterName, String theaterTimeSlot, Session session) {
		Query<Theaters> query = session.createQuery("FROM Theaters WHERE theaterName = :theaterName AND slot = :theaterTimeSlot", Theaters.class);
		query.setParameter("theaterName", theaterName);
		query.setParameter("theaterTimeSlot", theaterTimeSlot);
		return query.uniqueResult();
	}


	private Movies getMovieByName(String movName, Session session) {
    	Query<Movies> query = session.createQuery("FROM Movies WHERE movName = :movName", Movies.class);
		query.setParameter("movName", movName);
		return query.uniqueResult();
    }
    
	private User getUserByEmail(String uMail, Session session) {
		Query<User> query = session.createQuery("FROM User WHERE uMail = :uMail", User.class);
        query.setParameter("uMail", uMail);
        return query.uniqueResult();
    }
    
    
    @Override
    public List<Bookings> getAllBookings() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Query<Bookings> query = session.createQuery("FROM Bookings", Bookings.class);
    	return query.list();
    }

    
    @Override
    public Bookings getBookingById(long bookingId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Bookings.class, bookingId);
        }
    }

    
    @Override
    public Bookings getBookingsByUser(String useremail) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	Query<Bookings> query = session.createQuery("FROM Bookings WHERE userEmail = :useremail", Bookings.class);
        	query.setParameter("useremail", useremail);
        	return query.uniqueResult();
        }
    }
    
    
    @Override
    public void deleteBooking(Bookings booking) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(booking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    
    @Override
    public void updateBooking(Bookings booking) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(booking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	@Override
	public String isMovieAllocatedInTheater(String theaterName, String slot) {
		try (Session session = sessionFactory.openSession()) {
            Query<String> query = session.createQuery(
            		"SELECT m.movName " +
       		             "FROM Movies m " +
       		             "JOIN m.theaters t " +
       		             "WHERE t.theaterName = :theaterName AND t.slot = :slot", String.class);
            query.setParameter("theaterName", theaterName);
            query.setParameter("slot", slot);
            return query.uniqueResult();
        }
	}
	
	
    
}
