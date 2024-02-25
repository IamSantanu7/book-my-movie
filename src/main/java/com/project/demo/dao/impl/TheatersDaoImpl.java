package com.project.demo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.demo.dao.TheatersDao;
import com.project.demo.entity.Movies;
import com.project.demo.entity.Theaters;
import com.project.demo.utility.HibernateUtil;
/*
 * Santanu
 */
public class TheatersDaoImpl implements TheatersDao{
	
	private final SessionFactory sessionFactory;
	
	public TheatersDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	
	@Override
	public boolean addTheater(Theaters theater) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			
			session.save(theater);
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
	public boolean updateTheater(Theaters theater) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("update Movies set theaterlocation = :x, slot = :y, ticketprice = :z where theatername = :w");
            query.setParameter("w", theater.getTheatername());
            query.setParameter("x", theater.getTheaterlocation());
            query.setParameter("y", theater.getSlot());
            query.setParameter("z", theater.getTicketprice());

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
	public boolean deleteTheater(String theatername) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Theaters where theatername = :x");
            query.setParameter("x", theatername);

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
	public boolean getAllTheaterDetails() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query query = session.createQuery("from Theaters");
			
			List<Theaters> theater_list = query.list();
			//System.out.println(theater_list);
			
			for(Theaters t : theater_list) {
				System.out.println(t);
			}
			
			if(theater_list.size()>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Theaters> getTheatreByName(String theatreName, String slot) {
		try (Session session = sessionFactory.openSession()) {
            Query<Theaters> query = session.createQuery("FROM Theatre WHERE name = :theatrename AND slot = :slot", Theaters.class);
            return query.list();
        }
	}
	
	@Override
	public Theaters getTheaterByName(String theatreName, String slot) {
		try (Session session = sessionFactory.openSession()) {
            Query<Theaters> query = session.createQuery("FROM Theaters WHERE theaterName = :theatrename AND slot = :slot", Theaters.class);
            query.setParameter("theatrename", theatreName);
            query.setParameter("slot", slot);
            return query.uniqueResult();
        }
	}


}
