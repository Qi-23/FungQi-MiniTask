package com.miniprog.demo.controller;

import com.miniprog.demo.Service.JWTService;
import com.miniprog.demo.Service.TokenService;
import com.miniprog.demo.Service.UserService;
import com.miniprog.demo.model.Token;
import com.miniprog.demo.model.User;
import com.miniprog.demo.repo.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
public class AuthController {

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greet() {
        return "Welcome";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String token = userService.verify(user);
        Token tokenObj = new Token(token, user.getUsername(), "valid");

        tokenRepo.save(tokenObj);
        return "username: " + user.getUsername() + "\ntoken: " + token;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        Token updatedToken = tokenService.updateTokenStatus(token, "logout");

        return "Token username : " + updatedToken.getUsername() + "\nToken status : " + updatedToken.getStatus();
    }

}
