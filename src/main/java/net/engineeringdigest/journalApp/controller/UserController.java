package net.engineeringdigest.journalApp.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();       
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){

       if(userService.addUser(user)){
        return new ResponseEntity<>(user,HttpStatus.OK);
       };
       
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateUser(@RequestBody User newEntry,@PathVariable String name){
        User oldEntry=userService.FindUserByName(name);
        if(oldEntry!=null){
            oldEntry.setUserName(newEntry.getUserName()!=null && !newEntry.getUserName().equals("")?newEntry.getUserName():oldEntry.getUserName());
            oldEntry.setPassword(newEntry.getPassword()!=null && !newEntry.getPassword().equals("")?newEntry.getPassword():oldEntry.getPassword());
            userService.addUser(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
       

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    






}
