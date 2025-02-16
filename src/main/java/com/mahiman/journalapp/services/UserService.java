package com.mahiman.journalapp.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mahiman.journalapp.entity.User;
import com.mahiman.journalapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveNewUser(User user) {
try {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // Ensure password is hashed
            user.setRoles(Arrays.asList("USER")); 
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

       
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Ensure password is hashed
        user.setRoles(Arrays.asList("USER","ADMIN")); 
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
