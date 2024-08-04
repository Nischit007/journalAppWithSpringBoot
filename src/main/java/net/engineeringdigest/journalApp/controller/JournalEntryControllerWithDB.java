package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;

import java.util.List;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerWithDB {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
     
       List<JournalEntry> all=journalEntryService.getAll();
       if(all!=null && !all.isEmpty()){
         return new ResponseEntity<>(all,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable("username") String name){
     User user = userService.FindUserByName(name);
      List<JournalEntry> all=user.getJournalEntries();
      if(all!=null && !all.isEmpty()){
        return new ResponseEntity<>(all,HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@PathVariable("username") String name,@RequestBody JournalEntry myentry){
        try{
        journalEntryService.saveEntry(myentry,name);
        return new ResponseEntity<>(myentry,HttpStatus.CREATED);
        }catch(Exception e){
          System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
     
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid){
        Optional<JournalEntry>entries= journalEntryService.findById(myid);
        if(entries.isPresent()){
            return new ResponseEntity<>(entries.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{username}/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid,@PathVariable String username){
      
      journalEntryService.deleteByID(myid,username);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{myid}")
    public ResponseEntity<JournalEntry> updateJournalEntryByid(@PathVariable ObjectId myid,@RequestBody JournalEntry newentry,@PathVariable String username){
       JournalEntry oldentry=journalEntryService.findById(myid).orElse(null);
       if(oldentry!=null){
        
        oldentry.setTitle(newentry.getTitle()!=null && !newentry.getTitle().equals("")?newentry.getTitle():oldentry.getTitle());
        oldentry.setContent(newentry.getContent()!=null && !newentry.getContent().equals("")?newentry.getContent():oldentry.getContent());
        journalEntryService.saveEntry(oldentry);
        return new ResponseEntity<>(oldentry,HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
