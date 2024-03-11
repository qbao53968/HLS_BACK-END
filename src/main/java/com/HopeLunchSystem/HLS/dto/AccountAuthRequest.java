package com.HopeLunchSystem.HLS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAuthRequest {
    private String username;
    private String password;
}
