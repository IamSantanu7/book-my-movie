package com.project.demo.dao.impl;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.demo.MyApp;
import com.project.demo.dao.AdminDao;
import com.project.demo.dashboard.AdminDashboard;
import com.project.demo.entity.Admin;
import com.project.demo.utility.HibernateUtil;

/*
 * Santanu
 */

public class AdminDaoImpl implements AdminDao {

	@Override
	public void saveAdmin(Admin admin) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("from Admin where amail= :am and apass= :ap");
			query.setParameter("am", admin.getAmail());
			query.setParameter("ap", admin.getApass());
	        if (query.uniqueResult() == null) {
	            // If the admin-mail is not taken, save the admin
	            session.save(admin);
	            transaction.commit();
	            System.out.println("Admin registered successfully!");
	            //AdminDashboard.start(admin);
		        
	        } else {
	            // If the admin-mail is taken, display an error message
	            System.out.println("Admin already exists. Do you want to login? (yes/no)");
	            
	            Scanner sc = new Scanner(System.in);
	            String loginChoice = sc.nextLine().toLowerCase();
		        if (loginChoice.equals("yes")) {
		        	MyApp.loginAdmin();
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
	public Admin loginAdmin(String email, String password) {
		
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
    
            String hql = "FROM Admin WHERE amail = :amail AND apass = :apass";
    
            Admin admin = (Admin) session.createQuery(hql)
                    .setParameter("amail", email)
                    .setParameter("apass", password)
                    .uniqueResult();
    
            transaction.commit();
    
            return admin;
        }
		catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
		
}

