package com.ty.eat.n.sleep.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String pwd; 
	private long phoneNo;
	@CreationTimestamp
	private LocalDate joiningDate;
	@UpdateTimestamp
	private LocalDate checkoutDate;
	private double totalAmount;
	private double paidAmount;	
	private double pendingAmount;
	private String paymentStatus;
	private String govtID;
	@ManyToOne
    @JoinColumn
	private Room room;
	
	
	@JsonIgnore
	public Room getRoom()
	{
		return room;
		
	}
}
