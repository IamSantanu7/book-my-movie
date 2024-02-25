package com.project.demo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.demo.dao.MoviesDao;
import com.project.demo.entity.Movies;
import com.project.demo.utility.HibernateUtil;
//import com.project.demo.utility.HibernateUtil;

/*
 * Santanu
 */

public class MoviesDaoImpl implements MoviesDao {
	
	private final SessionFactory sessionFactory;
	
	public MoviesDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	@Override
	public boolean addMovie(Movies movie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			System.out.println("At addMovie implementation at MoviesDaoImpl.");
			
			session.save(movie);
            transaction.commit();
            
            return true;
	
		}catch(Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}
		return false;
	}

	@Override
    public boolean updateMovie(Movies movie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("update Movies set movgenre = :x, movlang = :y, movduration = :z where movname = :w");
            query.setParameter("w", movie.getMovname());
            query.setParameter("x", movie.getMovgenre());
            query.setParameter("y", movie.getMovlang());
            query.setParameter("z", movie.getMovDuration());

            int rowsUpdated = query.executeUpdate();

            transaction.commit();

            return rowsUpdated > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

	@Override
    public boolean deleteMovie(String movname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Movies where movname = :x");
            query.setParameter("x", movname);

            int rowsDeleted = query.executeUpdate();

            transaction.commit();

            return rowsDeleted > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public boolean getAMovieDetails(String movname) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from Movies where movname= :parameter");
			query.setParameter("parameter", movname);
			
			Object result = query.uniqueResult();
			
			System.out.println(result);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean getAllMovieDetails() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from Movies");
			
			List<Movies> movie_list = query.list();
			//System.out.println(movie_list);
//			System.out.println("-------------------------------------------------------------------------");
//			System.out.printf("| %-12s | %-25s | %-15s | %-12s |\n", "Movie Id", "Movie Name", "Movie Genre", "Language");
//			System.out.println("-------------------------------------------------------------------------");
			for(Movies m : movie_list) {
//				System.out.printf("| %-12s | %-25s | %-15s | %-12s |\n",
//						m.getMovid(),
//						m.getMovname(),
//						m.getMovgenre(),
//						m.getMovlang());
				System.out.println(m);
			}
//			System.out.println("-------------------------------------------------------------------------");
			
			if(movie_list.size()>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
