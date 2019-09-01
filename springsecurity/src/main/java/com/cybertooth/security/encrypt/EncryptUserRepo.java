package com.cybertooth.security.encrypt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EncryptUserRepo extends JpaRepository<EncryptUser	, Long> {

	EncryptUser findByUsername(String username);
}
