package com.ty.eat.n.sleep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.User;
import com.ty.eat.n.sleep.service.UserService;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/excel")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);

	}

	@GetMapping("user")
	public ResponseEntity<ResponseStructure<User>> getUser(@RequestParam int id) {
		return userService.getUser(id);

	}

	@PutMapping("user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam int id, @RequestBody User user) {
		return userService.updateUser(id, user);

	}

	@DeleteMapping("user")
	public ResponseEntity<ResponseStructure<Boolean>> deleteUser(@RequestParam int id) {
		return userService.deleteUser(id);

	}

	@GetMapping("allusers")
	public ResponseEntity<ResponseStructure<List<User>>> allGuests() {
		return userService.getAllUsers();

	}

	@GetMapping("/downloadusers")
	public ResponseEntity<Resource> getFile() {
		String filename = "usersExcel.xlsx";
		InputStreamResource file = new InputStreamResource(userService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

}
