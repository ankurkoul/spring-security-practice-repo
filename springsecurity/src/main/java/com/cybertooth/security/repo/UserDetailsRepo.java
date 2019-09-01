package com.cybertooth.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybertooth.security.pojo.User;

public interface UserDetailsRepo extends JpaRepository<User	, Long> {

	 User findByUsername(String username);
	
}
