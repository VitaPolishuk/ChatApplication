package com.chat.security;

import com.chat.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "chatApp";

    public User validate(String token) {

        User jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new User();

            jwtUser.setLogin(body.getSubject());
            jwtUser.setPassword((String)body.get("password"));
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
