package com.ty.eat.n.sleep.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Pg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 4, max = 50)
	@NotBlank(message = "Name is mandatory")
	private String name;
	@OneToMany
	private List<Branch> branches;

	@Size(min = 4, max = 50)
	@NotBlank(message = "Owner Name is Mandatory")
	private String ownerName;
}
