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
@RequestMapping("/person/update")
public class UpdatePersonController {
	
	@Autowired
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value="/updatePerson", method=RequestMethod.GET)
	private ModelAndView bindUpdateForm() {
		ModelAndView modelAndView = new ModelAndView("UpdatePerson");
		Person person = new Person();
		modelAndView.addObject("person", person);
		return modelAndView;
	}
	
	@RequestMapping(value="/updatePerson", method=RequestMethod.POST)
	private ModelAndView updatePerson(@SessionAttribute("email_address") Person sPerson, @ModelAttribute("person") Person person, ModelAndView modelAndView) {
		String result=null;
		try {
			personService.updatePersonFormValidation(person.getCity(), person.getState(), person.getCountry(), person.getZipcode(), person.getPhone_number(), person.getBank_name(), person.getAccount_details());
		} catch (PersonFormValidationException e) {
			result=e.getErrorMessage();
			e.printStackTrace();
		}
		
		if(result==null) {
			Person mPerson = personService.findByEmail(sPerson.getEmail_address());
			System.out.println("First name : " +mPerson.getFirst_name());
			
			mPerson.setAddress(person.getAddress());
			mPerson.setCity(person.getCity());
			mPerson.setState(person.getState());
			mPerson.setCountry(person.getCountry());
			mPerson.setZipcode(person.getZipcode());
			mPerson.setPhone_number(person.getPhone_number());
			mPerson.setBank_name(person.getBank_name());
			mPerson.setAccount_details(person.getAccount_details());
			
			personService.updatePerson(mPerson);
		
			modelAndView = new ModelAndView("Success");
			result = mPerson.getEmail_address() +", has been updated successfully";
			modelAndView.addObject("result", result);
		} else {
			//redirect to error page
			modelAndView = new ModelAndView("ErrorPage");
			modelAndView.addObject("result", result);
		}
		return modelAndView;
	}
}
