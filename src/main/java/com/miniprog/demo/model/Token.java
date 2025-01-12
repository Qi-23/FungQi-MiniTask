package com.miniprog.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "Token", language = "none")
public class Token {
    @Id
    private String id;

    @Indexed(unique = true)
    private String token;

    private String username;
    private String status;

    public Token (String token, String username, String status) {
        this.token = token;
        this.username = username;
        this.status = status;
    }

}
