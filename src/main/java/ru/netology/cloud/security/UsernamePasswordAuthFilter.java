package ru.netology.cloud.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.netology.cloud.entity.Credential;

import java.io.IOException;

@AllArgsConstructor
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private static final String SIGNIN_URL = "/login";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final UserAuthenticationProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (SIGNIN_URL.equals(request.getServletPath()) && HttpMethod.POST.equals(request.getMethod())) {
            Credential credential = MAPPER.readValue(request.getInputStream(), Credential.class);
            try {
                SecurityContextHolder.getContext().setAuthentication(
                    provider.validateCredentials(credential)
                );
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
        filterChain.doFilter(request, response);
    }
}
