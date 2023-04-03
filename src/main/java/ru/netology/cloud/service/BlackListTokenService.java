package ru.netology.cloud.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.cloud.entity.BlackListTokens;
import ru.netology.cloud.repository.BlackListTokenRepository;

@Service
@AllArgsConstructor
public class BlackListTokenService {
    private BlackListTokenRepository blackListTokenRepository;

    public boolean isTokenInBlackList(String token) {
        return blackListTokenRepository.findByToken(token).isPresent();
    }

    public void addToken(String token) {
        BlackListTokens blackListTokens = new BlackListTokens();
        blackListTokens.setToken(token);
        blackListTokenRepository.save(blackListTokens);
    }
}
