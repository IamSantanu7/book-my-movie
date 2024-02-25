package com.project.demo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.demo.dao.MovieTheaterMappingDao;
import com.project.demo.utility.HibernateUtil;

/*
 * Santanu
 */
public class MovieTheaterMappingDaoImpl implements MovieTheaterMappingDao{

private final SessionFactory sessionFactory;
	
	public MovieTheaterMappingDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

/*	
	@Override
	public boolean addMovieToTheater(Long movieId, Long theaterId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createNativeQuery("INSERT INTO movie_theater_mapping (movid, theaterid) VALUES (?, ?)");
	        		query
	        		.setParameter(1, movieId)
	                .setParameter(2, theaterId)
	                .executeUpdate();
	        
	        transaction.commit();
	        
	        return true;
		}catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}
		return false;
	}
*/
	
	@Override
	public boolean addMovieToTheater(Long movieId, Long theaterId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;

	    try {
	        transaction = session.beginTransaction();

	        // Check if there is an existing entry for the given theater
	        Query checkQuery = session.createNativeQuery("SELECT * FROM movie_theater_mapping WHERE theaterid = :theaterId")
	                .setParameter("theaterId", theaterId);

	        Object existingMapping = checkQuery.uniqueResult();

	        if (existingMapping != null) {
	            // Update the movie ID for the existing mapping
	            Query updateQuery = session.createNativeQuery("UPDATE movie_theater_mapping SET movid = :newMovieId WHERE theaterid = :theaterId")
	                    .setParameter("newMovieId", movieId)
	                    .setParameter("theaterId", theaterId);
	            updateQuery.executeUpdate();
	        } else {
	            // If no entry exists, create a new mapping
	            Query insertQuery = session.createNativeQuery("INSERT INTO movie_theater_mapping (movid, theaterid) VALUES (:movieId, :theaterId)")
	                    .setParameter("movieId", movieId)
	                    .setParameter("theaterId", theaterId);
	            insertQuery.executeUpdate();
	        }

	        transaction.commit();
	        return true;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return false;
	}


	@Override
	public List<String> getMoviesByTheaterId(Long theaterId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try {
		
			String hql = "SELECT m.movName " +
		             "FROM Movies m " +
		             "JOIN m.theaters t " +
		             "WHERE t.theaterId = :ti";
			
			List<String> movieNames = session.createQuery(hql, String.class)
			        .setParameter("ti", theaterId)
			        .getResultList();
			
			return movieNames;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}


	@Override
	public List<String> getMoviesByTheaterName(String theaterName) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try {
			String hql = "SELECT m.movName " +
		             "FROM Movies m " +
		             "JOIN m.theaters t " +
		             "WHERE t.theaterName = :tn";
			
			List<String> movieNames = session.createQuery(hql, String.class)
			        .setParameter("tn", theaterName)
			        .getResultList();
			
			return movieNames;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}


	@Override
	public List<String> getTheatersByMovieId(Long movieId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try {
		
			String hql = "SELECT t.theaterName " +
					"FROM Theaters t " +
		             "JOIN t.movies m " +
		             "WHERE m.movId = :mi";
			
			List<String> theaterNames = session.createQuery(hql, String.class)
			        .setParameter("mi", movieId)
			        .getResultList();
			
			return theaterNames;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}


	@Override
	public List<String> getTheatersByMovieName(String movieName) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();	
		try {
			String hql = "SELECT t.theaterName " +
		             "FROM Theaters t " +
		             "JOIN t.movies m " +
		             "WHERE m.movName = :mn";
			
			List<String> theaterNames = session.createQuery(hql, String.class)
			        .setParameter("mn", movieName)
			        .getResultList();
			
			return theaterNames;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}
