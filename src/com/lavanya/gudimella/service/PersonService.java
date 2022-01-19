package com.lavanya.gudimella.service;

import java.util.List; 
import com.lavanya.gudimella.dto.Person;
import com.lavanya.gudimella.exceptions.PersonFormValidationException;

public interface PersonService {

	public void savePerson(Person person);

	public List<Person> getAllPersons();

	public void updatePerson(Person person);

	public void deletePerson(Person person);

	public Person getPersonById(int person_id);

	public Person findByEmail(String email_address);
	
	public boolean findUser(String email_address);

	public void personalInfoFormValidation(String first_name, String middle_name, String last_name,
			String email_address) throws PersonFormValidationException;

	public void contactInfoFormValidation(String address, String city, String state, String country, String zipcode,
			String phone_number) throws PersonFormValidationException;

	public void bankInfoFormValidation(String bank_name, String account_details, String ssn_number)
			throws PersonFormValidationException;

	public void validateEmail(String email_address) throws PersonFormValidationException;

	public void updatePersonFormValidation(String city, String state, String country, String zipcode, String phone_number,
			String bank_name, String account_details) throws PersonFormValidationException;
}
