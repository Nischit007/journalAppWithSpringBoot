package com.example.journalApp.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.entity.User;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, UUID> {

	List<JournalEntry> findByUser(User user);

}
