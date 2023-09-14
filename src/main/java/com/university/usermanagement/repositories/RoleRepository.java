package com.university.usermanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.usermanagement.aggregates.user.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

	Optional<Role> findByName(String name);

}
