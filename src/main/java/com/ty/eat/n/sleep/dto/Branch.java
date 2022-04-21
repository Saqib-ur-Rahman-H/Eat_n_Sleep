package com.ty.eat.n.sleep.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 2, max = 30)
	private String name;
	@Email(message = "Enter Email Properley")
	private String email;
	@Size(min = 10, max = 17, message = "Number should have at least 10 or less than 17 digits")

	@NotNull(message = "Phone Number Should Be Not Null")
	@Size(min = 10, max = 10)
	private long phoneNo;

	@NotNull
	@Size(min = 2, max = 30)
	private String pgName;

	@NotNull
	@Size(min = 2, max = 30)
	private String address;

	@NotNull
	@Size(min = 2, max = 30)
	private String wifiPassword;
	@OneToMany(mappedBy = "branch")
	private List<Room> availableRooms;
	@OneToMany(mappedBy = "branch")
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
