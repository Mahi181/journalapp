package com.mahiman.journalapp.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mahiman.journalapp.entity.User;
import com.mahiman.journalapp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        public void saveEntry(User user) {
            // here we are calling the save method of the repository to save the entry in the database
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER")); 
            userRepository.save(user);
    
        }
    
        public List<User> getAll() {
            // here we are calling the findAll method of the repository to get all the entries from the database
            return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        // here we are calling the findById method of the repository to get the entry by id from the
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        // here we are calling the deleteById method of the repository to delete the entry from the database
        userRepository.deleteById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);

    }
}
// controller calls--> service calls --> repository
