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

import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.User;
import com.ty.eat.n.sleep.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@PostMapping("user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user)
	{
		return userService.saveUser(user);
		
	}
	
	
	
	@GetMapping("user")
	public ResponseEntity<ResponseStructure<User>> getUser(@RequestParam int id)
	{
		return  userService.getUser(id);
		
	}
	@PutMapping("user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam int id,@RequestBody User user)
	{
		return userService.updateUser(id, user);
		
	}
	
	@DeleteMapping("user")
	public ResponseEntity<ResponseStructure<Boolean>> deleteUser(@RequestParam int id)
	{
		return userService.deleteUser(id);
		
	}
	@GetMapping("allusers")
	public ResponseEntity<ResponseStructure<List<User>>> allGuests()
	{
		return userService.getAllUsers();
		
	}
	

}
