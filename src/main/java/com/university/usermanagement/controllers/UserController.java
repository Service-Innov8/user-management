package com.university.usermanagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.university.usermanagement.aggregates.user.User;
import com.university.usermanagement.aggregates.user.dtos.UpdateUserDto;
import com.university.usermanagement.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	

	@GetMapping("/all")
	public List<User> readAllUsers(){
		return userService.readAllUsers();
	}

	@GetMapping("/{ssn}")
	@ResponseStatus(code = HttpStatus.FOUND)
	public User readUserBySocialSecurityNumber(@PathVariable(name = "ssn") String socialSecurityNumber) {
		return userService.readUserBySocialSecurityNumber(socialSecurityNumber);
	}
	
	@GetMapping("/")
	public List<User> readSpecificUsers(@RequestBody User user){
		return userService.readSpecificUsers(user);
	}
	
	@PostMapping("/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable(name = "id") String uuid, @RequestBody UpdateUserDto dto){
		return userService.updateUserByUuid(uuid, dto);
	}
	
	@DeleteMapping("/{ssn}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable(name = "ssn") String socialSecurityNumber) {
			userService.deleteUserBySocialSecurityNumber(socialSecurityNumber);
	}
	
	@PutMapping("/{user_ssn}/{role_name}")
	public User assignARoleToAUser(@PathVariable(name = "user_ssn") String userSocialSecurityNumber, @PathVariable(name = "role_name") String roleName){ 
			return userService.assignARoleByNameToAUserBySocialSecurityNumber(userSocialSecurityNumber, roleName);
	}

	
}
