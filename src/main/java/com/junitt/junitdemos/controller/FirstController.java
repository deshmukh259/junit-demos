package com.junitt.junitdemos.controller;

import com.junitt.junitdemos.UserDao;
import com.junitt.junitdemos.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FirstController {

    @Autowired
    private UserDao userDao;
    @GetMapping(value = "/time")
    public long getTime(){
        return System.currentTimeMillis();
    }

    @PostMapping(value = "/user")
    public String addUser(@RequestBody User user){

        user = userDao.save(user);
        System.out.println("user saved id "+user.getUsername());
        return user.getUsername();
    }
    @GetMapping(value = "/user/{username}")
    public User getUser(@PathVariable String username){

        Optional<User> user = userDao.findById(username);
        System.out.println("user saved id "+user.get().getUsername());
        return user.get();
    }
}
