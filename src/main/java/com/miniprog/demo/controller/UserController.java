package com.miniprog.demo.controller;

import com.miniprog.demo.Service.UserService;
import com.miniprog.demo.model.User;
import com.miniprog.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{username}")
    public String getUser(@PathVariable String username) {
        User thisUser = userRepo.findByUsername(username);
        return "Username : " + thisUser.getUsername() + " Password : " + thisUser.getPassword();
    }

    @PostMapping("/saveUser")
    public String getUser(@RequestBody User user) {
        userRepo.save(user);
        return "User saved, username: " + user.getUsername();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
//        return "success";
    }
}
