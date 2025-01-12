package com.miniprog.demo.Service;

import com.miniprog.demo.model.Token;
import com.miniprog.demo.repo.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private TokenRepo tokenRepo;

    public Token updateTokenStatus(String token, String newStatus) {
        Token updateToken = tokenRepo.findByToken(token);
        updateToken.setStatus(newStatus);
        tokenRepo.save(updateToken);

        return updateToken;
    }
}
