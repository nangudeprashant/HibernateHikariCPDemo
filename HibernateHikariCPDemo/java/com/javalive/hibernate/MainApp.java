package com.javalive.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javalive.entity.Person;

/**
 * @author JavaLive.com
 */
public class MainApp {

	public static void main(String[] args) {
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
