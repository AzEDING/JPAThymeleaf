package com.setproject.domain.user.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserIdAndIsdel(Long userId, Boolean isdel);
	Optional<User> findByEmailAndIsdel(String email, Boolean isdel);
}
