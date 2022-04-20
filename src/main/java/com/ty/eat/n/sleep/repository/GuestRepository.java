package com.ty.eat.n.sleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.eat.n.sleep.dto.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
//	@Query("select r from room r where room r.id = (select g.room_Id  from Guest g where g.id = ?1 )")
//	public Guest findRoomById(int Id);
	
}
