package net.engineeringdigest.journalApp.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;

import net.engineeringdigest.journalApp.entity.JournalEntry;

import net.engineeringdigest.journalApp.entity.User;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    // @Transactional
    public void saveEntry(JournalEntry journalEntry, String name){

        try{
            User user = userService.findUserByName(name);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved= journalEntryRepository.save(journalEntry);

            user.getJournalEntries().add(saved);
            userService.addUser(user);
        }catch(Exception e){
            System.out.println(e);
            
        }
       
    }

    public void saveEntry(JournalEntry journalEntry){

  journalEntryRepository.save(journalEntry);
       
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public  Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteByID(ObjectId id,String username){
        User user =userService.findUserByName(username);
        user.getJournalEntries().removeIf(x->x.getid().equals(id));
        userService.addUser(user);
        journalEntryRepository.deleteById(id);
    }
}