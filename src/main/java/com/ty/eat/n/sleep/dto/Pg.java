package com.ty.eat.n.sleep.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Data
public class Pg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private List<Branch> branches;
	private String ownerName;
	@JsonIgnore
	public List<Branch> getBranches() {
		return branches;
	}
	
	
}
