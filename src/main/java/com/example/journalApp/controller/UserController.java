package com.example.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journalApp.entity.User;
import com.example.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User newEntry) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User oldEntry = userService.findUserByName(name);
		if (oldEntry != null) {
			oldEntry.setUsername(
					newEntry.getUsername() != null && !newEntry.getUsername().isEmpty() ? newEntry.getUsername()
							: oldEntry.getUsername());
			oldEntry.setPassword(
					newEntry.getPassword() != null && !newEntry.getPassword().isEmpty() ? newEntry.getPassword()
							: oldEntry.getPassword());
			userService.createUser(oldEntry);
			return new ResponseEntity<>(oldEntry, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestBody User newEntry) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userService.deleteUserByName(auth.getName());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
