package com.miniprog.demo.repo;

import com.miniprog.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepo extends MongoRepository<User, String> {

    @Query("{username:'?0'}")
    User findByUsername(String username);

}
