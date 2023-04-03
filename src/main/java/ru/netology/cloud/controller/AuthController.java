package ru.netology.cloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cloud.entity.Credential;
import ru.netology.cloud.exception.UnauthorizedError;
import ru.netology.cloud.model.Login;
import ru.netology.cloud.service.CredentialService;

@RestController
@AllArgsConstructor
public class AuthController {
    private static final String LOGIN_ENDPOINT = "login";
    private static final String LOGOUT_ENDPOINT = "logout";

    private CredentialService credentialService;

    @PostMapping(path = LOGIN_ENDPOINT)
    public ResponseEntity<Login> login(@RequestBody Credential credential) {
        if (credentialService.isCredentialCorrect(credential)) {
            String authToken = "1"; // TODO
            return ResponseEntity.ok(new Login(authToken));
        } else {
            throw new UnauthorizedError("Invalid credential");
        }
    }

    @PostMapping(path = LOGOUT_ENDPOINT)
    public ResponseEntity<String> logout(@RequestHeader(name = "auth-token") String authToken) {
        return ResponseEntity.ok("Success logout");
    }
}