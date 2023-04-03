package ru.netology.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.netology.cloud.entity.Credential;

import javax.transaction.Transactional;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Optional<Credential> findByLogin(String login);
    Optional<Credential> findByToken(String token);
    @Transactional
    @Modifying
    @Query(value = "UPDATE credential c SET token =:token WHERE c.id =:credentialId",
        nativeQuery = true)
    void updateTokenById(@Param("token") String token, @Param("credentialId") long credentialId);
}