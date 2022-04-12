package com.ty.eat.n.sleep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.UserDao;
import com.ty.eat.n.sleep.dto.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	public User getUser(int id) {
		if (userDao.getUser(id) != null) {
			return userDao.getUser(id);
		} else {
			return null;
		}
	}

	public boolean deleteUser(int id) {
		if (userDao.deleteUser(id)) {
			return true;
		} else {
			return false;
		}
	}

	public List<User> getAllUsers() {
		if (userDao.getAllUsers().size() > 0) {
			return userDao.getAllUsers();
		} else {
			return null;
		}
	}

	public User updateUser(int id, User user) {
		if (userDao.updateUser(id, user) != null) {
			return userDao.updateUser(id, user);
		} else {
			return null;
		}
	}
}
