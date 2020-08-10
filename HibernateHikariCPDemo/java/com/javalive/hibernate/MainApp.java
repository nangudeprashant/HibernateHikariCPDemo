package com.javalive.hibernate;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javalive.entity.Person;

/**
 * @author JavaLive.com
 */
public class MainApp {
	private static final Logger logger = Logger.getLogger(MainApp.class);
	public static void main(String[] args) {
		BasicConfigurator.configure(); //enough for configuring log4j
		Logger.getRootLogger().setLevel(Level.INFO); //changing log level
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();
			Person person = new Person();
			person.setName("Mike Lewis Jr");
			session.save(person);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

		HibernateUtil.shutdown();
	}
}
