package ru.netology.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.cloud.entity.Credential;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, String> {
    Optional<Credential> findByLogin(String login);
    Optional<Credential> findByToken(String token);
}