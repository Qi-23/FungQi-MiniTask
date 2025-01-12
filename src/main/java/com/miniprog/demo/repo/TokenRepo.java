package com.miniprog.demo.repo;

import com.miniprog.demo.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TokenRepo  extends MongoRepository<Token, String> {
    @Query("{token:'?0'}")
    Token findByToken(String token);
}
