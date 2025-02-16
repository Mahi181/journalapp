package com.mahiman.journalapp.controller;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahiman.journalapp.entity.User;
import com.mahiman.journalapp.repository.UserRepository;
import com.mahiman.journalapp.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId) {
        Optional<User> user = userService.findById(myId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User userInDb = userService.findByUsername(username);
        if (userInDb != null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword()); 
            userService.saveNewUser(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // @DeleteMapping
    // public ResponseEntity<?> deleteUserById() {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     userRepository.deleteByUserName(authentication.getName());
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}
