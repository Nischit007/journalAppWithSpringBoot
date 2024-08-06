package net.engineeringdigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;

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
            oldEntry.setUserName(newEntry.getUserName() != null && !newEntry.getUserName().isEmpty() ? newEntry.getUserName() : oldEntry.getUserName());
            oldEntry.setPassword(newEntry.getPassword() != null && !newEntry.getPassword().isEmpty() ? newEntry.getPassword() : oldEntry.getPassword());
            userService.addUser(oldEntry);
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
