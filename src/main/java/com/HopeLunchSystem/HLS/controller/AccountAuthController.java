package com.HopeLunchSystem.HLS.controller;

import com.HopeLunchSystem.HLS.constants.AppConstants;
import com.HopeLunchSystem.HLS.dto.AccountAuthRequest;
import com.HopeLunchSystem.HLS.dto.AccountAuthResponse;
import com.HopeLunchSystem.HLS.services.AccountAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/api/v1/account/login")
@RequiredArgsConstructor
public class AccountAuthController {
    public final AccountAuthService accountAuthService;
    @PostMapping("/admin")
    public ResponseEntity<AccountAuthResponse> adminAuthen(
            @RequestBody AccountAuthRequest authRequest
            ){
        return  ResponseEntity.ok(accountAuthService.login(authRequest, AppConstants.ADMIN_ROLE_ID));
    }
    @PostMapping("/user")
    public ResponseEntity<AccountAuthResponse> userAuthen(
            @RequestBody AccountAuthRequest authRequest
    ){
        return  ResponseEntity.ok(accountAuthService.login(authRequest, AppConstants.USER_ROLE_ID));
    }
    @GetMapping("/user")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Success");
    }
}
