package com.groupeisi.MicroServicieUtilisateur.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret = "MaCléSecrèteMaCléSecrèteMaCléSecrète";
    private final long expiration = 86400000;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenExpired(String token) {
        try {
            Date expirationDate = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expirationDate.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

//    public boolean validateToken(String token, String username) {
//        final String tokenUsername = getUsernameFromToken(token);
//        return (tokenUsername.equals(username) && !isTokenExpired(token));
//    }

    public boolean validateToken(String token, String username) {
        try {
            final String tokenUsername = getUsernameFromToken(token);
            boolean valid = (tokenUsername.equals(username) && !isTokenExpired(token));
            System.out.println("Token valide ? " + valid);
            return valid;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
