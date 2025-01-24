package com.bears.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/register").permitAll() // Rutas públicas
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Rutas protegidas para ADMIN
                        .requestMatchers("/customer/**").hasRole("CUSTOMER") // Rutas protegidas para CUSTOMER
                        .requestMatchers("/seller/**").hasRole("SELLER") // Rutas protegidas para SELLER
                        .anyRequest().authenticated() // Requiere autenticación para el resto
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/auth/login") // Personaliza la página de login si es necesario
                );

        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(clientRegistration());
    }

    private ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId("YOUR_CLIENT_ID")
                .clientSecret("YOUR_CLIENT_SECRET")
                .scope("openid", "profile", "email")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .build();
    }
}
