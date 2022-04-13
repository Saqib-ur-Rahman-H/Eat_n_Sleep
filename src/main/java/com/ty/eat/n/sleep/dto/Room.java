package com.ty.eat.n.sleep.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int size;
	private double cost;
	private String roomType;
	private String image;
	private String discription;
	@OneToMany(mappedBy = "room")
	private List<Guest> guests;

	@ManyToOne
	@JoinColumn
	private Branch branch;

}
