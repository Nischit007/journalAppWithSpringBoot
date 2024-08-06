package net.engineeringdigest.journalApp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean addNewUser(User user) {
        userRepository.save(user);
        return true;
    }

    public boolean addUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword())); 
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("Error is due to: " + e);
            return false;
        }
    }

    public User findUserByName(String name) {
        return userRepository.findByUserName(name);
    }

    public void deleteUserByName(String name) {
        try{
            userRepository.deleteByUserName(name);

        }catch(Exception e){
        throw new UnsupportedOperationException("Unimplemented method 'deleteByName'");
    }
}
}
