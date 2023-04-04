package ru.netology.cloud.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.cloud.security.JWTAuthFilter;
import ru.netology.cloud.security.UserAuthenticationEntryPoint;
import ru.netology.cloud.security.UserAuthenticationProvider;
import ru.netology.cloud.security.UsernamePasswordAuthFilter;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private static final String LOGIN_URL = "/login*";

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
            .cors(httpSecurityCorsConfigurer -> {
                CorsRegistry registry = new CorsRegistry();
                registry.addMapping("/**")
                    .allowCredentials(true)
                    .allowedOrigins(CrossOriginParams.CROSS_ORIGIN)
                    .allowedMethods(String.valueOf(HttpMethod.POST),
                        String.valueOf(HttpMethod.DELETE),
                        String.valueOf(HttpMethod.GET),
                        String.valueOf(HttpMethod.PUT));
            })
            .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
            .and()
            .addFilterBefore(new UsernamePasswordAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
            .addFilterBefore(new JWTAuthFilter(userAuthenticationProvider), UsernamePasswordAuthFilter.class)
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(request -> {
                try {
                    request
                        .requestMatchers(HttpMethod.POST, LOGIN_URL)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                        .and()
                        .logout(logout -> logout.logoutUrl("/base-logout").permitAll()
                            .logoutSuccessHandler((request1, response, authentication) -> {
                                response.setStatus(HttpServletResponse.SC_OK);
                            }));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        return http.build();
    }
}
