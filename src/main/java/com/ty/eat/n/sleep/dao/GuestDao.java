package com.ty.eat.n.sleep.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.repository.GuestRepository;

@Repository
public class GuestDao {
	@Autowired
	private GuestRepository guestRepository;

	public Guest saveGuest(Guest guest) {
		return guestRepository.save(guest);
	}

	public Guest getGuest(int id) {
		Optional<Guest> optional = guestRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public List<Guest> getAllGuests() {
		return guestRepository.findAll();
	}

	public boolean deleteGuest(int id) {
		Guest guest = getGuest(id);
		if (guest != null) {
			guestRepository.deleteById(id);
			return true;

		}
		return false;
	}

	public Guest updateGuest(int id, Guest guest) {
		Guest existingGuest = getGuest(id);
		if (existingGuest != null) {
			guestRepository.save(guest);
			return guest;

		}
		return null;
	}

	public Guest findRoomByGuestId(int guestId) {
		return guestRepository.findRoomByGuestId(guestId);
	}

}
