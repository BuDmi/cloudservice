package ru.netology.moneytransfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransfer.model.AuthInfo;
import ru.netology.moneytransfer.model.Login;

@RestController("cloud")
public class LoginController {
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String LOGOUT_ENDPOINT = "/logout";

    @PostMapping(path = LOGIN_ENDPOINT)
    public ResponseEntity<Login> login(@RequestBody AuthInfo authInfo) {
        String authToken = "1"; // TODO
        return ResponseEntity.ok(new Login(authToken));
    }

    @PostMapping(path = LOGOUT_ENDPOINT)
    public ResponseEntity<String> logout(@RequestHeader(name = "auth-token") String authToken) {
        return ResponseEntity.ok("Success logout");
    }
}