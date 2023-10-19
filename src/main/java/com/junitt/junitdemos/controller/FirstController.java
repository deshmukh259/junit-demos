package com.junitt.junitdemos.controller;

import com.junitt.junitdemos.UserDao;
import com.junitt.junitdemos.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FirstController {

    @Autowired
    private UserDao userDao;
    @GetMapping(value = "/time")
    public ResponseEntity<Long> getTime(){
        return ResponseEntity.ok(System.currentTimeMillis());
    }

    @PostMapping(value = "/user")
    public String addUser(@RequestBody User user){

        user = userDao.save(user);
        System.out.println("user saved id "+user.getUsername());
        return user.getUsername();
    }
    @GetMapping(value = "/user/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){

        try {
            Optional<User> user = userDao.findById(username);
            return ResponseEntity.ok(user.get());
        } catch (Exception e) {
            e.printStackTrace();
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }
}
