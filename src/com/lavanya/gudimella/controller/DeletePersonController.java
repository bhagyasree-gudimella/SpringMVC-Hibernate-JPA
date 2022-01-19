package com.lavanya.gudimella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lavanya.gudimella.dto.Person;
import com.lavanya.gudimella.exceptions.PersonFormValidationException;
import com.lavanya.gudimella.service.PersonService;

@RestController
@RequestMapping("/person/deletePerson")
public class DeletePersonController {
	
	@Autowired
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	private ModelAndView bindDeletePerson() {
		ModelAndView modelAndView = new ModelAndView("DeleteUser");
		Person person = new Person();
		modelAndView.addObject("person", person);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	private ModelAndView deletePerson(@ModelAttribute("person") Person person, ModelAndView modelAndView) {
		String result=null;
		try {
			personService.validateEmail(person.getEmail_address());
		} catch (PersonFormValidationException e) {
			result=e.getErrorMessage();
			e.printStackTrace();
		}
		
		if(result==null) {
			Person mPerson = personService.findByEmail(person.getEmail_address());
			personService.deletePerson(mPerson);
			
			modelAndView = new ModelAndView("Success");
			result = person.getEmail_address() +", has been deleted successfully";
			modelAndView.addObject("result", result);
		} else {
			//redirect to error page
			modelAndView = new ModelAndView("ErrorPage");
			modelAndView.addObject("result", result);
		}
		return modelAndView;
	}
}
