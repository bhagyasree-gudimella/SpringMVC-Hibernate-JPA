package com.lavanya.gudimella.dao;

import java.util.ArrayList;    
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lavanya.gudimella.dto.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void savePerson(Person person) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(person);
		transaction.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPersons() {
		List<Person> personsList = new ArrayList<Person>();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		personsList = session.createQuery("from Person").list();
		transaction.commit();
		session.close();
		return personsList;
	}

	@Override
	public void updatePerson(Person person) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(person);
		transaction.commit();
		session.close();
	}

	@Override
	public void deletePerson(Person person) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(person);
		transaction.commit();
		session.close();
	}
	
	@Override
	public Person getPersonById(int person_id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person person = session.get(Person.class, person_id);
		transaction.commit();
		session.close();
		return person;
	}

	@Override
	public Person findByEmail(String email_address) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person person = (Person) session.createQuery("from Person where email_address = :email_address").setParameter("email_address", email_address).getSingleResult();
		transaction.commit();
		session.close();
		return person;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findUser(String email_address) {
		boolean isUserFound = false;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Person> personsList = new ArrayList<Person>();
		personsList = session.createQuery("from Person where email_address = :email_address").setParameter("email_address", email_address).getResultList();
		for(Person person : personsList) {
			String email = person.getEmail_address();
			if(email.trim().equals(email_address.trim())) {
				isUserFound=true;
			}
		}
		transaction.commit();
		session.close();
		return isUserFound;
	}
}
