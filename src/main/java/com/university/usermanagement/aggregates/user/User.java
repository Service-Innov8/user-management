package com.university.usermanagement.aggregates.user;

import java.util.Set;

import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{


	@Id
	@UuidGenerator
	@Column(name="uuid")
	private String uuid;
	@Column(name="social_security_number")
	private String socialSecurityNumber;
	@Column(name="last_name")
	private String lastName;
	@Column(name="first_name")
	private String firstName;
	@Column(name="phone")
	private String phone;
	@Column(name="father_name")
	private String fatherName;
	@Column(name="mother_name")
	private String motherName;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_uuid"),
			inverseJoinColumns = @JoinColumn(name = "role_uuid"))
	private Set<Role> roles;


}
