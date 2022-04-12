package com.ty.eat.n.sleep.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.repository.RoomRepository;

@Repository
public class RoomDao {
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private BranchDao branchDao;
	public Room saveRoom(int branchId, Room room) {
		
			Branch branch = branchDao.getBranchById(branchId);
			branchDao.saveBranch(branch);
			roomRepository.save(room);
			return room;
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
