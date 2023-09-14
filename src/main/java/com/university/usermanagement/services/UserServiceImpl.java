package com.university.usermanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.university.usermanagement.aggregates.user.Role;
import com.university.usermanagement.aggregates.user.User;
import com.university.usermanagement.aggregates.user.dtos.UpdateUserDto;
import com.university.usermanagement.repositories.RoleRepository;
import com.university.usermanagement.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public List<User> readAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User readUserBySocialSecurityNumber(String socialSecurityNumber) {
		if(userRepository.findBySocialSecurityNumber(socialSecurityNumber).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not Found");
		}
		return userRepository.findBySocialSecurityNumber(socialSecurityNumber).get();
	}

	@Override
	@Transactional
	public User createUser(User user) {
		Optional<User> userRetrieved=userRepository.findBySocialSecurityNumber(user.getSocialSecurityNumber());
		if(userRetrieved.isPresent()) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"User Already Exists");
		}
		userRepository.save(user);
		return userRepository.findById(user.getUuid()).get();
	}

	@Override
	@Transactional
	public User updateUserByUuid(String uuid, UpdateUserDto dto) {
		Optional<User> userRetrieved = userRepository.findById(uuid);
		if (userRetrieved.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not Found");
		}
		userRetrieved.get().setLastName(dto.getLastName()!=null?dto.getLastName():userRetrieved.get().getLastName());
		userRetrieved.get().setFirstName(dto.getFirstName()!=null?dto.getFirstName():userRetrieved.get().getFirstName());
		userRetrieved.get().setPhone(dto.getPhone()!=null?dto.getPhone():userRetrieved.get().getPhone());
		userRetrieved.get().setEmail(dto.getEmail()!=null?dto.getEmail():userRetrieved.get().getEmail());
		userRetrieved.get().setPassword(dto.getPassword()!=null?dto.getPassword():userRetrieved.get().getPassword());
		userRetrieved.get().setFatherName(dto.getFatherName()!=null?dto.getFatherName():userRetrieved.get().getFatherName());
		userRetrieved.get().setMotherName(dto.getMotherName()!=null?dto.getMotherName():userRetrieved.get().getMotherName());
		userRetrieved.get().setRoles(dto.getRoles()!=null?dto.getRoles():userRetrieved.get().getRoles());
		userRepository.save(userRetrieved.get());
		return userRetrieved.get();
	}

	@Override
	public void deleteUserBySocialSecurityNumber(String socialSecurityNumber) {
		Optional<User> userRetrieved = userRepository.findBySocialSecurityNumber(socialSecurityNumber);
		if (userRetrieved.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not Found");
		}
		userRepository.delete(userRetrieved.get());
	}

	@Override
	@Transactional
	public User assignARoleByNameToAUserBySocialSecurityNumber(String userSocialSecurityNumber, String roleName) {
		Optional<User> userRetrieved = userRepository.findBySocialSecurityNumber(userSocialSecurityNumber);
		Optional<Role> roleRetrieved = roleRepository.findByName(roleName);
		if (userRetrieved.isEmpty() || roleRetrieved.isEmpty() ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong Input");
		}
		if(userRetrieved.get().getRoles().contains(roleRetrieved.get())) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Role Already Assigned");
		}
		userRetrieved.get().getRoles().add(roleRetrieved.get());
		userRepository.save(userRetrieved.get());
		return userRetrieved.get();
	}

	@Override
	public List<User> readSpecificUsers(User user) {
		Example<User> userExample=Example.of(user);
		return userRepository.findAll(userExample);
	}

}
