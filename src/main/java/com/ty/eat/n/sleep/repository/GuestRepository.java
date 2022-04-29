package com.ty.eat.n.sleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.eat.n.sleep.dto.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {

	
}
