package com.lscchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lscchat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
