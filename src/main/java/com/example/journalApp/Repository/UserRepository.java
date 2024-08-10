package com.example.journalApp.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.journalApp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	User findByUsername(String username);

	void deleteByUsername(String username);

}