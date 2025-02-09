package com.miniprog.demo.controller;

import com.miniprog.demo.model.Token;
import com.miniprog.demo.repo.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TokenController {
    @Autowired
    TokenRepo tokenRepo;

    @PostMapping("/getTokenStatus")
    @ResponseBody
    public String getTokenStatus(@RequestBody String token, HttpServletRequest request) {
        Token tokenObj = tokenRepo.findByToken(token);
        if (tokenObj == null) {
            return "No token";
        }

        return "Token status: " + tokenObj.getStatus();
    }

    @GetMapping("/validateToken")
    public String validateToken() {
        return "profile";
    }
}
