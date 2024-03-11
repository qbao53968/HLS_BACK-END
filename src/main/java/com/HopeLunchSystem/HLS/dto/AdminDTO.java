package com.HopeLunchSystem.HLS.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private String userName;
    private String lastName;
    private String firstName;
    private String password;
}
