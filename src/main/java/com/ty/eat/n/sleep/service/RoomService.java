package com.ty.eat.n.sleep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.BranchDao;
import com.ty.eat.n.sleep.dao.RoomDao;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.Room;

@Service
public class RoomService {
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private BranchDao branchDao;

	public ResponseStructure<Room> saveroom(int id, Room room) {
		Branch branch = branchDao.getBranchById(id);
		List<Room> rooms = branch.getAvailableRooms();
		rooms.add(room);
		branch.setAvailableRooms(rooms);
		ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Room saved Sucessfully");
		responseStructure.setData(roomDao.saveRoom(id, room));
		return responseStructure;
	}

	public ResponseStructure<Room> getRoom(int id) {
		if (roomDao.getRoomById(id) != null) {
			ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessful");
			responseStructure.setData(roomDao.getRoomById(id));
			return responseStructure;

		} else {
			ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Id Not Found");
			responseStructure.setData(null);
			return responseStructure;

		}
	}

	public ResponseStructure<Boolean> deleteRoom(int id) {
		if (roomDao.deleteRoomById(id)) {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Sucessfuly");
			responseStructure.setData(true);
			return responseStructure;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Deleted Sucessfuly");
			responseStructure.setData(false);
			return responseStructure;
		}
	}

	public ResponseStructure<List<Room>> getAllRooms() {
		if (roomDao.getAllRooms().size() > 0) {
			ResponseStructure<List<Room>> responseStructure = new ResponseStructure<List<Room>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfuly");
			responseStructure.setData(roomDao.getAllRooms());
			return responseStructure;
		} else {
			ResponseStructure<List<Room>> responseStructure = new ResponseStructure<List<Room>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Server Eror");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<Room> updateroom(int id, Room room) {
		if (roomDao.updateRoom(id, room) != null) {
			ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfuly Updated");
			responseStructure.setData(roomDao.updateRoom(id, room));
			return responseStructure;
		} else {
			ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Not Updated ");
			responseStructure.setData(null);
			return responseStructure;
		}
	}
}
