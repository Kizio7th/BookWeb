package com.example.demo.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.config.JwtToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import lombok.SneakyThrows;

@Component
public class SecurityUtils {

    // private static final String AUTHORIZATION_HEADER = "Authorization";
    // private static final String AUTHORIZATION_PREFIX = "Bearer_";

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private int JWT_EXPIRATION;

    public Key getKey(String secretKey) {
        return Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes());
    }

    @SneakyThrows
    public String generateToken(JwtToken jwtToken) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + this.JWT_EXPIRATION);
        Key key = getKey(this.SECRET_KEY);
        return Jwts.builder()
                .subject(JsonUtils.toJson(jwtToken))
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String validateToken(String token) {
        try {
            return Jwts.parser().verifyWith((SecretKey) getKey(this.SECRET_KEY)).build().parseSignedClaims(token)
                    .getPayload().getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public static JwtToken getSession() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("Not authorized.");
        }
        return (JwtToken) authentication.getPrincipal();
    }
}
