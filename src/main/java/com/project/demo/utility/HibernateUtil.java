package com.project.demo.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");   //in that file we have all our configuration
		sessionFactory = cfg.buildSessionFactory();    // this will build session factory
		
		return sessionFactory;
	}
	
}
