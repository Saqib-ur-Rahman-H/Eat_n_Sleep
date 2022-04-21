package com.ty.eat.n.sleep.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 10, max = 40)
	@NotNull(message = "Name is Mandatory")
	private String name;

	@Email(message = "Email is Mandatory")
	private String email;

	@Size(min = 10, max = 10, message = "Phone Number Should Contain 10 Numbers")
	private long phoneNo;

	@NotNull(message = "Password Is Mandatory")
	private String pwd;

	@Size(min = 10, max = 40)
	private String role;

}
