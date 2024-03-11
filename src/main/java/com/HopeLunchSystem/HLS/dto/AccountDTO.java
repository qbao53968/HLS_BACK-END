package com.HopeLunchSystem.HLS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String username;
    private String firstname;
    private String lastname;
    private String province;
    private String ccc;
    private String gender;
    private String deleteFlag;
}