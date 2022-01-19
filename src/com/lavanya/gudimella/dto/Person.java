package com.lavanya.gudimella.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Person")
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="p_id")
	private int person_id;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="middle_name")
	private String middle_name;
	
	@Column(name="last_name")
	private String last_name;
	
	@Column(name="email_address")
	private String email_address;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="zipcode")
	private String zipcode;
	
	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="bank_name")
	private String bank_name;
	
	@Column(name="account_details")
	private String account_details;
	
	@Column(name="ssn_number")
	private String ssn_number;
	
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAccount_details() {
		return account_details;
	}
	public void setAccount_details(String account_details) {
		this.account_details = account_details;
	}
	public String getSsn_number() {
		return ssn_number;
	}
	public void setSsn_number(String ssn_number) {
		this.ssn_number = ssn_number;
	}
	@Override
	public String toString() {
		return "Person [first_name=" + first_name + ", middle_name=" + middle_name + ", last_name=" + last_name
				+ ", email_address=" + email_address + ", gender=" + gender + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zipcode=" + zipcode + ", phone_number="
				+ phone_number + ", bank_name=" + bank_name + ", account_details=" + account_details + ", ssn_number="
				+ ssn_number + "]";
	}
}
