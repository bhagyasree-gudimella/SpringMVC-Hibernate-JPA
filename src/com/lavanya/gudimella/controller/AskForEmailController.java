 package com.lavanya.gudimella.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.lavanya.gudimella.dto.Person;
import com.lavanya.gudimella.exceptions.PersonFormValidationException;
import com.lavanya.gudimella.service.PersonService;

@RestController
@RequestMapping("/person/emailInfo")
@SessionAttributes("email_address")
public class AskForEmailController {

	@Autowired
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value="/askForEmail", method=RequestMethod.GET)
	private ModelAndView bindAskEmail() {
		ModelAndView modelAndView = new ModelAndView("AskForEmailAddress");
		Person person = new Person();
		modelAndView.addObject("email_address", person);
		return modelAndView;
	}
	
	@RequestMapping(value="/askForEmail", method=RequestMethod.POST)
	private ModelAndView updatePersonByEmail(@ModelAttribute("email_address") Person person, ModelAndView modelAndView) {
		System.out.println("Email address : " +person.getEmail_address());
		String result=null;
		try {
			personService.validateEmail(person.getEmail_address());
		} catch (PersonFormValidationException e) {
			result=e.getErrorMessage();
			e.printStackTrace();
		}
		
		if(result==null) {
			Person mPerson = personService.findByEmail(person.getEmail_address());
			System.out.println(mPerson.getFirst_name());
			modelAndView  = new ModelAndView("redirect:/person/update/updatePerson");
		} else {
			//redirect to error page
			modelAndView = new ModelAndView("ErrorPage");
			modelAndView.addObject("result", result);
		}
		return modelAndView;
	}
}
