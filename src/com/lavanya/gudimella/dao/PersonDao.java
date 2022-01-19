package com.lavanya.gudimella.dao;

import java.util.List;
import com.lavanya.gudimella.dto.Person;

public interface PersonDao {
	
	public void savePerson(Person person);
	
	public List<Person> getAllPersons();
	
	public void updatePerson(Person person);
	
	public void deletePerson(Person person);
	
	public Person getPersonById(int person_id);
	
	public Person findByEmail(String email_address);
	
	public boolean findUser(String email_address);
}
