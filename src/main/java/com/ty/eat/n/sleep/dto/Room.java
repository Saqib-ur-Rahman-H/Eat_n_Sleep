package com.ty.eat.n.sleep.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String roomType;
	private String image;
	@OneToMany(mappedBy = "room")
	private List<Guest> guests;
	
	@ManyToOne
	@JoinColumn
	private Branch branch;

	@JsonIgnore
	public List<Guest> getGuests() {
		return guests;
	}
}
