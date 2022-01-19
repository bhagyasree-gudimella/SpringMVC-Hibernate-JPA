package com.lavanya.gudimella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.lavanya.gudimella.dto.Person;
import com.lavanya.gudimella.exceptions.PersonFormValidationException;
import com.lavanya.gudimella.service.PersonService;

@RestController
@RequestMapping("/person/contactInfo")
public class ContactInfoController {
	
	@Autowired
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	@RequestMapping(value="/addContactInfo", method=RequestMethod.GET)
	private ModelAndView bindContactInfo() {
		ModelAndView modelAndView = new ModelAndView("ContactInfo");
		Person person = new Person();
		modelAndView.addObject("person", person);
		return modelAndView;
	}
	
	@RequestMapping(value="/addContactInfo", method=RequestMethod.POST)
	private ModelAndView addContactInfo(@SessionAttribute("person") Person mPerson, @ModelAttribute("person") Person person, ModelAndView modelAndView) {
		System.out.println("Address : " +person.getAddress());
		System.out.println("First name : " +mPerson.getFirst_name());
		
		String result=null;
		try {
			personService.contactInfoFormValidation(person.getAddress(), person.getCity(), person.getState(), person.getCountry(), person.getZipcode(), person.getPhone_number());
		} catch (PersonFormValidationException e) {
			result=e.getErrorMessage();
			e.printStackTrace();
		}
		
		if(result==null) {
			mPerson.setAddress(person.getAddress());
			mPerson.setCity(person.getCity());
			mPerson.setState(person.getState());
			mPerson.setCountry(person.getCountry());
			mPerson.setZipcode(person.getZipcode());
			mPerson.setPhone_number(person.getPhone_number());
			
			modelAndView = new ModelAndView("redirect:/person/bankInfo/addBankInfo");
		} else {
			modelAndView = new ModelAndView("ErrorPage");
			modelAndView.addObject("result", result);
		}
		
		return modelAndView;
	}
}
