package com.ty.eat.n.sleep.dao;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eat.n.sleep.ExcelSheetHelper.RoomExcelSheetGenerator;
import com.ty.eat.n.sleep.ExcelSheetHelper.UserExcelSheetGenerator;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.dto.User;
import com.ty.eat.n.sleep.repository.RoomRepository;

@Repository
public class RoomDao {
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private BranchDao branchDao;

	public Room saveRoom(int branchId, Room room) {

		Branch branch = branchDao.getBranchById(branchId);
		if (branch != null) {
			room.setBranch(branch);
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

	public Room updateRoom(int id, int bid, Room room) {
		Branch b1 = branchDao.getBranchById(bid);

		Room existingRoom = getRoomById(id);
		if (existingRoom != null && b1 != null) {
			room.setId(id);
			room.setBranch(b1);
			roomRepository.save(room);
			return room;

		}
		return null;
	}

	public ByteArrayInputStream load() {
		List<Room> rooms = roomRepository.findAll();
		ByteArrayInputStream in = RoomExcelSheetGenerator.roomsToExcel(rooms);
		return in;
	}

}
