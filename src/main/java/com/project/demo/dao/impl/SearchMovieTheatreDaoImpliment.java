package com.project.demo.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.project.demo.dao.SearchMovieTheatreDao;
import com.project.demo.entity.Movies;
import com.project.demo.entity.Theaters;
import com.project.demo.utility.HibernateUtil;

public class SearchMovieTheatreDaoImpliment implements SearchMovieTheatreDao {
	
	
	@Override
	public List<Movies> searchMovie(String moviename) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Movies> query = session.createQuery("FROM Movies WHERE movname = :moviename", Movies.class);
		query.setParameter("moviename", moviename);
		return query.list();
	}
	
	@Override
	public List<Theaters> searchTheatreByMovie(String moviename) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Theaters> query = session.createQuery("SELECT t FROM Theaters t JOIN t.movies m WHERE m.movName = :moviename", Theaters.class);
		query.setParameter("moviename", moviename);
		return query.getResultList();
	}
	
	@Override
	public List<Theaters> searchTheatre(String theatername) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Theaters> query = session.createQuery("FROM Theaters WHERE theatername = :theatername", Theaters.class);
		query.setParameter("theatername", theatername);
		return query.list();
	}
	
	@Override
	public List<Movies> searchMovieByTheatre(String theatrename) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Movies> query = session.createQuery("SELECT m FROM Movies m JOIN m.theaters t WHERE t.theaterName = :theatrename", Movies.class);
		//TypedQuery<Movies> query = session.createQuery("SELECT m FROM Movies AND t FROM Theaters WHERE t.theaterName = :theatrename", Movies.class);
		query.setParameter("theatrename", theatrename);
		return query.getResultList();
	}
}