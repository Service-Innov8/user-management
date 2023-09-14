package com.university.usermanagement.aggregates.user.dtos;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.university.usermanagement.aggregates.user.Role;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UpdateUserDto {
	
	
	private String socialSecurityNumber;
	private String lastName;
	private String firstName;
	private String phone;
	private String fatherName;
	private String motherName;
	private String email;
	private String password;
	private Set<Role> roles;	

	
}
