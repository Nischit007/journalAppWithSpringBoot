package com.example.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.journalApp.Repository.UserRepository;
import com.example.journalApp.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user != null) {
			return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
					.password(user.getPassword()).roles("USER").build();
		}

		throw new UsernameNotFoundException("User not found with username: " + username);
	}
}
