package com.ty.eat.n.sleep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.UserDao;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseStructure<User> saveUser(User user) {
		if(user!=null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfully Saved");
			responseStructure.setData(userDao.saveUser(user));
			return responseStructure;	
		}
		else {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Saved");
			responseStructure.setData(null);
			return responseStructure;	
		}
	} 

	public ResponseStructure<User> getUser(int id) {
		if (userDao.getUser(id) != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfully");
			responseStructure.setData(userDao.getUser(id));
			return responseStructure;
		} else {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public  ResponseStructure<Boolean> deleteUser(int id) {
		if (userDao.deleteUser(id)) {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfully deleted");
			responseStructure.setData(true);
			return responseStructure;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMessage("User Not Deleted");
			responseStructure.setData(false);
			return responseStructure;
		}
	}

	public ResponseStructure<List<User>> getAllUsers() {
		if (userDao.getAllUsers().size() > 0) {
			ResponseStructure<List<User>>  responseStructure = new ResponseStructure<List<User>> ();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfuly");
			responseStructure.setData(userDao.getAllUsers());
			return responseStructure;
		} else {
			ResponseStructure<List<User>>  responseStructure = new ResponseStructure<List<User>> ();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found List");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<User> updateUser(int id, User user) {
		if (userDao.updateUser(id, user) != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setData(userDao.updateUser(id, user));
			responseStructure.setMessage("Updated Sucessfuly");
			responseStructure.setStatus(HttpStatus.OK.value());
			return responseStructure;
		} else {
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setData(null);
			responseStructure.setMessage("Not Updated Sucessfuly");
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			return responseStructure;
		}
	}
}
