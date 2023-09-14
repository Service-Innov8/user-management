package com.university.usermanagement.services;

import java.util.List;

import com.university.usermanagement.aggregates.user.Role;
import com.university.usermanagement.aggregates.user.dtos.UpdateRoleDto;

public interface RoleService {
	
	List<Role> readAllRoles();

	Role createRole(Role role);

	Role updateRoleByUuid(String uuid, UpdateRoleDto dto);

	void deleteRoleByUuid(String uuid);

}
