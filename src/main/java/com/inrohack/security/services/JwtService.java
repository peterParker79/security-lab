package com.inrohack.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

// Clase para el servcio de generación de Tokens
@Service
public class JwtService {
    private static final String SECRET = "secret_key";
    public String generateToken(String username, String roles) {
        return JWT.create()
                .withSubject(username)
                .withClaim("roles", roles) //la petición
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour
                .sign(Algorithm.HMAC256(SECRET));
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    // Method to extract username (subject) from the token
    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();
    }

    public String extractRoles(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);

        return jwt.getClaim("roles").asString();
    }


}
