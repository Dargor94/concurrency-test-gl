package org.gl.franciscomasera.oauth2.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("http://localhost:9000/oauth2/authorization/products-reg-id"))
                .oauth2Client(withDefaults());
        return http.build();
    }

}
