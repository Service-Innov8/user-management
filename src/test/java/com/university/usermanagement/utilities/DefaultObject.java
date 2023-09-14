package com.university.usermanagement.utilities;

import org.springframework.stereotype.Component;

import com.university.usermanagement.aggregates.user.User;

@Component
public class DefaultObject {
	
	
	public User user() {
		User newUser=new User();
		newUser.setSocialSecurityNumber("123456789");
		newUser.setLastName("Papadakis");
		newUser.setEmail("papaioannis@umail.com");
		newUser.setFatherName("Ioannis");
		newUser.setFirstName("Antonis");
		newUser.setMotherName("Maria");
		newUser.setPassword("1234");
		newUser.setPhone("6945678912");
		return newUser;
	}

	
}
