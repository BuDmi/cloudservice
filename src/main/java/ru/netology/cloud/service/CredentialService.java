package ru.netology.cloud.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.netology.cloud.entity.Credential;
import ru.netology.cloud.repository.CredentialRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CredentialService {
    private CredentialRepository credentialRepository;

    public boolean isCredentialCorrect(Credential credential) {
        Optional<Credential> c = credentialRepository.findByLogin(credential.getLogin());
        return c.map(value -> value.getPassword().equals(credential.getPassword())).orElse(false);
    }
}
