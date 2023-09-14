package com.university.usermanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.university.usermanagement.aggregates.user.Role;
import com.university.usermanagement.aggregates.user.dtos.UpdateRoleDto;
import com.university.usermanagement.repositories.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class RoleServiceImpl implements RoleService{


	private final RoleRepository roleRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository=roleRepository;
	}


	@Override
	public List<Role> readAllRoles() {
		return roleRepository.findAll();
	}


	@Override
	@Transactional
	public Role createRole(Role role) {
		Optional<Role> roleRetrieved = roleRepository.findByName(role.getName());
		if(roleRetrieved.isPresent()) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Role Already Exists");
		}
		roleRepository.save(role);
		return roleRepository.findById(role.getUuid()).get();
	}


	@Override
	@Transactional
	public Role updateRoleByUuid(String uuid, UpdateRoleDto dto) {
		Optional<Role> roleRetrieved=roleRepository.findById(uuid);
		if(roleRetrieved.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Role Not Found");
		}
		if(Optional.ofNullable(dto.getName()).isEmpty() || dto.getName().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Role Name Required");
		}
		if(roleRetrieved.get().getName().equals(dto.getName())) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Update Already Done");
		}
		roleRetrieved.get().setName(dto.getName());
		return roleRepository.save(roleRetrieved.get());
	}


	@Override
	public void deleteRoleByUuid(String uuid) {
		Optional<Role> roleRetrieved = roleRepository.findById(uuid);
		if(roleRetrieved.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Role Not Found");
		}
		roleRepository.delete(roleRetrieved.get());
	}


}
