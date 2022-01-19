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
@RequestMapping("/person/bankInfo")
public class BankInfoController {
	
	@Autowired
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value="/addBankInfo", method=RequestMethod.GET)
	private ModelAndView bindBankInfo() {
		ModelAndView modelAndView = new ModelAndView("BankInfo");
		Person person = new Person();
		modelAndView.addObject("person", person);
		return modelAndView;
	}
	
	@RequestMapping(value="/addBankInfo", method=RequestMethod.POST)
	private ModelAndView addContactInfo(@SessionAttribute("person") Person mPerson, @ModelAttribute("person") Person person, ModelAndView modelAndView) {
		System.out.println("First name : " +mPerson.getFirst_name());
		System.out.println("Address : " +mPerson.getAddress());
		System.out.println("Bank name : " +person.getBank_name());
		
		String result=null;
		try {
			personService.bankInfoFormValidation(person.getBank_name(), person.getAccount_details(), person.getSsn_number());
		} catch (PersonFormValidationException e) {
			result=e.getErrorMessage();
			e.printStackTrace();
		}
		
		if(result==null) {
			// set the person details to session's person object
			mPerson.setBank_name(person.getBank_name());
			mPerson.setAccount_details(person.getAccount_details());
			mPerson.setSsn_number(person.getSsn_number());
			
			if(personService.findUser(mPerson.getEmail_address())) {
				//redirect to error page
				modelAndView = new ModelAndView("ErrorPage");
				result = "User already found in database";
				modelAndView.addObject("result", result);
			} else {
				//save to database
				personService.savePerson(mPerson);
				
				//redirect to success page
				modelAndView = new ModelAndView("RegistrationSuccess");
				modelAndView.addObject("mPerson", mPerson);
			}
		} else {
			//redirect to error page
			modelAndView = new ModelAndView("ErrorPage");
			modelAndView.addObject("result", result);
		}
		return modelAndView;
	}
}
