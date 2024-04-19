package bkendfinalproject.finalpj.security;

import bkendfinalproject.finalpj.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

//    public String createToken(Employee emp){
//        return Jwts.builder()
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 24* 7))
//                .subject(String.valueOf( emp.getId()))
//                .signWith( Keys.hmacShaKeyFor(secret.getBytes()))
//                .compact();
//    }
//
//    public void verifyToken(String token){
//        try {
//            Jwts.parser()
//                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
//                    .build().parse(token);
//        } catch (Exception ex) {
//            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
//        }
//    }
//
//    public String extractIdFromToken(String token){
//        return Jwts.parser()
//                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
//                .build().parseSignedClaims(token).getPayload().getSubject(); // Il subject è l'id dell'utente
//    }
}
