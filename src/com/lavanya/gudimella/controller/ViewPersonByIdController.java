package com.lavanya.gudimella.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lavanya.gudimella.dto.Person;
import com.lavanya.gudimella.service.PersonService;

@RestController
@RequestMapping("/person")
public class ViewPersonByIdController {
	
	@Autowired
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value="/personById", method=RequestMethod.GET)
	private ModelAndView bindViewPersonByEmail() {
		ModelAndView modelAndView = new ModelAndView("ViewPersonById");
		Person person = new Person();
		modelAndView.addObject("personId", person);
		return modelAndView;
	}
	
	@RequestMapping(value="/personById", method=RequestMethod.POST)
	private ModelAndView viewPersonByEmail(@ModelAttribute("personId") Person person, ModelAndView modelAndView) {
		System.out.println("Person Id : " +person.getPerson_id());
		
		Person mPerson = personService.getPersonById(person.getPerson_id());
		System.out.println(mPerson.getFirst_name());
		
		modelAndView = new ModelAndView("ViewPerson");
		modelAndView.addObject("mPerson", mPerson);
		
		return modelAndView;
	}
}
