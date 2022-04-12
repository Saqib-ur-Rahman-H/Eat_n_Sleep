package com.ty.eat.n.sleep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.BranchDao;
import com.ty.eat.n.sleep.dao.RoomDao;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Room;

@Service
public class RoomService {
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private BranchDao branchDao;
	public Room saveroom(int id,Room room) {
		Branch branch = branchDao.getBranchById(id);
		List<Room> rooms = branch.getAvailableRooms();
		rooms.add(room);
		branch.setAvailableRooms(rooms);
		return roomDao.saveRoom(id, room);
	}

	public Room getRoom(int id) {
		if (roomDao.getRoomById(id) != null) {
			return roomDao.getRoomById(id);
		} else {
			return null;
		}
	}

	public boolean deleteRoom(int id) {
		if (roomDao.deleteRoomById(id)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Room> getAllRooms() {
		if (roomDao.getAllRooms().size() > 0) {
			return roomDao.getAllRooms();
		} else {
			return null;
		}
	}

	public Room updateroom(int id, Room room) {
		if (roomDao.updateRoom(id, room) != null) {
			return roomDao.updateRoom(id, room);
		} else {
			return null;
		}
	}
}
