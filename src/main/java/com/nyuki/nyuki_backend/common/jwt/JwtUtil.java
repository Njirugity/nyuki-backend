package com.nyuki.nyuki_backend.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpirationMs;
    private SecretKey key;

    public long getExpirationMs(){
        return jwtExpirationMs;
    }

    public String getTokenFromHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }

    private Key key(){
        if(key == null){
            key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        }
        return key;
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }
    public Claims getAllClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String getUsernameFromToken(String token){
        return getAllClaims(token).getSubject();
    }
    public boolean validateJwtToken(String token){
        try {
            getAllClaims(token);
            return true;
        } catch (SecurityException e){
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e){
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e){
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e){
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }
}
