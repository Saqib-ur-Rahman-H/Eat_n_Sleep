package com.ty.eat.n.sleep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.eat.n.sleep.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
