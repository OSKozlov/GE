package com.lux.ge.facade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lux.ge.facade.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
