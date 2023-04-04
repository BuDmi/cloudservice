package ru.netology.cloud.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.netology.cloud.entity.Credential;
import ru.netology.cloud.service.CredentialService;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthenticationProvider {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private final CredentialService credentialService;
    private static final int TOKEN_DURATION = 3600000; //Время жизни токена. 1 час.

    public UserAuthenticationProvider(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login) {
        Claims claims = Jwts.claims().setSubject(login);
        Date now = new Date();
        Date validity = new Date(now.getTime() + TOKEN_DURATION);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public Authentication validateToken(String token) {
        String login = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();

        Credential credential = credentialService.findByLogin(login);
//        return new UsernamePasswordAuthenticationToken(credential, null, Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(null, credential, Collections.emptyList());
    }

    public Authentication validateCredentials(Credential credential) {
        Credential c = credentialService.findByCredential(credential);
//        return new UsernamePasswordAuthenticationToken(c, null, Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(null, credential, Collections.emptyList());
    }
}
