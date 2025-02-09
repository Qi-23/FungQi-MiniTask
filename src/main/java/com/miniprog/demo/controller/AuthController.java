package com.miniprog.demo.controller;

import com.miniprog.demo.Service.JWTService;
import com.miniprog.demo.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.miniprog.demo.Service.TokenService;
import com.miniprog.demo.Service.UserService;
import com.miniprog.demo.model.Token;
import com.miniprog.demo.model.User;
import com.miniprog.demo.repo.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/notAuth")
    public String notAuth() {
        return "notAuth";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Login page
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, Model model) {
        User user = new User(username, password);

        String token = userService.verify(user);
        if (!"fail".equals(token)) {
            Token tokenObj = new Token(token, user.getUsername(), "valid");

            tokenRepo.save(tokenObj);

            model.addAttribute("username", user.getUsername());
            model.addAttribute("token", token);
            return "profile";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        Token updatedToken = tokenService.updateTokenStatus(token, "logout");

        return "home";
    }

    @PostMapping("/userProfile")
    public String userProfile(Model model, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ") && authHeader.length() > 7) {
            token = authHeader.substring(7);
            try {
                username = jwtService.extractUsername(token);
            } catch (Exception e) {
                model.addAttribute("error", "invalid-token");
                return "notAuth";
            }
        }

        model.addAttribute("username", username);
        model.addAttribute("token", token);
        return "profile";
    }
}
