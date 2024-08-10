package com.example.journalApp.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.journalApp.Repository.JournalEntryRepository;
import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.User;

@Component
public class JournalEntryService {
	@Autowired
	private JournalEntryRepository journalEntryRepository;

	@Autowired
	private UserService userService;

	// @Transactional
	public void saveEntry(JournalEntry journalEntry, String name) {

		try {
			User user = userService.findUserByName(name);
			journalEntry.setUser(user);
			journalEntry.setCreatedAt(Instant.now());
			journalEntry.setUpdatedAt(Instant.now());
			journalEntryRepository.save(journalEntry);
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	public void saveEntry(JournalEntry journalEntry) {

		journalEntryRepository.save(journalEntry);

	}

	public List<JournalEntry> getAll() {
		return journalEntryRepository.findAll();
	}

	public Optional<JournalEntry> findById(UUID id) {
		return journalEntryRepository.findById(id);
	}

	public void deleteByID(UUID id, String username) {
		journalEntryRepository.deleteById(id);
	}

	public List<JournalEntry> getAllByUser(User user) {
		return journalEntryRepository.findByUser(user);
	}
}