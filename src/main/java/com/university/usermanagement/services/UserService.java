package com.university.usermanagement.services;

import java.util.List;

import com.university.usermanagement.aggregates.user.User;
import com.university.usermanagement.aggregates.user.dtos.UpdateUserDto;

public interface UserService {
	
	
	List<User> readAllUsers();

	User readUserBySocialSecurityNumber(String socialSecurityNumber);

	User createUser(User user);

	User updateUserByUuid(String uuid, UpdateUserDto dto);

	void deleteUserBySocialSecurityNumber(String socialSecurityNumber);

	User assignARoleByNameToAUserBySocialSecurityNumber(String userSocialSecurityNumber, String roleName);

	List<User> readSpecificUsers(User user);


}
