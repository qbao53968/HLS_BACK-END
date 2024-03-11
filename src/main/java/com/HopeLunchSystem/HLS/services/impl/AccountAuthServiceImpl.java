package com.HopeLunchSystem.HLS.services.impl;

import com.HopeLunchSystem.HLS.config.JWTService;
import com.HopeLunchSystem.HLS.dto.AccountAuthRequest;
import com.HopeLunchSystem.HLS.dto.AccountAuthResponse;
import com.HopeLunchSystem.HLS.repositories.AccountRepository;
import com.HopeLunchSystem.HLS.repositories.AdminRepository;
import com.HopeLunchSystem.HLS.services.AccountAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import java.util.Date;
@RequiredArgsConstructor
@Service
@Slf4j
public class AccountAuthServiceImpl implements AccountAuthService {
    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final AccountRepository  accountRepository;


    @Override
    public AccountAuthResponse login(AccountAuthRequest accountAuthRequest, String role) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountAuthRequest.getUsername(),
                        accountAuthRequest.getPassword())
        );
        log.info(role);
        if(role.equals("1")){
            //tao token cho admin
            var admin = adminRepository.findByUsername(accountAuthRequest.getUsername())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(admin);
            var refreshToken = jwtService.generateRefreshToken(admin);
            log.info("admin");

            Date expirationDate = jwtService.getExpirationDate(jwtToken);
            long expiresIn = expirationDate.getTime();
            return AccountAuthResponse.builder()
                    .access_token(jwtToken)
                    .refresh_token(refreshToken)
                    .username(admin.getUsername())
                    .access_token_expires_in(expiresIn)
                    .build();
        } else if(role.equals("2")){
            log.info("user");
            //tao token cho admin
            var user = accountRepository.findByUsername(accountAuthRequest.getUsername())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            Date expirationDate = jwtService.getExpirationDate(jwtToken);
            long expiresIn = expirationDate.getTime();
            return AccountAuthResponse.builder()
                    .access_token(jwtToken)
                    .refresh_token(refreshToken)
                    .username(user.getUsername())
                    .access_token_expires_in(expiresIn)
                    .build();
        }
        return null;
    }
}
