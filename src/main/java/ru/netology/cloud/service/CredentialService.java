package ru.netology.cloud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.cloud.entity.Credential;
import ru.netology.cloud.exception.BadCredentials;
import ru.netology.cloud.exception.UnauthorizedError;
import ru.netology.cloud.repository.CredentialRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CredentialService {
    private CredentialRepository credentialRepository;

    public boolean isCredentialCorrect(Credential credential) {
        Optional<Credential> c = credentialRepository.findByLogin(credential.getLogin());
        if (c.isPresent()) {
            return c.get().getPassword().equals(credential.getPassword());
        } else {
            throw new UnauthorizedError("Invalid login or password");
        }
    }

    public Credential findUserByLogin(String login) {
        Optional<Credential> credential = credentialRepository.findByLogin(login);
        String encoded;
        if (credential.isPresent()) {
           encoded = credential.get().getPassword();//passwordEncoder.encode(CharBuffer.wrap(user.getCredential().getPassword()));
            return credential.get();
        } else {
            throw new BadCredentials("Invalid login or password");
        }
        //passwordEncoder.matches(CharBuffer.wrap(user.getCredential().getPassword()), encoded) ? user : null;
    }

    public Credential findByToken(String token) {
        Optional<Credential> credential = credentialRepository.findByToken(token.replace("Bearer ", ""));
        if (credential.isPresent()) {
            return credential.get();
        } else {
            throw new UnauthorizedError("Invalid token");
        }
    }
}
