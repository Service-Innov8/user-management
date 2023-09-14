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

import com.university.usermanagement.aggregates.user.Role;
import com.university.usermanagement.aggregates.user.dtos.UpdateRoleDto;
import com.university.usermanagement.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {


	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService=roleService;
	}


	@GetMapping("/all")
	public List<Role> readAllRoles(){
		return roleService.readAllRoles();
	}

	@PostMapping("/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Role createRole(@RequestBody Role role) {
		return roleService.createRole(role);
	}

	@PutMapping("/{id}")
	public Role updateRole(@PathVariable(name = "id") String uuid, @RequestBody UpdateRoleDto dto) {
		return roleService.updateRoleByUuid(uuid, dto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteRole(@PathVariable(name = "id") String uuid) {
		roleService.deleteRoleByUuid(uuid);
	}


}
