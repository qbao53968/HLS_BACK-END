package com.HopeLunchSystem.HLS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountAuthResponse {
    private String access_token;
    private String refresh_token;
    private String username;
    private long access_token_expires_in;
}
