package com.lavanya.gudimella.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavanya.gudimella.dao.PersonDao;
import com.lavanya.gudimella.dto.Person;
import com.lavanya.gudimella.exceptions.PersonFormValidationException;
import com.lavanya.gudimella.validations.PersonFormValidation;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;

	@Override
	public void savePerson(Person person) {
		personDao.savePerson(person);
	}

	@Override
	public List<Person> getAllPersons() {
		List<Person> personsList = new ArrayList<Person>();
		personsList = personDao.getAllPersons();
		return personsList;
	}

	@Override
	public void updatePerson(Person person) {
		personDao.updatePerson(person);
	}

	@Override
	public void deletePerson(Person person) {
		personDao.deletePerson(person);
	}

	@Override
	public Person getPersonById(int person_id) {
		Person person = personDao.getPersonById(person_id);
		return person;
	}

	@Override
	public Person findByEmail(String email_address) {
		Person person = personDao.findByEmail(email_address);
		return person;
	}
	
	@Override
	public boolean findUser(String email_address) {
		return personDao.findUser(email_address);
	}

	@Override
	public void personalInfoFormValidation(String first_name, String middle_name, String last_name,
			String email_address) throws PersonFormValidationException {
		String result=null;
		PersonFormValidation personFormValidation = new PersonFormValidation();
		result = personFormValidation.validatePersonalInfoForm(first_name, middle_name, last_name, email_address);
		if(result!=null) {
			PersonFormValidationException personFormValidationException = new PersonFormValidationException();
			personFormValidationException.setErrorMessage(result);
			throw personFormValidationException;
		}
	}

	@Override
	public void contactInfoFormValidation(String address, String city, String state, String country, String zipcode,
			String phone_number) throws PersonFormValidationException {
		String result=null;
		PersonFormValidation personFormValidation = new PersonFormValidation();
		result = personFormValidation.validateContactInfoForm(address, city, state, country, zipcode, phone_number);
		if(result!=null) {
			PersonFormValidationException personFormValidationException = new PersonFormValidationException();
			personFormValidationException.setErrorMessage(result);
			throw personFormValidationException;
		}
	}

	@Override
	public void bankInfoFormValidation(String bank_name, String account_details, String ssn_number)
			throws PersonFormValidationException {
		String result=null;
		PersonFormValidation personFormValidation = new PersonFormValidation();
		result = personFormValidation.validateBankInfoForm(bank_name, account_details, ssn_number);
		if(result!=null) {
			PersonFormValidationException personFormValidationException = new PersonFormValidationException();
			personFormValidationException.setErrorMessage(result);
			throw personFormValidationException;
		}
	}

	@Override
	public void validateEmail(String email_address) throws PersonFormValidationException {
		String result=null;
		PersonFormValidation personFormValidation = new PersonFormValidation();
		result = personFormValidation.validateEmailAddress(email_address);
		if(result!=null) {
			PersonFormValidationException personFormValidationException = new PersonFormValidationException();
			personFormValidationException.setErrorMessage(result);
			throw personFormValidationException;
		}
	}

	@Override
	public void updatePersonFormValidation(String city, String state, String country, String zipcode, String phone_number,
			String bank_name, String account_details) throws PersonFormValidationException {
		String result=null;
		PersonFormValidation personFormValidation = new PersonFormValidation();
		result = personFormValidation.updatePersonFormvalidation(city, state, country, zipcode, phone_number, bank_name, account_details);
		if(result!=null) {
			PersonFormValidationException personFormValidationException = new PersonFormValidationException();
			personFormValidationException.setErrorMessage(result);
			throw personFormValidationException;
		}
	}
}
