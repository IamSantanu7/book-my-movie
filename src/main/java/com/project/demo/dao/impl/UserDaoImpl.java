package com.project.demo.dao.impl;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.demo.MyApp;
import com.project.demo.dao.UserDao;
import com.project.demo.dashboard.UserDashboard;
//import com.project.demo.dashboard.UserDashboard;
import com.project.demo.entity.User;
import com.project.demo.utility.HibernateUtil;

/*
 * Santanu
 */

public class UserDaoImpl implements UserDao {

	@Override
	public void saveUser(User user) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("from User where umail= :um and upass= :up");
			query.setParameter("um", user.getUmail());
			query.setParameter("up", user.getUpass());
	        if (query.uniqueResult() == null) {
	            // If the user-mail is not taken, save the user
	            session.save(user);
	            transaction.commit();
	            System.out.println("User registered successfully!");
	            //MyApp.registerUser();
	        } else {
	            // If the user-mail is taken, display an error message
	            System.out.println("User already exists. Do you want to login? (yes/no)");
	            
	            Scanner sc = new Scanner(System.in);
	            String loginChoice = sc.nextLine().toLowerCase();
		        if (loginChoice.equals("yes")) {
		        	MyApp.loginUser();
		        } else {
		            System.out.println("Exiting...");
		        }
	        }
	        session.close();
		}
		catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		
	}

	@Override
	public User loginUser(String email, String password) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
		    // HQL query to check if the user with the given email and password exists
		    String hql = "FROM User WHERE umail = :umail AND upass = :upass";
	
		    User user = (User) session.createQuery(hql)
		            .setParameter("umail", email)
		            .setParameter("upass", password)
		            .uniqueResult();
	
		    transaction.commit();
		   
		    return user;   
		} 
		catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

}
