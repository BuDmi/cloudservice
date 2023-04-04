package ru.netology.cloud.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {
    private final UserAuthenticationProvider provider;
    private static final String AUTH_TOKEN = "auth-token";
    private static final String BEARER = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTH_TOKEN);
        if (header != null) {
            String[] authElements = header.split(" ");
            if (authElements.length == 2 && BEARER.equals(authElements[0])) {
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                        provider.validateToken(authElements[1])
                    );
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
