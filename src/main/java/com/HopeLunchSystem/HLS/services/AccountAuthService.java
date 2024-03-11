package com.HopeLunchSystem.HLS.services;

import com.HopeLunchSystem.HLS.dto.AccountAuthRequest;
import com.HopeLunchSystem.HLS.dto.AccountAuthResponse;

public interface AccountAuthService {
    AccountAuthResponse login(AccountAuthRequest adminAuthRequest, String role);
}
