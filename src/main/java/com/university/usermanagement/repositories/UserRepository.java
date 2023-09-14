package com.university.usermanagement.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.usermanagement.aggregates.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findBySocialSecurityNumber(String socialSecurityNumber);
	
}
