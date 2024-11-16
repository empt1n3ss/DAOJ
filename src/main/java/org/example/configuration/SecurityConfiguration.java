package org.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/persons/by-city").permitAll()
                        .requestMatchers("/persons/by-city").hasAuthority("write")
                        .requestMatchers("/persons/by-city").hasAuthority("read")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form.defaultSuccessUrl("/default").permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.inMemoryAuthentication()
                .withUser("User1")
                .password(encoder().encode("password1"))
                .authorities("write")
                .and()
                .withUser("User2")
                .password(encoder().encode("password2"))
                .authorities("read", "write")
                .and()
                .withUser("User3")
                .password(encoder().encode("password3"))
                .authorities("write");
        return auth.build();
    }
}

