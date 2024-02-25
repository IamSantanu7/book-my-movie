package com.project.demo.dao.impl;

import java.util.List;

import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.project.demo.dao.ReviewDao;
import com.project.demo.entity.Movies;
import com.project.demo.entity.ReviewTable;
import com.project.demo.entity.User;
import com.project.demo.utility.HibernateUtil;

public class ReviewDaoImpliment implements ReviewDao {
	
	@Override
	public List<Movies> checkValidMovie(String moviename) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Movies> query = session.createQuery("FROM Movies WHERE movName = :moviename", Movies.class);
		query.setParameter("moviename", moviename);
		return query.list();
	}
	
	@Override
	public void saveRating(ReviewTable reviewTable) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			if (session != null) {
				transaction = session.beginTransaction();
				User user = getUserByEmail(reviewTable.getuMail(), session);
				Movies movie = getMovieByName(reviewTable.getMovName(), session);
				reviewTable.setUser(user);
				reviewTable.setMovie(movie);
				session.save(reviewTable);
				transaction.commit();
				System.out.println("\nRating Saved Successfully\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
				System.out.println("\nRating Not Saved\n");
			}
		}
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
	public List<ReviewTable> viewMyRating(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<ReviewTable> query = session.createQuery("FROM ReviewTable WHERE uMail = :email", ReviewTable.class);
		query.setParameter("email", email);
		return query.list();
	}
	
	@Override
	public List<ReviewTable> viewAllRating() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<ReviewTable> query = session.createQuery("FROM ReviewTable", ReviewTable.class);
		return query.list();
	}
	
	@Override
	public Double viewAllRatingMovie(String moviename) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Double> query = session.createQuery("SELECT AVG(ratingPoints) FROM ReviewTable WHERE movName = :moviename", Double.class);
		query.setParameter("moviename", moviename);
		return query.uniqueResult();
	}
	
	@Override
	public List<ReviewTable> viewRating(String moviename) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<ReviewTable> query = session.createQuery("FROM ReviewTable WHERE movName = :moviename", ReviewTable.class);
		query.setParameter("moviename", moviename);
		return query.list();
	}
	
	@Override
	public void deleteRating(String moviename) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			if (session != null) {
				transaction = session.beginTransaction();
				Query query = session.createQuery("DELETE FROM ReviewTable WHERE movName = :moviename");
				query.setParameter("moviename", moviename);
				query.executeUpdate();
				transaction.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	@Override
	public void updateReview(String moviename, String review) {
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			if (session != null) {
				transaction = session.beginTransaction();
				Query query = session.createQuery("UPDATE ReviewTable SET review = :review WHERE movName = :moviename");
				query.setParameter("review", review);
				query.setParameter("moviename", moviename);
				query.executeUpdate();
				transaction.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
				System.out.println("Review Not Updated");
			}
		}
	}
}
