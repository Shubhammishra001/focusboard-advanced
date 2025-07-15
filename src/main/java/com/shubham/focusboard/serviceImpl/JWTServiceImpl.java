package com.shubham.focusboard.serviceImpl;



import java.security.Key;
import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.service.JWTService;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
@Service
public class JWTServiceImpl implements JWTService{
	private static final Logger logger = (Logger) LoggerFactory.getLogger(JWTServiceImpl.class);

   // private static final String SECRET = "YourSuperSecretKey123456789012345678901234567890";
  //  private static final long EXPIRATION_MS = 86400000; // 1 day


	@Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private long EXPIRATION_MS;
	
	
	
    private Key key;

    // ✅ Initialize 'key' only after Spring injects values
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getLoginId())
                .setIssuedAt(new Date(0))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractLoginId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean validateToken(String token, User user) {
        try {
            String loginId = extractLoginId(token);
            return loginId.equals(user.getLoginId());
        } catch (JwtException | IllegalArgumentException e) {
           // logger.error("Invalid JWT token", e);
        	logger.error("Error in registerUser", e);
            return false;
        }
    }	

}
