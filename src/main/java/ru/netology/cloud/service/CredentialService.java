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

    public Credential findByCredential(Credential credential) {
        Optional<Credential> c =
            credentialRepository.findCredentialByLoginAndPassword(credential.getLogin(), credential.getPassword());
        if (c.isPresent()) {
            return c.get();
        } else {
            throw new UnauthorizedError("Invalid login");
        }
    }

    public Credential findByLogin(String login) {
        Optional<Credential> credential = credentialRepository.findByLogin(login);
        if (credential.isPresent()) {
            return credential.get();
        } else {
            throw new UnauthorizedError("Invalid login");
        }
    }

    public Credential findByToken(String token) {
        Optional<Credential> credential = credentialRepository.findByToken(token);
        if (credential.isPresent()) {
            return credential.get();
        } else {
            throw new UnauthorizedError("Invalid token");
        }
    }

    public void updateToken(String login, String token) {
        long credentialId = findCredentialIdByLogin(login);
        credentialRepository.updateTokenById(token, credentialId);
    }

    private long findCredentialIdByLogin(String login) {
        Optional<Credential> credential = credentialRepository.findByLogin(login);
        if (credential.isPresent()) {
            return credential.get().getId();
        } else {
            throw new BadCredentials("Invalid login");
        }
    }

    private long findCredentialIdByToken(String token) {
        Optional<Credential> credential = credentialRepository.findByToken(token);
        if (credential.isPresent()) {
            return credential.get().getId();
        } else {
            throw new BadCredentials("Invalid token");
        }
    }

    public void clearToken(String token) {
        long credentialId = findCredentialIdByToken(token);
        credentialRepository.updateTokenById("0", credentialId);
    }
}
