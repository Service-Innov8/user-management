package com.university.usermanagement.aggregates.user;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
public class Role{

	
	@Id
	@UuidGenerator
	@Column(name="uuid")
	private String uuid;
	@NonNull
	@Column(name="name")
	private String name;
	

}
