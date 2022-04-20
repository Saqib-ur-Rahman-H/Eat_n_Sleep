package com.ty.eat.n.sleep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.service.RoomService;

@RestController
public class RoomController {
	@Autowired
	private RoomService roomService;
	
	@PostMapping("room")
	public ResponseEntity<ResponseStructure<Room>> saveRoom(@RequestParam int id,    @RequestBody Room room)
	{
		return roomService.saveroom(id,room);
		
	}
	
	
	
	@GetMapping("room")
	public ResponseEntity<ResponseStructure<Room>> getRoom(@RequestParam int id)
	{
		return  roomService.getRoom(id);
		
	}
	@PutMapping("room")
	public ResponseEntity<ResponseStructure<Room>> updateRoom(@RequestParam int id,@RequestParam int bid,@RequestBody Room room)
	{
		return roomService.updateroom(id,bid, room);
		
	}
	
	@DeleteMapping("room")
	public ResponseEntity<ResponseStructure<Boolean>> deleteRoom(@RequestParam int id)
	{
		return roomService.deleteRoom(id);
		
	}
	@GetMapping("allrooms")
	public ResponseEntity<ResponseStructure<List<Room>>> allRooms()
	{
		return roomService.getAllRooms();
		
	}
		
	
	

}
