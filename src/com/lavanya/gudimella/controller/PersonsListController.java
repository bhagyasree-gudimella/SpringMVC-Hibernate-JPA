package com.lavanya.gudimella.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lavanya.gudimella.dto.Person;
import com.lavanya.gudimella.service.PersonService;

@RestController
@RequestMapping("/person/list")
public class PersonsListController {
	
	@Autowired
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value="/personsList", method=RequestMethod.GET)
	private ModelAndView modelAndView() {
		List<Person> personsList = new ArrayList<Person>();
		ModelAndView modelAndView = new ModelAndView("ViewPersonsList");
		personsList = personService.getAllPersons();
		modelAndView.addObject("personsList", personsList);
		return modelAndView;
	}
}
