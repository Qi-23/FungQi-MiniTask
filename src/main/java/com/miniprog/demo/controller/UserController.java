package com.miniprog.demo.controller;

import com.miniprog.demo.Service.TokenService;
import com.miniprog.demo.Service.UserService;
import com.miniprog.demo.model.User;
import com.miniprog.demo.repo.TokenRepo;
import com.miniprog.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    TokenRepo tokenRepo;

    @Autowired
    TokenService tokenService;

    @GetMapping("/getUser/{username}")
    public String getUser(@PathVariable String username) {
        User thisUser = userRepo.findByUsername(username);
        return "Username : " + thisUser.getUsername() + " Password : " + thisUser.getPassword();
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "User saved, username: " + user.getUsername();
    }
}
