package net.engineeringdigest.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;

@RequestMapping("/public")
public class PublicController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();       
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> addUser(@RequestBody User user){

       if(userService.addUser(user)){
        return new ResponseEntity<>(user,HttpStatus.OK);
       };
       
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
