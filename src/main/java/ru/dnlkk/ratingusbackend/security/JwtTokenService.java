package ru.dnlkk.ratingusbackend.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.dnlkk.ratingusbackend.model.User;
import ru.dnlkk.ratingusbackend.model.UserDetailsImpl;
import ru.dnlkk.ratingusbackend.model.enums.Role;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        claims.put("surname", user.getSurname());
        claims.put("patronymic", user.getPatronymic());
        claims.put("login", user.getLogin());
        String roleName = String.valueOf(Role.GUEST);
        if (user.getUserRole() != null){
            roleName = user.getUserRole().getName();
        }
        claims.put("role", roleName);
        if (user.getUserRole() != null){
            claims.put("school", user.getUserRole().getSchool());
        }


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(),SignatureAlgorithm.HS512)
                .compact();
    }


    public String getName(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getSurname(String token) {
        return getAllClaimsFromToken(token).get("surname", String.class);
    }

    public String getPatronymic(String token) {
        return getAllClaimsFromToken(token).get("patronymic", String.class);
    }


    public String getRole(String token) {
        return getAllClaimsFromToken(token).get("role", String.class);
    }

    public String getSchool(String token) {
        return getAllClaimsFromToken(token).get("school", String.class);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public boolean validateToken(String token, UserDetailsImpl userDetails) {
        final String username = getName(token);
        System.out.println(username);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}


