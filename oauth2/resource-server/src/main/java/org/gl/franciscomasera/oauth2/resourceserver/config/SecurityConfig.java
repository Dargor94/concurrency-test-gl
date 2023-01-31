package org.gl.franciscomasera.oauth2.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(matcher -> matcher
                        .requestMatchers("/resource/**")
                        .authenticated())
                .oauth2ResourceServer()
                .jwt();

        return httpSecurity.build();
    }

}
