package com.ty.eat.n.sleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.eat.n.sleep.dto.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
	public Guest findRoomByGuestId(int guestId);
	public Guest findRoomByGovtId(int govtId);
}
