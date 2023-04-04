package ru.netology.cloud;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.netology.cloud.controller.AuthController;
import ru.netology.cloud.entity.Credential;
import ru.netology.cloud.repository.CredentialRepository;
import ru.netology.cloud.service.BlackListTokenService;
import ru.netology.cloud.service.CredentialService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthorizationControllerTest {
    public MockMvc mockMvc;
    @Autowired
    public AuthController authController;

    @MockBean
    private CredentialRepository credentialRepository;
    @MockBean
    private BlackListTokenService blackListTokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CredentialService credentialService = new CredentialService(credentialRepository, passwordEncoder);

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testSignIn() throws Exception {
        //given
        Credential credential = Credential.builder()
            .login("login")
            .password("password")
            .build();
        //when
        when(credentialRepository.findCredentialByLoginAndPassword(credential.getLogin(), credential.getPassword()))
            .thenReturn(Optional.of(Credential.builder().id(1L)
                .login(credential.getLogin())
                .password(credential.getPassword())
                .build()));

        when(credentialRepository.findByLogin(credential.getLogin()))
            .thenReturn(Optional.of(Credential.builder().id(1L)
                .login(credential.getLogin())
                .password(credential.getPassword())
                .build()));

        //then
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(credential)))
            .andExpect(status().is(200));

    }

    @Test
    void testSignOut() throws Exception {
        // given

        // when
        when(credentialRepository.findByToken(any()))
            .thenReturn(Optional.of(Credential.builder().id(1L)
                .login("login")
                .password("password")
                .token("1")
                .build()));

        // then
        mockMvc.perform(post("/logout").header("auth-token", "1"))
            .andExpect(status().is(200));
    }
}
