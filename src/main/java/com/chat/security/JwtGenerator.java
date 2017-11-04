package com.chat.security;

import com.chat.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

    public String generate(User jwtUser) {

        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getLogin());
        claims.put("password", String.valueOf(jwtUser.getPassword()));
        claims.put("role", "user");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "chatApp")
                .compact();
    }
}