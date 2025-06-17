package com.example.ProductServiceJan31Capstone.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration
{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> {
                            try {
                                requests
                                        .anyRequest().permitAll()
                                        .and().cors().disable()
                                        .csrf().disable()
                                        .logout()
                                        .logoutUrl("/logout")
                                        .logoutSuccessUrl("/login?logout") // Redirect after logout
                                        .invalidateHttpSession(true)
                                        .deleteCookies("JSESSIONID");
//                                        .permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        return http.build();
    }
}
