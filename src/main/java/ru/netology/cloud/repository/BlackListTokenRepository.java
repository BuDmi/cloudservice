package ru.netology.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.netology.cloud.entity.BlackListTokens;

import java.util.Optional;

public interface BlackListTokenRepository extends JpaRepository<BlackListTokens, Long> {
    Optional<BlackListTokens> findByToken(String token);
    @Modifying
    @Query(value = "INSERT INTO black_list_tokens (token) VALUES (:token)",
        nativeQuery = true)
    void addNewToken(@Param("token") String token);
}
