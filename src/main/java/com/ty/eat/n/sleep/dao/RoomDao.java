package com.ty.eat.n.sleep.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.repository.RoomRepository;

@Repository
public class RoomDao {
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private GuestDao guestDao;

	public Room saveRoom(int guestid, Room room) {
		Guest guest = guestDao.getGuest(guestid);
		List<Guest> guests = room.getGuests();
		if (guest != null) {
			room.setGuests(guests);
			roomRepository.save(room);
			return room;
		}
		return null;
	}

	public Room getRoomById(int id) {

		Optional<Room> optional = roomRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();

	}

	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	public boolean deleteRoomById(int id) {
		Room room = getRoomById(id);
		if (room != null) {
			roomRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Room updateRoom(int id, Room room) {
		Room existingRoom = getRoomById(id);
		if (existingRoom != null) {
			roomRepository.save(room);
			return room;

		}
		return null;
	}

}
