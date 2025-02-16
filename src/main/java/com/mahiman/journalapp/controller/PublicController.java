package com.mahiman.journalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahiman.journalapp.entity.User;
import com.mahiman.journalapp.services.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
    
@Autowired private UserService userService;

    @GetMapping("/health-check")
    public String healthcheck(){ 

        return "Ok";
    }
    
    @PostMapping
    public void CreateUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
