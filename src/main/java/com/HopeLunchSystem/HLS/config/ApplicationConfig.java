package com.HopeLunchSystem.HLS.config;

import com.HopeLunchSystem.HLS.constants.AppConstants;
import com.HopeLunchSystem.HLS.entity.Account;
import com.HopeLunchSystem.HLS.entity.Admin;
import com.HopeLunchSystem.HLS.repositories.AccountRepository;
import com.HopeLunchSystem.HLS.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<Admin> adminOptional = adminRepository.findByUsername(username);
            if (adminOptional.isPresent()) {
                return adminOptional.get();
            } else {
                Optional<Account> accountOptional = accountRepository.findByUsername(username);
                return accountOptional.orElseThrow(() -> new UsernameNotFoundException(AppConstants.USER_NOT_FOUND));
            }
        };
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
