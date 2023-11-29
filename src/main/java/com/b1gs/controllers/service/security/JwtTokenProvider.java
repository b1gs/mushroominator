package com.b1gs.controllers.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private static final SecretKey secretKey = Jwts.SIG.HS256.key().build();


    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000);


        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(authToken);
            logger.info("Claims {} ", claimsJws);
            return true;
        } catch (Exception ex) {
            logger.error("Error decrypt a token ", ex);
        }
//        catch (SignatureException ex) {
//
//        } catch (MalformedJwtException ex) {
//            // Log token structure issues
//        } catch (ExpiredJwtException ex) {
//            // Log token expiration
//        } catch (UnsupportedJwtException ex) {
//            // Log unsupported token
//        } catch (IllegalArgumentException ex) {
//            // Log missing or invalid token parts
//        }
        return false;
    }

}
