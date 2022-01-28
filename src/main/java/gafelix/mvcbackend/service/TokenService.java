package gafelix.mvcbackend.service;

import gafelix.mvcbackend.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Service
public class TokenService {

    @Value("${forum.jwt.secret}")
    private String secret;
    @Value("${forum.jwt.expiration}")
    private String expiration;

    public String createToken(Authentication authentication) {
        User subject = (User) authentication.getPrincipal();
        Date now = new Date();
        return Jwts.builder()
                .setIssuer("API Alura")
                .setIssuedAt(now)
                .setSubject(subject.getId().toString())
                .setExpiration(new Date(now.getTime() + Long.parseLong(getExpiration())))
                .signWith(SignatureAlgorithm.HS256, getSecret())
                .compact();
    }

}