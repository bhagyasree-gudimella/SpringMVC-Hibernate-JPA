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
@RequestMapping("/person/personalInfo")
@SessionAttributes("person")
public class PersonalInfoController {
	
	@Autowired
	private PersonService personService;

	@RequestMapping(value="/addPersonalInfo", method=RequestMethod.GET)
	private ModelAndView bindPersonalInfo() {
		ModelAndView modelAndView = new ModelAndView("PersonalInfo");
		Person person  = new Person();
		modelAndView.addObject("person", person);
		return modelAndView;
	}
	
	@RequestMapping(value="/addPersonalInfo", method=RequestMethod.POST)
	private ModelAndView addPersonalInfo(@ModelAttribute("person") Person person, ModelAndView modelAndView) {
		System.out.println(person.getFirst_name());
		
		String result = null;
		try {
			personService.personalInfoFormValidation(person.getFirst_name(), person.getMiddle_name(), person.getLast_name(), person.getEmail_address());
		} catch (PersonFormValidationException e) {
			result=e.getErrorMessage();
			e.printStackTrace();
		}
		
		if(result==null) {
			modelAndView = new ModelAndView("redirect:/person/contactInfo/addContactInfo");
		} else {
			modelAndView = new ModelAndView("ErrorPage");
			modelAndView.addObject("result", result);
		}
		
		
		return modelAndView;
	}
}
