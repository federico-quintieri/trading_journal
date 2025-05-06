package org.trading_journal.example.trading_journal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/trades/create", "/trades/edit/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/strategies/create", "/strategies/edit/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/accounts/create", "/accounts/edit/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/trades/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/accounts/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/strategies/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/trades", "trades/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .requestMatchers("/**").permitAll()
                .and().formLogin().defaultSuccessUrl("/trades", true)
                .and().logout()
                .and().exceptionHandling();

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }
}
