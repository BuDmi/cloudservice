package ru.netology.cloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.netology.cloud.config.CrossOriginParams;
import ru.netology.cloud.entity.Credential;
import ru.netology.cloud.exception.UnauthorizedError;
import ru.netology.cloud.model.Login;
import ru.netology.cloud.security.UserAuthenticationProvider;
import ru.netology.cloud.service.BlackListTokenService;
import ru.netology.cloud.service.CredentialService;

@RestController
@AllArgsConstructor
public class AuthController {
    private static final String LOGIN_ENDPOINT = "login";
    private static final String LOGOUT_ENDPOINT = "logout";

    private final CredentialService credentialService;
    private final BlackListTokenService blackListTokenService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping(path = LOGIN_ENDPOINT)
    @CrossOrigin(origins = CrossOriginParams.CROSS_ORIGIN, allowCredentials = CrossOriginParams.ALLOW_CREDENTIALS_VALUE)
    public ResponseEntity<Login> login(@RequestBody Credential credential) {
        if (credentialService.isCredentialCorrect(credential)) {
            Credential c = credentialService.findByCredential(credential);
            String authToken = userAuthenticationProvider.createToken(c.getLogin());
            credentialService.updateToken(credential.getLogin(), authToken);
            return ResponseEntity.ok(new Login(authToken));
        } else {
            throw new UnauthorizedError("Invalid credential");
        }
    }

    @PostMapping(path = LOGOUT_ENDPOINT)
    @CrossOrigin(origins = CrossOriginParams.CROSS_ORIGIN, allowCredentials = CrossOriginParams.ALLOW_CREDENTIALS_VALUE)
    public ResponseEntity<String> logout(@RequestHeader(name = "auth-token") String authToken) {
        String clearedAuthToken = authToken.replace("Bearer ", "");
        credentialService.clearToken(clearedAuthToken);
        blackListTokenService.addToken(clearedAuthToken);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Success logout");
    }
}