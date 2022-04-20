package com.ty.eat.n.sleep.service;

import java.io.ByteArrayInputStream;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.ExcelSheetHelper.UserExcelSheetGenerator;
import com.ty.eat.n.sleep.dao.UserDao;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.User;
import com.ty.eat.n.sleep.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		if (user != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfully Saved");
			responseStructure.setData(userDao.saveUser(user));
			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Saved");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<User>> getUser(int id) {
		if (userDao.getUser(id) != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfully");
			responseStructure.setData(userDao.getUser(id));
			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteUser(int id) {
		if (userDao.deleteUser(id)) {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfully deleted");
			responseStructure.setData(true);
			ResponseEntity<ResponseStructure<Boolean>> responseEntity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMessage("User Not Deleted");
			responseStructure.setData(false);
			ResponseEntity<ResponseStructure<Boolean>> responseEntity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
		if (userDao.getAllUsers().size() > 0) {
			ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfuly");
			responseStructure.setData(userDao.getAllUsers());
			ResponseEntity<ResponseStructure<List<User>>> responseEntity = new ResponseEntity<ResponseStructure<List<User>>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found List");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<List<User>>> responseEntity = new ResponseEntity<ResponseStructure<List<User>>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(int id, User user) {
		if (userDao.updateUser(id, user) != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setData(userDao.updateUser(id, user));
			responseStructure.setMessage("Updated Sucessfuly");
			responseStructure.setStatus(HttpStatus.OK.value());
			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setData(null);
			responseStructure.setMessage("Not Updated Sucessfuly");
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			ResponseEntity<ResponseStructure<User>> responseEntity = new ResponseEntity<ResponseStructure<User>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ByteArrayInputStream load() {
		ByteArrayInputStream in = userDao.load();
		return in;
	}
}
