package com.miniprog.demo.controller;

import com.miniprog.demo.Service.TokenService;
import com.miniprog.demo.model.Token;
import com.miniprog.demo.model.User;
import com.miniprog.demo.repo.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    @Autowired
    TokenRepo tokenRepo;

    @PostMapping("/getTokenStatus")
    public String getTokenStatus(@RequestBody String token, HttpServletRequest request) {
        Token tokenObj = tokenRepo.findByToken(token);
        if (tokenObj == null) {
            return "No token";
        }

        return "Token status: " + tokenObj.getStatus();
    }
}
