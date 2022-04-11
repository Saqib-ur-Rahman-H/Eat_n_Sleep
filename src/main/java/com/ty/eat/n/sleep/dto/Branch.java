package com.ty.eat.n.sleep.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data

public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long phoneNo;
	private String pgName;
	private String address;
	@OneToMany(mappedBy = "branch")
	private List<Room> availableRooms;
	private List<Room> bookedRooms;

	@JsonIgnore
	public List<Room> getAvailableRooms() {
		return availableRooms;
	}

	@JsonIgnore
	public List<Room> getBookedRooms() {
		return bookedRooms;
	}

}
