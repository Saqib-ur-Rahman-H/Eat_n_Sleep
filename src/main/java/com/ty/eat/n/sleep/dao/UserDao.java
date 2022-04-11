package com.ty.eat.n.sleep.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eat.n.sleep.dto.User;
import com.ty.eat.n.sleep.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getUser(int id) {
		Optional<User> optional = userRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public boolean deleteUser(int id) {
		User user = getUser(id);
		if (user != null) {
			userRepository.deleteById(id);
			return true;

		}
		return false;
	}

	public User updateUser(int id, User user) {
		User existingUser = getUser(id);
		if (existingUser != null) {
			userRepository.save(user);
			return user;

		}
		return null;
	}

}
