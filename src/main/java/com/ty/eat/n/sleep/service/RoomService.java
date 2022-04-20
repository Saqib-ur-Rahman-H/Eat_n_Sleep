package com.ty.eat.n.sleep.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.BranchDao;
import com.ty.eat.n.sleep.dao.RoomDao;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.Room;

@Service
public class RoomService {
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private BranchDao branchDao;

	public ResponseEntity<ResponseStructure<Room>> saveroom(int id, Room room) {
		Branch branch = branchDao.getBranchById(id);
		List<Room> rooms = branch.getAvailableRooms();
		rooms.add(room);
		branch.setAvailableRooms(rooms);
		ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Room saved Sucessfully");
		responseStructure.setData(roomDao.saveRoom(id, room));
		ResponseEntity<ResponseStructure<Room>> responseEntity=new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.OK); 
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Room>> getRoom(int id) {
		if (roomDao.getRoomById(id) != null) {
			ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessful");
			responseStructure.setData(roomDao.getRoomById(id));
			ResponseEntity<ResponseStructure<Room>> responseEntity=new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.OK); 
			return responseEntity;

		} else {
			ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Id Not Found");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Room>> responseEntity=new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;

		}
	}

	public 	ResponseEntity<ResponseStructure<Boolean>> deleteRoom(int id) {
		if (roomDao.deleteRoomById(id)) {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Sucessfuly");
			responseStructure.setData(true);
			ResponseEntity<ResponseStructure<Boolean>> responseEntity=new ResponseEntity<ResponseStructure<Boolean>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Deleted Sucessfuly");
			responseStructure.setData(false);
			ResponseEntity<ResponseStructure<Boolean>> responseEntity=new ResponseEntity<ResponseStructure<Boolean>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<List<Room>>> getAllRooms() {
		if (roomDao.getAllRooms().size() > 0) {
			ResponseStructure<List<Room>> responseStructure = new ResponseStructure<List<Room>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfuly");
			responseStructure.setData(roomDao.getAllRooms());
			ResponseEntity<ResponseStructure<List<Room>>> responseEntity=new ResponseEntity<ResponseStructure<List<Room>>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
			ResponseStructure<List<Room>> responseStructure = new ResponseStructure<List<Room>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Server Eror");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<List<Room>>> responseEntity=new ResponseEntity<ResponseStructure<List<Room>>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<Room>> updateroom(int id,int bid, Room room) {
		if (roomDao.updateRoom(id,bid, room) != null) {
			ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfuly Updated");
			responseStructure.setData(roomDao.updateRoom(id,bid, room));
			ResponseEntity<ResponseStructure<Room>> responseEntity=new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
			ResponseStructure<Room> responseStructure = new ResponseStructure<Room>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Not Updated ");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Room>> responseEntity=new ResponseEntity<ResponseStructure<Room>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}
	public ByteArrayInputStream load() {
		ByteArrayInputStream in = roomDao.load();
		return in;
	}
}
